package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class addOutcome implements IFunc {
  Appendable ap;
  Connection conn;
  Scanner scan;

  public addOutcome(Appendable ap, Connection conn, Scanner scan) {
    this.ap = ap;
    this.conn = conn;
    this.scan = scan;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to add a new outcome");
      this.ap.append("\n");
      this.ap.append("Please enter the arguments for the outcome you would like to add as following and press enter:");
      this.ap.append("\n");
      this.ap.append("Outcome_id Treatment_id desc");
      this.ap.append("\n");
      this.ap.append("\n");
      this.ap.append("\n");
      String arg1 = this.scan.next();
      String arg2 = this.scan.next();
      String arg3 = this.scan.nextLine();

      if (new validateOutcomeID(this.ap, this.conn).apply(arg1)) {
        this.ap.append("This outcome already exists.");
        this.ap.append("\n");
      } else if (!new validateTreatmentID(this.ap, this.conn).apply(arg2)) {
        this.ap.append("This is not a valid Treatment id");
        this.ap.append("\n");
      } else {
        String call = "{CALL addOutcome(?,?,?)}";
        java.sql.CallableStatement supplyFunc = this.conn.prepareCall(call);
        supplyFunc.setString(1, arg1);
        supplyFunc.setString(2, arg2);
        supplyFunc.setString(3, arg3);
        ResultSet res = supplyFunc.executeQuery();
        this.ap.append("Updated table. To see new result, type 'get_outcomes'");
      }
    }
     catch (IOException | SQLException e) {
      System.out.print("Something is wrong with your input. Be sure that you're inputting what is specified and try running the command again.");
    }
  }
}
