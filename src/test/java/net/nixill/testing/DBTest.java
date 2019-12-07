package net.nixill.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.nixill.databases.DBConnection;

public class DBTest {
  public static void main(String[] args) {
    DBConnection conn = new DBConnection("pokedex.db");
    
    // test getList()
    System.out.println("Listing language IDs:");
    ArrayList<Object> list = conn.getList("select id from languages;");
    for (Object obj : list) {
      System.out.println(obj);
    }
    System.out.println();
    System.out.print("Selecting language 1: ");
    
    // test getResult();
    System.out.println(
        conn.getResult("select identifier from languages where id = 1;"));
    
    // test getRow()
    HashMap<String, Object> row = conn
        .getRow("select * from languages where id = 1");
    for (Entry<String, Object> ent : row.entrySet()) {
      System.out.println(ent.getKey() + ": " + ent.getValue().toString());
    }
    System.out.println();
    
    // test getMap()
    System.out.println("Names:");
    HashMap<Object, Object> map = conn.getMap(
        "select local_language_id, name from language_names where language_id = 1");
    for (Entry<Object, Object> ent : map.entrySet()) {
      System.out.println(
          ent.getKey().toString() + ": " + ent.getValue().toString());
    }
  }
}
