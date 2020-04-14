package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class updateAppointment implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public updateAppointment(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new appointment");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the appointment you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("DocID NurseID PatientID Date(YYYY/MM/DD) Summary");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.next();
      String arg4 = this.scan.next();
      String arg5 = this.scan.nextLine();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date javaVers = sdf.parse(arg4);
      java.sql.Date sqlVers = new java.sql.Date(javaVers.getTime());
      if(!new validateAppointment(this.ap, this.conn, arg1, arg3, arg4).apply("")){
        this.ap.append("This appointment does not exist, try adding instead");
      }
      else {
        String call = "{CALL updateAppointment(?,?,?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setString(3, arg3);
        supplyFunc.setDate(4, sqlVers);
        supplyFunc.setString(5, arg5);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_appointments'");
      }
    }
     catch (IOException | SQLException | ParseException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting something as specified. Try running the command again.");
    }
  }
}
