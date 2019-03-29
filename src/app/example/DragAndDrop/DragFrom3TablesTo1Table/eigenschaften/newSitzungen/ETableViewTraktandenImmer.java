package app.example.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen;

import java.util.List;
import java.util.stream.Collectors;

import app.example.DragAndDrop.DragFrom3TablesTo1Table.controller.ControllerNewSitzung;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.interfaces.FunktionenAllgemein;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleTraktandum;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.util.Pair;

public class ETableViewTraktandenImmer implements FunktionenAllgemein {

	private ControllerNewSitzung controllerNewSitzung;

	private TableView<TabelleTraktandum> tableViewImmerAnzeigen;
	private TableColumn<TabelleTraktandum, String> columnSignaturImmerAnzeigen;
	private TableColumn<TabelleTraktandum, String> columnTraktandumImmerAnzeigen;

	private ObservableList<TabelleTraktandum> masterListe = FXCollections.observableArrayList();
	private ObservableList<TabelleTraktandum> gefilterteListe = FXCollections.observableArrayList();


	public ETableViewTraktandenImmer(ControllerNewSitzung controllerNewSitzung, TableView<TabelleTraktandum> tableViewImmerAnzeigen,
			TableColumn<TabelleTraktandum, String> columnSignaturImmerAnzeigen, TableColumn<TabelleTraktandum, String> columnTraktandumImmerAnzeigen) {

		this.controllerNewSitzung = controllerNewSitzung;
		this.tableViewImmerAnzeigen = tableViewImmerAnzeigen;
		this.columnSignaturImmerAnzeigen = columnSignaturImmerAnzeigen;
		this.columnTraktandumImmerAnzeigen = columnTraktandumImmerAnzeigen;

		tableViewImmerAnzeigen.setPlaceholder(new Label("es konnte kein Traktandum gefunden werden"));


//		addContextMenu();
		filterDaten();
		addColumns();

		addListenerTableViewSpalteAnFensterAnpassen(tableViewImmerAnzeigen, columnTraktandumImmerAnzeigen, 200, -2);
		dragAndDropRowsBetweenTwoTables(controllerNewSitzung, tableViewImmerAnzeigen, controllerNewSitzung.getTableViewTraktandenListe(), false, false, true, true);

	}

	public void addColumns(){

		columnSignaturImmerAnzeigen.setCellValueFactory(
			    new PropertyValueFactory<TabelleTraktandum, String>("signatur")
			);

		columnSignaturImmerAnzeigen.setCellFactory(column -> {
			return new TableCell<TabelleTraktandum, String>() {
		         @Override
		         protected void updateItem(String item, boolean empty)
		          {
		             super.updateItem(item, empty);
		             setText( item );
		             if(item != null){
//		            	Pair<Boolean, String> liste = toolTipSignatur(systemTrayIcon, item);
//
//		            	Tooltip toolTip = null;
//		            	if (liste.getKey()) {
//							toolTip = new Tooltip("Die Signatur \"" + item + "\" konnte in der Datenbank nicht gefunden werden");
//							setStyle("-fx-background-color: " + SystemTrayIcon.colorSignaturNichtGefunden + ";");
////							setFehlerMeldung();
//						} else {
//							toolTip = new Tooltip(liste.getValue());
//						}
//
////		            	toolTipEigenschaften(toolTip); // Zeit verlängern, wo der ToolTip angezeigt wird -> gibt ein Dastellungsfehler, deshalb disabled
//		            	setTooltip(toolTip);

		             } else {
		            	 setText( null );
		            	 setTooltip(null);
		             }
		          }
		       };
		    });





		columnTraktandumImmerAnzeigen.setCellFactory(column -> {


			return new TableCell<TabelleTraktandum, String>() {
		         @Override
		         protected void updateItem(String item, boolean empty)
		          {
		             super.updateItem(item, empty);

		             if(item != null){
				            if (item.contains("\n")) {
				            	String[] part = item.split("\n");
				            	setText( part[0] + "...");
							} else {
								setText( item );
							}

		            		Tooltip toolTip = new Tooltip(item);
//		            		toolTipEigenschaften(toolTip); // Zeit verlängern, wo der ToolTip angezeigt wird -> gibt ein Dastellungsfehler, deshalb disabled
				            setTooltip(toolTip);

		             } else {
		            	 setText( null );
		            	 setTooltip(null);
		             }
		          }
		       };
		    });


		columnTraktandumImmerAnzeigen.setCellValueFactory(
			    new PropertyValueFactory<TabelleTraktandum, String>("traktandum")
			);



//		tableColumnSchlagwort.setSortType(TableColumn.SortType.ASCENDING);


//		tableViewSitzungen.setItems(systemTrayIcon.getDatenbankenLaden().get(0).getTabelleSitzungen());
//		tableViewSchlagwort.getSortOrder().add(tableColumnSchlagwort); // sehr leistungsintensiv, aber nur hier in der Methode

	}


