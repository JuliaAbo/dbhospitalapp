package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getPatient implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getPatient(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to access a specific patient");
      this.ap.append("\n");
      this.ap.append("Please enter the id for the patient you would like to see and press enter:");
      this.ap.append("\n");

      String arg1 = this.scan.next();

      if(!new validatePatientID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect patient id, please try again. Re-type your command'");
        this.ap.append("\n");
      }
      else {
        String call = "{CALL getPatient(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Id, Name, Age, Phonenum, Gender, Address, InsuranceID");
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
          this.ap.append(", ");
          this.ap.append(res.getString(6));
          this.ap.append(", ");
          this.ap.append(res.getString(7));
          this.ap.append("\n");
        }
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input");
    }

  }
}
