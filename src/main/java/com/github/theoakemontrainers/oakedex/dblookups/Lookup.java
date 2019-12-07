package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Lookup {
  public DBConnection conn;
  public DBStatement alias;
  
  public Lookup(DBConnection c) {
    conn = c;
    alias = c.prepare(
        "select pokemon_id from pokemon_aliases where alias like ?;");
  }
  
  public ArrayList<Integer> getResults(String name) {
    ArrayList<Integer> out = new ArrayList<>();
    String newName = name.replaceAll("[.,:\\-'\"%_]", "").replace('?', '_')
        .replace('*', '%').toLowerCase();
    
    try {
      out.add(Integer.parseInt(newName));
    } catch (NumberFormatException ex) {
      ArrayList<Object> objs = conn.getList(alias, newName, newName);
      for (Object obj : objs) {
        out.add((Integer) obj);
      }
    }
    
    return out;
  }
}
