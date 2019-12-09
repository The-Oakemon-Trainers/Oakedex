package com.github.theoakemontrainers.oakedex.dblookups;

public class Statements {
  public static final String ENGLISH_NAME = "select\n"
      + "pokemon_species_names.name AS \"species name\",\n"
      + "pokemon_form_names.form_name AS \"form name\"\n"
      + "from pokemon_species_names\n"
      + "join pokemon on pokemon_species_names.pokemon_species_id = pokemon.species_id\n"
      + "join pokemon_forms on pokemon.id = pokemon_forms.pokemon_id\n"
      + "left join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id\n"
      + "and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id\n"
      + "where pokemon_forms.id = ?\n"
      + "and pokemon_species_names.local_language_id = 9;\n";
  
  public static final String ALL_NAMES = "select\n"
      + "language_names.name AS \"language\",\n"
      + "pokemon_species_names.name AS \"species name\",\n"
      + "pokemon_form_names.form_name AS \"form name\"\n"
      + "from pokemon_species_names\n" + "join pokemon\n"
      + "on pokemon_species_names.pokemon_species_id = pokemon.species_id\n"
      + "join pokemon_forms\n"
      + "on pokemon.id = pokemon_forms.pokemon_id\n"
      + "join language_names\n"
      + "on language_names.language_id = pokemon_species_names.local_language_id\n"
      + "left join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id\n"
      + "and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id\n"
      + "where pokemon_forms.id = ?\n"
      + "and language_names.local_language_id = 9;\n";
  
  public static final String MAIN_INFO = "SELECT"
      + "  spec.id AS \"number\"," + "  t1n.name AS \"type 1\","
      + "  t2n.name AS \"type 2\"," + "  a1n.name AS \"ability 1\","
      + "  a2n.name AS \"ability 2\","
      + "  a3n.name AS \"hidden ability\","
      + "  spec.gender_rate AS \"gender ratio\","
      + "  spec.generation_id AS \"generation\""
      + " FROM pokemon_species AS spec" + "  JOIN pokemon AS pkmn"
      + "    ON spec.id = pkmn.species_id" + "  JOIN pokemon_forms AS form"
      + "    ON pkmn.id = form.pokemon_id" + "  JOIN pokemon_types AS ptyp"
      + "    ON pkmn.id = ptyp.pokemon_id" + "  JOIN type_names AS t1n"
      + "    ON ptyp.type_id_1 = t1n.type_id"
      + "      AND t1n.local_language_id = 9"
      + "  LEFT JOIN type_names AS t2n"
      + "    ON ptyp.type_id_2 = t2n.type_id"
      + "      AND t2n.local_language_id = 9"
      + "  JOIN pokemon_abilities AS pabi"
      + "    ON pkmn.id = pabi.pokemon_id" + "  JOIN ability_names AS a1n"
      + "    ON pabi.ability_id_1 = a1n.ability_id"
      + "      AND a1n.local_language_id = 9"
      + "  LEFT JOIN ability_names AS a2n"
      + "    ON pabi.ability_id_2 = a2n.ability_id"
      + "      AND a2n.local_language_id = 9"
      + "  LEFT JOIN ability_names AS a3n"
      + "    ON pabi.hidden_ability_id = a3n.ability_id"
      + "      AND a3n.local_language_id = 9" + " WHERE form.id = ?";
  
  public static final String STATS = "select" + "  stats.identifier,"
      + "  pokemon_stats.base_stat" + " from pokemon_stats"
      + "  join pokemon_forms"
      + "    on pokemon_stats.pokemon_id = pokemon_forms.pokemon_id"
      + "  join stats" + "    on pokemon_stats.stat_id = stats.id"
      + " where pokemon_forms.id = ?;";
  
  public static final String DEXNUMS = "SELECT\n"
      + "pokedex_prose.name AS \"region\",\n"
      + "pokemon_dex_numbers.pokedex_number AS \"number\"\n"
      + "FROM pokemon_dex_numbers\n" + "JOIN pokedexes\n"
      + "ON pokemon_dex_numbers.pokedex_id = pokedexes.id\n"
      + "JOIN pokedex_prose\n"
      + "ON pokedexes.id = pokedex_prose.pokedex_id\n"
      + "AND pokedex_prose.local_language_id = 9\n" + "JOIN pokemon\n"
      + "ON pokemon_dex_numbers.species_id = pokemon.species_id\n"
      + "JOiN pokemon_forms\n"
      + "ON pokemon.id = pokemon_forms.pokemon_id\n"
      + "WHERE pokemon_forms.pokemon_id = ?;";
  
  public static final String RESET_SEARCH = "DELETE FROM search_results;\n"
      + "INSERT INTO search_results\n" + "SELECT id FROM pokemon_forms;\n";
  
