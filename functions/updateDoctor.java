package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class updateDoctor implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public updateDoctor(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to update a doctor");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the doctor you would like to update as following and press enter:");
      this.ap.append("\n");
      this.ap.append("ID Specialty");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.nextLine();
      if(!new validateDoctorID(this.ap, this.conn).apply(arg1)){
        this.ap.append("This doctor does not already exist, try adding employee first");
      }
      else {
        String call = "{CALL updateDoctor(?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_doctors'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting something as specified. Try running the command again.");
    }
  }
}
