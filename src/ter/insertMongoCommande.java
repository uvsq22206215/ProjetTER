package ter;

import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class insertMongoCommande implements Command {

	private Receiver receiver;
	private MongoCollection<Document> collection;
	
	public insertMongoCommande(Receiver receiver, MongoCollection<Document> collection) {
		this.receiver = receiver;
		this.collection = collection;
	}

	@Override
	public void execute() throws SQLException {
		receiver.insertMongo(collection);

	}

}
