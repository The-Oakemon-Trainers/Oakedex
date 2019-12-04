package net.nixill.databases;

@SuppressWarnings("serial")
public class DBException extends RuntimeException {
  public DBException(Throwable cause) {
    super(cause);
  }
}
