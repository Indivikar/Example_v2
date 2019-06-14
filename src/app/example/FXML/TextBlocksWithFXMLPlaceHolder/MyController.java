package app.example.FXML.TextBlocksWithFXMLPlaceHolder;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class MyController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    	columnSetDefault.setCellFactory(booleanCellFactory);
    	
    	columnID.setStyle( "-fx-alignment: CENTER;");
    	columnSetDefault.setStyle( "-fx-alignment: CENTER;");

        ObservableList<Map.Entry<String, TextBlock>> listData = FXCollections.observableArrayList(FXUtil.getDBTextBlock());   	
    	tableViewData.setItems(listData);
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
            checkBox.setDisable(true);
            
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(isEditing())                   	
                        commitEdit(newValue == null ? false : newValue);
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }
        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }
        public void commitEdit(Boolean value) {
            super.commitEdit(value);
            checkBox.setDisable(true);
        }
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);
            }
        }
    }
}
