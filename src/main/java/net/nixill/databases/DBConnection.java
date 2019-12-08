package net.nixill.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DBConnection {
  Connection conn;
  Statement stmt;
  
  /**
   * Opens a connection to a file.
   */
  public DBConnection(String file) {
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:" + file);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Creates a prepared statement that can be used in the other methods.
   * 
   * @param query
   *   The query to prepare
   * @return The prepared statement object
   */
  public DBStatement prepare(String query) {
    return new DBStatement(query);
  }
  
  /**
   * Runs a query and returns the whole thing.
   * <p>
   * Each {@link HashMap} is one row, as described in
   * {@link #getRow(String)}. The ArrayList is all of the rows, in the
   * order returned.
   * 
   * @param query
   *   The query to execute.
   * @return The list of maps, as described above.
   */
  public ArrayList<HashMap<String, Object>> query(String query) {
    try {
      return query(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the whole thing.
   * <p>
   * For a more complete description, see {@link #query(ResultSet)}
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The list of maps, as described above.
   */
  public ArrayList<HashMap<String, Object>> query(DBStatement dbst,
      Object... objs) {
    dbst.populate(objs);
    return query(dbst.run());
  }
  
  private ArrayList<HashMap<String, Object>> query(ResultSet res) {
    try {
      ArrayList<HashMap<String, Object>> out = new ArrayList<>();
      
      while (res.next()) {
        out.add(getRow(res));
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the whole thing using Strings.
   * <p>
   * Each {@link HashMap} is one row, as described in
   * {@link #getStringRow(String)}. The ArrayList is all of the rows, in
   * the order returned.
   * 
   * @param query
   *   The query to execute.
   * @return The list of maps, as described above.
   */
  public ArrayList<HashMap<String, String>> stringQuery(String query) {
    try {
      return stringQuery(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the whole thing using Strings.
   * <p>
   * For a more complete description, see {@link #stringQuery(ResultSet)}
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The list of maps, as described above.
   */
  public ArrayList<HashMap<String, String>> stringQuery(DBStatement dbst,
      Object... objs) {
    dbst.populate(objs);
    return stringQuery(dbst.run());
  }
  
  private ArrayList<HashMap<String, String>> stringQuery(ResultSet res) {
    try {
      ArrayList<HashMap<String, String>> out = new ArrayList<>();
      
      while (res.next()) {
        out.add(getStringRow(res));
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the first column.
   * <p>
   * The result of this method is only the first column of each row.
   * 
   * @param query
   *   The query to run
   * @return The list of objects, as described above
   */
  public ArrayList<Object> getList(String query) {
    try {
      return getList(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the first column.
   * <p>
   * For a more complete description, see {@link #getList(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The list of objects, as described above
   */
  public ArrayList<Object> getList(DBStatement dbst, Object... objs) {
    dbst.populate(objs);
    return getList(dbst.run());
  }
  
  private ArrayList<Object> getList(ResultSet res) {
    try {
      ArrayList<Object> out = new ArrayList<>();
      
      while (res.next()) {
        out.add(res.getObject(1));
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the first column, as Strings.
   * <p>
   * The result of this method is only the first column of each row. All
   * objects are converted to {@link String}s.
   * 
   * @param query
   *   The query to run
   * @return The list of strings, as described above
   */
  public ArrayList<String> getStringList(String query) {
    try {
      return getStringList(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the first column, as strings.
   * <p>
   * For a more complete description, see {@link #getStringList(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The list of strings, as described above
   */
  public ArrayList<String> getStringList(DBStatement dbst,
      Object... objs) {
    dbst.populate(objs);
    return getStringList(dbst.run());
  }
  
  private ArrayList<String> getStringList(ResultSet res) {
    try {
      ArrayList<String> out = new ArrayList<>();
      
      while (res.next()) {
        Object obj = res.getObject(1);
        if (obj == null) {
          out.add(null);
        } else {
          out.add(obj.toString());
        }
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the top left value.
   * <p>
   * The result of this method is only the first column of the first row,
   * even if the actual result set is bigger.
   * 
   * @param query
   *   The query to run
   * @return The single object
   */
  public Object getResult(String query) {
    try {
      return getResult(stmt.executeQuery(query));
    } catch (SQLException ex) {
      return new DBException(ex);
    }
  }
  
  /**
   * Runs a query and returns the top left value.
   * <p>
   * For more information, see {@link #getResult(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The single object
   */
  public Object getResult(DBStatement dbst, Object... objs) {
    dbst.populate(objs);
    return getResult(dbst.run());
  }
  
  private Object getResult(ResultSet res) {
    try {
      if (res.next()) {
        return res.getObject(1);
      } else {
        return null;
      }
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Returns a single-row result.
   * <p>
   * This method creates a HashMap of the row, where the keys are the
   * column labels and the values are the... well, values.
   * 
   * @param query
   *   The query to run
   * @return The row, as described above.
   */
  public HashMap<String, Object> getRow(String query) {
    try {
      ResultSet res = stmt.executeQuery(query);
      if (res.next()) {
        return getRow(stmt.executeQuery(query));
      } else {
        return null;
      }
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Returns a single-row result.
   * <p>
   * For further information, see {@link #getRow(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The row, as described above.
   */
  public HashMap<String, Object> getRow(DBStatement dbst, Object... objs) {
    try {
      dbst.populate(objs);
      ResultSet res = dbst.run();
      if (res.next()) {
        return getRow(res);
      } else {
        return null;
      }
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
  
  /**
   * Returns a single-row result, using strings.
   * <p>
   * This method creates a HashMap of the row, where the keys are the
   * column labels and the values are the... well, values. All values are
   * converted to {@link String}s.
   * 
   * @param query
   *   The query to run
   * @return The row, as described above.
   */
  public HashMap<String, String> getStringRow(String query) {
    try {
      ResultSet res = stmt.executeQuery(query);
      if (res.next()) {
        return getStringRow(res);
      } else {
        return null;
      }
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Returns a single-row result, using Strings.
   * <p>
   * For further information, see {@link #getStringRow(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The row, as described above.
   */
  public HashMap<String, String> getStringRow(DBStatement dbst,
      Object... objs) {
    try {
      dbst.populate(objs);
      ResultSet res = dbst.run();
      if (res.next()) {
        return getStringRow(res);
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new DBException(e);
    }
  }
  
  private HashMap<String, String> getStringRow(ResultSet res) {
    try {
      HashMap<String, String> out = new HashMap<>();
      ResultSetMetaData meta = res.getMetaData();
      int cols = meta.getColumnCount();
      for (int col = 1; col <= cols; col++) {
        String label = meta.getColumnLabel(col);
        Object obj = res.getObject(col);
        if (obj != null) {
          out.put(label, obj.toString());
        } else {
          out.put(label, null);
        }
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Creates a map out of a two-column result.
   * <p>
   * The keys of the returned map are the values in the first column of the
   * result set. If there are any repeated values, only the final value for
   * each key is retained. The values of the map are the values in the
   * second column of the result set.
   * 
   * @param query
   *   The query to run
   * @return The map, as described above.
   */
  public HashMap<Object, Object> getMap(String query) {
    try {
      return getMap(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Creates a map out of a two-column result.
   * <p>
   * For more information, see {@link #getMap(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The map, as described above.
   */
  public HashMap<Object, Object> getMap(DBStatement dbst, Object... objs) {
    dbst.populate(objs);
    return getMap(dbst.run());
  }
  
  private HashMap<Object, Object> getMap(ResultSet res) {
    try {
      HashMap<Object, Object> out = new HashMap<>();
      
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
  
  /**
   * Creates a map out of a two-column result, converting all to strinsg.
   * <p>
   * The keys of the returned map are the values in the first column of the
   * result set. If there are any repeated values, only the final value for
   * each key is retained. The values of the map are the values in the
   * second column of the result set. All objects on both sides are
   * converted to Strings using toString().
   * 
   * @param query
   *   The query to run
   * @return The map, as described above.
   */
  public HashMap<String, String> getStringMap(String query) {
    try {
      return getStringMap(stmt.executeQuery(query));
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  /**
   * Creates a map out of a two-column result.
   * <p>
   * For more information, see {@link #getMap(String)}.
   * 
   * @param dbst
   *   A prepared statement created by {@link #prepare(String)}.
   * @param objs
   *   The parameters to use in the statement.
   * @return The map, as described above.
   */
  public HashMap<String, String> getStringMap(DBStatement dbst,
      Object... objs) {
    dbst.populate(objs);
    return getStringMap(dbst.run());
  }
  
  private HashMap<String, String> getStringMap(ResultSet res) {
    try {
      HashMap<String, String> out = new HashMap<>();
      
      while (res.next()) {
        Object key = res.getObject(1);
        Object value = res.getObject(2);
        String keyString = (key == null) ? null : key.toString();
        String valString = (value == null) ? null : value.toString();
        out.put(keyString, valString);
      }
      
      return out;
    } catch (SQLException ex) {
      throw new DBException(ex);
    }
  }
  
  public class DBStatement {
    private PreparedStatement psmt;
    
    private DBStatement(String sql) {
      try {
        psmt = conn.prepareStatement(sql);
      } catch (SQLException ex) {
        throw new DBException(ex);
      }
    }
    
    private void populate(Object... objs) {
      try {
        psmt.clearParameters();
        for (int i = 1; i <= objs.length; i++) {
          psmt.setObject(i, objs[i - 1]);
        }
      } catch (SQLException ex) {
        throw new DBException(ex);
      }
    }
    
    private ResultSet run() {
      try {
        return psmt.executeQuery();
      } catch (SQLException ex) {
        throw new DBException(ex);
      }
    }
    
    public String toString() {
      return psmt.toString();
    }
  }
}
