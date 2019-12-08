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
