package app.models;

import javafx.beans.property.SimpleStringProperty;



public class App {

	private SimpleStringProperty bezeichnung;
	private SimpleStringProperty kategorie;
	private SimpleStringProperty startFile;
	
	public App(String bezeichnung, String kategorie, String startFile) {
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
		this.kategorie = new SimpleStringProperty(kategorie);
		this.startFile = new SimpleStringProperty(startFile);
	} 
	
	// Getter
	public String getBezeichnung() {return bezeichnung.get();}
	public String getKategorie() {return kategorie.get();}
	public String getStartFile() {return startFile.get();}
	
	// Setter
	public void setBezeichnung(String bezeichnung) {this.bezeichnung.set(bezeichnung);}
	public void setKategorie(String kategorie) {this.kategorie.set(kategorie);}
	public void setStartFile(String startFile) {this.kategorie.set(startFile);}
	
}
