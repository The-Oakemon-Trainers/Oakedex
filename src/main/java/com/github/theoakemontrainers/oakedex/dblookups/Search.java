package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;
import java.util.HashMap;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Search {
  private DBConnection conn;
  
  private DBStatement reset;
  private DBStatement count;
  private DBStatement searchName;
  private DBStatement searchAbilityStart;
  private DBStatement searchAbilityNormal;
  private DBStatement searchAbilityHidden;
  private DBStatement searchAbilityFilter;
  private DBStatement searchAbilityEnd;
  private DBStatement searchTypesStart;
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
  private DBStatement searchTypesEnd;
  private DBStatement results;
  
  public Search(DBConnection c) {
    conn = c;
    reset = c.prepare(Statements.RESET_SEARCH);
    count = c.prepare(Statements.SEARCH_COUNT);
    searchName = c.prepare(Statements.SEARCH_NAMES);
    searchAbilityStart = c.prepare(Statements.SEARCH_ABILITIES_CREATE);
    searchAbilityNormal = c.prepare(Statements.SEARCH_ABILITIES_NORMAL);
    searchAbilityHidden = c.prepare(Statements.SEARCH_ABILITIES_HIDDEN);
    searchAbilityFilter = c.prepare(Statements.SEARCH_ABILITIES_FILTER);
    searchAbilityEnd = c.prepare(Statements.SEARCH_ABILITIES_DROP);
    searchTypesStart = c.prepare(Statements.SEARCH_TYPES_START);
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
    searchTypesEnd = c.prepare(Statements.SEARCH_TYPES_END);
    results = c.prepare(Statements.SEARCH_EXECUTE);
  }
  
  public void resetSearch() {
    conn.update(reset);
  }
  
  public int filterName(String name) {
    conn.update(searchName, name);
    return (int) conn.getResult(count);
  }
  
  public int filterAbilities(String name, boolean normal, boolean hidden) {
    conn.update(searchAbilityStart, name.toLowerCase());
    
    if (normal || !hidden) {
      conn.update(searchAbilityNormal);
    }
    if (hidden || !normal) {
      conn.update(searchAbilityHidden);
    }
    
    conn.update(searchAbilityFilter);
    conn.update(searchAbilityEnd);
    
    return (int) conn.getResult(count);
  }
  
  public int filterTypes(int mode, boolean normal, boolean fighting,
      boolean flying, boolean poison, boolean ground, boolean rock,
      boolean bug, boolean ghost, boolean steel, boolean fire,
      boolean water, boolean grass, boolean electric, boolean psychic,
      boolean ice, boolean dragon, boolean dark, boolean fairy) {
    conn.update(searchTypesStart);
    
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
    conn.update(searchTypesEnd);
    
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
