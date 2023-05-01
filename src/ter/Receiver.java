package ter;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoException;
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

}
