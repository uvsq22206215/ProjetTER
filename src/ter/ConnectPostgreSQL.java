package ter;

import java.sql.*;

public class ConnectPostgreSQL {
	
  public static void squ() {
	  ConnectMongoDB.connect();
	  
    /*try {
      //étape 1: charger la classe de driver
      Class.forName("org.postgresql.Driver");
      //étape 2: créer l'objet de connexion
      Connection conn = DriverManager.getConnection(
      "jdbc:postgresql://pg.adam.uvsq.fr/piratage","piratage","f0f89cee55");
      //étape 3: créer l'objet statement 
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery("SELECT * FROM bsa");
      //étape 4: exécuter la requête
      while(res.next())
        System.out.println(res.getInt(1)+"  "+res.getString(2)
        +"  "+res.getString(3) + " " +res.getString(4)+ " " +  res.getString(5) + " " + res.getString(6));
      //étape 5: fermez l'objet de connexion
      conn.close();
    }
    catch(Exception e){ 
      System.out.println(e);
    }*/
  }
}