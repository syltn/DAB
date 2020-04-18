package Modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.WritableDoubleValue;


public class Compte {
	private  IntegerProperty id_compte= new SimpleIntegerProperty();
	private  IntegerProperty id_client= new SimpleIntegerProperty();
    private  StringProperty date_creation =new SimpleStringProperty();
    private  DoubleProperty solde =new SimpleDoubleProperty();
    private  IntegerProperty limite_retrait=new SimpleIntegerProperty();
    private  IntegerProperty num_compte=new SimpleIntegerProperty();
    private  StringProperty nom =new SimpleStringProperty();
//    /**
//     * Default constructor.
//     */
//    public compte() {
//        this(null, null);
//    }
//
//    /**
//     * Constructor with some initial data.
//     *
//     * @param num_compte
//     * @param date_creation
//     */
//    public compte(Integer num_compte, date date_creation) {
//    	this.num_compte.set(num_compte);
//        this.date_creation.set(date_creation);
//      }


public Compte(int id_compte,String date_creation, String nom,  double solde, int limite_retrait, int num_compte) {
	this.id_compte.set(id_compte);
    //this.id_client.set(id_client);
    this.date_creation.set(date_creation);
    this.nom.set(nom);
    this.solde.set(solde);
    this.limite_retrait.set(limite_retrait);
    this.num_compte.set(num_compte);
    
	}

public Compte(int limite_retrait) {

    this.limite_retrait.set(limite_retrait);  
	}

public Compte() {
	// TODO Auto-generated constructor stub
}


public IntegerProperty getId_compte() {
	return id_compte;
}




public void setId_compte(IntegerProperty id_compte) {
	this.id_compte = id_compte;
}




public IntegerProperty getId_client() {
	return id_client;
}




public void setId_client(IntegerProperty id_client) {
	this.id_client = id_client;
}




public DoubleProperty getSolde() {
	return solde;
}




public void setSolde(DoubleProperty solde) {
	this.solde = solde;
}




public IntegerProperty getLimite_retrait() {
	return limite_retrait;
}




public void setLimite_retrait(IntegerProperty limite_retrait) {
	this.limite_retrait = limite_retrait;
}




public IntegerProperty getNum_compte() {
	return num_compte;
}




public void setNum_compte(IntegerProperty num_compte) {
	this.num_compte = num_compte;
}




public StringProperty getNom() {
	return nom;
}




public void setNom(StringProperty nom) {
	this.nom = nom;
}




public StringProperty getDate_creation() {
	return date_creation;
}




public void setDate_creation(SimpleStringProperty date_creation) {
	this.date_creation = date_creation;
}




}

