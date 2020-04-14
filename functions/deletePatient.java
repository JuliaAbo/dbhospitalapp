package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class deletePatient implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public deletePatient(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to delete information about one of your patients");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the patient you would like to delete as following and press enter:");
      this.ap.append("\n");
      this.ap.append("id");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      if(new validatePatientID(this.ap, this.conn).apply(arg1)) {
        String call = "{CALL deletePatient(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Deleted desired outcome. To see new result, type 'get_patients'");
      } else {
        this.ap.append("You did not give a valid patient id");
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string that has a number for the id. You may need to re-run your command.'");
    }

  }
}
