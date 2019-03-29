package application.module.db.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleTraktanden {

	private SimpleStringProperty id;
	private SimpleStringProperty id_Traktandum;
	private SimpleStringProperty id_Sitzung;
	private SimpleIntegerProperty position;

	public TabelleTraktanden(String id, String id_Traktandum, String id_Sitzung, String position) {
		this.id = new SimpleStringProperty(id);
		this.id_Traktandum = new SimpleStringProperty(id_Traktandum);
		this.id_Sitzung = new SimpleStringProperty(id_Sitzung);
		this.position = new SimpleIntegerProperty(Integer.parseInt(position));
	}

    public ReadOnlyStringProperty idProperty() {return id;}
    public ReadOnlyStringProperty idTraktandumProperty() {return id_Traktandum;}
    public ReadOnlyStringProperty idSitzungProperty() {return id_Sitzung;}
    public ReadOnlyIntegerProperty positionProperty() {return position;}

	// Getter
	public String getID(){ return id.get();	}
	public String getIDTraktandum(){ return id_Traktandum.get();}
	public String getIDSitzung(){ return id_Sitzung.get();}
	public int getPosition(){ return position.get();	}

	// Setter
	public void setID(String id){ this.id.set(id); }
	public void setIDTraktandum(String id_Traktandum){ this.id_Traktandum.set(id_Traktandum); }
	public void setIDSitzung(String id_Sitzung){ this.id_Sitzung.set(id_Sitzung); }
	public void setPosition(int position){ this.position.set(position); }


}
