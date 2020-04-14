package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getSupply implements IFunc{
  Appendable ap;
  Connection conn;
  Scanner scan;

  getSupply(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to access a specific supply");
      this.ap.append("\n");
      this.ap.append("Please enter the id for the supply you would like to update and press enter:");
      this.ap.append("\n");

      String arg1 = this.scan.next();

      if(!new validateSupplyID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have input an incorrect supply id, please try again. Re-type 'update_supplies'");
        this.ap.append("\n");
      }
      else {
        String call = "{CALL getSupply(?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Id, Supply, Remaining");
        this.ap.append("\n");
        while(res.next()){
          this.ap.append(res.getString(1));
          this.ap.append(res.getString(2));
          this.ap.append(res.getString(3));
        }
      }
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input");
    }

  }
}
