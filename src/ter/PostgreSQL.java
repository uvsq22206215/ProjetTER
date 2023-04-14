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
		commands.put("QUIT", new quitCommand(receiver,conn));
		System.out.println("***Bienvenue sur la base Piratage***");
		System.out.println("Tapez SELECT * FROM [nom_de_table] pour faire un select sur une table");
		System.out.println("Tapez QUIT pour fermer la connection et revenir à l'acceuil");
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
