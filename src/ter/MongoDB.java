package ter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	private MongoClient mongoClient;
	private static HashMap<String, Command> commands = new HashMap<>();
	private static Receiver receiver = new Receiver();
	
	public MongoDB(MongoClient mongoClient) throws SQLException {
		System.out.println("***Bienvenue sur la base Judilibre***");
		System.out.println("Tapez SELECT * pour faire un select de la base");
		System.out.println("Tapez SELECT id pour selectionner un id particulier");
		System.out.println("Tapez cour");
		System.out.println("Tapez delete id pour supprimer un tuple particulier");
		System.out.println("Tapez insert pour ré insérer le tuple supprimé");
		System.out.println("Tapez QUIT pour fermer la connexion et revenir à l'acceuil");
		this.mongoClient = mongoClient;
		MongoDatabase database = mongoClient.getDatabase("mshclemi");
		MongoCollection<Document> collection = database.getCollection("decision");
		commands.put("SELECT *", new selectMongoCommande(receiver,collection));
		commands.put("SELECT id", new selectOneTupleMongoCommande(receiver,collection));
		commands.put("cour", new selectCourMongoCommande(receiver,collection));
		commands.put("DELETE id", new deleteMongoCommande(receiver,collection));
		commands.put("INSERT id", new insertMongoCommande(receiver,collection));
		commands.put("QUIT", new quitMCommand(this.mongoClient,receiver));
		while (true) {
			System.out.print(">> ");
			Scanner Commande = new Scanner(System.in);
			String nextCommande = Commande.nextLine();
			if(commands.containsKey(nextCommande)) {
				commands.get(nextCommande).execute();
			}else {
				System.out.print("Commande inconnue");
			}
		}
	}

}
