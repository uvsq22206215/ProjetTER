package ter;

import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class deleteMongoCommande implements Command {

	private Receiver receiver;
	private MongoCollection<Document> collection;
	
	public deleteMongoCommande(Receiver receiver, MongoCollection<Document> collection) {
		this.receiver = receiver;
		this.collection = collection;
	}

	@Override
	public void execute() throws SQLException {
		receiver.deleteMongo(collection);

	}

}
