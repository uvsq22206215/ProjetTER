package ter;

import java.sql.SQLException;

import com.mongodb.client.MongoClient;

public class quitMCommand implements Command {
	
	private MongoClient mongoClient;
	private Receiver receiver;
	
	public quitMCommand(MongoClient mongoClient, Receiver receiver) {
		this.mongoClient = mongoClient;
		this.receiver = receiver;
	}

	@Override
	public void execute() throws SQLException {
		receiver.quitM(mongoClient);

	}

}
