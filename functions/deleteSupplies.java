package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteSupplies implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public deleteSupplies(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to delete information about one of your supplies");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the supply you would like to delete as following and press enter:");
      this.ap.append("\n");
      this.ap.append("id");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();

      String call = "{CALL deleteSupplies(?)}";
      java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
      supplyFunc.setString(1, arg1);
      ResultSet res = supplyFunc.executeQuery();
      this.ap.append("Updated table. To see new result, type 'get_supplies'");
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string with spaces that has a number for the id, a number for the quantity remaining, and then a name. Format it like this: 'id remaining_number name'");
    }

  }
}