  public static final String SEARCH_COUNT = "SELECT count(id) FROM search_results;";
  
  public static final String SEARCH_NAMES = "delete from search_results\n"
      + "where id not in\n" + "(select id\n" + "from pokemon_forms\n"
      + "join pokemon_aliases\n"
      + "on pokemon_forms.pokemon_id = pokemon_aliases.pokemon_id\n"
      + "where alias like ?);\n";
  
  public static final String SEARCH_ABILITIES_RESET = "DELETE FROM search_abilities;\n"
      + "INSERT INTO search_abilities\n"
      + "SELECT ability_id FROM ability_names\n"
      + "WHERE local_language_id = 9\n" + "AND lower(name) LIKE 'blaze';\n"
      + "DELETE FROM search_ability_results;\n";
  
  public static final String SEARCH_ABILITIES_NORMAL = "INSERT INTO search_ability_results\n"
      + "SELECT id FROM pokemon_abilities\n" + "JOIN pokemon_forms\n"
      + "ON pokemon_abilities.pokemon_id = pokemon_forms.pokemon_id\n"
      + "WHERE pokemon_abilities.ability_id_1 IN search_abilities\n"
      + "OR pokemon_abilities.ability_id_2 IN search_abilities;\n";
  
  public static final String SEARCH_ABILITIES_HIDDEN = "INSERT INTO search_ability_results\n"
      + "SELECT id FROM pokemon_abilities\n" + "JOIN pokemon_forms\n"
      + "ON pokemon_abilities.pokemon_id = pokemon_forms.pokemon_id\n"
      + "WHERE pokemon_abilities.hidden_ability_id IN search_abilities;\n";
  
  public static final String SEARCH_ABILITIES_FILTER = "DELETE FROM search_results\n"
      + "WHERE id NOT IN search_ability_results;\n";
  
  public static final String SEARCH_TYPES_START = "DELETE FROM search_types;\n"
      + "DELETE FROM search_type_results;\n";
  
  public static final String SEARCH_TYPES_AT_LEAST = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_1 IN search_types\n"
      + "OR type_id_2 IN search_types;\n";
  
  public static final String SEARCH_TYPES_ONE_OF = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_1 IN search_types\n" + "AND (type_id_2 IS NULL\n"
      + "OR type_id_2 IN search_types);\n";
  
  public static final String SEARCH_TYPES_ONLY = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE (type_id_1 IN search_types)\n"
      + "<> NOT (type_id_2 IS NULL OR type_id_2 NOT IN search_types);\n";
  
  public static final String SEARCH_TYPES_ONLY_ONE_OF = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_1 IN search_types\n" + "AND type_id_2 IS NULL;\n";
  
  public static final String SEARCH_TYPES_TWO_OF = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_1 IN search_types\n"
      + "AND type_id_2 IN search_types;\n";
  
  public static final String SEARCH_TYPES_NOT = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE NOT (type_id_1 IN search_types\n"
      + "OR (type_id_2 IS NOT NULL\n"
      + "AND type_id_2 IN search_types));\n";
  
  public static final String SEARCH_TYPES_NOT_JUST = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_2 IS NOT NULL\n"
      + "AND (type_id_1 IN search_types)\n"
      + "<> (type_id_2 IN search_types);\n";
  
  public static final String SEARCH_TYPES_PRIMARILY = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_1 IN search_types;\n";
  
  public static final String SEARCH_TYPES_SECONDARILY = "INSERT INTO search_type_results\n"
      + "SELECT pokemon_id\n" + "FROM pokemon_types\n"
      + "WHERE type_id_2 IN search_types;\n";
  
  public static final String SEARCH_TYPES_GET_RESULTS = "DELETE FROM search_results\n"
      + "WHERE id NOT IN\n" + "(SELECT id\n" + "FROM pokemon_forms\n"
      + "WHERE pokemon_id IN search_type_results);\n";
  
  public static final String SEARCH_EXECUTE = "SELECT\n"
      + "pokemon_forms.id,\n" + "pokemon_species_names.name,\n"
      + "pokemon_form_names.form_name\n" + "FROM pokemon_species_names\n"
      + "JOIN pokemon ON pokemon_species_names.pokemon_species_id = pokemon.species_id\n"
      + "JOIN pokemon_forms ON pokemon.id = pokemon_forms.pokemon_id\n"
      + "LEFT JOIN pokemon_form_names ON pokemon_forms.id = pokemon_form_names.pokemon_form_id\n"
      + "AND pokemon_species_names.local_language_id = pokemon_form_names.local_language_id\n"
      + "WHERE pokemon_forms.id IN search_results\n"
      + "AND pokemon_species_names.local_language_id = 9;\n";
}
