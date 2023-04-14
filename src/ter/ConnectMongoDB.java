package ter;

import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class ConnectMongoDB {
	
	public static void connect() {
		try {
			MongoClient mongoClient = MongoClients.create("mongodb+srv://Zeyphax:zeyphax00@bd-decisions.eok3p.mongodb.net/mshclemi");
			MongoDatabase database = mongoClient.getDatabase("mshclemi");
			MongoCollection<Document> collection = database.getCollection("decision");
			MongoCursor<Document> cursor = collection.find().iterator();
			Document query = new Document("source", new Document("$eq", "dila"));
			for (Document document : collection.find(query)) {
	            System.out.println(document.toJson());
	        }
			/*while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}*/
			cursor.close();
			mongoClient.close();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
