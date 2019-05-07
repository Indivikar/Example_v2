package app.example.TableView.SortTableViewWithDragNDrop;

import java.util.ArrayList;
import java.util.function.Function;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableViewDragRowsDemo extends Application {

	public TableViewDragRowsDemo() {
		Platform.runLater(() -> {           
		           start(new Stage());		       
		 });
	}
	
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
    private ArrayList<Person> selections = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView = new TableView<>();
        tableView.getColumns().add(createCol("First Name", Person::firstNameProperty, 150));
        tableView.getColumns().add(createCol("Last Name", Person::lastNameProperty, 150));
        tableView.getColumns().add(createCol("Email", Person::emailProperty, 200));
        tableView.getColumns().add(createCol("Country", Person::countryProperty, 200));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getItems().addAll(
            new Person("Jacob", "Smith", "jacob.smith@example.com","A"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com","A"),
            new Person("Ethan", "Williams", "ethan.williams@example.com","A"),
            new Person("Emma", "Jones", "emma.jones@example.com","B"),
            new Person("da", "Jones", "emma.jones@example.com","B"),
            new Person("csd", "Jones", "emma.jones@example.com","B"),
            new Person("dsf", "Jones", "emma.jones@example.com","B"),
            new Person("fsd", "Jones", "emma.jones@example.com","B"),
            new Person("feferef", "Jones", "emma.jones@example.com","B"),
            new Person("Michael", "Brown", "michael.brown@example.com","C"),
            new Person("XMan", "Brown", "michael.brown@example.com","C"),
            new Person("ZMan", "Brown", "michael.brown@example.com","D"),
            new Person("YMan", "Brown", "michael.brown@example.com","D"),
            new Person("DDDMan", "Brown", "michael.brown@example.com","D")
        );

        tableView.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();

                    selections.clear();//important...

                    ObservableList<Person> items = tableView.getSelectionModel().getSelectedItems();

                    for(Person iI:items) {
                        selections.add(iI);
                    }


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
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();

                if (db.hasContent(SERIALIZED_MIME_TYPE)) {

                    int dropIndex;Person dI=null; 

                    if (row.isEmpty()) {
                        dropIndex = tableView.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                        dI = tableView.getItems().get(dropIndex);
                    }
                    int delta=0;
                    if(dI!=null)
                    while(selections.contains(dI)) {
                        delta=1;
                        --dropIndex;
                        if(dropIndex<0) {
                            dI=null;dropIndex=0;
                            break;
                        }
                        dI = tableView.getItems().get(dropIndex);
                    }

                    for(Person sI:selections) {
                        tableView.getItems().remove(sI);
                    }

                    if(dI!=null)
                        dropIndex=tableView.getItems().indexOf(dI)+delta;
                    else if(dropIndex!=0)
                        dropIndex=tableView.getItems().size();



                    tableView.getSelectionModel().clearSelection();

                    for(Person sI:selections) {
                        //draggedIndex = selections.get(i);
                        tableView.getItems().add(dropIndex, sI);
                        tableView.getSelectionModel().select(dropIndex);
                        dropIndex++;

                    }

                    event.setDropCompleted(true);
                    selections.clear();
                    event.consume();
                }
            });

            return row ;
        });


        Scene scene = new Scene(new BorderPane(tableView), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
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
        private final StringProperty country = new SimpleStringProperty(this, "country");;

        public Person(String firstName, String lastName, String email, String country) {
            this.firstName.set(firstName);
            this.lastName.set(lastName);
            this.email.set(email);
            this.country.set(country);
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
        public final StringProperty countryProperty() {
            return this.country;
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