package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;
import java.util.HashMap;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Info {
  private DBConnection conn;
  private DBStatement englishName;
  private DBStatement allNames;
  
  public Info(DBConnection c, Lookup l) {
    conn = c;
    englishName = c.prepare(Statements.ENGLISH_NAME);
    allNames = c.prepare(Statements.ALL_NAMES);
  }
  
  public String getEnglishName(int pokemonFormID) {
    HashMap<String, Object> engName = conn.getRow(englishName,
        pokemonFormID);
    String spName = (String) engName.get("species name");
    String fmName = (String) engName.get("form name");
    if (fmName == null) {
      return spName;
    } else {
      return spName + " (" + fmName + ")";
    }
  }
  
  public ArrayList<String> getNames(int pokemonFormID) {
    ArrayList<HashMap<String, Object>> names = conn.query(allNames,
        pokemonFormID);
    ArrayList<String> out = new ArrayList<>();
    
    for (HashMap<String, Object> map : names) {
      String spName = (String) map.get("species name");
      String fmName = (String) map.get("form name");
      String language = (String) map.get("language");
      if (fmName == null) {
        out.add(language + ": " + spName);
      } else {
        out.add(language + ": " + spName + " (" + fmName + ")");
      }
    }
    
    return out;
  }
}
