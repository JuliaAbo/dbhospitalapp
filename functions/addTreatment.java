package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addTreatment implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addTreatment(Appendable ap, Connection conn, Scanner scan) {
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
      String arg3 = scan.nextLine();

      if (new validateTreatmentID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("This treatment already exists.");
        this.ap.append("\n");
      } else if (!new validateDoctorID(this.ap, this.conn).apply(arg2)) {
        this.ap.append("This is not a valid Doctor id");
        this.ap.append("\n");
      } else {

        String call = "{CALL addTreatment(?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setString(3, arg3);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_treatments'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting the correct arguments. Try re-starting the command. ");
    }
  }
}
