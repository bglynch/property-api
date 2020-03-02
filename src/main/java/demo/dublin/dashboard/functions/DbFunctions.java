package demo.dublin.dashboard.functions;

import java.sql.*;

public class DbFunctions {

  public static final String DB_NAME = "data/liteDb02.sqlite3";
  public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

  public static void main(String[] args) {

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string, other string)");
      statement.executeUpdate("insert into person values(1, 'leo', null)");
      statement.executeUpdate("insert into person values(2, 'yui', null)");
      statement.executeUpdate("insert into person values(3, 'ben', null)");
      statement.executeUpdate("insert into person values(4, 'nik', null)");
      statement.executeUpdate("insert into person values(5, 'bri', null)");
      ResultSet rs = statement.executeQuery("select * from person");
      System.out.println(statement.executeQuery("select id from person").getArray(0));
      {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
}
