package app.examples.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleEinladungen {

	private SimpleStringProperty id;
	private SimpleStringProperty zeit;
	private SimpleStringProperty ort;
	private SimpleStringProperty path;

	public TabelleEinladungen(String id, String zeit, String ort, String path) {
		this.id = new SimpleStringProperty(id);
		this.zeit = new SimpleStringProperty(zeit);
		this.ort = new SimpleStringProperty(ort);
		this.path = new SimpleStringProperty(path);
	}

    public ReadOnlyStringProperty idProperty() {return id;}
    public ReadOnlyStringProperty zeitProperty() {return zeit;}
    public ReadOnlyStringProperty ortProperty() {return ort;}
    public ReadOnlyStringProperty pathProperty() {return path;}

	// Getter
	public String getID(){ return id.get();	}
	public String getZeit(){ return zeit.get();	}
	public String getOrt(){ return ort.get();	}
	public String getPath(){ return path.get();	}

	// Setter
	public void setID(String id){ this.id.set(id); }
	public void setZeit(String zeit){ this.zeit.set(zeit); }
	public void setOrt(String ort){ this.ort.set(ort); }
	public void setPath(String path){ this.path.set(path); }

}
