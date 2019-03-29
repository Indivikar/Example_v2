package app.example.DragAndDrop.DragFrom3TablesTo1Table.controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import app.example.DragAndDrop.DragFrom3TablesTo1Table.StageNewSitzungDemo;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETableViewAlle;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETableViewTraktandenImmer;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETableViewTraktandenListe;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETableViewZuletztErstellt;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETextFieldSucheSignatur;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen.ETextFieldSucheTraktandum;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.interfaces.FunktionenAllgemein;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleSitzungen;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleTraktandum;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;

public class ControllerNewSitzung implements Initializable, FunktionenAllgemein{

	private StageNewSitzungDemo stageNewSitzung;

	private ETableViewTraktandenImmer eTableViewTraktandenImmer;
	private ETableViewAlle eTableViewAlle;
	private ETableViewZuletztErstellt eTableViewZuletztErstellt;
	private ETableViewTraktandenListe eTableViewTraktandenListe;

	private TableRow<TabelleSitzungen> row;

	private SimpleStringProperty propStringCSSDatePicker = new SimpleStringProperty();



	// Tab - Traktanden
	@FXML private Button buttonAddTraktanden;
	@FXML private TextField textFieldSucheSignatur;
	@FXML private Button buttonRegistaturPlan;
	@FXML private TextField textFieldSucheTraktandum;
	@FXML private Accordion accordionImmerAnzeigen;
	@FXML private TitledPane titledPaneImmer;
	@FXML private AnchorPane anchorPaneTitleImmerAnzeigen;
	@FXML private CheckBox checkBoxSucheImmerAnzeigen;	
	@FXML private TableView<TabelleTraktandum> tableViewImmerAnzeigen;
		@FXML private TableColumn<TabelleTraktandum, String> columnSignaturImmerAnzeigen;
		@FXML private TableColumn<TabelleTraktandum, String> columnTraktandumImmerAnzeigen;
	@FXML private TitledPane titledPaneAlle;
	@FXML private AnchorPane anchorPaneTitleAlle;
	@FXML private CheckBox checkBoxSucheAlle;
	@FXML private TableView<TabelleTraktandum> tableViewAlle;
		@FXML private TableColumn<TabelleTraktandum, String> columnSignaturAlle;
		@FXML private TableColumn<TabelleTraktandum, String> columnTraktandumAlle;
		@FXML private TableColumn<TabelleTraktandum, String> columnZuletztInAlle;
	@FXML private TitledPane titledPaneZuletzt;
	@FXML private AnchorPane anchorPaneTitleZuletztErstellt;
	@FXML private Spinner<Integer> spinnerZuletztErstellt;
	@FXML private TableView<TabelleTraktandum> tableViewZuletztErstellt;
		@FXML private TableColumn<TabelleTraktandum, String> columnSignaturZuletztErstellt;
		@FXML private TableColumn<TabelleTraktandum, String> columnTraktandumZuletztErstellt;
	@FXML private TableView<TabelleTraktandum> tableViewTraktandenListe;
		@FXML private TableColumn<TabelleTraktandum, String> columnSignaturTraktandenListe;
		@FXML private TableColumn<TabelleTraktandum, String> columnTrakandumTraktandenListe;

	// Allgemein
	@FXML private AnchorPane mainAnchorPane;

	private String allesOK = "-fx-border-radius: 3; -fx-border-color: green; -fx-focus-color: green; -fx-faint-focus-color: #CAFFDC22;";
	private String fehler = "-fx-border-radius: 3; -fx-border-width: 1; -fx-border-color: red; -fx-focus-color: red; -fx-faint-focus-color: #FFBCBC22;";


	// Tab - Traktanden
	@FXML private void actionButtonAddTraktanden(ActionEvent event) {
		System.out.println("actionButtonUpdate");
	}

	@FXML private void actionButtonRegistaturPlan(ActionEvent event) {
		System.out.println("actionButtonRegistaturPlan");
	}

