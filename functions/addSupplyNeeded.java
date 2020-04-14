package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class addSupplyNeeded implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addSupplyNeeded(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to link a supply to a treatment");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the supply you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("supply_id treatment_id");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      if(!new validateSupplyID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect supply id, please try again. Re-type your command.");
        this.ap.append("\n");
      } else if (!new validateTreatmentID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect supply id, please try again. Re-type your command.");
        this.ap.append("\n");
      }else {
        String call = "{CALL addSupplyNeeded(?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2,arg2);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_supp_needed'");
      }
    } catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string with spaces that has a number for the id, a number for the quantity remaining, and then a name. Format it like this: '2 25 name'");
    }

  }
}
