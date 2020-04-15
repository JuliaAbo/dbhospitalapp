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
    functions.put("quit", new quit(ap, conn));
    // Functions operating on the Supply table
    functions.put("get_supplies", new getSupplies(ap, conn));
    functions.put("update_supplies", new updateSupplies(ap, conn, scan));
    functions.put("add_supply", new addSupplies(ap, conn, scan));
    functions.put("delete_supply", new deleteSupplies(ap, conn, scan));
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

    // Functions on Employees

    functions.put("get_employees", new getEmployees(ap, conn));
    functions.put("get_employee", new getEmployee(ap, conn, scan));
    functions.put("add_employee", new addEmployee(ap, conn, scan));
    functions.put("delete_employee", new deleteEmployee(ap, conn, scan));
    functions.put("update_employee", new updateEmployee(ap, conn, scan));

    // functions on Doctors
    functions.put("get_doctors", new getDoctors(ap, conn));
    functions.put("get_doctor", new getDoctor(ap, conn, scan));
    functions.put("add_doctor", new addDoctor(ap, conn, scan));
    functions.put("update_doctor", new updateDoctor(ap, conn, scan));
    functions.put("delete_doctor", new deleteDoctor(ap, conn, scan));

    // Functions on Patient

    functions.put("get_patients", new getPatients(ap, conn));
    functions.put("get_patient", new getPatient(ap, conn, scan));
    functions.put("add_patient", new addPatient(ap, conn, scan));
    functions.put("update_patient", new updatePatient(ap, conn, scan));
    functions.put("delete_patient", new deletePatient(ap, conn, scan));

    //Functions on Diagnosis

    functions.put("get_all_diagnosis", new getAllDiagnosis(ap, conn));
    functions.put("get_diagnosis", new getDiagnosis(ap, conn, scan));
    functions.put("add_diagnosis", new addDiagnosis(ap, conn, scan));
    functions.put("update_diagnosis", new updateDiagnosis(ap, conn, scan));
    functions.put("delete_diagnosis", new deleteDiagnosis(ap, conn, scan));



  }

}
