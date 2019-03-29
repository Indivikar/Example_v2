package app.examples.DragAndDrop.DragFrom3TablesTo1Table.interfaces;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

import app.examples.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleTraktandum;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public interface FunktionenAllgemein {

//	private SystemTrayIcon systemTrayIcon;
//
//	public FunktionenAllgemein(SystemTrayIcon systemTrayIcon) {
//		this.systemTrayIcon = systemTrayIcon;
//	}

	/**************************************************************************************/
	/*  - Start */

	/*  - Ende */
	/**************************************************************************************/


	/**************************************************************************************/
	/* Alert - Start */


	public default void alertOhneFehlerCode(AlertType alertType, String headerText, String contentText, boolean showAndWait) {
		alertMitFehlerCode(alertType, headerText, contentText, null, showAndWait, null);
	}

	public default void alertOhneFehlerCode(AlertType alertType, String headerText, String contentText, boolean showAndWait, Exception e) {
		alertMitFehlerCode(alertType, headerText, contentText, null, showAndWait, e);
	}
	public default void alertMitFehlerCode(AlertType alertType, String headerText, String contentText, String fehlerCodes, boolean showAndWait) {
		alertMitFehlerCode(alertType, headerText, contentText, fehlerCodes, showAndWait, null);
	}

	public default void alertMitFehlerCode(AlertType alertType, String headerText, String contentText, String fehlerCodes, boolean showAndWait, Exception e) {

		// TODO - das Fenster noch in im Blauen Layout erstellen

		Platform.runLater(new Runnable() {
			public void run() {

					String titel = null;

					switch (alertType) {
					case INFORMATION:
						titel = "Information";
						break;
					case WARNING:
						titel = "Warnung";
						break;
					case ERROR:
						titel = "Error";
						break;

					default:
						break;

					}

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle(titel);
					alert.setHeaderText(headerText);
					if(fehlerCodes == null){
						alert.setContentText(contentText + "\n");
					} else {
						alert.setContentText("Fehlercode: " + fehlerCodes + "\n\n" + contentText + "\n");
					}

					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//					stage.getIcons().add(new Image(SystemTrayIcon.class.getResourceAsStream( "/application/images/img.png" )));
					stage.setAlwaysOnTop(true);

			//		Exception ex = new FileNotFoundException("Could not find file blabla.txt");

					// Create expandable Exception.
			//		StringWriter sw = new StringWriter();
			//		PrintWriter pw = new PrintWriter(sw);
			//		ex.printStackTrace(pw);

					if(e != null){
						String exceptionText = exceptionToString(e);

						Label label = new Label("Fehlermeldung:");

						TextArea textArea = new TextArea(exceptionText);
						textArea.setEditable(false);
						textArea.setWrapText(true);

						textArea.setMaxWidth(Double.MAX_VALUE);
						textArea.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(textArea, Priority.ALWAYS);
						GridPane.setHgrow(textArea, Priority.ALWAYS);

						GridPane expContent = new GridPane();
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(label, 0, 0);
						expContent.add(textArea, 0, 1);

						// Set expandable Exception into the dialog pane.
						alert.getDialogPane().setExpandableContent(expContent);
					}


					alert.showAndWait();
			}
		});



	}

	public default String exceptionToString(Exception ex) {
	    StringBuilder result = new StringBuilder();
	    for (Throwable cause = ex; cause != null; cause = cause.getCause()) {
	        if (result.length() > 0)
	            result.append("Caused by: ");
	        result.append(cause.getClass().getName());
	        result.append(": ");
	        result.append(cause.getMessage());
	        result.append("\n");
	        for (StackTraceElement element: cause.getStackTrace()) {
	            result.append("\tat ");
	            result.append(element.getMethodName());
	            result.append("(");
	            result.append(element.getFileName());
	            result.append(":");
	            result.append(element.getLineNumber());
	            result.append(")\n");
	        }
	    }
	    return result.toString();
	}


	/* Alert - Ende */
	/**************************************************************************************/


	/**************************************************************************************/
	/* TableView Spalte an Fenster anpassen - Start */

	public default void addListenerTableViewSpalteAnFensterAnpassen(TableView<?> tableView,
																	TableColumn<?, ?> spalteDieAngepasstWerdenSoll,
																	int minWidth, int korrekturPlusOderMinus) {

		tableView.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	System.out.println("------------------");
		    	double neueWidth = tableView.getWidth();

		    	for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {

		    		if(!tableColumn.equals(spalteDieAngepasstWerdenSoll)){
		    			System.out.println("TableColumns: " + tableColumn.getText() + " -> " + neueWidth + " - " + tableColumn.getWidth());
		    			neueWidth = neueWidth - tableColumn.getWidth();
		    		}

		    	}

		    	System.out.println(neueWidth + " <= " + minWidth);
				if(neueWidth <= minWidth){
					System.out.println(1);
					spalteDieAngepasstWerdenSoll.setPrefWidth(minWidth + korrekturPlusOderMinus);
				} else {
					spalteDieAngepasstWerdenSoll.setPrefWidth(neueWidth + korrekturPlusOderMinus);
				}
				System.out.println("------------------");
		    }
		});

	}

	/* TableView Spalte an Fenster anpassen - Ende */
	/**************************************************************************************/


	/**************************************************************************************/
	/* maxZeichenImTextArea  - Start */

	public default void maxZeichenImTextArea(TextArea textArea, Label labelAnzahlZeichenInTextArea, Label labelAnzahlZeichenMaxInTextArea, Label labelMeldung, int maxZeichen) {

		labelAnzahlZeichenMaxInTextArea.setText("/ " + maxZeichen);

        textArea.setTextFormatter(new TextFormatter<String>(change ->
        change.getControlNewText().length() <= maxZeichen ? ok(change, labelAnzahlZeichenInTextArea, labelMeldung) : meldung(labelMeldung, maxZeichen)));

	}

	public default Change ok(Change change, Label labelAnzahlZeichenInTextArea, Label labelMeldung) {
		labelAnzahlZeichenInTextArea.setText(change.getControlNewText().length() + "");
		labelMeldung.setText("");
		return change;
	}

	public default Change meldung(Label labelMeldung, int maxZeichen) {
		labelMeldung.setTextFill(Color.RED);
		labelMeldung.setText("Es sind nur maximal \"" + maxZeichen + "\" Zeichen erlaubt.");
		return null;
	}

	/* maxZeichenImTextArea  - Ende */
	/**************************************************************************************/


	/**************************************************************************************/
	/* maxZeichenImTextArea  - Start */

	public static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

