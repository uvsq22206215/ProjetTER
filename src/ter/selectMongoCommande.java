package ter;

import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


public class selectMongoCommande implements Command {
	
	private Receiver receiver;
	private MongoCollection<Document> collection;
	
	public selectMongoCommande(Receiver receiver, MongoCollection<Document> collection) {
		this.receiver = receiver;
		this.collection = collection;
	}

	@Override
	public void execute() throws SQLException {
		receiver.selectMongo(collection);

	}

}
