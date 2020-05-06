package Utile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Modele.Compte;
import Modele.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Operation_dao {

//methode de recherche d'operation
	public ObservableList<Operation> resultatSearchOpe(int num_compte_search ) {

		ObservableList<Operation> resultatlistop = FXCollections.observableArrayList();
		Connection connect = Connectdb.initConnection();
		String sql;
		sql = "SELECT * FROM operation INNER JOIN compte on operation.id_compte=compte.id_compte WHERE num_compte="+num_compte_search;
		
		try {
			Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id_operation=rs.getInt("id_operation");
				int num_op=rs.getInt("num_op");
				String operation= rs.getString("operation");
				int montant= rs.getInt("montant");
				int id_compte=rs.getInt("id_compte");
				int num_compte=rs.getInt("num_compte");
				String date_creation=rs.getString("date");
				

				Operation desOpe = new Operation( id_operation, num_op,  operation,  montant,  id_compte, num_compte, date_creation);
				resultatlistop.add(desOpe);

				}
		}catch ( Exception e) {
			System.err.println(e.getMessage ());
				e.printStackTrace();
		}
		return resultatlistop;
	}

}
	