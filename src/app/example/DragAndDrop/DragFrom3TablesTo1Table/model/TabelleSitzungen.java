package app.example.DragAndDrop.DragFrom3TablesTo1Table.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleSitzungen {

	private SimpleStringProperty idDatum;
	private SimpleIntegerProperty idRegisterName;
	private SimpleStringProperty wochenTag;
	private SimpleStringProperty datumFormatCH;
	private SimpleStringProperty startZeit;
	private SimpleStringProperty ort;
	private SimpleStringProperty registerName;
	private SimpleStringProperty pfadEinladung;
	private SimpleBooleanProperty einladungVerschickt;
	private SimpleStringProperty pfadProtokoll;
	private SimpleBooleanProperty protokollVerschickt;
	private SimpleBooleanProperty abgeschlossen;
	private SimpleStringProperty notiz;

	public TabelleSitzungen(String idDatum, int idRegisterName, String datumFormatCH, String startZeit, String ort, String registerName,
			String pfadEinladung, boolean einladungVerschickt, String pfadProtokoll, boolean protokollVerschickt, boolean abgeschlossen, String notiz) {
		this.idDatum = new SimpleStringProperty(idDatum);
		this.idRegisterName = new SimpleIntegerProperty(idRegisterName);
		this.datumFormatCH = changeDatumFormat(idDatum);
		this.wochenTag = getWochenTag(this.datumFormatCH.get());
		this.startZeit = new SimpleStringProperty(startZeit);
		this.ort = new SimpleStringProperty(ort);
		this.registerName = new SimpleStringProperty(registerName);
		this.pfadEinladung = new SimpleStringProperty(pfadEinladung);
		this.einladungVerschickt = new SimpleBooleanProperty(einladungVerschickt);
		this.pfadProtokoll = new SimpleStringProperty(pfadProtokoll);
		this.protokollVerschickt = new SimpleBooleanProperty(protokollVerschickt);
		this.abgeschlossen = new SimpleBooleanProperty(abgeschlossen);
		this.notiz = new SimpleStringProperty(notiz);
	}

    public ReadOnlyStringProperty idDatumProperty() {return idDatum;}
    public ReadOnlyIntegerProperty idRegisterNameProperty() {return idRegisterName;}
    public ReadOnlyStringProperty wochenTagProperty() {return wochenTag;}
    public ReadOnlyStringProperty datumFormatCHProperty() {return datumFormatCH;}
    public ReadOnlyStringProperty startZeitProperty() {return startZeit;}
    public ReadOnlyStringProperty ortProperty() {return ort;}
    public ReadOnlyStringProperty registerNameProperty() {return registerName;}
    public ReadOnlyStringProperty pfadEinladungProperty() {return pfadEinladung;}
    public ReadOnlyBooleanProperty einladungVerschicktProperty() {return einladungVerschickt;}
    public ReadOnlyStringProperty pfadProtokollProperty() {return pfadProtokoll;}
    public ReadOnlyBooleanProperty protokollVerschicktProperty() {return protokollVerschickt;}
    public ReadOnlyBooleanProperty abgeschlossenProperty() {return abgeschlossen;}
    public ReadOnlyStringProperty notizProperty() {return notiz;}

	// Getter
	public final String getIdDatum(){ return idDatum.get();	}
	public final SimpleIntegerProperty getIdRegisterName() {return idRegisterName;}
	public final String getWochenTag(){ return wochenTag.get();	}
	public final String getDatumFormatCH(){ return datumFormatCH.get();	}
	public final String getStartZeit(){ return startZeit.get();	}
	public final String getOrt(){ return ort.get();	}
	public final String getRegisterName(){ return registerName.get();	}
	public final String getPfadEinladung(){ return pfadEinladung.get();	}
	public final SimpleBooleanProperty getEinladungVerschickt() {return einladungVerschickt;}
	public final String getPfadProtokoll(){ return pfadProtokoll.get();	}
	public final SimpleBooleanProperty getProtokollVerschickt() {return protokollVerschickt;}
	public final SimpleBooleanProperty getAbgeschlossen() {return abgeschlossen;}
	public final String getNotiz(){ return notiz.get();	}

	// Setter Properties
	public final void setIDDatum(SimpleStringProperty idDatum){ this.idDatum = idDatum; }
	public final void setIdRegisterName(SimpleIntegerProperty idRegisterName) {this.idRegisterName = idRegisterName;}
//	public final void setWochenTag(String datum){ this.wochenTag = getWochenTag(datum); }
	public final void setDatumFormatCH(SimpleStringProperty datumFormatCH){
		this.datumFormatCH = changeDatumFormat(idDatum);
		this.wochenTag = getWochenTag(datumFormatCH.get());
	}
	public final void setStartZeit(SimpleStringProperty startZeit){ this.startZeit = startZeit; }
	public final void setOrt(SimpleStringProperty ort){ this.ort = ort; }
	public final void setRegisterName(SimpleStringProperty registerName){ this.registerName = registerName; }
	public final void setPfadEinladung(SimpleStringProperty pfadEinladung){ this.pfadEinladung = pfadEinladung; }
	public final void setEinladungVerschickt(SimpleBooleanProperty einladungVerschickt) {this.einladungVerschickt = einladungVerschickt;}
	public final void setPfadProtokoll(SimpleStringProperty pfadProtokoll){ this.pfadProtokoll = pfadProtokoll; }
	public final void setProtokollVerschickt(SimpleBooleanProperty protokollVerschickt) {this.protokollVerschickt = protokollVerschickt;}
	public final void setAbgeschlossen(SimpleBooleanProperty abgeschlossen) {this.abgeschlossen = abgeschlossen;}
	public final void setNotiz(SimpleStringProperty notiz){ this.notiz = notiz; }

	private SimpleStringProperty changeDatumFormat(SimpleStringProperty altesDatum){
		return changeDatumFormat(altesDatum.get());
	}

	private SimpleStringProperty changeDatumFormat(String altesDatum){
		String newDateString = null;

		try {
			final String OLD_FORMAT = "yyyy-MM-dd";
			final String NEW_FORMAT = "dd.MM.yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			Date d = sdf.parse(altesDatum);

			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("Datum: " + newDateString);
		return new SimpleStringProperty(newDateString);
	}

	private SimpleStringProperty getWochenTag(String datum){

//		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date MyDate = null;
		try {
			MyDate = newDateFormat.parse(datum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newDateFormat.applyPattern("EE"); // um den Tag Ganz aus zu schreiben "EEEE"
		String wochenTag = newDateFormat.format(MyDate);
//		System.out.println(wochenTag);
		return new SimpleStringProperty(wochenTag);
	}

}
