import com.mysql.cj.xdevapi.Result;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class getSupply implements IFunc{
  Appendable ap;
  Connection conn;

  getSupply(Appendable ap, Connection conn){
    this.ap = ap;
    this.conn = conn;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("Here's all the supplies in your system: ");
      this.ap.append("\n");
      String arg = "{CALL getSupplies}";
      java.sql.CallableStatement supplyFunc = this.conn.prepareCall(arg);
      ResultSet res = supplyFunc.executeQuery();
      ArrayList<String> ColumnNames = new ArrayList<String>();
      ColumnNames.add("ID");
      ColumnNames.add("Supply");
      ColumnNames.add("Remaining");
      this.ap.append("Id, Supply, Remaining");
      this.ap.append("\n");
      while(res.next()){
        this.ap.append(res.getString(1));
        this.ap.append(", ");
        this.ap.append(res.getString(2));
        this.ap.append(", ");
        this.ap.append(res.getString(3));
        this.ap.append("\n");
      }

    } catch(IOException | SQLException e){
      System.out.print("had an issue communicating with output");
    }

  }
}
