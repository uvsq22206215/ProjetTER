package ter;

import java.sql.SQLException;
import java.sql.Statement;

public class selectPostgreSQLOneTupleCommande implements Command {

	private Receiver receiver;
	private Statement stmt;
	
	public selectPostgreSQLOneTupleCommande(Receiver receiver, Statement stmt) {
		this.receiver = receiver;
		this.stmt = stmt;
	}

	@Override
	public void execute() throws SQLException {
		receiver.selectPostgreSQLOneTuple(stmt);

	}

}
