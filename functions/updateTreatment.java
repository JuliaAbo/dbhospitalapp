package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class updateTreatment implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public updateTreatment(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new treatment");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the treatment you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("id doctor_id description");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.nextLine();

      if (!new validateTreatmentID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("This treatment does not already exist.");
        this.ap.append("\n");
      } else if (!new validateDoctorID(this.ap, this.conn).apply(arg2)) {
        this.ap.append("This is not a valid Doctor id");
        this.ap.append("\n");
      } else {
        String call = "{CALL updateTreatment(?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setString(3, arg3);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_treatments'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string with spaces that has a number for the id, a number for the quantity remaining, and then a name. Format it like this: '2 25 name'");
    }
  }
}
