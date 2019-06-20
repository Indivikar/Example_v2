package app.example.FXML.TextBlocksWithFXMLPlaceHolder;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MyController implements Initializable {

	@FXML private TextField textFieldSearch;
	@FXML private Button buttonDelTextFieldSearch;
	@FXML private ComboBox<String> comboBoxNodeCat;
	@FXML private Button buttonDelComboBoxNodeCat;
	@FXML private ComboBox<String> comboBoxSpecification;
	@FXML private Button buttonDelComboBoxSpecification;
	
	@FXML private Label label;
	
	@FXML private TableView<Map.Entry<String, TextBlock>> tableViewData;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnKey;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnID;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnNodeCat;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnSpecification;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnDefaultText;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnNewText;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnPromptText;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, String> columnToolTip;
	@FXML private TableColumn<Map.Entry<String, TextBlock>, Boolean> columnSetDefault;
	
	private ObservableList<Map.Entry<String, TextBlock>> masterListData;
	private FilteredList<Entry<String, TextBlock>> filteredList;
	private SortedList<Entry<String, TextBlock>> sortedList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	masterListData = FXCollections.observableArrayList(FXUtil.getDBTextBlock());
    	sortedList = getFilterList();
    	
    	addButtonActions();
    	setComboBoxProps();
    	addComboBoxData(masterListData);
    	setTableViewProps();
    	setListeners();

    }

    private void addButtonActions() {
    	buttonDelTextFieldSearch.setOnAction(e -> {
    		textFieldSearch.setText("");
    	});

    	buttonDelComboBoxNodeCat.setOnAction(e -> {
    		comboBoxNodeCat.valueProperty().set(null);
    	});
    	
    	buttonDelComboBoxSpecification.setOnAction(e -> {
    		comboBoxSpecification.valueProperty().set(null);
    	});
	}
    
    private void setComboBoxProps() {
    	comboBoxNodeCat.setButtonCell(cell(comboBoxNodeCat).call(null));
    	comboBoxNodeCat.setCellFactory(cell(comboBoxNodeCat));
	
    	comboBoxSpecification.setButtonCell(cell(comboBoxSpecification).call(null));
    	comboBoxSpecification.setCellFactory(cell(comboBoxSpecification));
	}
    
    
    private Callback<ListView<String>, ListCell<String>> cell(ComboBox<String> comboBox) {
	  
	   Callback<ListView<String>, ListCell<String>> cellNodeCat = new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				return new ListCell<String>() {
		            @Override
		            protected void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item == null || empty) {
		                	setText(comboBox.getPromptText());
		                    setGraphic(null);		                    
		                } else {
							setText(item);
		                    setGraphic(null);	
		                }
		            }
		        } ;
			}
	   };

	   return cellNodeCat;
   	}
    
    private void setListeners() {
        textFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			setFilter(filteredList);
			tableViewData.refresh();
        });
    	
    	comboBoxNodeCat.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			setFilter(filteredList);
			tableViewData.refresh();
		});
    	
    	comboBoxSpecification.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			setFilter(filteredList);
			tableViewData.refresh();
		});
	}
    
    private void setFilter(FilteredList<Entry<String, TextBlock>> filteredData) {

    	String textField = textFieldSearch.getText();
        String nodeCat = comboBoxNodeCat.getSelectionModel().getSelectedItem();
        String specification = comboBoxSpecification.getSelectionModel().getSelectedItem();

        filteredData.setPredicate (item -> {

  	        boolean boolTextField = false;
  	        if (textField == null || textField.isEmpty()) {
  	        	boolTextField = true;
  	        } else {
  	        	String lowerCaseFilter = textField.toLowerCase();
                if (item.getValue().getKey().toLowerCase().contains(lowerCaseFilter)) {
                	boolTextField = true; // Filter matches first name.
                } else if (item.getValue().getDefaultText().toLowerCase().contains(lowerCaseFilter)) {
                	boolTextField =  true; // Filter matches last name.
                } else if (item.getValue().getNewText().toLowerCase().contains(lowerCaseFilter)) {
                	boolTextField =  true; // Filter matches last name.
                } else if (item.getValue().getPromptText().toLowerCase().contains(lowerCaseFilter)) {
                	boolTextField =  true; // Filter matches last name.
                } else if (item.getValue().getToolTip().toLowerCase().contains(lowerCaseFilter)) {
                	boolTextField =  true; // Filter matches last name.
                } else {
                	boolTextField = false;
                }
  	        }
        	
  	        boolean boolNodeCat = false;
  	        if (nodeCat == null || nodeCat.isEmpty()) {
  	        	boolNodeCat = true;
  	        } else {
  	        	boolNodeCat = item.getValue().getNodeCat().toLowerCase().equals(nodeCat.toLowerCase());
  	        }
  	        
  	        boolean boolSpecification= false;
  	        if (specification == null || specification.isEmpty()) {
  	        	boolSpecification = true;
  	        } else {
  	        	boolSpecification = item.getValue().getSpecification().toLowerCase().equals(specification.toLowerCase());
  	        }
 
  	        return boolTextField && boolNodeCat && boolSpecification;
        });
      }
    
    
    private void setTableViewProps() {
    	Callback<TableColumn<Map.Entry<String, TextBlock>, Boolean>, TableCell<Map.Entry<String, TextBlock>, Boolean>> booleanCellFactory = 
                new Callback<TableColumn<Map.Entry<String, TextBlock>, Boolean>, TableCell<Map.Entry<String, TextBlock>, Boolean>>() {
                @Override
                    public TableCell<Map.Entry<String, TextBlock>, Boolean> call(TableColumn<Map.Entry<String, TextBlock>, Boolean> p) {
                        return new BooleanCell();
                }
            };
    	   	
    	columnKey.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
         	new SimpleStringProperty(p.getValue().getKey()));
    	columnID.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
     		new SimpleStringProperty(p.getValue().getValue().getID()));
    	columnNodeCat.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getNodeCat()));
    	columnSpecification.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getSpecification()));
    	columnDefaultText.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getDefaultText()));    	
    	columnNewText.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getNewText()));	
    	columnPromptText.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getPromptText()));
    	columnToolTip.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, String> p) -> 
    		new SimpleStringProperty(p.getValue().getValue().getToolTip()));
    	columnSetDefault.setCellValueFactory((TableColumn.CellDataFeatures<Map.Entry<String, TextBlock>, Boolean> p) -> 
    		new SimpleBooleanProperty(p.getValue().getValue().isDefault()));

    	// Set Cell Editable   	
    	columnNewText.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnNewText.setOnEditCommit((CellEditEvent<Map.Entry<String, TextBlock>, String> t) -> {
            t.getTableView().getItems().get(t.getTablePosition().getRow())
                    .getValue().setNewText(t.getNewValue());
    	});
    	
    	columnPromptText.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnPromptText.setOnEditCommit((CellEditEvent<Map.Entry<String, TextBlock>, String> t) -> {
            t.getTableView().getItems().get(t.getTablePosition().getRow())
                    .getValue().setNewText(t.getNewValue());
    	});
    	
    	columnToolTip.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnToolTip.setOnEditCommit((CellEditEvent<Map.Entry<String, TextBlock>, String> t) -> {
            t.getTableView().getItems().get(t.getTablePosition().getRow())
                    .getValue().setNewText(t.getNewValue());
    	});
    	
    	// set CheckBox
    	columnSetDefault.setCellFactory(booleanCellFactory); 	
    	
    	// set CellText center
    	columnID.setStyle( "-fx-alignment: CENTER;");
    	columnSetDefault.setStyle( "-fx-alignment: CENTER;");

    	tableViewData.setItems(sortedList);

	}
    
    private void addComboBoxData(ObservableList<Entry<String, TextBlock>> list) {
    	ObservableSet<String> setListNodeCat = FXCollections.observableSet(new HashSet<>());
    	ObservableSet<String> setListSpecification = FXCollections.observableSet(new HashSet<>());

    	for (Entry<String, TextBlock> item : list) {
    		if (item.getValue().getNodeCat() != null ) {
				setListNodeCat.add(item.getValue().getNodeCat());
			}
    		
    		setListSpecification.add(item.getValue().getSpecification()); 		
		}
    	
    	ObservableList<String> listNodeCat = FXCollections.observableArrayList(setListNodeCat);
    	ObservableList<String> listSpecification = FXCollections.observableArrayList(setListSpecification);
    	
    	Collections.sort(listNodeCat);
    	Collections.sort(listSpecification);

//    	setComboBoxProps();       
        comboBoxNodeCat.setItems(listNodeCat);   	
        comboBoxSpecification.setItems(listSpecification);

	}
    
    private SortedList<Map.Entry<String, TextBlock>> getFilterList() {
    	
    	filteredList = new FilteredList<>(masterListData, p -> true);    

        SortedList<Map.Entry<String, TextBlock>> sortedData = new SortedList<>(filteredList);      
        sortedData.comparatorProperty().bind(tableViewData.comparatorProperty());

		return sortedData;
	}
    
    
	public void setTextBlocks(Parent root) {
		ObservableMap<String, TextBlock> list = FXUtil.getAllChildren(root);
        for (Entry<String, TextBlock> item : list.entrySet()) {
        	System.out.println(item.getKey() + " -> " + item.getValue().toString());	
		}		
	}
	
	class BooleanCell extends TableCell<Map.Entry<String, TextBlock>, Boolean> {
        private CheckBox checkBox;
        public BooleanCell() {
            checkBox = new CheckBox();
//            checkBox.setDisable(true);
            
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                	getTableView().getItems().get(getIndex()).getValue().setDefault(newValue);
                	commitEdit(newValue == null ? false : newValue);                       
                }
            });

            this.setEditable(true);
        }
        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
//            checkBox.setDisable(false);
            checkBox.requestFocus();
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }
        public void commitEdit(Boolean value) {
            super.commitEdit(value);
//            checkBox.setDisable(true);
        }
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                this.setGraphic(checkBox);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                checkBox.setSelected(item);
            } else {
            	setGraphic(null);
            }
        }
    }
}
