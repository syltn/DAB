package Utile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modele.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Client_dao {

// methode qui recupere et retourne une liste de client
	public ObservableList<Client> trouverTousClients() {

		ObservableList<Client> listClients = FXCollections.observableArrayList();
		Connection connect = Connectdb.initConnection();
		String sql;
		sql = "SELECT * FROM client" ;
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id_client=rs.getInt("id_client");
				String nomClient=rs.getString("nom");
				String prenomClient= rs.getString("prenom");
				String villeClient= rs.getString("ville");
				String adresseClient=rs.getString("adresse");
				String telephoneClient=rs.getString("telephone");
				String emailClient=rs.getString("email");
			

				Client unclient = new Client(id_client,nomClient,prenomClient,villeClient,adresseClient,telephoneClient,emailClient);
				listClients.add(unclient);
			}
		}catch ( Exception e) {
			System.err.println(e.getMessage ());
		}
		return listClients;
	}

//methode de mise a jour du client
	public void clientUpdate(Client leClient ) {
		Connection connect = Connectdb.initConnection();
		String sql;
		sql = "UPDATE client SET nom ='"+ leClient.getnom()+"', "
				+ "prenom='"+leClient.getprenom()+"',"
						+ "Ville='"+ leClient.getville()+"', "
								+ "Adresse= '"+ leClient.getadresse()+"',"
										+ "telephone= '"+ leClient.gettelephone()+"', "
												+ "email= '"+ leClient.getemail()+"'"
														+ "WHERE id_client = '"+leClient.getid_client()+"' ";
			try {
			Statement st = connect.createStatement();
			st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//methode de recherche du client
	public ObservableList<Client> resultatSearch(String nomSearch, String prenomSearch, String villeSearch) {

		ObservableList<Client> resultatlist = FXCollections.observableArrayList();
		Connection connect = Connectdb.initConnection();
		String sql;
		sql = "SELECT * FROM client WHERE nom like '%"+ nomSearch +"%'"
				+ " and prenom like '%"+ prenomSearch +"%'"
						+ "and ville like '%"+ villeSearch +"%'";
		try {
			Statement st = connect.createStatement();
 			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id_client=rs.getInt("id_client");
				String nomClient=rs.getString("nom");
				String prenomClient= rs.getString("prenom");
				String villeClient= rs.getString("ville");
				String adresseClient=rs.getString("adresse");
				String telephoneClient=rs.getString("telephone");
				String emailClient=rs.getString("email");

				Client unclient = new Client(id_client,nomClient,prenomClient,villeClient,adresseClient,telephoneClient,emailClient);
				resultatlist.add(unclient);
				}
		}catch ( Exception e) {
			System.err.println(e.getMessage ());
				e.printStackTrace();
		}
		return resultatlist;
}

//methode de creation du client
	public ObservableList<Client> clientcreat(Client unClient) {
	Connection connect = Connectdb.initConnection();
	String nomClient=unClient.getnom();
	String prenomClient= unClient.getprenom();
	String villeClient= unClient.getville();
	String adresseClient=unClient.getadresse();
	String emailClient=unClient.getemail();
	String telephoneClient=unClient.gettelephone();
	
	String sql;
	sql="INSERT INTO `banque`.`client` (`nom`, `prenom`, `ville`, `adresse`, `email`, `telephone`) VALUES ('"+nomClient+"', '"+prenomClient+"', '"+villeClient+"', '"+adresseClient+"', '"+emailClient+"', '"+telephoneClient+"')";
	try {
		Statement st = connect.createStatement();
		st.executeUpdate(sql);

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

//methode de suppression du client
	public ObservableList<Client> clientsuppr(Client unClient) {
Connection connect = Connectdb.initConnection();
int id_client=unClient.getid_client();

String sql;
sql= "DELETE FROM `banque`.`client` WHERE `client`.`id_client` = ('"+id_client+"')";
try {
	Statement st = connect.createStatement();
	st.executeUpdate(sql);

} catch (SQLException e) {
	e.printStackTrace();
}
return null;
}

//methode de recuperation d'id_compte du client
	public String resultatidCompteClient(String nomclient) {
			
			String resultatlist="";
			Connection connect = Connectdb.initConnection();
			String sql;
			sql = "SELECT num_compte FROM compte INNER JOIN client ON compte.id_client=client.id_client WHERE nom like '%"+ nomclient +"%'";
			try {
				Statement st = connect.createStatement();
	 			ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					int id_compte=rs.getInt("num_compte");
					resultatlist= resultatlist+","+String.valueOf(id_compte);
					}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
					e.printStackTrace();
			}
			return resultatlist;
	}

}

