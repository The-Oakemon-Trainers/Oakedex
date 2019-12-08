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
