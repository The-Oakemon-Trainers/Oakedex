package net.nixill.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.theoakemontrainers.oakedex.dblookups.Info;
import com.github.theoakemontrainers.oakedex.dblookups.Lookup;

import net.nixill.databases.DBConnection;

public class DBTest {
  private static final DBConnection conn;
  private static final Lookup look;
  private static final Info info;
  // private static final Search search;
  
  // this has to be up here but is ok
  static {
    conn = new DBConnection("pokedex.db");
    look = new Lookup(conn);
    info = new Info(conn);
    // search = new Search(conn);
    
  }
  
  public static void main(String[] args) {
    // test1();
    // test2();
    test3();
  }
  
  public static void test1() {
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
    HashMap<String, Object> row = conn.getRow(
        "select id as \"language ID\", identifier as \"language identifier\" from languages where id = 1");
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
  
  public static void test2() {
    DBConnection conn = new DBConnection("pokedex.db");
    Lookup look = new Lookup(conn);
    
    ArrayList<Integer> ints = look.getResults("*eon");
    
    for (Integer i : ints) {
      System.out.println(i);
    }
  }
  
  public static void test3() {
    deepPrint(info.getEnglishName(386));
    System.out.println();
    deepPrint(info.getNames(386));
    System.out.println();
    deepPrint(info.getMainInfo(386));
    System.out.println();
    deepPrint(info.getStats(386));
  }
  
  public static void deepPrint(Object obj) {
    deepPrint(obj, 0);
  }
  
  public static void deepPrint(Object obj, int level) {
    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }
    
    if (obj instanceof Collection) {
      Collection<?> coll = (Collection<?>) obj;
      System.out.println(coll.getClass().getSimpleName() + " ("
          + coll.size() + " item(s)):");
      for (Object itm : coll) {
        deepPrint(itm, level + 1);
      }
    } else if (obj instanceof Map) {
      Map<?, ?> map = (Map<?, ?>) obj;
      System.out.println(map.getClass().getSimpleName() + " (" + map.size()
          + " item(s)):");
      for (Entry<?, ?> ent : map.entrySet()) {
        deepPrint(ent, level + 1);
      }
    } else if (obj instanceof Entry) {
      Entry<?, ?> ent = (Entry<?, ?>) obj;
      System.out.println(ent.getKey());
      deepPrint(ent.getValue(), level + 1);
    } else {
      System.out.println(obj);
    }
  }
}