	 private void dragAndDropRowsBetweenTwoTables(ControllerNewSitzung controllerNewSitzung,
	    		TableView<TabelleTraktandum> quellTable, TableView<TabelleTraktandum> zielTable,
				boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben,
				boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden) {

					dummyHandler(zielTable);
					dummyHandler(quellTable);

					quellTable.setRowFactory(tv -> {
						TableRow<TabelleTraktandum> row = new TableRow<>();

						row.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
							@Override
					        public void handle(MouseEvent t) {
					        	TableRow<TabelleTraktandum> row = (TableRow<TabelleTraktandum>) t.getSource();
					        	if (t.getClickCount() == 2 && (! row.isEmpty()) ) {
					        		// TODO - wenn doppelklick, dann in TableView "Traktandenliste" kopieren

//					        		System.out.println(!gibtEsDenEintragSchon(quellTable, zielTable.getSelectionModel().getSelectedItem(), darfEsDenNeuenEintragMehrmalsGeben));

						        	if(!gibtEsDenEintragSchon(zielTable, row.getItem(), darfEsDenNeuenEintragMehrmalsGeben)){
						        		System.out.println("in");
						        		controllerNewSitzung.geteTableViewTraktandenListe().addNewRow(row);
//						        		row.setDisable(true);
						        		if(!quellTable.isFocused() || !zielTable.isFocused()){
							        		dummyHandler(zielTable);
											dummyHandler(quellTable);
						        		}
						        	}

					            }

					        }
						});


					row.hoverProperty().addListener((observable) -> {
							final TabelleTraktandum person = row.getItem();

							if (row.isHover() && person != null) {
								row.setCursor(Cursor.OPEN_HAND);
							} else {
								row.setCursor(Cursor.DEFAULT);
						}
					});
						
					row.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
						row.setCursor(Cursor.CLOSED_HAND);
		            });

					row.setOnDragDetected(event -> {
						if (! row.isEmpty()) {
							Integer index = row.getIndex();
							Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
							db.setDragView(row.snapshot(null, null));
							ClipboardContent cc = new ClipboardContent();
							cc.put(SERIALIZED_MIME_TYPE, index);
							db.setContent(cc);
							event.consume();
						}
					});

					row.setOnDragOver(event -> {
						Dragboard db = event.getDragboard();
						if (db.hasContent(SERIALIZED_MIME_TYPE)) {
						//if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
						    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
						    event.consume();
						//}
						}
					});

//					row.setOnDragDropped(event -> {
//						Dragboard db = event.getDragboard();
//						if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//
//							TableView<TabelleTraktandum> targetTableView;
//
//							// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden
//							if(row.getTableView().isFocused()){
//								// sortieren
//								targetTableView = quellTable;
//							} else {
//								// neues Item
//								targetTableView = zielTable;
//							}
//
//							int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
//							TabelleTraktandum draggedObject = targetTableView.getItems().get(draggedIndex);
//
//
//							int dropIndex ;
//
//							if (row.isEmpty()) {
//							    dropIndex = targetTableView.getItems().size() ;
//							//    System.out.println("row.isEmpty(): " + dropIndex);
//							} else {
//							    dropIndex = row.getIndex();
//							//    System.out.println("nicht row.isEmpty(): " + dropIndex);
//							}
//
//							// Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
//							if(dropIndex > quellTable.getItems().size()){
//								dropIndex = quellTable.getItems().size();
//							}
//
//							System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + targetTableView.getItems().size());
//
//							if(darfQuellTableNeuenEintragAdden || row.getTableView().isFocused() && darfQuellTablePerDragAndDropSortiertWerden){
//								System.out.println(1);
//							    if (!gibtEsDenEintragSchon(quellTable, draggedObject, darfEsDenNeuenEintragMehrmalsGeben) || row.getTableView().isFocused()) {
//							    	System.out.println(2);
//							    	if(nachDropAusQuellTableRemoven || row.getTableView().isFocused()){
//							    		System.out.println(3);
//							        	draggedObject = targetTableView.getItems().remove(draggedIndex);
//							        }
//							    	System.out.println(4);
//									quellTable.getItems().add(dropIndex, draggedObject);
//
//								}
//							}
//
//							// Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die
//							// DragAndDrop funktion nicht mehr
//							dummyHandler(zielTable);
//							dummyHandler(quellTable);
//
//							event.setDropCompleted(true);
//							quellTable.getSelectionModel().select(dropIndex);
//							event.consume();
//						}
//					});

					return row ;
					});
	}

