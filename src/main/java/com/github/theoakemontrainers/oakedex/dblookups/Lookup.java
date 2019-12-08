package com.github.theoakemontrainers.oakedex.dblookups;

import java.util.ArrayList;

import net.nixill.databases.DBConnection;
import net.nixill.databases.DBConnection.DBStatement;

public class Lookup {
  private DBConnection conn;
  private DBStatement alias;
  private DBStatement psID;
  private DBStatement pID;
  
  public Lookup(DBConnection c) {
    conn = c;
    alias = c.prepare(
        "select pokemon_id from pokemon_aliases where alias like ?;");
    psID = c.prepare("select species_id from pokemon where id = "
        + "(select pokemon_id from pokemon_forms where id = ?);");
    pID = c.prepare("select pokemon_id from pokemon_forms where id = ?;");
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
  
  protected int getPokemonSpeciesID(int pokemonFormID) {
    return (int) conn.getResult(psID, pokemonFormID);
  }
  
  protected int getPokemonID(int pokemonFormID) {
    return (int) conn.getResult(pID, pokemonFormID);
  }
}
