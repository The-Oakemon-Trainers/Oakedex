package com.github.theoakemontrainers.oakedex.dblookups;

public class Statements {
  public static final String ENGLISH_NAME = "select"
      + "   language_names.name as \"language\","
      + "   pokemon_species_names.name AS \"species name\","
      + "   pokemon_form_names.form_name AS \"form name\""
      + " from pokemon_species_names"
      + "   join pokemon on pokemon_species_names.pokemon_species_id = pokemon.species_id"
      + "   join pokemon_forms on pokemon.id = pokemon_forms.pokemon_id"
      + "   join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id"
      + "     and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id"
      + "   join language_names on pokemon_form_names.local_language_id = language_names.language_id"
      + "     and language_names.local_language_id = 9"
      + " where pokemon_forms.id = ?;";
  
  public static final String ALL_NAMES = "select"
      + "   language_names.name as \"language\","
      + "   pokemon_species_names.name AS \"species name\","
      + "   pokemon_form_names.form_name AS \"form name\""
      + " from pokemon_species_names"
      + "   join pokemon on pokemon_species_names.pokemon_species_id = pokemon.species_id"
      + "   join pokemon_forms on pokemon.id = pokemon_forms.pokemon_id"
      + "   join pokemon_form_names on pokemon_forms.id = pokemon_form_names.pokemon_form_id"
      + "     and pokemon_species_names.local_language_id = pokemon_form_names.local_language_id"
      + "   join language_names on pokemon_form_names.local_language_id = language_names.language_id"
      + " where pokemon_forms.id = ?;";
  
  public static final String MAIN_INFO = "being written";
}
