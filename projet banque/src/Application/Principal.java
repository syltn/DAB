package Application;

import java.io.IOException;
import Modele.Client;
import Modele.Identification;
import Utile.Client_dao;
import View.VueGlobalController;
import View.identificationController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Principal extends Application {

	private Identification log;
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Client> ClientData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Authentification");
       initRootLayout();
       identification();
       VueGlobal();

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("/View/Layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void identification() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
  
            loader.setLocation(Principal.class.getResource("/View/identification.fxml"));
            AnchorPane identification = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(identification);

            // Give the controller access to the main app.
            identificationController controller = (identificationController) loader.getController();
           controller.setMainapp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
	
    }

    public  void VueGlobal() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("/View/VueGlobal.fxml"));
            AnchorPane VueGlobal = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(VueGlobal);

            // Give the controller access to the main app.
            VueGlobalController controller = loader.getController();
           controller.setMainapp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Client>  getClientData() {
    	ClientData.clear();
    	Client_dao cldao = new Client_dao();
     	ClientData.addAll(cldao.trouverTousClients());

        return ClientData;
    }
    
    
    public void showVueGlobalController(Identification log){
    	
    	System.out.println("AFFICHE MOI ça Avant Try");
    	
    try {
    	
    	System.out.println("AFFICHE MOI 'ShowMainOverview'");	
    	
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/view/VueGlobal.fxml"));
        AnchorPane showVueGlobalController = (AnchorPane) loader.load();
       
        VueGlobalController controller = loader.getController();
        // Set person overview into the center of root layout.
    	controller.setMainapp(this);
    	this.setLog(log);
    	
    	
        rootLayout.setCenter(showVueGlobalController);
  	
  		} catch (IOException e) {
    		e.printStackTrace();
    	
    		
    	} 
    }

     
    
	public Identification getLog() {
		return log;
	}

	public void setLog(Identification log) {
		this.log = log;
	}
}
