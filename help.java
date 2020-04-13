import java.io.IOException;
import java.sql.Connection;

public class help implements IFunc {
  Appendable ap;

  public help(Appendable ap){
    this.ap = ap;
  }

  @Override
  public void apply() {
    try {
      this.ap.append("To use the system to edit your hospital records, you can enter one of the following to get" +
              "options for that element of the system.");
      this.ap.append("\n");
      this.ap.append("appointments");
      this.ap.append("\n");
      this.ap.append("patient");
      this.ap.append("\n");
      this.ap.append("employee");
      this.ap.append("\n");
      this.ap.append("treatment");
      this.ap.append("\n");
      this.ap.append("supply");
      this.ap.append("\n");
      this.ap.append("Use 'q' or 'quit' to quit the application");
      this.ap.append("\n");
    } catch(IOException e) {
      System.out.print("had issues connecting");
    }
  }
}
