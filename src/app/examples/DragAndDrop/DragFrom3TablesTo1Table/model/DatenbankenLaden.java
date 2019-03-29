package app.examples.DragAndDrop.DragFrom3TablesTo1Table.model;

import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatenbankenLaden {

	private ObservableList<TabelleEinladungen> tabelleEinladungen = FXCollections.observableArrayList ();
	private ObservableList<TabelleHaupttitel> tabelleHaupttitel = FXCollections.observableArrayList ();
	private ObservableList<TabelleRegistraturplan> tabelleDetails = FXCollections.observableArrayList ();
	private ObservableList<TabelleProtokolle> tabelleProtokolle = FXCollections.observableArrayList();
	private ObservableList<TabelleRegister> tabelleRegister = FXCollections.observableArrayList();
	private ObservableList<TabelleRegisterName> tabelleRegisterName = FXCollections.observableArrayList();
	private ObservableList<TabelleSchlagwort> tabelleSchlagwort = FXCollections.observableArrayList();
	private ObservableList<TabelleSitzungen> tabelleSitzungen = FXCollections.observableArrayList();
	private ObservableList<TabelleTraktanden> tabelleTraktanden = FXCollections.observableArrayList();
	private ObservableList<TabelleTraktandum> tabelleTraktandum = FXCollections.observableArrayList();

    private String dateiName;
    private String dbName;
    private Statement s;

	public DatenbankenLaden(
//				ObservableList<TabelleEinladungen> tabelleEinladungen,
				ObservableList<TabelleHaupttitel> tabelleHaupttitel,
				ObservableList<TabelleRegistraturplan> tabelleDetails,
//				ObservableList<TabelleProtokolle> tabelleProtokolle,
				ObservableList<TabelleSchlagwort> tabelleSchlagwort,
				ObservableList<TabelleSitzungen> tabelleSitzungen,
				ObservableList<TabelleRegister> tabelleRegister,
				ObservableList<TabelleRegisterName> tabelleRegisterName,
				ObservableList<TabelleTraktanden> tabelleTraktanden,
				ObservableList<TabelleTraktandum> tabelleTraktandum,
				String dateiName, String dbName, Statement s) {

//							this.tabelleEinladungen = tabelleEinladungen;
					        this.tabelleHaupttitel = tabelleHaupttitel;
					        this.tabelleDetails = tabelleDetails;
//					        this.tabelleProtokolle = tabelleProtokolle;
					        this.tabelleSchlagwort = tabelleSchlagwort;
					        this.tabelleSitzungen = tabelleSitzungen;
					        this.tabelleRegister = tabelleRegister;
					        this.tabelleRegisterName = tabelleRegisterName;
					        this.tabelleTraktanden = tabelleTraktanden;
					        this.tabelleTraktandum = tabelleTraktandum;
					        this.dateiName = dateiName;
					        this.dbName = dbName;
					        this.s = s;

    }

	// Getter
    public ObservableList<TabelleHaupttitel> getTabelleHaupttitel() {return tabelleHaupttitel;}
	public final ObservableList<TabelleEinladungen> getTabelleEinladungen() {return tabelleEinladungen;}
	public final ObservableList<TabelleProtokolle> getTabelleProtokolle() {return tabelleProtokolle;}
	public final ObservableList<TabelleTraktanden> getTabelleTraktanden() {return tabelleTraktanden;}
	public final ObservableList<TabelleTraktandum> getTabelleTraktandum() {return tabelleTraktandum;}
	public ObservableList<TabelleRegistraturplan> getTabelleDetails() {return tabelleDetails;}
	public ObservableList<TabelleSchlagwort> getTabelleSchlagwort() {return tabelleSchlagwort;}
	public final ObservableList<TabelleSitzungen> getTabelleSitzungen() {return tabelleSitzungen;}
	public ObservableList<TabelleRegister> getTabelleRegister() {return tabelleRegister;}
	public ObservableList<TabelleRegisterName> getTabelleRegisterName() {return tabelleRegisterName;}
	public String getDateiName() {return dateiName;}
	public String getDbName() {return dbName;}
	public final Statement getS() {return s;}

	// Setter
	public void setTabelleHaupttitel(ObservableList<TabelleHaupttitel> tabelleHaupttitel) {this.tabelleHaupttitel = tabelleHaupttitel;}
	public final void setTabelleEinladungen(ObservableList<TabelleEinladungen> tabelleEinladungen) {this.tabelleEinladungen = tabelleEinladungen;}
	public final void setTabelleProtokolle(ObservableList<TabelleProtokolle> tabelleProtokolle) {this.tabelleProtokolle = tabelleProtokolle;}
	public final void setTabelleTraktanden(ObservableList<TabelleTraktanden> tabelleTraktanden) {this.tabelleTraktanden = tabelleTraktanden;}
	public final void setTabelleTraktandum(ObservableList<TabelleTraktandum> tabelleTraktandum) {this.tabelleTraktandum = tabelleTraktandum;}
	public void setTabelleDetails(ObservableList<TabelleRegistraturplan> tabelleDetails) {this.tabelleDetails = tabelleDetails;}
	public void setTabelleSchlagwort(ObservableList<TabelleSchlagwort> tabelleSchlagwort) {this.tabelleSchlagwort = tabelleSchlagwort;}
	public final void setTabelleSitzungen(ObservableList<TabelleSitzungen> tabelleSitzungen) {this.tabelleSitzungen = tabelleSitzungen;}
	public void setTabelleRegister(ObservableList<TabelleRegister> tabelleRegister) {this.tabelleRegister = tabelleRegister;}
	public void setTabelleRegisterName(ObservableList<TabelleRegisterName> tabelleRegisterName) {this.tabelleRegisterName = tabelleRegisterName;}
	public void setDateiName(String dateiName) {this.dateiName = dateiName;}
	public void setDbName(String dbName) {this.dbName = dbName;}
	public final void setS(Statement s) {this.s = s;}

}
