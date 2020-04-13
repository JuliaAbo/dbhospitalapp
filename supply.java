import java.io.IOException;

public class supply implements IFunc {
  Appendable ap;

  public supply(Appendable ap){
    this.ap = ap;
  }


  @Override
  public void apply() {
    try {
      this.ap.append("You've decided to work on your supply! Here are commands to do so:");
      this.ap.append("\n");
      this.ap.append("'get_supplies' gets a masterlist for all supplies ");
      this.ap.append("\n");
      this.ap.append("'get_supply (int id)' gets information for a specific supply");
      this.ap.append("\n");
      this.ap.append("get_treat_supply (int treatment)  gets information for supplies needed for a specific treatment");
      this.ap.append("\n");
      this.ap.append("'low_supply' gets what supplies are under 25% stocked");
      this.ap.append("\n");
    } catch(IOException e){
      System.out.print("had issue writing to system.out");
    }
  }
}
