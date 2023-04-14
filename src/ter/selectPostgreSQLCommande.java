package ter;

import java.sql.*;

public class selectPostgreSQLCommande implements Command {
	
	private Receiver receiver;
	private Statement stmt;
	
	public selectPostgreSQLCommande(Receiver receiver, Statement stmt) {
		this.receiver = receiver;
		this.stmt = stmt;
	}

	@Override
	public void execute() throws SQLException {
		receiver.selectPostgreSQL(stmt);

	}

}
