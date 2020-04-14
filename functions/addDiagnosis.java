package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addDiagnosis implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addDiagnosis(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new diagnosis");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the diagnosis you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("ID PatientID Tid DocId Descr");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.next();
      String arg4 = this.scan.next();
      String arg5 = this.scan.nextLine();
      if(new validateDiagnosisID(this.ap, this.conn).apply(arg1)){
        this.ap.append("This diagnosis already exists.");
      } else if(!new validateDoctorID(this.ap, this.conn).apply(arg4)){
        this.ap.append("This is not a valid doctor");
      } else if(!new validatePatientID(this.ap, this.conn).apply(arg2)){
        this.ap.append("This is not a valid patient");
      } else if(!new validateTreatmentID(this.ap, this.conn).apply(arg3)){
        this.ap.append("This is not a valid treatment");
      }
      else {
        String call = "{CALL addDiagnosis(?,?,?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setString(3, arg3);
        supplyFunc.setString(4, arg4);
        supplyFunc.setString(5, arg5);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_all_diagnosis'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting something as specified. Try running the command again.");
    }
  }
}