//    public default void dragAndDropRowsBetweenTwoTables(ControllerNewSitzung controllerNewSitzung,
//    		TableView<TabelleTraktandum> quellTable, TableView<TabelleTraktandum> zielTable,
//			boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben,
//			boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden) {
//
//				dummyHandler(zielTable);
//				dummyHandler(quellTable);
//
//				quellTable.setRowFactory(tv -> {
//					TableRow<TabelleTraktandum> row = new TableRow<>();
//
//					row.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//						@Override
//				        public void handle(MouseEvent t) {
//				        	TableRow<TabelleTraktandum> row = (TableRow<TabelleTraktandum>) t.getSource();
//				        	if (t.getClickCount() == 2 && (! row.isEmpty()) ) {
//				        		// TODO - wenn doppelklick, dann in TableView "Traktandenliste" kopieren
//
////				        		System.out.println(!gibtEsDenEintragSchon(quellTable, zielTable.getSelectionModel().getSelectedItem(), darfEsDenNeuenEintragMehrmalsGeben));
//
//					        	if(!gibtEsDenEintragSchon(zielTable, row.getItem(), darfEsDenNeuenEintragMehrmalsGeben)){
//					        		System.out.println("in");
//					        		controllerNewSitzung.geteTableViewTraktandenListe().addNewRow(row);
////					        		row.setDisable(true);
//					        		if(!quellTable.isFocused() || !zielTable.isFocused()){
//						        		dummyHandler(zielTable);
//										dummyHandler(quellTable);
//					        		}
//					        	}
//
//				            }
//
//				        }
//					});
//
//
//					row.hoverProperty().addListener((observable) -> {
//						final TabelleTraktandum person = row.getItem();
//
//						if (row.isHover() && person != null) {
//							row.setCursor(Cursor.CLOSED_HAND);
//						} else {
//							row.setCursor(Cursor.DEFAULT);
//					}
//				});
//
//				row.setOnDragDetected(event -> {
//					if (! row.isEmpty()) {
//						Integer index = row.getIndex();
//						Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
//						db.setDragView(row.snapshot(null, null));
//						ClipboardContent cc = new ClipboardContent();
//						cc.put(SERIALIZED_MIME_TYPE, index);
//						db.setContent(cc);
//						event.consume();
//					}
//				});
//
//				row.setOnDragOver(event -> {
//					Dragboard db = event.getDragboard();
//					if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//					//if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
//					    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//					    event.consume();
//					//}
//					}
//				});
//
//				row.setOnDragDropped(event -> {
//					Dragboard db = event.getDragboard();
//					if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//
//						TableView<TabelleTraktandum> targetTableView;
//
//						// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden
//						if(row.getTableView().isFocused()){
//							// sortieren
//							targetTableView = quellTable;
//						} else {
//							// neues Item
//							targetTableView = zielTable;
//						}
//
//						int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
//						TabelleTraktandum draggedObject = targetTableView.getItems().get(draggedIndex);
//
//
//						int dropIndex ;
//
//						if (row.isEmpty()) {
//						    dropIndex = targetTableView.getItems().size() ;
//						//    System.out.println("row.isEmpty(): " + dropIndex);
//						} else {
//						    dropIndex = row.getIndex();
//						//    System.out.println("nicht row.isEmpty(): " + dropIndex);
//						}
//
//						// Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
//						if(dropIndex > quellTable.getItems().size()){
//							dropIndex = quellTable.getItems().size();
//						}
//
//						System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + targetTableView.getItems().size());
//
//						if(darfQuellTableNeuenEintragAdden || row.getTableView().isFocused() && darfQuellTablePerDragAndDropSortiertWerden){
//							System.out.println(1);
//						    if (!gibtEsDenEintragSchon(quellTable, draggedObject, darfEsDenNeuenEintragMehrmalsGeben) || row.getTableView().isFocused()) {
//						    	System.out.println(2);
//						    	if(nachDropAusQuellTableRemoven || row.getTableView().isFocused()){
//						    		System.out.println(3);
//						        	draggedObject = targetTableView.getItems().remove(draggedIndex);
//						        }
//						    	System.out.println(4);
//								quellTable.getItems().add(dropIndex, draggedObject);
//
//							}
//						}
//
//						// Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die
//						// DragAndDrop funktion nicht mehr
//						dummyHandler(zielTable);
//						dummyHandler(quellTable);
//
//						event.setDropCompleted(true);
//						quellTable.getSelectionModel().select(dropIndex);
//						event.consume();
//					}
//				});
//
//				return row ;
//				});
//}

	public default boolean gibtEsDenEintragSchon(TableView<TabelleTraktandum> zielTable, TabelleTraktandum draggedObject, boolean darfEsDenNeuenEintragMehrmalsGeben){

		if(darfEsDenNeuenEintragMehrmalsGeben){
			return false;
		}

		for (int i = 0; i < zielTable.getItems().size(); i++) {

			String draggedObjectSignatur = draggedObject.getSignatur();
			String draggedObjectTraktandum = draggedObject.getTraktandum();

			String quellItemSignatur = zielTable.getItems().get(i).getSignatur();
			String quellItemTraktandum = zielTable.getItems().get(i).getTraktandum();

			System.out.println(draggedObjectSignatur + " = " + quellItemSignatur + "  &&  " + draggedObjectTraktandum + " = " + quellItemTraktandum);

			if (draggedObjectSignatur.equals(quellItemSignatur) && draggedObjectTraktandum.equals(quellItemTraktandum)) {
				System.out.println("return true");
				return true;
			}

//			if (zielTable.getItems().get(i).equals(draggedObject)) {
//				return true;
//			}
		}

		return false;
	}


	public default void dummyHandler(TableView<TabelleTraktandum> tableView1) {
		if(tableView1.getItems().size() <= 2){
			for (int i = 0; i < tableView1.getItems().size(); i++) {
			TabelleTraktandum items = tableView1.getItems().get(i);
			System.out.println("dummy");
				if(items.getSignatur().equals("") && items.getTraktandum().equals("")){
					System.out.println("remove dummy");
					tableView1.getItems().remove(i);
				}
			}
		}

		if(tableView1.getItems().isEmpty()){
			tableView1.getItems().add(new TabelleTraktandum(0, 0, "", false, ""));
		}
	}


	/* maxZeichenImTextArea  - Ende */
	/**************************************************************************************/

}
