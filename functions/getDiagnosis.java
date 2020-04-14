package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getDiagnosis implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getDiagnosis(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to access a specific Diagnosis");
      this.ap.append("\n");
      this.ap.append("Please enter the id for the diagnosis you would like to read and press enter:");
      this.ap.append("\n");

      String arg1 = this.scan.next();

      if(!new validateDiagnosisID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect id, please try again. Re-type your command'");
        this.ap.append("\n");
      }
      else {
        String call = "{CALL getDiagnosis(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Dignosis, Patient, Treatment, Doctor, Description");
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
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input");
    }

  }
}
