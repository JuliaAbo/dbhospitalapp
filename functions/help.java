package functions;

import java.io.IOException;
import java.sql.Connection;

import functions.IFunc;

public class help implements IFunc {
  Appendable ap;

  public help(Appendable ap){
    this.ap = ap;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("To use the system to edit your hospital records, you can enter one of the following:  \n"
              + "--- Functions to assist the user’s interactions \n" +
              "'help' - get help with using the system\n" +
              "'quit' - quit the application, close the connection\n" +
              "\n" +
              "--- Functions operating on Supply\n" +
              "     'get_supplies' -get all supplies\n" +
              "     'Update_supplies'-update a supply\n" +
              "     'add_supply'-add a new supply\n" +
              "     'delete_supply'-delete a supply\n" +
              "     'get_supply'- get a specific supply\n" +
              "\n" +
              "--- Functions operating on the Supplies needed \n" +
              "     'add_supp_needed' - add a link between a supply and a treatment\n" +
              "     'get_supp_needed' - get all linked supplies and treatments\n" +
              "     'delete_supp_needed' - delete a link between a supply and a treatment\n" +
              "\n" +
              "--- Functions operating on Treatments\n" +
              "     'add_treatment' - add a treatment\n" +
              "     'delete_treatment' -delete a treatment\n" +
              "     'get_treatments' - see all treatments \n" +
              "     'get_treatment' - get a specific treatment\n" +
              "     'update_treatment' - update a specific treatment \n" +
              "\n" +
              "--- Functions operating on Outcomes \n" +
              "\n" +
              "     'get_outcomes' - see all outcomes \n" +
              "     'get_outcome' - see a specific outcome\n" +
              "     'add_outcome' - add an outcome\n" +
              "     'update_outcome' - update an outcome\n" +
              "     'delete_outcome' - delete an outcome\n" +
              "\n" +
              "--- Functions operating on Appointments \n" +
              "     'get_appointments' - see all appointments\n" +
              "     'get_appointment' - see a specific appointment\n" +
              "     'add_appointment' - add a new appointment\n" +
              "     'update_appointment' - update an existing appointment\n" +
              "     'delete_appointment' - delete an appointment\n" +
              "\n" +
              " --- Functions on Employees\n" +
              "     'get_employees' - get all employees\n" +
              "     'get_employee' - get a specific employee\n" +
              "     'add_employee' - add a new employee\n" +
              "     'delete_employee' - delete an existing employee\n" +
              "     'update_employee' - update an existing employee\n" +
              "\n" +
              "--- Functions on Doctors\n" +
              "     'get_doctors' -get all doctors\n" +
              "     'get_doctor' - get a specific doctor\n" +
              "     'add_doctor' - add a new doctor\n" +
              "     'update_doctor' -  update a doctor\n" +
              "     'delete_doctor' - delete a doctor\n" +
              "\n" +
              "--- Functions on Patient\n" +
              "     'get_patients' - get all patients\n" +
              "     'get_patient' - get a specific patient\n" +
              "     'add_patient' - add a new patient\n" +
              "     'update_patient' - update an existing patient\n" +
              "     'delete_patient' - delete a patient \n" +
              "\n" +
              "---Functions on Diagnosis\n" +
              "     'get_all_diagnosis' -get all diagnoses\n" +
              "     'get_diagnosis' - get a specific diagnosis\n" +
              "     'add_diagnosis' -add a new diagnosis\n" +
              "     'update_diagnosis' - update the diagnosis\n" +
              "     'delete_diagnosis' - delete a diagnosis\n");

    } catch(IOException e) {
      System.out.print("had issues connecting");
    }
  }
}
