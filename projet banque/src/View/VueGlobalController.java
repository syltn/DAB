package View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Application.Principal;
import Modele.Client;
import Modele.Compte;
import Modele.Operation;
import Utile.Client_dao;
import Utile.Compte_dao;
import Utile.Connectdb;
import Utile.Operation_dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class VueGlobalController {
    private static final TableView<Client> CompteTable = null;
	@FXML
    private TableView<Client> ClientTable;
    @FXML
    private TableColumn<Client, String> nomcolumn;
    @FXML
    private TableColumn<Client, String> prenomcolumn;
    @FXML
    private TableColumn<Client, String> villecolumn;
    @FXML
    private TableColumn<Client, String> adressecolumn;

    //declaration des champs d'affichage page client
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_ville;
    @FXML
    private TextField tf_telephone;
    @FXML
    private TextField tf_id_client;

    //declaration des champ de recherche page client
    @FXML
    private TextField tf_s_nom;
    @FXML
    private TextField tf_s_prenom;
    @FXML
    private TextField tf_s_ville;

    //declaration des champ de recherche page compte
    @FXML
    private TextField tf_s_num;
    // declaration des champ d'affichage du compte
    @FXML
    private TextField tf_s_num_compte;
    @FXML
    private TextField tf_s_nom_compte;
    @FXML
    private TextField tf_s_date_creation;
   @FXML
    private TextField tf_s_solde;
    @FXML
    private TextField tf_s_limite_retrait;
    @FXML
    private TextField tf_s_id_compte;

    @FXML
    private TableView<Operation>OperationTable;
    @FXML
    private TableColumn<Operation, String> numerocomptecolumn;
    @FXML
    private TableColumn<Operation, String> numerooperationcolumn;
    @FXML
    private TableColumn<Operation, String> typecolumn;
    @FXML
    private TableColumn<Operation, String> montantcolumn;
    @FXML
    private TableColumn<Operation, String> datecreationcolumn;



    @FXML
    private TextField tf_id_operation;
    @FXML
    private TextField tf_num_op;
    @FXML
    private TextField tf_operation;
    @FXML
    private TextField tf_montant;
    @FXML
    private TextField tf_date;
 
    @FXML 
    private TextField tf_resultatidCompteClient;

 

    // Reference to the main application.
    private Principal mainapp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public VueGlobalController() {
    }
 
    public Principal getMainapp() {
    	return mainapp;
    }


    public void setMainapp(Principal mainapp) {
    	this.mainapp = mainapp;
    }


//     Initializes the controller class. This method is automatically called
//      after the fxml file has been loaded.

    @FXML
   private void initialize() {
        // Initialize the person table with the four columns.

        nomcolumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomcolumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        villecolumn.setCellValueFactory(cellData -> cellData.getValue().villeProperty());
        adressecolumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());


        // Clear person details.
        ClientDetail(null);

        // Listen for selection changes and show the person details when changed.
        ClientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> ClientDetail(newValue));
        initializeop();
    }


//Is called by the main application to give a reference back to itself.



// appele de la methode  + affichage des client present en BDD
	private void ClientDetail(Client person) {
	    if (person != null) {
	        // Fill the labels with info from the person object.
	        tf_nom.setText(person.getnom());
	        tf_prenom.setText(person.getprenom());
	        tf_adresse.setText(person.getadresse());
	        tf_telephone.setText((person.gettelephone()));
	        tf_ville.setText(person.getville());
	        tf_email.setText(person.getemail());
	        //tf_id_client.setText(Integer.toString(person.getid_client()));
	        tf_resultatidCompteClient.setText(resultatidCompteClient(person.getnom()));

	       } else {
	        // Person is null, remove all the text.
	    	tf_nom.setText("");
	    	tf_prenom.setText("");
	    	tf_adresse.setText("");
	        tf_telephone.setText("");
	        tf_ville.setText("");
	        tf_email.setText("");
	        //tf_id_client.setText("");
	        tf_resultatidCompteClient.setText("");

	    }
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
			int nb=0;
			while (rs.next()) {
				int id_compte=rs.getInt("num_compte");

				if (nb==0) {
					resultatlist= resultatlist+String.valueOf(id_compte);
				}
				else {
					resultatlist= resultatlist+", "+String.valueOf(id_compte);

				}
				nb++;
				}
		}catch ( Exception e) {
			System.err.println(e.getMessage ());
				e.printStackTrace();
		}
		return resultatlist;
}

	// appele de la methode  + attribution au bouton de mise a jour du client
	@FXML
	private void bt_updateClient () {

	    int selectedIndex = ClientTable.getSelectionModel().getSelectedIndex();
	    Client selectedClient = ClientTable.getSelectionModel().getSelectedItem();
	    if (selectedIndex >= 0) {
	    	

			int id=ClientTable.getSelectionModel().getSelectedItem().getid_client();
			String nomClient=tf_nom.getText();
			String prenomClient=tf_prenom.getText();
			String villeClient=tf_ville.getText();
			String adresseClient=tf_adresse.getText();
			String telephoneClient=tf_telephone.getText();
			String emailClient=tf_email.getText();
//rajout id si besoin de test
			Client unclient = new Client(id,nomClient,prenomClient,villeClient,adresseClient,telephoneClient,emailClient);

			/**
			 * appel de la methode clientUpdate de la classe Client_dao
			 */

			Client_dao cldao = new Client_dao();
			cldao.clientUpdate(unclient); //Maj dans la BDD
			ClientTable.setItems(mainapp.getClientData());//mise a jour de l'affichage
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainapp.getPrimaryStage());
	        alert.setTitle("Aucune selection");
	        alert.setHeaderText("Pas de client selectionn�(e)");
	        alert.setContentText("Merci de selectionn�(e) un(e) client(e) dans la liste.");

	        alert.showAndWait();


	    }


	}

