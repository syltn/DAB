package Utile;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectdb {
	private static Connection connect = null;
	//private String url = "jdbc:mysql://127.0.0.1:3306/banque";
	private String url = "jdbc:mysql://brash.duckdns.org:443/banque";
	//private String user = "adrar";
	private String user = "syltar";
	// ne pas utiliser le compte root, desactvation par default
	//private String passwd = "toor";
	private String passwd = "oFr5Z(PTRP3#lzC";
	
	

	//le constructeur ci-dessous creer l'objet connect de type connections
	public Connectdb () {
		try {
			// verif
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("drivers ok");
			//System.out.println(url);
			connect = DriverManager.getConnection(url, user, passwd);
			//System.out.println("connection ok");
			} catch (Exception e) {
				System.out.println("Echec connexion dans CATCH");
				e.printStackTrace();
				//System.err.println(e.getMessage());
			}
	}
	
	//methode de type static pour ne pas avoir a creer un objet ( une instance avec new).
		public static Connection initConnection() {
			if (connect==null) {new Connectdb() ;}

			return connect;

		}
	}


