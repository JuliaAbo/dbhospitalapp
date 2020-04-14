package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class updateEmployee implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public updateEmployee(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new employee");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the appointment you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("ID Phonenum(9 digits) Gender JobType Name");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.next();
      String arg4 = this.scan.next();
      String name = this.scan.nextLine();
      if(!new validateEmployee(this.ap, this.conn).apply(arg1)){
        this.ap.append("This employee does not exists");
      }
      else {
        String call = "{CALL updateEmployee(?,?,?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, name);
        supplyFunc.setString(3, arg2);
        supplyFunc.setString(4, arg3);
        supplyFunc.setString(5, arg4);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_employees'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting something as specified. Try running the command again.");
    }
  }
}
