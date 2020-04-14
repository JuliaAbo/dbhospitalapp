package functions;

import java.sql.SQLException;

public interface IFunction<A, R> {
  R apply(A arg) throws SQLException;
}
