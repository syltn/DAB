package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	private  IntegerProperty id_client= new SimpleIntegerProperty();
    private  StringProperty nom =new SimpleStringProperty();
    private  StringProperty prenom =new SimpleStringProperty();
    private  StringProperty adresse =new SimpleStringProperty();
    private  StringProperty ville=new SimpleStringProperty();
    private  StringProperty telephone=new SimpleStringProperty();
    private  StringProperty email=new SimpleStringProperty();
    public Client() {
        this(null, null);
    }

    public Client(String Nom, String Prenom) {
    	this.nom.set(Nom);
        this.prenom.set(Prenom);
    }




public Client(int id_client, String nom, String prenom, String ville, String adresse, String telephone, String email) {
	this.nom.set(nom);
    this.prenom.set(prenom);
    this.ville.set(ville);
    this.adresse.set(adresse);
    this.telephone.set(telephone);
    this.email.set(email);
    this.id_client.set(id_client);
	}

public String getnom() {
    return nom.get();
}

public void setnom(String nom) {
    this.nom.set(nom);
}

public StringProperty nomProperty() {
    return nom;
}

public String getprenom() {
    return prenom.get();
}

public void setprenom(String prenom) {
    this.prenom.set(prenom);
}

public StringProperty prenomProperty() {
    return prenom;
}

public String getadresse() {
    return adresse.get();
}

public void setadresse(String adresse) {
    this.adresse.set(adresse);
}

public StringProperty adresseProperty() {
    return adresse;
}

public String getville() {
    return ville.get();
}

public void setville(String ville) {
    this.ville.set(ville);
}

public StringProperty villeProperty() {
    return ville;
}

public String gettelephone() {
    return telephone.get();
}

public void settelephone(String telephone) {
    this.telephone.set(telephone);
}

public StringProperty telephoneProperty() {
    return telephone;
}

public String getemail() {
    return email.get();
}

public void setemail(String email) {
    this.email.set(email);
}

public StringProperty emailProperty() {
    return email;
}

public Integer getid_client() {
    return id_client.get();
}

public void setid_client(Integer id_client) {
    this.id_client.set(id_client);
}

public IntegerProperty Aid_clientProperty() {
    return id_client;
}

}