	// Allgemein


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


//		accordionImmerAnzeigen.widthProperty().addListener((obs, oldVal, newVal) -> {
//			double anchorPaneImmer = anchorPaneTitleImmerAnzeigen.getWidth();
//			double mainAnchor = mainAnchorPane.getWidth();
//
//			System.out.println(  mainAnchor - anchorPaneImmer);
//
////			if ((Double)newVal < (Double)oldVal) {
//////				anchorPaneTitleImmerAnzeigen.setPrefWidth((Double)newVal - 35.0);
////			} else {
////				anchorPaneTitleImmerAnzeigen.setPrefWidth((Double)newVal - 35.0);
////			}
//
//
//		});


//      Tab - Traktanden
        addCheckBoxSucheImmerAnzeigenEigenschaften();
        addCheckBoxSucheAlleEigenschaften();
        addSpinnerEigenschaften();

	}


	private void addCheckBoxSucheImmerAnzeigenEigenschaften() {

		checkBoxSucheImmerAnzeigen.setSelected(true);
		checkBoxSucheImmerAnzeigen.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                if(new_val){
	                	eTableViewTraktandenImmer.filtern();
	                } else {
	                	eTableViewTraktandenImmer.filterReset();
	                }
	        }
	    });
	}

	private void addCheckBoxSucheAlleEigenschaften() {

		checkBoxSucheAlle.setSelected(true);
		checkBoxSucheAlle.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                if(new_val){
	                	eTableViewAlle.filtern();
	                } else {
	                	eTableViewAlle.filterReset();
	                }
	        }
	    });
	}

	private void addSpinnerEigenschaften() {
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);

        spinnerZuletztErstellt.setValueFactory(valueFactory);

        spinnerZuletztErstellt.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                	spinnerZuletztErstellt.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }

                if(!newValue.equals("")){
                	System.out.println("newValue: " + newValue);
            		 eTableViewZuletztErstellt.filterDaten();
            	} else {
            		spinnerZuletztErstellt.getEditor().setText("0");
            	}


            }
        });

        spinnerZuletztErstellt.getEditor().setText("1");

	}

    public void tooltipStartTime(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Getter
    public final TextField getTextFieldSucheSignatur() {return textFieldSucheSignatur;}
	public final TextField getTextFieldSucheTraktandum() {return textFieldSucheTraktandum;}
    public final ETableViewTraktandenImmer getETableViewTraktandenImmer() {return eTableViewTraktandenImmer;}
	public final ETableViewAlle getETableViewAlle() {return eTableViewAlle;}
	public final ETableViewZuletztErstellt getETableViewZuletztErstellt() {return eTableViewZuletztErstellt;}
	public final ETableViewTraktandenListe geteTableViewTraktandenListe() {return eTableViewTraktandenListe;}
	public final Spinner<Integer> getSpinnerZuletztErstellt() {return spinnerZuletztErstellt;}
	public final CheckBox getCheckBoxSucheImmerAnzeigen() {return checkBoxSucheImmerAnzeigen;}
	public final CheckBox getCheckBoxSucheAlle() {return checkBoxSucheAlle;}
	public final StageNewSitzungDemo getStageNewSitzung() {return stageNewSitzung;}
	public final AnchorPane getMainAnchorPane() {return mainAnchorPane;}
	public final TableView<TabelleTraktandum> getTableViewImmerAnzeigen() {return tableViewImmerAnzeigen;}
	public final TableView<TabelleTraktandum> getTableViewAlle() {return tableViewAlle;}
	public final TableView<TabelleTraktandum> getTableViewZuletztErstellt() {return tableViewZuletztErstellt;}
	public final TableView<TabelleTraktandum> getTableViewTraktandenListe() {return tableViewTraktandenListe;}

	// Setter
	public void setMainStageModulPR(StageNewSitzungDemo stageNewSitzung, TableRow<TabelleSitzungen> row) {
		this.stageNewSitzung = stageNewSitzung;
		this.row = row;

		new ETextFieldSucheSignatur(this, textFieldSucheSignatur);

		new ETextFieldSucheTraktandum(this, textFieldSucheTraktandum);

		eTableViewTraktandenImmer =
		new ETableViewTraktandenImmer(this, tableViewImmerAnzeigen, columnSignaturImmerAnzeigen, columnTraktandumImmerAnzeigen);

		eTableViewAlle =
		new ETableViewAlle(this, tableViewAlle, columnSignaturAlle, columnTraktandumAlle);

		eTableViewZuletztErstellt =
		new ETableViewZuletztErstellt(this, tableViewZuletztErstellt, columnSignaturZuletztErstellt, columnTraktandumZuletztErstellt);

		eTableViewTraktandenListe =
		new ETableViewTraktandenListe(this, tableViewTraktandenListe, columnSignaturTraktandenListe, columnTrakandumTraktandenListe);

		
		titledPaneImmer.setExpanded(true);
		titledPaneAlle.setExpanded(true);
		titledPaneZuletzt.setExpanded(true);
		
	}



}
