package ter;

import java.sql.SQLException;
import java.sql.Statement;

public class selectPostgreSQLOneTuple1Commande implements Command {

	private Receiver receiver;
	private Statement stmt;
	
	public selectPostgreSQLOneTuple1Commande(Receiver receiver, Statement stmt) {
		this.receiver = receiver;
		this.stmt = stmt;
	}

	@Override
	public void execute() throws SQLException {
		receiver.selectPostgreSQLOneTuple1(stmt);

	}

}
