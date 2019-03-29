package app.examples.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen;

import java.util.List;
import java.util.stream.Collectors;

import app.examples.DragAndDrop.DragFrom3TablesTo1Table.controller.ControllerNewSitzung;
import app.examples.DragAndDrop.DragFrom3TablesTo1Table.interfaces.FunktionenAllgemein;
import app.examples.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleTraktandum;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.util.Pair;

public class ETableViewTraktandenListe implements FunktionenAllgemein {

	private ControllerNewSitzung controllerNewSitzung;

	private TableView<TabelleTraktandum> tableViewTraktandenListe;
	private TableColumn<TabelleTraktandum, String> columnSignaturTraktandenListe;
	private TableColumn<TabelleTraktandum, String> columnTrakandumTraktandenListe;

	private ObservableList<TabelleTraktandum> masterListe = FXCollections.observableArrayList();
	private ObservableList<TabelleTraktandum> gefilterteListe = FXCollections.observableArrayList();

	public ETableViewTraktandenListe(ControllerNewSitzung controllerNewSitzung, TableView<TabelleTraktandum> tableViewTraktandenListe,
			TableColumn<TabelleTraktandum, String> columnSignaturTraktandenListe, TableColumn<TabelleTraktandum, String> columnTrakandumTraktandenListe) {


		this.controllerNewSitzung = controllerNewSitzung;
		this.tableViewTraktandenListe = tableViewTraktandenListe;
		this.columnSignaturTraktandenListe = columnSignaturTraktandenListe;
		this.columnTrakandumTraktandenListe = columnTrakandumTraktandenListe;

		tableViewTraktandenListe.setPlaceholder(new Label("es konnte kein Traktandum gefunden werden"));



//		addContextMenu_MouseEvent_DragAndDrop();
		tableViewTraktandenListe.setItems(masterListe);
//		filterDaten(systemTrayIcon.getListernerWerte().getSelectedDB(), systemTrayIcon.getListernerWerte().getSelectedRegister());
		addColumns();

		addListenerTableViewSpalteAnFensterAnpassen(tableViewTraktandenListe, columnTrakandumTraktandenListe, 200, -2);

		@SuppressWarnings("unchecked")
		ObservableList<TableView<TabelleTraktandum>> listeQuellTables = FXCollections.observableArrayList(
				tableViewTraktandenListe,
				controllerNewSitzung.getTableViewImmerAnzeigen(),
				controllerNewSitzung.getTableViewAlle(),
				controllerNewSitzung.getTableViewZuletztErstellt());

		dragAndDropRowsBetweenTwoTables(controllerNewSitzung, listeQuellTables, tableViewTraktandenListe, false, false, false, false);
//		dragAndDropRowsBetweenTwoTables(controllerNewSitzung, controllerNewSitzung.getTableViewZuletztErstellt(), tableViewTraktandenListe, false, false, false, false);

	}

	public void clearListe() {
		masterListe.clear();
		dummyHandler(tableViewTraktandenListe);
	}

	public void addNewRow(TableRow<TabelleTraktandum> row) {
		masterListe.add(new TabelleTraktandum(row.getItem().getIDTraktandum(), row.getItem().getIDRegister(), row.getItem().getSignatur(),
				row.getItem().isImmerAnzeigen(), row.getItem().getTraktandum()));
	}

