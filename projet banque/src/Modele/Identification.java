package Modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Identification {
	
	private  StringProperty login= new SimpleStringProperty();
    private  StringProperty mdp =new SimpleStringProperty();
    private  StringProperty admin =new SimpleStringProperty();

public  Identification (String admin, String login,String mdp ) {
	   this.admin.set(admin);
	   this.login.set(login);
	   this.mdp.set(mdp);
   }


public StringProperty getLogin() {
	return login;
}

public StringProperty getMdp() {
	return mdp;
}

public void setMdp(StringProperty mdp) {
	this.mdp = mdp;
}

public StringProperty getAdmin() {
	return admin;
}

public void setAdmin(StringProperty admin) {
	this.admin = admin;
}

 void setLogin(StringProperty login) {
	this.login = login;
}

}
   