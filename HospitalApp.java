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

	private final Readable rd;
    private final Appendable ap;
	
  public HospitalApp(String username, String password, Readable rd, Appendable ap) {
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
    Scanner scan = new Scanner(this.rd);
    String chosenChar = "";

		// Create a table
    // Create a table
    try {
      this.ap.append("Welcome to Your Hospital Management system! For a list of available commands " +
              "you can type 'h' or 'help'");
      this.ap.append("\n");
      while (scan.hasNext()) {
        String command = scan.next();
        if (command.equals("add_doctor")) {
          // temporary filler so we can see what we want to do
          this.ap.append("You would like to add a doctor");
        } else if (command.equals("h") || command.equals("help")) {
           this.ap.append("To use the system to edit your hospital records, you can enter one of the following to get" +
                   "options for that element of the system.");
          this.ap.append("\n");
            this.ap.append("appointments");
            this.ap.append("\n");
            this.ap.append("patient");
            this.ap.append("\n");
            this.ap.append("employee");
            this.ap.append("\n");
            this.ap.append("treatment");
            this.ap.append("\n");
            this.ap.append("supply");
            this.ap.append("\n");
            this.ap.append("Use 'q' or 'quit' to quit the application");
            this.ap.append("\n");
        }
        else if (command.equals("q") || command.equals("quit")) {
          this.ap.append("You've decided to quit! Thank you for using the application!");
          conn.close();
          break;
        } else if (command.equals("appointments")) {
            this.ap.append("You've decided to work on your appointments! Here are commands to do so:");
            this.ap.append("\n");
            this.ap.append("'schedule' -schedules an appointment. Include id, Dr., Patient, Nurse, date/ time fields ");
            this.ap.append("'edit' -edits an appointment. Include all necessary elements of the appointment.");
            this.ap.append("\n");
        }

        else if (command.equals("s") || command.equals("supply")) {
          //this.ap.append("You've decided to quit! Thank you for using the application!");
          String supply = "SELECT * FROM Supply";
          Statement statement = conn.createStatement();
          ResultSet supplyset = statement.executeQuery(supply);
          ArrayList<String> supplies = new ArrayList<String>();
          while (supplyset.next()) {
            supplies.add(supplyset.getString(1));
            supplies.add(supplyset.getString(2));
          }
          for(String each: supplies){
            this.ap.append(each.toString());
          }
          conn.close();
          break;
        }
        else {
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
        } catch (SQLException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    }


    /*try {
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
      //this.ap.append("Closing connection now");
      //conn.close();

     */

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