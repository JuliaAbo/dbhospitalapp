package functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class validateAppointment implements IFunction<String,Boolean> {
  Connection conn;
  Appendable ap;
  String DocId;
  String PatientID;
  String Date;

  public validateAppointment(Appendable ap, Connection conn, String DocId, String Pid, String date) {
    this.ap = ap;
    this.conn = conn;
    this.DocId = DocId;
    this.PatientID = Pid;
    this.Date = date;
  }


  @Override
  public Boolean apply(String arg) throws SQLException {
    ArrayList<String> validTreats = new ArrayList<String>();
    try {
      String callValidation = "{CALL getAppointments}";
      java.sql.CallableStatement validFunc = this.conn.prepareCall(callValidation);
      ResultSet valid = validFunc.executeQuery();

      while (valid.next()) {
        if( valid.getString(1).equals(this.DocId) &&
                valid.getString(3).equals(this.PatientID)
          && valid.getString(4).equals(this.Date)){
          return true;
        }
      }

    } catch ( SQLException e) {
      System.out.print("An error has occurred");
    }
    return false;
  }
}
