package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addSupplies implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addSupplies(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add new supplies");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the supply you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("id remaining_number name");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.next();
      String args = arg1.concat(" ").concat(arg2).concat(" ").concat(arg3);

      if (new validateSupplyID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("You have tried to add a supply that already exists. please try again. Re-type 'add_supplies'");
        this.ap.append("\n");
      } else {

        String call = "{CALL addSupplies(?,?,?)}";
        String[] arguments = args.split(" ");
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arguments[0]);
        supplyFunc.setString(2, arguments[1]);
        supplyFunc.setString(3, arguments[2]);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_supplies'");
      }
    } catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting what is specified. Try running the command again.");
    }
  }
}
