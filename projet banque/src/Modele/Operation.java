package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Operation {
	private  IntegerProperty id_operation= new SimpleIntegerProperty();
	private  IntegerProperty num_op= new SimpleIntegerProperty();
    private  StringProperty operation =new SimpleStringProperty();
    private  IntegerProperty montant=new SimpleIntegerProperty();
    //private  DateTimeProperty date_creation =new SimpleStringProperty();
	private  IntegerProperty id_compte= new SimpleIntegerProperty();



public Operation (int id_operation,int num_op, String operation, int montant, int id_compte){
	this.id_operation.set(id_operation);
	this.num_op.set(num_op);
	this.operation.set(operation);
	this.montant.set(montant);
	this.id_compte.set(id_compte);
	}



public IntegerProperty getId_operation() {
	return id_operation;
}


public void setId_operation(IntegerProperty id_operation) {
	this.id_operation = id_operation;
}


public IntegerProperty getNum_op() {
	return num_op;
}


public void setNum_op(IntegerProperty num_op) {
	this.num_op = num_op;
}


public StringProperty getoperation() {
	return operation;
}


public void setOperation(StringProperty operation) {
	this.operation = operation;
}


public IntegerProperty getMontant() {
	return montant;
}


public void setMontant(IntegerProperty montant) {
	this.montant = montant;
}


public IntegerProperty getId_compte() {
	return id_compte;
}


public void setId_compte(IntegerProperty id_compte) {
	this.id_compte = id_compte;
}
}