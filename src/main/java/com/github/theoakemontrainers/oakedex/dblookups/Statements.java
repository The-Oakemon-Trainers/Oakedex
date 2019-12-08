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
}
