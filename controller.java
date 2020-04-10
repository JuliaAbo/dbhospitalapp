import java.sql.Connection;

public class controller {
    private final Readable input;
    private final Appendable output;

    public controller(Readable rd, Appendable ap) throws IllegalArgumentException {
        if(rd == null || ap == null){
            throw new IllegalArgumentException("You have null inputs or outputs to your controller");
        }
        input = rd;
        output = ap;
    }

    public void useDB(Connection conn){

    }

}
