package ter;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Receiver {
	
	String newLine = System.getProperty("line.separator");

	public void connect(String string) throws SQLException {
		if(string == "PostgreSQL") {
			try {
			      //étape 1: charger la classe de driver
			      Class.forName("org.postgresql.Driver");
			      //étape 2: créer l'objet de connexion
			      Connection conn = DriverManager.getConnection(
			      "jdbc:postgresql://pg.adam.uvsq.fr/piratage","piratage","f0f89cee55");
			      PostgreSQL p = new PostgreSQL(conn);
		   }catch(Exception e){ 
			      System.out.println(e);
		    }
		
	    }else if(string == "MongoDB"){
	    	try {
				MongoClient mongoClient = MongoClients.create("mongodb+srv://Zeyphax:zeyphax00@bd-decisions.eok3p.mongodb.net/mshclemi");
				MongoDB m = new MongoDB(mongoClient);
	    	}catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    }

	public void selectPostgreSQL(Statement stmt) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT * FROM bsa");
		while(res.next())
	        System.out.println(res.getInt(1)+"  "+res.getString(2)
	        +"  "+res.getString(3) + " " +res.getString(4)+ " " +  res.getString(5) + " " + res.getString(6));
	}

	public void quit(Connection conn) throws SQLException {
		conn.close();
		App app = new App();
		
	}

	public void exit() {
		System.out.print("Au revoir !");
		System.exit(1);	
	}

	public void quitM(MongoClient mongoClient) throws SQLException {
		mongoClient.close();
		App app = new App();
	}

	public void selectMongo(MongoCollection<Document> collection) throws SQLException {
	    int pageSize = 20;
	    int pageNumber = 1;

	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        MongoCursor<Document> cursor = collection.find().skip((pageNumber - 1) * pageSize).limit(pageSize).iterator();

	        while (cursor.hasNext()) {
	            System.out.println(cursor.next().toJson());
	        }

	        cursor.close();
	        System.out.println(newLine);
	        System.out.println("**Appuie sur entrée pour afficher d'autres tuples ou sur e pour quitter l'affichage**");

	        if (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            if (line.equalsIgnoreCase("e")) {
	            	break;
	            }
	        }

	        pageNumber++;
	    }
	}

	public void afficheTablePostgreSQL(Statement stmt) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT tablename FROM pg_tables WHERE schemaname = 'public'");
		System.out.println("tableName");
		System.out.println("..........");
		while(res.next()) {
			String tableName = res.getString(1);
	        System.out.println(tableName);
		}
		
	}

	public void selectPostgreSQLOneTuple(Statement stmt) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT * FROM bsa WHERE country = 'Australia' AND year = 1995");
		while(res.next()) {
	        System.out.println(res.getInt(1)+"  "+res.getString(2)
	        +"  "+res.getString(3) + " " +res.getString(4)+ " " +  res.getString(5) + " " + res.getString(6));
		}
		
	}

	public void updatePostgreSQLOneTuple(Statement stmt) throws SQLException {
		 int numRowsAffected = stmt.executeUpdate("UPDATE bsa SET index = 30 WHERE country = 'Australia' AND year = 1995");
		    if (numRowsAffected > 0) {
		        System.out.println(numRowsAffected + " lignes ont ete modifiees !");
		    } else {
		        System.out.println("Aucune ligne n'a ete modifiee.");
		    }
		
	}

	public void updatePostgreSQLOneTupleReverse(Statement stmt) throws SQLException {
		 int numRowsAffected = stmt.executeUpdate("UPDATE bsa SET index = 35 WHERE country = 'Australia' AND year = 1995");
		    if (numRowsAffected > 0) {
		        System.out.println(numRowsAffected + " lignes ont ete modifiees !");
		    } else {
		        System.out.println("Aucune ligne n'a ete modifiee.");
		    }
	}

	public void InsertPostgreSQLCommande(Statement stmt) throws SQLException {
		String sql = "INSERT INTO bsa (region_id,country_id,country,year,value,index) VALUES (1,1,'Japon',1995,90,30)";

	    int numRowsAffected = stmt.executeUpdate(sql);

	    if (numRowsAffected > 0) {
	        System.out.println(numRowsAffected + " ligne(s) ont ete inseree(s) !");
	    } else {
	        System.out.println("Aucune ligne n'a ete inseree.");
	    }
		
	}

	public void selectPostgreSQLOneTuple1(Statement stmt) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT * FROM bsa WHERE country = 'Japon' AND year = 1995");
		while(res.next()) {
	        System.out.println(res.getInt(1)+"  "+res.getString(2)
	        +"  "+res.getString(3) + " " +res.getString(4)+ " " +  res.getString(5) + " " + res.getString(6));
		}
		
	}

	public void deletePostgreSQLCommande(Statement stmt) throws SQLException {
		String sql = "DELETE FROM bsa WHERE country = 'Japon' AND year = 1995";

	    int numRowsAffected = stmt.executeUpdate(sql);

	    if (numRowsAffected > 0) {
	        System.out.println(numRowsAffected + " ligne(s) ont été supprimee(s) !");
	    } else {
	        System.out.println("Aucune ligne n'a ete supprimee.");
	    }
		
	}

	public void selectOneTupleMongo(MongoCollection<Document> collection) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez entrer un id : ");
		String idToSelect = scanner.nextLine();
        Document filter = new Document("_id", idToSelect);
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
	}

	public void selectCourMongo(MongoCollection<Document> collection) {
		int pageSize = 20;
	    int pageNumber = 1;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez entrer jurica pour cour d'appel ou jurinet/dila pour cour d'assise : ");
		String cour = scanner.nextLine();
		Document query = new Document("source", new Document("$eq",cour));
		while (true) {
			MongoCursor<Document> cursor = collection.find(query).skip((pageNumber - 1) * pageSize).limit(pageSize).iterator();;

	        while (cursor.hasNext()) {
	            System.out.println(cursor.next().toJson());
	        }

	        cursor.close();
	        System.out.println(newLine);
	        System.out.println("**Appuie sur entrée pour afficher d'autres tuples ou sur e pour quitter l'affichage**");

	        if (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            if (line.equalsIgnoreCase("e")) {
	            	break;
	            }
	        }

	        pageNumber++;
	    }
		
	}

	public void deleteMongo(MongoCollection<Document> collection) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez entrer un id : ");
		String idToDelete = scanner.nextLine();
		Document filter = new Document("_id", idToDelete);
		collection.deleteOne(filter);
		System.out.println("Tuple supprime avec succes.");
		
	}

	public void insertMongo(MongoCollection<Document> collection) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Veuillez entrer un id : ");
		String id = scanner.nextLine();
		// Création du document à insérer
        Document document = new Document("_id", id)
                .append("contested", null)
                .append("numbers", new Document())
                .append("publication", new Document())
                .append("themes", new Document())
                .append("timeline", null)
                .append("visa", new Document())
                .append("rapprochements", new Document())
                .append("source", "jurinet")
                .append("text", "N° S 16-86.881 F-P+B\n\nN° 113\n\nFAR\n27 FÉVRIER 2018\n\n\nCASSATION PARTIELL…")
                .append("chamber", "Chambre criminelle")
                .append("decision_date", "2018-02-27T00:00:00.000+00:00")
                .append("ecli", "ECLI:FR:CCASS:2018:CR00113")
                .append("jurisdiction", "Cour de cassation")
                .append("number", "16-86.881")
                .append("solution", "Cassation")
                .append("type", "Autre")
                .append("formation", "Formation restreinte hors RNSM/NA")
                .append("summary", "La cour d'appel saisie d'une demande indemnitaire pour des faits de co…")
                .append("update_date", "2019-03-07T00:00:00.000+00:00")
                .append("forward", null)
                .append("__v", 0);
     // Insertion du document dans la collection
        collection.insertOne(document);
        System.out.println("Tuple insere avec succes.");
		
	}

}
