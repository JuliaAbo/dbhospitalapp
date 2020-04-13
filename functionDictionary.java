import java.sql.Connection;
import java.util.HashMap;
import java.util.Scanner;

public class functionDictionary {
  HashMap<String, IFunc> functions;
  Appendable ap;
  Connection conn;
  Scanner scan;


  public functionDictionary(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.functions = new HashMap<String, IFunc>();
    this.scan = scan;
    functions.put("get_supplies", new getSupply(ap, conn));
    functions.put("help", new help(ap));
    functions.put("appointments", new appointments(ap));
    functions.put("supply", new supply(ap));
    functions.put("update_supplies", new updateSupplies(ap, conn, scan));
  }

}
