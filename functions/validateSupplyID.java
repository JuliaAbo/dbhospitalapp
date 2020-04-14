package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class validateSupplyID implements IFunction<String, Boolean> {
  Connection conn;
  Appendable ap;

  public validateSupplyID(Appendable ap, Connection conn) {
    this.ap = ap;
    this.conn = conn;

  }


  @Override
  public Boolean apply(String arg) {
    ArrayList<String> validSupplies = new ArrayList<String>();
    try {
      String callValidation = "{CALL getSupplyIDs}";
      java.sql.CallableStatement validFunc = this.conn.prepareCall(callValidation);
      ResultSet valid = validFunc.executeQuery();

      while (valid.next()) {
        validSupplies.add(valid.getString(1));
      }
      return (validSupplies.contains(arg));

    } catch ( SQLException e) {
      System.out.print("An error has occurred");
    }
    return (validSupplies.contains(arg));
  }


}
