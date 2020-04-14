package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class getAppointment implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getAppointment(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to get information for a specific appointment");
      this.ap.append("\n");
      this.ap.append("Please enter the following information for the appointment you would like to see and press enter:");
      this.ap.append("\n");
      this.ap.append("DocId PatientID Date(YYYY-MM-DD)");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 =  this.scan.next();
      String arg3 = this.scan.next();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date javaVers = sdf.parse(arg3);
      java.sql.Date sqlVers = new java.sql.Date(javaVers.getTime());
        String call = "{CALL getAppointment(?, ?, ?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setDate(3, sqlVers);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("DocId, NurseID, PatientID, Date, Summary");
        this.ap.append("\n");
        while(res.next()){
          this.ap.append(res.getString(1));
          this.ap.append(", ");
          this.ap.append(res.getString(2));
          this.ap.append(", ");
          this.ap.append(res.getString(3));
          this.ap.append(", ");
          this.ap.append(res.getString(4));
          this.ap.append(", ");
          this.ap.append(res.getString(5));
          this.ap.append("\n");
        }
    } catch(IOException | SQLException | ParseException e){
      System.out.print("There is something wrong with your input");
    }

  }
}
