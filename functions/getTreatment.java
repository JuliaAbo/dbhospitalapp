package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getTreatment implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getTreatment(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to access a specific treatment");
      this.ap.append("\n");
      this.ap.append("Please enter the id for the treatment you would like to update and press enter:");
      this.ap.append("\n");

      String arg1 = this.scan.next();

      if(!new validateTreatmentID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect treatment id, please try again. Re-type your command");
        this.ap.append("\n");
      }
      else {
        String call = "{CALL getTreatment(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Id, Doc, Desc");
        this.ap.append("\n");
        while(res.next()){
          this.ap.append(res.getString(1));
          this.ap.append(", ");
          this.ap.append(res.getString(2));
          this.ap.append(", ");
          this.ap.append(res.getString(3));
        }
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input");
    }

  }
}
