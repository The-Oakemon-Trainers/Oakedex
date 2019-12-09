package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;
import java.util.HashMap;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Search {
  private DBConnection conn;
  
  private DBStatement reset;
  private DBStatement start;
  private DBStatement count;
  private DBStatement searchName;
  private DBStatement searchAbilityReset1;
  private DBStatement searchAbilityReset2;
  private DBStatement searchAbilityStart;
  private DBStatement searchAbilityNormal;
  private DBStatement searchAbilityHidden;
  private DBStatement searchAbilityFilter;
  private DBStatement searchTypesStart1;
  private DBStatement searchTypesStart2;
  private DBStatement searchTypesAtLeast;
  private DBStatement searchTypesOneOf;
  private DBStatement searchTypesOnly;
  private DBStatement searchTypesOnlyOneOf;
  private DBStatement searchTypesTwoOf;
  private DBStatement searchTypesNot;
  private DBStatement searchTypesNotJust;
  private DBStatement searchTypesPrimarily;
  private DBStatement searchTypesSecondarily;
  private DBStatement searchTypesGetResults;
  private DBStatement searchGenders;
  private DBStatement results;
  
  public Search(DBConnection c) {
    conn = c;
    reset = c.prepare(Statements.CLEAR_SEARCH);
    start = c.prepare(Statements.START_SEARCH);
    count = c.prepare(Statements.SEARCH_COUNT);
    searchName = c.prepare(Statements.SEARCH_NAMES);
    searchAbilityReset1 = c.prepare(Statements.SEARCH_ABILITIES_RESET_1);
    searchAbilityReset2 = c.prepare(Statements.SEARCH_ABILITIES_RESET_2);
    searchAbilityStart = c.prepare(Statements.SEARCH_ABILITIES_GET);
    searchAbilityNormal = c.prepare(Statements.SEARCH_ABILITIES_NORMAL);
    searchAbilityHidden = c.prepare(Statements.SEARCH_ABILITIES_HIDDEN);
    searchAbilityFilter = c.prepare(Statements.SEARCH_ABILITIES_FILTER);
    searchTypesStart1 = c.prepare(Statements.SEARCH_TYPES_START_1);
    searchTypesStart2 = c.prepare(Statements.SEARCH_TYPES_START_2);
    searchTypesAtLeast = c.prepare(Statements.SEARCH_TYPES_AT_LEAST);
    searchTypesOneOf = c.prepare(Statements.SEARCH_TYPES_ONE_OF);
    searchTypesOnly = c.prepare(Statements.SEARCH_TYPES_ONLY);
    searchTypesOnlyOneOf = c.prepare(Statements.SEARCH_TYPES_ONLY_ONE_OF);
    searchTypesTwoOf = c.prepare(Statements.SEARCH_TYPES_TWO_OF);
    searchTypesNot = c.prepare(Statements.SEARCH_TYPES_NOT);
    searchTypesNotJust = c.prepare(Statements.SEARCH_TYPES_NOT_JUST);
    searchTypesPrimarily = c.prepare(Statements.SEARCH_TYPES_PRIMARILY);
    searchTypesSecondarily = c
        .prepare(Statements.SEARCH_TYPES_SECONDARILY);
    searchTypesGetResults = c.prepare(Statements.SEARCH_TYPES_GET_RESULTS);
    searchGenders = c.prepare(Statements.SEARCH_GENDERS);
    results = c.prepare(Statements.SEARCH_EXECUTE);
  }
  
  public void resetSearch() {
    conn.update(reset);
    conn.update(start);
  }
  
  public int filterName(String name) {
    String newName = name.replaceAll("[.,:\\-'\"%_]", "").replace('?', '_')
        .replace('*', '%').toLowerCase();
    conn.update(searchName, newName);
    return (int) conn.getResult(count);
  }
  
  public int filterAbilities(String name, boolean normal, boolean hidden) {
    conn.update(searchAbilityReset1);
    conn.update(searchAbilityReset2);
    
    conn.update(searchAbilityStart,
        name.toLowerCase().replace('*', '%').replace('?', '_'));
    
    if (normal || !hidden) {
      conn.update(searchAbilityNormal);
    }
    if (hidden || !normal) {
      conn.update(searchAbilityHidden);
    }
    
    conn.update(searchAbilityFilter);
    
    return (int) conn.getResult(count);
  }
  
  public int filterTypes(int mode, boolean normal, boolean fighting,
      boolean flying, boolean poison, boolean ground, boolean rock,
      boolean bug, boolean ghost, boolean steel, boolean fire,
      boolean water, boolean grass, boolean electric, boolean psychic,
      boolean ice, boolean dragon, boolean dark, boolean fairy) {
    conn.update(searchTypesStart1);
    conn.update(searchTypesStart2);
    
    String insertion = "";
    if (normal) insertion += ", (1)";
    if (fighting) insertion += ", (2)";
    if (flying) insertion += ", (3)";
    if (poison) insertion += ", (4)";
    if (ground) insertion += ", (5)";
    if (rock) insertion += ", (6)";
    if (bug) insertion += ", (7)";
    if (ghost) insertion += ", (8)";
    if (steel) insertion += ", (9)";
    if (fire) insertion += ", (10)";
    if (water) insertion += ", (11)";
    if (grass) insertion += ", (12)";
    if (electric) insertion += ", (13)";
    if (psychic) insertion += ", (14)";
    if (ice) insertion += ", (15)";
    if (dragon) insertion += ", (16)";
    if (dark) insertion += ", (17)";
    if (fairy) insertion += ", (18)";
    
    conn.update(
        "INSERT INTO search_types VALUES " + insertion.substring(2) + ";");
    
    DBStatement typeStatement = null;
    
    switch (mode) {
      case 1:
        typeStatement = searchTypesAtLeast;
        break;
      case 2:
        typeStatement = searchTypesOneOf;
        break;
      case 3:
        typeStatement = searchTypesOnly;
        break;
      case 4:
        typeStatement = searchTypesOnlyOneOf;
        break;
      case 5:
        typeStatement = searchTypesTwoOf;
        break;
      case 6:
        typeStatement = searchTypesNot;
        break;
      case 7:
        typeStatement = searchTypesNotJust;
        break;
      case 8:
        typeStatement = searchTypesPrimarily;
        break;
      case 9:
        typeStatement = searchTypesSecondarily;
        break;
    }
    
    conn.update(typeStatement);
    conn.update(searchTypesGetResults);
    
    return (int) conn.getResult(count);
  }
  
  public int filterGender(int mode, int selection) {
    if (selection == -1) {
      conn.update(searchGenders, -1, -1);
    } else {
      int min = selection;
      int max = selection;
      
      if (mode == 1) max = 8;
      if (mode == 3) min = 0;
      
      conn.update(searchGenders, min, max);
    }
    
    return (int) conn.getResult(count);
  }
  
  public int filterGeneration(boolean first, boolean second, boolean third,
      boolean fourth, boolean fifth, boolean sixth, boolean seventh) {
    String gens = "";
    
    if (first) gens += ", 1";
    if (second) gens += ", 2";
    if (third) gens += ", 3";
    if (fourth) gens += ", 4";
    if (fifth) gens += ", 5";
    if (sixth) gens += ", 6";
    if (seventh) gens += ", 7";
    
    gens = "(" + gens.substring(2) + ")";
    
    conn.update(String.format(Statements.SEARCH_GENERATIONS, gens));
    
    return (int) conn.getResult(count);
  }
  
  public ArrayList<String> getResults() {
    ArrayList<HashMap<String, Object>> searchResults = conn.query(results);
    ArrayList<String> out = new ArrayList<>();
    
    for (HashMap<String, Object> map : searchResults) {
      String output = (Integer) map.get("id") + " " + map.get("name");
      String fmName = (String) map.get("form name");
      if (fmName != null) {
        output += " (" + fmName + ")";
      }
      out.add(output);
    }
    
    return out;
  }
}
