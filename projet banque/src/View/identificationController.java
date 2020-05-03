package View;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Application.Principal;
import Modele.Identification;
import Utile.Connectdb;
import Utile.connection_dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class identificationController {

	@FXML
	private TextField tf_login;
	@FXML
	private TextField tf_mdp;
	@FXML
	private Label tf_lblStatus;
	
	
	private Principal mainapp;

	public Principal getMainapp() {
		return getMainapp();
	}
	
	public void setMainapp(Principal mainapp) {
		this.mainapp = mainapp;
	}	
	
	Connection con = Connectdb.initConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	
	@FXML
	public void bt_logIn(ActionEvent event) {
		
		connection_dao pud = new connection_dao();
		String email = tf_login.getText().toString();
		String password = tf_mdp.getText().toString();
		System.out.println("Entre Login");
		String sqlLog;
		sqlLog = "SELECT * "
				+ "FROM utilisateur "
				+ "WHERE login = ? "
				+ "AND mdp = ? ";
		System.out.println("Avant Try");

		try {
			System.out.println("debut try");
			preparedStatement = con.prepareStatement(sqlLog);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);			
			System.out.println("try aprés password");
			resultSet = preparedStatement.executeQuery();
			System.out.println("fin try");

			if(email.isEmpty() || (password.isEmpty())) {
			//	tf_lblStatus.setText(Color.red);
				tf_lblStatus.setText("Field is Empty ");
				System.out.println("Empty");
				
				
			} else if(resultSet.next()) {
				//tf_lblStatus.setTextFill(Color.GREEN);
				tf_lblStatus.setText("Login Successful ... Redirecting");
				System.out.println("Login OK");
				
				//Identification log = new Identification(resultSet.getString("admin"),resultSet.getString("login"),resultSet.getString("mdp") );
				Principal toto = new Principal();
				toto.VueGlobal();		
				
			} else {
				//tf_lblStatus.setTextFill(Color.red);
				tf_lblStatus.setText("Enter Correct Email/Password");
				System.out.println("Incorrect");
			}

		} catch (Exception e) {
			System.out.println("Interieur Catch");
			
		}
	
	}
	

@FXML
private void bt_Cancel () {

	tf_login.clear();
	tf_mdp.clear();

}
}