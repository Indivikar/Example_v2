package app.example.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleTraktandum {

	private SimpleIntegerProperty id_Traktandum;
//	private SimpleStringProperty datenbank;
	private SimpleIntegerProperty id_register;
	private SimpleStringProperty signatur;
	private SimpleBooleanProperty immerAnzeigen;
	private SimpleStringProperty traktandum;


	public TabelleTraktandum(int id_Traktandum, int id_register, String signatur, boolean immerAnzeigen, String traktandum) {
		this.id_Traktandum = new SimpleIntegerProperty(id_Traktandum);
//		this.datenbank = new SimpleStringProperty(datenbank);
		this.id_register = new SimpleIntegerProperty(id_register);
		this.signatur = new SimpleStringProperty(signatur);
		this.immerAnzeigen = new SimpleBooleanProperty(immerAnzeigen);
		this.traktandum = new SimpleStringProperty(traktandum);

	}

    public ReadOnlyIntegerProperty idTraktandumProperty() {return id_Traktandum;}
//    public ReadOnlyStringProperty idDatenbankProperty() {return datenbank;}
    public ReadOnlyIntegerProperty registerProperty() {return id_register;}
    public ReadOnlyStringProperty signaturProperty() {return signatur;}
    public ReadOnlyBooleanProperty immerAnzeigenProperty() {return immerAnzeigen;}
    public ReadOnlyStringProperty traktandumVonProperty() {return traktandum;}


	// Getter
	public int getIDTraktandum(){ return id_Traktandum.get();	}
//	public String getDatenbank(){ return datenbank.get();}
	public int getIDRegister(){ return id_register.get();}
	public String getSignatur(){ return signatur.get();	}
	public boolean isImmerAnzeigen(){ return immerAnzeigen.get();	}
	public String getTraktandum(){ return traktandum.get();}


	// Setter
	public void setIDTraktandum(int id_Traktandum){ this.id_Traktandum.set(id_Traktandum); }
//	public void setDatenbank(String datenbank){ this.datenbank.set(datenbank); }
	public void setIDRegister(int id_register){ this.id_register.set(id_register); }
	public void setSignatur(String signatur){ this.signatur.set(signatur); }
	public void setImmerAnzeigen(boolean immerAnzeigen){ this.immerAnzeigen.set(immerAnzeigen); }
	public void setTraktandum(String traktandum){ this.traktandum.set(traktandum); }



}
