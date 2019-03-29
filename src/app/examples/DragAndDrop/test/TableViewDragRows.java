package DragAndDrop.test;

import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableViewDragRows extends Application {

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView1 = new TableView<>();
        tableView1.getColumns().add(createCol("First Name", Person::firstNameProperty, 150));
        tableView1.getColumns().add(createCol("Last Name", Person::lastNameProperty, 150));
        tableView1.getColumns().add(createCol("Email", Person::emailProperty, 200));

        tableView1.getItems().addAll(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );

        TableView<Person> tableView2 = new TableView<>();
        tableView2.getColumns().add(createCol("First Name", Person::firstNameProperty, 150));
        tableView2.getColumns().add(createCol("Last Name", Person::lastNameProperty, 150));
        tableView2.getColumns().add(createCol("Email", Person::emailProperty, 200));

        tableView2.getItems().addAll(
            new Person("J", "S", "jacob.smith@example.com"),
            new Person("I", "J", "isabella.johnson@example.com"),
            new Person("E", "W", "ethan.williams@example.com"),
            new Person("E", "J", "emma.jones@example.com"),
            new Person("M", "B", "michael.brown@example.com")
        );

    	// boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben,
        // boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden
    	dragAndDropRowsBetweenTwoTables(tableView1, tableView2, false, false, false, false);
    	dragAndDropRowsBetweenTwoTables(tableView2, tableView1, false, false, true, true);


        HBox hbox = new HBox(tableView1, tableView2);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dragAndDropRowsBetweenTwoTables(TableView<Person> quellTable, TableView<Person> zielTable,
    							boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben,
    							boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden) {

    	quellTable.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();

		    row.hoverProperty().addListener((observable) -> {
		        final Person person = row.getItem();

		        if (row.isHover() && person != null) {
		        	row.setCursor(Cursor.CLOSED_HAND);
		        } else {
		        	row.setCursor(Cursor.DEFAULT);
		        }
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
//                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
//                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {

                	TableView<Person> targetTableView;

                	// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden
                	if(row.getTableView().isFocused()){
                		// sortieren
                		targetTableView = quellTable;
                    } else {
                    	// neues Item
                    	targetTableView = zielTable;
					}

                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Person draggedPerson = targetTableView.getItems().get(draggedIndex);


                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = targetTableView.getItems().size() ;
//                        System.out.println("row.isEmpty(): " + dropIndex);
                    } else {
                        dropIndex = row.getIndex();
//                        System.out.println("nicht row.isEmpty(): " + dropIndex);
                    }

                    // Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
                    if(dropIndex > quellTable.getItems().size()){
                    	dropIndex = quellTable.getItems().size();
                    }

//                    System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + tableView2.getItems().size());

                    if(darfQuellTableNeuenEintragAdden || row.getTableView().isFocused() && darfQuellTablePerDragAndDropSortiertWerden){
	                    if (!gibtEsDenEintragSchon(quellTable, draggedPerson, darfEsDenNeuenEintragMehrmalsGeben) || row.getTableView().isFocused()) {

	                    	if(nachDropAusQuellTableRemoven || row.getTableView().isFocused()){
		                    	draggedPerson = targetTableView.getItems().remove(draggedIndex);
		                    }
							quellTable.getItems().add(dropIndex, draggedPerson);

						}
                    }

                    // Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die
                    // DragAndDrop funktion nicht mehr
                    dummyHandler(zielTable);
                    dummyHandler(quellTable);

                    event.setDropCompleted(true);
                    quellTable.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });
	}

    private boolean gibtEsDenEintragSchon(TableView<Person> quellTable, Person draggedPerson, boolean darfEsDenNeuenEintragMehrmalsGeben){

    	if(darfEsDenNeuenEintragMehrmalsGeben){
    		return false;
    	}

    	for (int i = 0; i < quellTable.getItems().size(); i++) {

        	if (quellTable.getItems().get(i).equals(draggedPerson)) {
				return true;
			}
		}

		return false;
    }


    private void dummyHandler(TableView<Person> tableView1) {
        if(tableView1.getItems().size() <= 2){
            for (int i = 0; i < tableView1.getItems().size(); i++) {
				Person items = tableView1.getItems().get(i);
				System.out.println("dummy");
				if(items.getFirstName().equals("") && items.getLastName().equals("") && items.getEmail().equals("")){
					System.out.println("remove dummy");
					tableView1.getItems().remove(i);
				}
			}
        }

		if(tableView1.getItems().isEmpty()){
			tableView1.getItems().add(new Person("", "", ""));
		}
	}

    private TableColumn<Person, String> createCol(String title,
            Function<Person, ObservableValue<String>> mapper, double size) {

        TableColumn<Person, String> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> mapper.apply(cellData.getValue()));
        col.setPrefWidth(size);

        return col ;
    }


   public class Person {
        private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
        private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
        private final StringProperty email = new SimpleStringProperty(this, "email");

        public Person(String firstName, String lastName, String email) {
            this.firstName.set(firstName);
            this.lastName.set(lastName);
            this.email.set(email);
        }

        public final StringProperty firstNameProperty() {
            return this.firstName;
        }

        public final String getFirstName() {
            return this.firstNameProperty().get();
        }

        public final void setFirstName(final String firstName) {
            this.firstNameProperty().set(firstName);
        }

        public final StringProperty lastNameProperty() {
            return this.lastName;
        }

        public final String getLastName() {
            return this.lastNameProperty().get();
        }

        public final void setLastName(final String lastName) {
            this.lastNameProperty().set(lastName);
        }

        public final StringProperty emailProperty() {
            return this.email;
        }

        public final String getEmail() {
            return this.emailProperty().get();
        }

        public final void setEmail(final String email) {
            this.emailProperty().set(email);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
