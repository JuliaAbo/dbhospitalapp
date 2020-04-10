import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class LotrApp {

	/** The name of the MySQL account to use (or empty for anonymous) */
  private String userName;

	/** The password for the MySQL account (or empty for anonymous) */
  private String password;

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
  private final String dbName = "lotrfinalAbouelheigaJ";
	
	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";

	private final Readable rd;
    private final Appendable ap;
	
  public LotrApp(String username, String password, Readable rd, Appendable ap) {
    this.userName = username;
    this.password = password;
    this.rd = rd;
    this.ap = ap;
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
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
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
    Scanner scan = new Scanner(this.rd);
    String chosenChar = "";

		// Create a table
    // Create a table
    try {
      String characters = "SELECT character_name FROM lotr_character";
      Statement statement = conn.createStatement();
      ResultSet characterNames = statement.executeQuery(characters);
      ArrayList<String> lotrchars = new ArrayList<String>();

      while (characterNames.next()) {
        lotrchars.add(characterNames.getString(1));
      }

      this.ap.append("Enter your character name here:");
      chosenChar = scan.next();

      while (!lotrchars.contains(chosenChar)) {
              this.ap.append("Sorry, that character is not in our database. Please specify a character name with the first " +
                      "letter capitalized.");
              chosenChar = scan.next();
      }

    }
    catch (SQLException e) {
      throw new IllegalArgumentException("ERROR: Could not fetch character names :/");
    }
    catch(IOException e) {
        throw new IllegalArgumentException("Had issues printing the output");
    }

    try {
      String callFunction = "{CALL track_character(?)}";
      java.sql.CallableStatement track_character = conn.prepareCall(callFunction);
      track_character.setString(1, chosenChar);
      ResultSet res = track_character.executeQuery();
      while (res.next()) {
       this.ap.append(res.getString(1));
       this.ap.append(" ");
       this.ap.append(res.getString(2));
       this.ap.append(" ");
       this.ap.append(res.getString(3));
       this.ap.append(" ");
       this.ap.append(res.getString(4));
       this.ap.append(" ");
      }

      this.ap.append("");
      this.ap.append("Closing connection now");
      conn.close();

    }
    catch (SQLException e) {
      this.ap.append("ERROR: Had issue executing track_character function");
    }
    catch(IOException e){
		// Drop the table
	}
	
 /* public String charChoose() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a character's name: ");
    String charchoose = scanner.nextLine();
    return charchoose;

  } */


	}
}