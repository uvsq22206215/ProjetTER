package ter;

import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class selectCourMongoCommande implements Command {

	private Receiver receiver;
	private MongoCollection<Document> collection;
	
	public selectCourMongoCommande(Receiver receiver, MongoCollection<Document> collection) {
		this.receiver = receiver;
		this.collection = collection;
	}

	@Override
	public void execute() throws SQLException {
		receiver.selectCourMongo(collection);

	}
}