//	private void addContextMenu() {
//		tableViewImmerAnzeigen.setRowFactory(
//			new Callback<TableView<TabelleTraktandum>, TableRow<TabelleTraktandum>>() {
//				@Override
//				public TableRow<TabelleTraktandum> call(TableView<TabelleTraktandum> tableView) {
//				    final TableRow<TabelleTraktandum> row = new TableRow<>();
//				    row.addEventFilter(MouseEvent.MOUSE_CLICKED, new mouseEventRowHandler());
////				    new TableViewContextMenuNewTraktandum(systemTrayIcon, controllerNewSitzung, row);
//				return row;
//			}
//		});
//	}

	public void filterDaten(){
		long startTime = System.currentTimeMillis();

		masterListe.clear();
		for (int i = 0; i < 10; i++) {
			masterListe.add(new TabelleTraktandum(	i, i,
					"A." + i, true, "Tabelle Immer 1" + i));
		}

		long endTimeVorFiltern = System.currentTimeMillis();
		long durationVorFiltern = (endTimeVorFiltern - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("====================================================================================================");
    	System.out.println("Klasse TableViewNewTraktandumEigenschaften -> nur Master filterDaten(): " + durationVorFiltern + " ms");
    	System.out.println("====================================================================================================");


    	filtern();

		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("====================================================================================================");
    	System.out.println("Klasse TableViewNewTraktandumEigenschaften -> filterDaten() und filtern(): " + duration + " ms");
    	System.out.println("====================================================================================================");

	}

    public void filtern() {
    	long startTime = System.currentTimeMillis();
//    	// Damit nur einmal gefiltert wird, fals es überschneidungen, bei den Actionen gibt und ChangeListenern gibt
//    	if(!controllerNewTraktandum.getTextFieldSignatur().getText().equals(savedSignatur) ||
//    			!controllerNewTraktandum.getTextArea().getText().equals(savedTraktandum)){

	    	List<TabelleTraktandum> filteredList = masterListe.stream()
	//		        .sorted()
			        .filter(string -> string.getSignatur().toLowerCase().contains(controllerNewSitzung.getTextFieldSucheSignatur().getText().toLowerCase())
			        		&& string.getTraktandum().toLowerCase().contains(controllerNewSitzung.getTextFieldSucheTraktandum().getText().toLowerCase()))
			        .collect(Collectors.toList());
	    	gefilterteListe.clear();
	    	gefilterteListe.addAll(filteredList);
	    	tableViewImmerAnzeigen.setItems(gefilterteListe);

//    	}
			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
			System.out.println("====================================================================================================");
	    	System.out.println("Klasse TableViewNewTraktandumEigenschaften -> filtern(): " + duration + " ms");
	    	System.out.println("====================================================================================================");



//    	savedSignatur = controllerNewTraktandum.getTextFieldSignatur().getText();
//    	savedTraktandum = controllerNewTraktandum.getTextArea().getText();
    }

    public void filterReset() {
    	System.out.println("filterReset");
    	List<TabelleTraktandum> filteredList = masterListe.stream()
//		        .sorted()
		        .collect(Collectors.toList());
    	gefilterteListe.clear();
    	gefilterteListe.addAll(filteredList);
    	tableViewImmerAnzeigen.setItems(gefilterteListe);
    }





//    class mouseEventRowHandler implements EventHandler<MouseEvent> {
//
//        @Override
//        public void handle(MouseEvent t) {
//        	TableRow<TabelleTraktandum> row = (TableRow<TabelleTraktandum>) t.getSource();
//        	if (t.getClickCount() == 2 && (! row.isEmpty()) ) {
//        		// TODO - wenn doppelklick, dann in TableView "Traktandenliste" kopieren
//        		controllerNewSitzung.geteTableViewTraktandenListe().addNewRow(row);
//            }
//
//        }
//    }
}
