package ter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class App {
	
	private static HashMap<String, Command> commands = new HashMap<>();
	private static Receiver receiver = new Receiver();
	
	public App() throws SQLException {
		String newLine = System.getProperty("line.separator");
		commands.put("PostgreSQL", new connecterCommande(receiver,"PostgreSQL"));
		commands.put("MongoDB", new connecterCommande(receiver,"MongoDB"));
		System.out.println("***Integration Database Application by OUMEZZAOUCHE Mohamed***");
		System.out.println("Veuillez vous connecter à l'une des bases en saissisant PostgreSQL ou MongoDB");
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
