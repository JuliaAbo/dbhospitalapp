package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addDoctor implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addDoctor(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new doctor");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the doctor you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("ID Specialty");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.nextLine();
      if(!new validateEmployee(this.ap, this.conn).apply(arg1)){
        this.ap.append("This employee does not already exist, try adding employee first");
      } else if(new validateDoctorID(this.ap, this.conn).apply(arg1)){
        this.ap.append("This doctor already exists, try updating");
      }
      else {
        String call = "{CALL addDoctor(?,?)}";
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
