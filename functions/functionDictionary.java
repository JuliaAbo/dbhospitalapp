package functions;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Scanner;

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
    // Administrative functions
    functions.put("help", new help(ap));
    functions.put("appointments", new appointments(ap));
    functions.put("supply", new supply(ap));


    // Functions operating on the Supply table
    functions.put("get_supplies", new getSupplies(ap, conn));
    functions.put("update_supplies", new updateSupplies(ap, conn, scan));
    functions.put("add_supply", new addSupplies(ap, conn, scan));
    functions.put("delete_supply", new addSupplies(ap, conn, scan));
    functions.put("get_supply", new getSupply(ap, conn, scan));

    // Functions operating on the Supp_needed table
    functions.put("add_supp_needed", new addSupplyNeeded(ap, conn, scan));
    functions.put("get_supp_needed", new getSuppNeeded(ap, conn));
    functions.put("delete_supp_needed", new deleteSupplyNeeded(ap, conn, scan));

    // Functions operating on the Treatments table
    functions.put("add_treatment", new addTreatment(ap, conn, scan));
    functions.put("delete_treatment", new deleteTreatment(ap, conn, scan));
    functions.put("get_treatments", new getTreatments(ap, conn));
    functions.put("get_treatment", new getTreatment(ap, conn, scan));
    functions.put("update_treatment", new updateTreatment(ap, conn, scan));

    // Functions operating on the Outcomes table

    functions.put("get_outcomes", new getOutcomes(ap, conn));
    functions.put("get_outcome", new getOutcome(ap, conn, scan));
    functions.put("add_outcome", new addOutcome(ap, conn, scan));
    functions.put("update_outcome", new updateOutcome(ap, conn, scan));
    functions.put("delete_outcome", new deleteOutcome(ap, conn, scan));

    // Functions operating on the Appointments table

    functions.put("get_appointments", new getAppointments(ap, conn));
    functions.put("get_appointment", new getAppointment(ap, conn, scan));
    functions.put("add_appointment", new addAppointment(ap, conn, scan));
    functions.put("update_appointment", new updateAppointment(ap, conn, scan));
    functions.put("delete_appointment", new deleteAppointment(ap, conn, scan));





  }

}
