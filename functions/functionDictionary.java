package functions;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Scanner;

import functions.IFunc;

public class functionDictionary {
  public HashMap<String, IFunc> functions;
  Appendable ap;
  Connection conn;
  Scanner scan;


  public functionDictionary(Appendable ap, Connection conn, Scanner scan){
    this.ap = ap;
    this.conn = conn;
    this.functions = new HashMap<String, IFunc>();
    this.scan = scan;
    functions.put("help", new help(ap));
    functions.put("appointments", new appointments(ap));
    functions.put("supply", new supply(ap));
    functions.put("get_supplies", new getSupply(ap, conn));
    functions.put("update_supplies", new updateSupplies(ap, conn, scan));
    functions.put("add_supply", new addSupplies(ap, conn, scan));
    functions.put("delete_supply", new addSupplies(ap, conn, scan));
  }

}