// appele de la methode  + attribution au bouton de recherche du client
	@FXML
	private void bt_searchPerson () {

	    	ObservableList<Client> searchlist = FXCollections.observableArrayList();
	    	String nomS = tf_s_nom.getText();
	    	String prenomS = tf_s_prenom.getText();
	    	String villeS = tf_s_ville.getText();
	    	Client_dao cldao = new Client_dao();
	    	searchlist = cldao.resultatSearch(nomS, prenomS, villeS);
	    	ClientTable.setItems(searchlist);
	    	tf_s_nom.clear();
	    	tf_s_prenom.clear();
	    	tf_s_ville.clear();

	    }

// appele de la methode  + attribution au bouton de creation du client
	@FXML
	private void handleNewPerson() {

	    int selectedIndex = ClientTable.getSelectionModel().getSelectedIndex();
	    
	    
		if (tf_nom .getLength()!= 0	&& tf_prenom.getLength()!=0 && tf_ville.getLength()!=0	) {
		 	String nomClient=tf_nom.getText();
			String prenomClient=tf_prenom.getText();
			String villeClient=tf_ville.getText();
			String adresseClient=tf_adresse.getText();
			String telephoneClient=tf_telephone.getText();
			String emailClient=tf_email.getText();

			Client unClient = new Client();
			unClient.setnom(nomClient);
			unClient.setprenom(prenomClient);
			unClient.setville(villeClient);
			unClient.setadresse(adresseClient);
			unClient.settelephone(telephoneClient);
			unClient.setemail(emailClient);

			Client_dao cldao = new Client_dao();
			cldao.clientcreat(unClient);

			ClientTable.setItems(mainapp.getClientData());//mise a jour de l'affichage
		}
		else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainapp.getPrimaryStage());
	        alert.setTitle("Champ requis non remplis");
	        alert.setHeaderText("Pas d'information saisie");
	        alert.setContentText("Merci de remplir les champs requis (Nom, Prenom, Ville) afin de creer un nouveau client(e).");
	        alert.showAndWait();

	    }
	}

// creation du bouton supprimer du client
		@FXML
	private void bt_suppr () {

		    int selectedIndex = ClientTable.getSelectionModel().getSelectedIndex();
		    Client selectedClient = ClientTable.getSelectionModel().getSelectedItem();

System.out.println(selectedIndex);
				//int idClient = ClientTable.getSelectionModel().getSelectedItem().getid_client();

		    if (selectedIndex >= 0) {

		    	int id=ClientTable.getSelectionModel().getSelectedItem().getid_client();
		    	String nomClient=tf_nom.getText();
		    	String prenomClient=tf_prenom.getText();
		    	String villeClient=tf_ville.getText();
		    	String adresseClient=tf_adresse.getText();
		    	String telephoneClient=tf_telephone.getText();
		    	String emailClient=tf_email.getText();
//rajout id si besoin de test
		    	Client unclient = new Client(id, nomClient,prenomClient,villeClient,adresseClient,telephoneClient,emailClient);


		    	Client_dao cldao = new Client_dao();
		    	cldao.clientsuppr(unclient); //Maj dans la BDD
		    	ClientTable.setItems(mainapp.getClientData());//mise a jour de l'affichage

		    } else {
		        // Nothing selected.
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(mainapp.getPrimaryStage());
		        alert.setTitle("Aucune selection");
		        alert.setHeaderText("Pas de client selectionn�(e)");
		        alert.setContentText("Merci de selectionn�(e) un(e) client(e) dans la liste.");

		        alert.showAndWait();


		    }
		}