	public void addColumns(){

		columnSignaturTraktandenListe.setCellValueFactory(
			    new PropertyValueFactory<TabelleTraktandum, String>("signatur")
			);

		columnSignaturTraktandenListe.setCellFactory(column -> {
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





		columnTrakandumTraktandenListe.setCellFactory(column -> {


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


		columnTrakandumTraktandenListe.setCellValueFactory(
			    new PropertyValueFactory<TabelleTraktandum, String>("traktandum")
			);

	}


	 private void dragAndDropRowsBetweenTwoTables(ControllerNewSitzung controllerNewSitzung,
			 	ObservableList<TableView<TabelleTraktandum>> quellTable,
			 	TableView<TabelleTraktandum> zielTable,
				boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben,
				boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden) {

		 dummyHandler(zielTable);

		 zielTable.setRowFactory(tv -> {
	            TableRow<TabelleTraktandum> row = new TableRow<>();

	            // ContextMenu
	            row.contextMenuProperty().bind(
	                    Bindings.when(row.emptyProperty())
	                    .then((ContextMenu)null)
	                    .otherwise(addContextMenu(row))
	            );

			    // Mouse Event
			    row.addEventFilter(MouseEvent.MOUSE_CLICKED, new mouseEventRowHandler());

			    // Mouse Cursor Event
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

			    // Drag and Drop
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
//	                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
	                	zielTable.setCursor(Cursor.CLOSED_HAND);
	                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                        event.consume();
//	                    }
	                }
	            });

	            row.setOnDragDropped(event -> {
	            	System.out.println("Drop in Liste");
					Dragboard db = event.getDragboard();
					if (db.hasContent(SERIALIZED_MIME_TYPE)) {

						TableView<TabelleTraktandum> targetTableView = null;

						// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden, wenn Item hinzugefügt werden soll, aus welcher Tabelle
						for (TableView<TabelleTraktandum> tableView : quellTable) {
							if(tableView.isFocused()){
								targetTableView = tableView;
								break;
							}
						}
						if(row.getTableView().isFocused()){
							// sortieren
							targetTableView = zielTable;
//						} else {
//							// neues Item
//							targetTableView = quellTable;
						}

						System.out.println("Drop in Liste 2");

						int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);

						// Wenn der "draggedIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
//						if(draggedIndex > zielTable.getItems().size()){
//							draggedIndex = zielTable.getItems().size() - 1;
//						}
						TabelleTraktandum draggedObject = targetTableView.getItems().get(draggedIndex);

						System.out.println("Drop in Liste 3");
						int dropIndex ;

						if (row.isEmpty()) {
						    dropIndex = targetTableView.getItems().size() ;
						    System.out.println("row.isEmpty(): " + dropIndex);
						} else {
						    dropIndex = row.getIndex();
						    System.out.println("nicht row.isEmpty(): " + dropIndex);
						}



						System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + targetTableView.getItems().size());


							System.out.println(1);
						    if (!gibtEsDenEintragSchon(zielTable, draggedObject, darfEsDenNeuenEintragMehrmalsGeben) || row.getTableView().isFocused()) {
						    	System.out.println(2);
						    	if(nachDropAusQuellTableRemoven || row.getTableView().isFocused()){
						    		System.out.println(3);
						        	draggedObject = targetTableView.getItems().remove(draggedIndex);
						        }
						    	System.out.println("draggedObject in Liste eintragen: " + draggedObject.getSignatur() + " | " + draggedObject.getTraktandum());

						    	// Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
								if(dropIndex > zielTable.getItems().size()){
									dropIndex = zielTable.getItems().size();
								}
								
						    	System.out.println("dropIndex: " + dropIndex);
								zielTable.getItems().add(dropIndex, draggedObject);
							}

						// Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die
						// DragAndDrop funktion nicht mehr
						dummyHandler(zielTable);

						event.setDropCompleted(true);
						zielTable.getSelectionModel().select(dropIndex);
						event.consume();
	                }
	            });

	            return row ;
	        });
	}


	 private ContextMenu addContextMenu(TableRow<TabelleTraktandum> row) {
		  final ContextMenu contextMenu = new ContextMenu();
          final MenuItem removeMenuItem = new MenuItem("entfernen");
          removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
            	  row.getTableView().getItems().remove(row.getItem());
              }
          });
          contextMenu.getItems().add(removeMenuItem);
          return contextMenu;
	}



    class mouseEventRowHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
        	TableRow<TabelleTraktandum> row = (TableRow<TabelleTraktandum>) t.getSource();
        	if (t.getClickCount() == 2 && (! row.isEmpty()) ) {
        		System.out.println("Doppel-Klick");
            }

        }
    }
}
