package functions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class quit implements IFunc {
  Appendable ap;
  Connection conn;

  public quit(Appendable ap, Connection conn){
    this.ap = ap;
    this.conn = conn;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to quit the application. Thanks for using it!\n");
      this.ap.append("Closing connection and quitting...\n");
      this.conn.close();
      System.exit(0);
    } catch(IOException | SQLException e) {
      System.out.print("had issues quitting");
    }
  }
}
