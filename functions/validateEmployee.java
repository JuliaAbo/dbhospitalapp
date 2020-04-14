package functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class validateEmployee implements IFunction<String,Boolean> {
  Connection conn;
  Appendable ap;

  public validateEmployee(Appendable ap, Connection conn) {
    this.ap = ap;
    this.conn = conn;
  }


  @Override
  public Boolean apply(String arg) throws SQLException {
    ArrayList<String> validTreats = new ArrayList<String>();
    try {
      String callValidation = "{CALL getEmployeeIDs}";
      java.sql.CallableStatement validFunc = this.conn.prepareCall(callValidation);
      ResultSet valid = validFunc.executeQuery();

      while (valid.next()) {
        validTreats.add(valid.getString(1));
      }
      return (validTreats.contains(arg));

    } catch ( SQLException e) {
      System.out.print("An error has occurred");
    }
    return (validTreats.contains(arg));
  }
}
