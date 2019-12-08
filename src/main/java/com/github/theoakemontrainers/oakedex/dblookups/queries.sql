/*
-- English name
select
  pokemon_species_names.name AS "species name",
  pokemon_form_names.form_name AS "form name"
from pokemon_species_names
  join pokemon on pokemon_species_names.pokemon_species_id = pokemon.species_id
  join pokemon_forms on pokemon.id = pokemon_forms.pokemon_id
  left join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id
    and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id
where pokemon_forms.id = ?
    and pokemon_species_names.local_language_id = 9;

-- All names
select
  language_names.name AS "language name",
  pokemon_species_names.name AS "species name",
  pokemon_form_names.form_name AS "form name"
from pokemon_species_names
  join pokemon
    on pokemon_species_names.pokemon_species_id = pokemon.species_id
  join pokemon_forms
    on pokemon.id = pokemon_forms.pokemon_id
  join language_names
    on language_names.language_id = pokemon_species_names.local_language_id
  left join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id
    and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id
where pokemon_forms.id = ?
  and language_names.local_language_id = 9;

-- stats
select
  stats.identifier,
  pokemon_stats.base_stat
from pokemon_stats
  join pokemon_forms
    on pokemon_stats.pokemon_id = pokemon_forms.pokemon_id
  join stats
    on pokemon_stats.stat_id = stats.id
where pokemon_forms.id = ?;

-- main info
SELECT
  spec.id AS "number",
  t1n.name AS "type 1",
  t2n.name AS "type 2",
  a1n.name AS "ability 1",
  a2n.name AS "ability 2",
  a3n.name AS "hidden ability",
  spec.gender_rate AS "gender ratio",
  spec.generation_id AS "generation"
FROM pokemon_species AS spec
  JOIN pokemon AS pkmn
    ON spec.id = pkmn.species_id
  JOIN pokemon_forms AS form
    ON pkmn.id = form.pokemon_id
  JOIN pokemon_types AS ptyp
    ON pkmn.id = ptyp.pokemon_id
  JOIN type_names AS t1n
    ON ptyp.type_id_1 = t1n.type_id
      AND t1n.local_language_id = 9
  LEFT JOIN type_names AS t2n
    ON ptyp.type_id_2 = t2n.type_id
      AND t2n.local_language_id = 9
  JOIN pokemon_abilities AS pabi
    ON pkmn.id = pabi.pokemon_id
  JOIN ability_names AS a1n
    ON pabi.ability_id_1 = a1n.ability_id
      AND a1n.local_language_id = 9
  LEFT JOIN ability_names AS a2n
    ON pabi.ability_id_2 = a2n.ability_id
      AND a2n.local_language_id = 9
  LEFT JOIN ability_names AS a3n
    ON pabi.hidden_ability_id = a3n.ability_id
      AND a3n.local_language_id = 9
WHERE form.id = ?;

-- Pokédex Numbers
SELECT
  pokedex_prose.name AS "region",
  pokemon_dex_numbers.pokedex_number AS "number"
FROM pokemon_dex_numbers
  JOIN pokedexes
    ON pokemon_dex_numbers.pokedex_id = pokedexes.id
  JOIN pokedex_prose
    ON pokedexes.id = pokedex_prose.pokedex_id
      AND pokedex_prose.local_language_id = 9
  JOIN pokemon
    ON pokemon_dex_numbers.species_id = pokemon.species_id
  JOiN pokemon_forms
    ON pokemon.id = pokemon_forms.pokemon_id
WHERE pokemon_forms.pokemon_id = ?;
*/

-- Reset search table
DROP TABLE IF EXISTS search_results;
CREATE TEMP TABLE search_results AS
  SELECT id FROM pokemon_forms;

-- Search by name
/*
DELETE FROM search_results
WHERE id NOT IN
  (SELECT id
  FROM pokemon_forms
    JOIN pokemon_aliases
      ON pokemon_forms.pokemon_id = pokemon_aliases.pokemon_id
  WHERE alias LIKE ?);
-- */

/*
-- Search by ability (create tables)
CREATE TEMP TABLE search_abilities AS
SELECT ability_id FROM ability_names
WHERE local_language_id = 9
  AND lower(name) LIKE 'blaze';
CREATE TEMP TABLE search_ability_results (
  id INTEGER
);

-- Search by ability (normal)
INSERT INTO search_ability_results
SELECT id FROM pokemon_abilities
  JOIN pokemon_forms
    ON pokemon_abilities.pokemon_id = pokemon_forms.pokemon_id
WHERE pokemon_abilities.ability_id_1 IN search_abilities
  OR pokemon_abilities.ability_id_2 IN search_abilities;

-- Search by ability (hidden)
INSERT INTO search_ability_results
SELECT id FROM pokemon_abilities
  JOIN pokemon_forms
    ON pokemon_abilities.pokemon_id = pokemon_forms.pokemon_id
WHERE pokemon_abilities.hidden_ability_id IN search_abilities;

-- Filter by searched abilities
DELETE FROM search_results
WHERE id NOT IN search_ability_results;

-- Search by ability (drop tables)
DROP TABLE search_abilities;
DROP TABLE search_ability_results;
-- */

/*
-- Search by type (create tables)
CREATE TEMP TABLE search_types (id INTEGER PRIMARY KEY REFERENCES types);
CREATE TEMP TABLE search_type_results (id INTEGER PRIMARY KEY REFERENCES pokemon);

-- Search by type ("At least:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_1 IN search_types
  OR type_id_2 IN search_types;

-- Search by type ("Only:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_1 IN search_types
  AND (type_id_2 IS NULL
    OR type_id_2 IN search_types);

-- Search by type ("One of:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE (type_id_1 IN search_types)
  <> NOT (type_id_2 IS NULL OR type_id_2 NOT IN search_types);

-- Search by type ("Only one of:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_1 IN search_types
  AND type_id_2 IS NULL;

-- Search by type ("Two of:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_1 IN search_types
  AND type_id_2 IN search_types;

-- Search by type ("Not:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE NOT (type_id_1 IN search_types
  OR (type_id_2 IS NOT NULL
    AND type_id_2 IN search_types));

-- Search by type ("Not just:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_2 IS NOT NULL
  AND (type_id_1 IN search_types)
    <> (type_id_2 IN search_types);

-- Search by type ("Primarily:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_1 IN search_types;

-- Searcy by type ("Secondarily:")
INSERT INTO search_type_results
SELECT pokemon_id
FROM pokemon_types
WHERE type_id_2 IN search_types;

-- Search by type (alter results)
DELETE FROM search_results
WHERE id NOT IN
  (SELECT id
  FROM pokemon_forms
  WHERE pokemon_id IN search_type_results);

-- Search by type (drop tables)
DROP TABLE search_types;
DROP TABLE search_type_results;
*/

-- Get results
SELECT
  pokemon_forms.id,
  pokemon_species_names.name,
  pokemon_form_names.form_name
FROM pokemon_species_names
  JOIN pokemon ON pokemon_species_names.pokemon_species_id = pokemon.species_id
  JOIN pokemon_forms ON pokemon.id = pokemon_forms.pokemon_id
  LEFT JOIN pokemon_form_names ON pokemon_forms.id = pokemon_form_names.pokemon_form_id
    AND pokemon_species_names.local_language_id = pokemon_form_names.local_language_id
WHERE pokemon_forms.id IN search_results
    AND pokemon_species_names.local_language_id = 9;
