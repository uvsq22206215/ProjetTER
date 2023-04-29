package ter;

import java.sql.SQLException;

public class exitCommande implements Command {
	
	private Receiver receiver;
	
	public exitCommande(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() throws SQLException {
		receiver.exit();
	}

}
