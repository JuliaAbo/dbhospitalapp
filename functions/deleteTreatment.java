package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteTreatment implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public deleteTreatment(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to delete information about one of your treatments");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the treatment you would like to delete as following and press enter:");
      this.ap.append("\n");
      this.ap.append("Treatment_id");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      if(new validateTreatmentID(this.ap, this.conn).apply(arg1)) {
        String call = "{CALL deleteTreatment(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Deleted desired pairing. To see new result, type 'get_treatments'");
      } else {
        this.ap.append("You did not give a valid treatment id");
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string with spaces that has a number for the id, a number for the quantity remaining, and then a name. Format it like this: 'Tid'");
    }

  }
}
