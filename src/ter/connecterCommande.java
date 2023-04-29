package ter;

import java.sql.SQLException;

public class connecterCommande implements Command {
	
	private Receiver receiver;
	private String string;
	
	public connecterCommande(Receiver receiver, String string) {
		this.receiver = receiver;
		this.string = string;
	}

	@Override
	public void execute() throws SQLException {
		receiver.connect(string);

	}

}
