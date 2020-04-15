package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addPatient implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addPatient(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new patient");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the patient when prompted:");
      this.ap.append("\n");
      this.ap.append("ID\n");
      scan.nextLine();
      String arg1 = this.scan.nextLine();
      this.ap.append("Name\n");
      String arg2 = this.scan.nextLine();
      this.ap.append("Age\n");
      String arg3 = this.scan.nextLine();
      this.ap.append("Gender\n");
      String arg4 = this.scan.nextLine();
      this.ap.append("Address\n");
      String arg5 = this.scan.nextLine();
      this.ap.append("PhoneNum\n");
      String arg6 = this.scan.nextLine();
      this.ap.append("InsuranceID\n");
      String arg7 = this.scan.nextLine();
      if(new validatePatientID(this.ap, this.conn).apply(arg1)){
        this.ap.append("This patient already exists, try updating");
      }
      else {
        String call = "{CALL addPatient(?,?, ?, ?, ?, ?, ?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg3);
        supplyFunc.setString(3, arg2);
        supplyFunc.setString(4, arg4);
        supplyFunc.setString(5, arg5);
        supplyFunc.setString(6, arg6);
        supplyFunc.setString(7, arg7);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_patients'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting something as specified. Try running the command again.");
    }
  }
}
