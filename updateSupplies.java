import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class updateSupplies implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public updateSupplies(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to update this supply");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.next();
      String args = arg1.concat(" ").concat(arg2).concat(" ").concat(arg3);

      String call = "{CALL updateSupplies(?,?,?)}";
      String[] arguments = args.split(" ");
      java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
      supplyFunc.setString(1, arguments[0]);
      supplyFunc.setString(2, arguments[1]);
      supplyFunc.setString(3, arguments[2]);
      ResultSet res = supplyFunc.executeQuery();
      this.ap.append("Updated table. To see new result, type 'get_supplies'");
    } catch(IOException | SQLException e){
      System.out.print("Something is wrong with your input. Be sure that you're inputting a string with spaces that has a number for the id, a number for the quantity remaining, and then a name. Format it like this: '2 25 name'");
    }
  }
}
