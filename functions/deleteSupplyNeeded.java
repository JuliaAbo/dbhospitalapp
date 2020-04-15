package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteSupplyNeeded implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public deleteSupplyNeeded(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to delete information about one of your supplies-treatment links");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the supply you would like to delete as following and press enter:");
      this.ap.append("\n");
      this.ap.append("Supply_id Treatment_id");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();

      String call = "{CALL deleteSuppNeeded(?,?)}";
      java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
      supplyFunc.setString(1, arg1);
      supplyFunc.setString(2, arg2);
      ResultSet res = supplyFunc.executeQuery();
      this.ap.append("Deleted desired pairing. To see new result, type 'get_supp_needed'");
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input. Be sure that you're inputting the correct arguments. Try re-starting the command. ");
    }

  }
}
