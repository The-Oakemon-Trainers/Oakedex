package net.nixill.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DBConnection {
  Connection conn;
  Statement stmt;
  
  public DBConnection(String file) {
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:" + file);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public ArrayList<HashMap<String, Object>> query(String query) {
    try {
      ArrayList<HashMap<String, Object>> out = new ArrayList<>();
      ResultSet res = stmt.executeQuery(query);
      while (res.next()) {
        out.add(getRow(res));
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public ArrayList<Object> getList(String query) {
    try {
      ResultSet res = stmt.executeQuery(query);
      ArrayList<Object> out = new ArrayList<>();
      
      while (res.next()) {
        out.add(res.getObject(1));
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public Object getResult(String query) {
    try {
      ResultSet res = stmt.executeQuery(query);
      if (res.next()) {
        return res.getObject(1);
      } else {
        return null;
      }
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public HashMap<String, Object> getRow(String query) {
    try {
      return getRow(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public HashMap<Object, Object> getMap(String query) {
    try {
      HashMap<Object, Object> out = new HashMap<>();
      ResultSet res = stmt.executeQuery(query);
      
      while (res.next()) {
        Object key = res.getObject(1);
        Object value = res.getObject(2);
        out.put(key, value);
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  private HashMap<String, Object> getRow(ResultSet res) {
    try {
      HashMap<String, Object> out = new HashMap<>();
      ResultSetMetaData meta = res.getMetaData();
      int cols = meta.getColumnCount();
      for (int col = 1; col <= cols; col++) {
        String label = meta.getColumnLabel(col);
        Object obj = res.getObject(col);
        out.put(label, obj);
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
}
