package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getPatients implements IFunc {
  Appendable ap;
  Connection conn;

  getPatients(Appendable ap, Connection conn){
    this.ap = ap;
    this.conn = conn;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("Here's all the patients in your system: ");
      this.ap.append("\n");
      String arg = "{CALL getPatients}";
      java.sql.CallableStatement supplyFunc = this.conn.prepareCall(arg);
      ResultSet res = supplyFunc.executeQuery();
      this.ap.append("Id, Name, Age, Phonenum, Gender, Address, InsuranceID");
      this.ap.append("\n");
      while(res.next()){
        this.ap.append(res.getString(1));
        this.ap.append(", ");
        this.ap.append(res.getString(2));
        this.ap.append(", ");
        this.ap.append(res.getString(3));
        this.ap.append(", ");
        this.ap.append(res.getString(4));
        this.ap.append(", ");
        this.ap.append(res.getString(5));
        this.ap.append(", ");
        this.ap.append(res.getString(6));
        this.ap.append(", ");
        this.ap.append(res.getString(7));
        this.ap.append("\n");
      }

    } catch(IOException | SQLException e){
      System.out.print("had an issue communicating with output");
    }

  }
}
