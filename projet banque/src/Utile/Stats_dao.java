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
	public int calculresultatStat(String lf_annee, String lf_mois, String lf_ville) {
		int lf_stat_result = 0;
		Connection connect = Connectdb.initConnection();
		String annee = lf_annee;
		String mois = lf_mois;
		String ville = lf_ville;
		String sql =  null ;
				//sql=  
		//"SELECT sum(montant) FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client where year (date) = '2020' and month (date) = '04' and operation = 'credit' and Ville = 'Lannemezan'";
//					"SELECT sum(montant) "
//					+"FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client"
//					+" where operation = 'credit' "
//						+"and year (date) = '"+lf_annee+"'"
//						+"and month (date) = '"+lf_mois+"'"
//						+" and Ville = '"+lf_ville+"'";
						
						if (!annee.isEmpty()){
				    if (!mois.isEmpty()){
				        if (!ville.isEmpty()){
				            sql = "SELECT sum(montant) "
									+"FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client where year (date) = '"+lf_annee+"' and month (date) = '"+lf_mois+"' and Ville = '"+lf_ville+"' and operation = 'credit'";
				        }
				        else{
				            sql = "SELECT sum(montant) "
									+"FROM operation where year (date) = '"+lf_annee+"' and month (date) = '"+lf_mois+"' and operation = 'credit'";
				        }
				    }
				    else if (!ville.isEmpty()){
				        sql = "SELECT sum(montant) "
									+"FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client where year (date) = '"+lf_annee+"' and Ville = '"+lf_ville+"' and operation = 'credit'";
				    }
				    else {
				        sql = "SELECT sum(montant) "
									+"FROM operation where year (date) = '"+lf_annee+"' and operation = 'credit'";
				    }
				}
				else if (!ville.isEmpty()){
				    sql = "SELECT sum(montant) "
									+"FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client where Ville = '"+lf_ville+"' and operation = 'credit'";
				}
						
				try {
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {
						 lf_stat_result=rs.getInt("sum(montant)");
						
					}
				}catch ( Exception e) {
					System.err.println(e.getMessage ());
						e.printStackTrace();
				}
				//return uncompte;
				return lf_stat_result;		
	}


		
		// methode qui recupere et retourne une liste d'année
		public ObservableList<String> resultatStatAnnee() {

			ObservableList<String> resultatStatAnnee = FXCollections.observableArrayList();
			Connection connect = Connectdb.initConnection();
			String sql;
			sql =   "SELECT date_format(date, '%Y') as date FROM banque.operation Group By date_format(date, '%Y')";
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					System.out.println("année : " + rs.getString("date"));
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
			sql =   "SET lc_time_names = 'fr_FR'; SELECT date_format (date, '%m') FROM banque.operation Group By date_format (date, '%m'), date_format (date, '%m') order by date_format (date, '%m')";
		//	sql =   "SET lc_time_names = 'fr_FR'; SELECT date_format (date, '%M') FROM banque.operation Group By date_format (date, '%M'), date_format (date, '%m') order by date_format (date, '%m')";

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
			sql =   "SELECT ville" + 
					"FROM operation INNER JOIN compte ON operation.id_compte=compte.id_compte INNER JOIN client ON compte.id_client=client.id_client" + 
					"group by ville order by ville";
			try {
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
		
					resultatStatVille.add(rs.getString("Ville"));
				}
			}catch ( Exception e) {
				System.err.println(e.getMessage ());
			}
			return resultatStatVille;
		}
		
}
