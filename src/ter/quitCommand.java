package ter;

import java.sql.Connection;
import java.sql.SQLException;

public class quitCommand implements Command {
	
	private Connection conn;
	private Receiver receiver;
	
	public quitCommand(Receiver receiver, Connection conn) {
		this.receiver = receiver;
		this.conn = conn;
	}

	@Override
	public void execute() throws SQLException {
		receiver.quit(conn);

	}

}
