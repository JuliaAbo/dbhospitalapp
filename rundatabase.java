import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class rundatabase {

    public static void main(String[] args) {
        String username = "root";
        String password = "root";
        String charname = "";
        Readable in = new InputStreamReader(System.in);
        Appendable out = System.out;
        try (Scanner scan = new Scanner(in)) {
            out.append("Enter username: ");
            username = scan.nextLine();
            out.append("Enter password: ");
            password = scan.nextLine();
            out.append("Thank you! Connecting now...");
            HospitalApp app = new HospitalApp(username, password, in, out);
            app.run();
            //System.out.println("Thank you! Program ending now.");
            //System.exit(0);
        }catch(IOException e){
            throw new IllegalArgumentException("Yikes");
        }
    }
}
