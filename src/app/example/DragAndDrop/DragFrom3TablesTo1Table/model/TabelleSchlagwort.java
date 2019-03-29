package app.example.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleSchlagwort {
	
	private final SimpleStringProperty sign;
	private String c;
	private final SimpleStringProperty schlagwort;
						
	public TabelleSchlagwort(String sign, String c, String schlagwort) {
		this.sign = new SimpleStringProperty(sign);
		this.c = c;
		this.schlagwort = new SimpleStringProperty(schlagwort);

	}
	
    public ReadOnlyStringProperty signaturProperty() {
        return sign;
    }
    
    public ReadOnlyStringProperty schlagwortProperty() {
        return schlagwort;
    }
		
	// Getter
	public String getSign(){ return sign.get();	}
	public String getC(){ return c;	}
	public String getSchlagwort(){ return schlagwort.get();	}

	
	// Setter
	public void setSign(String sign){ this.sign.set(sign); }
	public void setC(String c){ this.c = c; }
	public void setSchlagwort(String schlagwort){ this.schlagwort.set(schlagwort); }

	
}
