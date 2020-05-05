package Utile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Modele.Client;
import Modele.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Stats_dao {

//methode de recherche global
	public String resultatStat(String lf_annee, String lf_mois, String lf_ville) {
		Connection connect = Connectdb.initConnection();
		String sql;
				sql =  "SELECT sum(montant) "
					+"FROM operation"
					+"where year (date) = '"+lf_annee+"'"
						+"and month (date) = '"+lf_mois+"'"
						+"and Ville = '"+lf_ville+"'and operation = 'credit'";
				try {
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {
						int lf_stat_result=rs.getInt(sql);
						
					}
				}catch ( Exception e) {
					System.err.println(e.getMessage ());
						e.printStackTrace();
				}
				//return uncompte;
				return sql;		
	}

////methode de recherche annee
//		public Compte resultatStatAnnee(String lf_annee) {
//			Connection connect = Connectdb.initConnection();
//			String sql;
//					sql =  "SELECT date_format(date, \"%Y\") \r\n" + 
//							"FROM banque.operation\r\n" + 
//							"Group By date_format(date, \"%Y\")";
//					try {
//						Statement st = connect.createStatement();
//						ResultSet rs = st.executeQuery(sql);
//						while (rs.next()) {
//							int id_compte=rs.getInt("id_compte");
//							int limite_retrait=rs.getInt("limite_retrait");
//							int num_compte=rs.getInt("num_compte");
//							}
//					}catch ( Exception e) {
//						System.err.println(e.getMessage ());
//							e.printStackTrace();
//					}
//					//return uncompte;
//					return resultatStatAnnee(lf_annee);		
//		}

////methode de recherche mois
//		public Compte resultatStatMois(String lf_mois) {
//			Connection connect = Connectdb.initConnection();
//			String sql;
//					sql =  "SELECT date_format(date, \"%M\")" + 
//							"FROM banque.operation" + 
//							"Group By date_format (date, \"%M\"), date_format (date, \"%m\")" + 
//							"order by date_format (date, \"%m\")";
//					try {
//						Statement st = connect.createStatement();
//						ResultSet rs = st.executeQuery(sql);
//						while (rs.next()) {
//							int id_compte=rs.getInt("id_compte");
//							int limite_retrait=rs.getInt("limite_retrait");
//							int num_compte=rs.getInt("num_compte");
//
//							}
//					}catch ( Exception e) {
//						System.err.println(e.getMessage ());
//							e.printStackTrace();
//					}
//					//return uncompte;
//					return resultatStatMois(lf_mois);		
//		}		
		
////methode de recherche ville
//		public Compte resultatStatVille(String lf_ville) {
//					Connection connect = Connectdb.initConnection();
//					String sql;
//							sql = "SELECT ville \r\n" + 
//									"		FROM operation\r\n" + 
//									"		INNER JOIN compte ON operation.id_compte=compte.id_compte" + 
//									"		INNER JOIN client ON compte.id_client=client.id_client" + 
//									"        group by ville" + 
//									"        order by ville";
//							try {
//								Statement st = connect.createStatement();
//								ResultSet rs = st.executeQuery(sql);
//								while (rs.next()) {
//									String resultatStatVille=rs.getString("lf_ville");
//									}
//							}catch ( Exception e) {
//								System.err.println(e.getMessage ());
//									e.printStackTrace();
//							}
//							//return uncompte;
//							return resultatStatVille(lf_ville);		
//				}
		
		
		
		
		
		// methode qui recupere et retourne une liste d'année
		public ObservableList<String> resultatStatAnnee() {

			ObservableList<String> resultatStatAnnee = FXCollections.observableArrayList();
			Connection connect = Connectdb.initConnection();
			String sql;
			sql =   "SELECT date_format(date, \"%Y\")as date " + 
					"FROM banque.operation" + 
					"Group By date_format(date, \"%Y\")";
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
		
					resultatStatAnnee.add(rs.getString("date"));
				}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
			}
			return resultatStatAnnee;
		}
	
		// methode qui recupere et retourne une liste de mois
		public ObservableList<String> resultatStatMois() {

			ObservableList<String> resultatStatMois = FXCollections.observableArrayList();
			Connection connect = Connectdb.initConnection();
			String sql;
			sql =   "SELECT date_format(date, \"%M\")" + 
					"FROM banque.operation" + 
					"Group By date_format (date, \"%M\"), date_format (date, \"%m\")" + 
					"order by date_format (date, \"%m\")";
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
		
					resultatStatMois.add(rs.getString("mois"));
				}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
			}
			return resultatStatMois;
		}

		// methode qui recupere et retourne une liste de ville
		public ObservableList<String> resultatStatVille() {

			ObservableList<String> resultatStatVille = FXCollections.observableArrayList();
			Connection connect = Connectdb.initConnection();
			String sql;
			sql =   "SELECT ville \r\n" + 
					"		FROM operation\r\n" + 
					"		INNER JOIN compte ON operation.id_compte=compte.id_compte" + 
					"		INNER JOIN client ON compte.id_client=client.id_client" + 
					"        group by ville" + 
					"        order by ville";
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
		
					resultatStatVille.add(rs.getString("mois"));
				}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
			}
			return resultatStatVille;
		}
		
}
