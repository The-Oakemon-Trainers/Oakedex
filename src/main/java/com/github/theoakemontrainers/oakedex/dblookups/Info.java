package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;
import java.util.HashMap;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Info {
  private DBConnection conn;
  private DBStatement englishName;
  private DBStatement allNames;
  private DBStatement mainInfo;
  private DBStatement stats;
  private DBStatement dexNumbers;
  
  public Info(DBConnection c) {
    conn = c;
    englishName = c.prepare(Statements.ENGLISH_NAME);
    allNames = c.prepare(Statements.ALL_NAMES);
    mainInfo = c.prepare(Statements.MAIN_INFO);
    stats = c.prepare(Statements.STATS);
    dexNumbers = c.prepare(Statements.DEXNUMS);
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
    ArrayList<HashMap<String, String>> names = conn.stringQuery(allNames,
        pokemonFormID);
    ArrayList<String> out = new ArrayList<>();
    
    for (HashMap<String, String> map : names) {
      String spName = map.get("species name");
      String fmName = map.get("form name");
      String language = map.get("language");
      if (fmName == null) {
        out.add(language + ": " + spName);
      } else {
        out.add(language + ": " + spName + " (" + fmName + ")");
      }
    }
    
    return out;
  }
  
  public HashMap<String, String> getMainInfo(int pokemonFormID) {
    return conn.getStringRow(mainInfo, pokemonFormID);
  }
  
  public HashMap<String, String> getStats(int pokemonFormID) {
    return conn.getStringMap(stats, pokemonFormID);
  }
  
  public ArrayList<String> getDexNumber(int pokemonFormID) {
    ArrayList<HashMap<String, Object>> query = conn.query(dexNumbers,
        pokemonFormID);
    ArrayList<String> out = new ArrayList<>();
    
    for (HashMap<String, Object> row : query) {
      String line = row.get("region") + ": " + row.get("number");
      out.add(line);
    }
    
    return out;
  }
}
