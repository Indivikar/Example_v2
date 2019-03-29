package app.example.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleProtokolle {

	private SimpleStringProperty id;
	private SimpleStringProperty zeitVon;
	private SimpleStringProperty zeitBis;
	private SimpleStringProperty ort;
	private SimpleStringProperty path;

	public TabelleProtokolle(String id, String zeitVon, String zeitBis, String ort, String path) {
		this.id = new SimpleStringProperty(id);
		this.zeitVon = new SimpleStringProperty(zeitVon);
		this.zeitBis = new SimpleStringProperty(zeitBis);
		this.ort = new SimpleStringProperty(ort);
		this.path = new SimpleStringProperty(path);
	}

    public ReadOnlyStringProperty idProperty() {return id;}
    public ReadOnlyStringProperty zeitVonProperty() {return zeitVon;}
    public ReadOnlyStringProperty zeitBisProperty() {return zeitBis;}
    public ReadOnlyStringProperty ortProperty() {return ort;}
    public ReadOnlyStringProperty pathProperty() {return path;}

	// Getter
	public String getID(){ return id.get();	}
	public String getZeitVon(){ return zeitVon.get();}
	public String getZeitBis(){ return zeitBis.get();}
	public String getOrt(){ return ort.get();	}
	public String getPath(){ return path.get();	}

	// Setter
	public void setID(String id){ this.id.set(id); }
	public void setZeitVon(String zeitVon){ this.zeitVon.set(zeitVon); }
	public void setZeitBis(String zeitBis){ this.zeitBis.set(zeitBis); }
	public void setOrt(String ort){ this.ort.set(ort); }
	public void setPath(String path){ this.path.set(path); }

}
