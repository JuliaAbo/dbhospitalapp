package functions;

import java.io.IOException;

import functions.IFunc;

public class appointments implements IFunc {
    Appendable ap;

    public appointments(Appendable ap){
      this.ap = ap;
    }

    @Override
    public void apply() {
      try {
        this.ap.append("You've decided to work on your appointments! Here are commands to do so:");
        this.ap.append("\n");
        this.ap.append("'schedule' -schedules an appointment. Include id, Dr., Patient, Nurse," +
                " date/ time fields ");
        this.ap.append("\n");
        this.ap.append("'edit' -edits an appointment. Include all necessary elements of the appointment.");
        this.ap.append("\n");
        this.ap.append("'all_app' -see a master list of all appointments.");
        this.ap.append("\n");
        this.ap.append("'see_app (int)' -see a specific appointment with the appointment id");
        this.ap.append("\n");
      } catch(IOException e) {
        System.out.print("had issues connecting");
      }
    }
  }

