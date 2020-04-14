package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getOutcome implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getOutcome(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to get information for a specific the outcome");
      this.ap.append("\n");
      this.ap.append("Please enter the id for the outcome you would like to read and press enter:");
      this.ap.append("\n");

      String arg1 = this.scan.next();

      if(!new validateOutcomeID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect outcome id, please try again. Re-type your command");
        this.ap.append("\n");
      }
      else {
        String call = "{CALL getOutcome(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Id, Treatment, Descr");
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
