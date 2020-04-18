package Application;

import java.io.IOException;
import Modele.Client;
import Utile.Client_dao;
import View.VueGlobalController;
import View.identificationController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Principal extends Application {

    private Stage primaryStage;
    
    private BorderPane rootLayout;
    
    private ObservableList<Client> ClientData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
       initRootLayout();
       identification();

       
       

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
    
    public boolean identification() {
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
		return false;
    }

//    public boolean VueGlobal() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Principal.class.getResource("/View/VueGlobal.fxml"));
//            AnchorPane VueGlobal = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(VueGlobal);
//
//            // Give the controller access to the main app.
//            VueGlobalController controller = loader.getController();
//           controller.setMainapp(this);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		return false;
//    }

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

}
