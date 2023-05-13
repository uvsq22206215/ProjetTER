package ter;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class PostgreSQL {
	
	private Connection conn;
	private static HashMap<String, Command> commands = new HashMap<>();
	private static Receiver receiver = new Receiver();
	
	public PostgreSQL(Connection conn) throws SQLException {
		this.conn = conn;
		Statement stmt = conn.createStatement();
		commands.put("SELECT * FROM bsa", new selectPostgreSQLCommande(receiver,stmt));
		commands.put("SELECT * FROM bsa WHERE country = 'Australia' AND year = 1995", new selectPostgreSQLOneTupleCommande(receiver,stmt));
		commands.put("SELECT * FROM bsa WHERE country = 'Japon' AND year = 1995", new selectPostgreSQLOneTuple1Commande(receiver,stmt));
		commands.put("UPDATE bsa SET index = 30 WHERE country = 'Australia' AND year = 1995", new updatePostgreSQLOneTupleCommade(receiver,stmt));
		commands.put("UPDATE bsa SET index = 35 WHERE country = 'Australia' AND year = 1995", new updatePostgreSQLOneTupleReverseCommade(receiver,stmt));
		commands.put("d", new afficheTablePostgreSQLCommande(receiver,stmt));
		commands.put("DELETE FROM bsa WHERE country = 'Japon' AND year = 1995", new deletePostgreSQLCommande(receiver,stmt));
		commands.put("INSERT INTO bsa (region_id,country_id,country,year,value,index) VALUES (1,1,'Japon',1995,90,30)", new InsertPostgreSQLCommande(receiver,stmt));
		commands.put("QUIT", new quitCommand(receiver,conn));
		System.out.println("***Bienvenue sur la base Piratage***");
		System.out.println("Tapez SELECT * FROM [nom_de_table] pour faire un select sur une table");
		System.out.println("Tapez SELECT * FROM [nom_de_table] WHERE [attribut] = valeur AND [attribut] = valeur pour faire un select d'un tuple sur une table");
		System.out.println("Tapez UPDATE [nom_de_table] SET [attribut] = valeur WHERE [attribut] = valeur AND [attribut] = valeur pour faire un update d'un tuple sur une table");
		System.out.println("Tapez INSERT INTO [nom_de_table] (...) VALUES (...) pour insérer dans une table");
		System.out.println("Tapez DELETE FROM [nom_de_table] WHERE [attribut] = valeur AND [attribut] = valeur pour supprimer un tuple d'une table");
		System.out.println("Tapez d pour afficher le nom des tables");
		System.out.println("Tapez QUIT pour fermer la connexion et revenir a l'acceuil");
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
