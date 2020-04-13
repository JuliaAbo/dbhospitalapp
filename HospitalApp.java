import com.mysql.cj.xdevapi.Column;

import java.awt.image.ColorModel;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import dnl.utils.text.table.TextTable;


public class HospitalApp {

	/** The name of the MySQL account to use (or empty for anonymous) */
  private String userName;

	/** The password for the MySQL account (or empty for anonymous) */
  private String password;

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
  private final String dbName = "mydb";
	
	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";

	private functionDictionary commands;

	Scanner scan;

	Connection conn;
	private final Readable rd;
    private final Appendable ap;
	
  public HospitalApp(String username, String password, Readable rd, Appendable ap) {
    this.userName = username;
    this.password = password;
    this.rd = rd;
    this.ap = ap;
    conn = null;
    try {
      conn = this.getConnection();
    }
    catch (SQLException e1) {
      // TODO Auto-generated catch block
    }
    this.scan = new Scanner(this.rd);
    this.commands = new functionDictionary(this.ap, conn, this.scan);
  }

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

    conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName+"?useSSL=false",
				connectionProps);
		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() throws IOException {

		// Connect to MySQL
    Connection conn = null;
    try {
      conn = this.getConnection();
    }
    catch (SQLException e1) {
      // TODO Auto-generated catch block
     this.ap.append("Had an issue connecting! Check your user / password");
    }
    String chosenChar = "";

    try {
      this.ap.append("Welcome to Your Hospital Management system! For a list of available commands " +
              "you can type 'h' or 'help'");
      this.ap.append("\n");
      while (scan.hasNext()) {
        String command = scan.next();
        // Check if the user input is a valid command
        if(this.commands.functions.containsKey(command)) {
          // run the function for that command
          this.commands.functions.get(command).apply();
        } else {
          this.ap.append("Sorry, that's not a valid function! Please reference 'help' to get started");
        }
      }
    }
    catch(IOException e){
		// Drop the table
	}
	}
}