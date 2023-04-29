package ter;

import java.sql.SQLException;
import java.sql.Statement;

public class afficheTablePostgreSQLCommande implements Command {
	private Receiver receiver;
	private Statement stmt;
	
	public afficheTablePostgreSQLCommande(Receiver receiver, Statement stmt) {
		this.receiver = receiver;
		this.stmt = stmt;
	}

	@Override
	public void execute() throws SQLException {
		receiver.afficheTablePostgreSQL(stmt);

	}


}
