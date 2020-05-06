package Utile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modele.Compte;

public class Compte_dao {

//methode de recherche du compte
	public Compte resultatSearchnum(Integer num_search) {
			Connection connect = Connectdb.initConnection();
			Compte uncompte = new Compte();
			String sql;
			sql = "SELECT * FROM compte inner join client on compte.id_client=client.id_client where num_compte="+num_search;
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					int id_compte=rs.getInt("id_compte");
					//int id_client=rs.getInt("id_client");
					String nom=rs.getString("nom") ;
					//java.util.Date date_creation= rs.getDate("date_creation");
					String date_creation=rs.getString("date_creation");
					double solde= rs.getDouble("solde");
					int limite_retrait=rs.getInt("limite_retrait");
					int num_compte=rs.getInt("num_compte");


					uncompte = new Compte( id_compte, date_creation,  nom,  solde,  limite_retrait,  num_compte);
					//resultatlistCompte.add(uncompte);

					}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
					e.printStackTrace();

			}
			//return uncompte;
			return uncompte;
	}
		
//methode de mise a jour du retrait
	public void compteUpdate(int id_compte, int limite_retrait) {
			Connection connect = Connectdb.initConnection();
			String sql;
			sql = "UPDATE compte SET limite_retrait ='"+ limite_retrait+"' "
						+ "WHERE id_compte ='"+id_compte+"' ";
				try {
				Statement st = connect.createStatement();
				st.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

}
	