// appele de la methode  + attribution au bouton de recherche du compte
		@FXML
	private void bt_searchCount () {
				String temp = tf_s_num.getText();
				boolean isDisabled= temp.isEmpty();
				if (!isDisabled) {
					int numS = Integer.parseInt(temp);

			     	Compte_dao cldao = new Compte_dao();
			    	Compte compteTr = cldao.resultatSearchnum(numS);
			    	tf_s_nom_compte.textProperty().bind(compteTr.getNom());
			    	tf_s_solde.textProperty().bind(compteTr.getSolde().asString());
			    	String tempo = Integer.toString(compteTr.getLimite_retrait());
			    	tf_s_limite_retrait.setText(tempo);
			    	tf_s_date_creation.textProperty().bind(compteTr.getDate_creation());
			    	String tempo2 = Integer.toString(compteTr.getId_compte());
			    	System.out.println("tempo2 : "+ tempo2);
			    	tf_s_limite_retrait.setText(tempo);
			    	tf_s_id_compte.setText(tempo2);

		    	}else {

		    		Alert alert = new Alert(AlertType.WARNING);
			        alert.initOwner(mainapp.getPrimaryStage());
			        alert.setTitle("Erreur de selection");
			        alert.setHeaderText("Erreur de recherche");
			        alert.setContentText("Merci de rentr�e un numero de compte.");


			        alert.showAndWait();
		    	}
		}
		
		
// appele de la methode  + attribution au bouton de mise a jour du compte solde retrait
		@FXML
		private void bt_updateCompte () {

		if (tf_s_limite_retrait != null) {
			
			int limite_retrait = Integer.parseInt(tf_s_limite_retrait.getText());
			int id_compte = Integer.parseInt(tf_s_id_compte.getText());
			Compte_dao cldao = new Compte_dao();
			cldao.compteUpdate(id_compte,limite_retrait); //Maj dans la BDD num compte + nouvelle limite 
			
			tf_s_num_compte.setText("");
			tf_s_nom_compte.clear();
			tf_s_date_creation.clear();
			tf_s_solde.clear();
			
		} else{
		    // Nothing selected.
		    Alert alert = new Alert(AlertType.WARNING);
		    alert.initOwner(mainapp.getPrimaryStage());
		    alert.setTitle("Aucune selection");
		    alert.setHeaderText("Pas de compte affich�");
		    alert.setContentText("Merci de recherch�(e) un compte client(e).");

		    alert.showAndWait();
			}
		}

		// appele de la methode  + attribution au bouton de recherche des operations
		@FXML
		private void bt_searchOpe () {
			
			String temp = tf_s_num_compte.getText();
			boolean isDisabled= temp.isEmpty();
			if (!isDisabled) {
				
		    	ObservableList<Operation> searchlistope = FXCollections.observableArrayList();
		    	int numS = Integer.parseInt(tf_s_num_compte.getText());
		    	Operation_dao cldao = new Operation_dao();
		    	searchlistope = cldao.resultatSearchOpe(numS);
		    	OperationTable.setItems(searchlistope);
		    	tf_s_num_compte.clear();
			}else {

	    		Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(mainapp.getPrimaryStage());
		        alert.setTitle("Erreur de selection");
		        alert.setHeaderText("Erreur de recherche");
		        alert.setContentText("Merci de rentr�e un numero de compte.");


		        alert.showAndWait();
	    	}
		}

	    @FXML
	    private void initializeop() {


	    	numerocomptecolumn.setCellValueFactory(cellData -> cellData.getValue().numerocompteProperty());
	    	numerooperationcolumn.setCellValueFactory(cellData -> cellData.getValue().numerooperationProperty());
	    	typecolumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
	    	montantcolumn.setCellValueFactory(cellData -> cellData.getValue().montantProperty());
	    	datecreationcolumn.setCellValueFactory(cellData -> cellData.getValue().datecreationProperty());
		

}

}





