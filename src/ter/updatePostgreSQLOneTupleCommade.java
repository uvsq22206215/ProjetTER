package ter;

import java.sql.SQLException;
import java.sql.Statement;

public class updatePostgreSQLOneTupleCommade implements Command {

	private Receiver receiver;
	private Statement stmt;
	
	public updatePostgreSQLOneTupleCommade(Receiver receiver, Statement stmt) {
		this.receiver = receiver;
		this.stmt = stmt;
	}

	@Override
	public void execute() throws SQLException {
		receiver.updatePostgreSQLOneTuple(stmt);

	}
}
