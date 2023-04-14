package ter;

import java.sql.*;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Receiver {

	public void connect(String string) {
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
		
	    }else {
	    	try {
				MongoClient mongoClient = MongoClients.create("mongodb+srv://Zeyphax:zeyphax00@bd-decisions.eok3p.mongodb.net/mshclemi");
				MongoDatabase database = mongoClient.getDatabase("mshclemi");
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
}
