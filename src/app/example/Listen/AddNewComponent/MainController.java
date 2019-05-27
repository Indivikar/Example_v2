package app.example.Listen.AddNewComponent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.layout.VBox;

public class MainController implements Initializable {

	@FXML private VBox vBoxListing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {    	
    	addNewComponent();
    }

	public void addNewComponent() {
    	ObservableList<Node> list = vBoxListing.getChildren();
		list.add(new Component(this, list.size()));
	}

    public void removeComponent(int number) {
    	ObservableList<Node> list = vBoxListing.getChildren();   
    	
    	// if only one componet in the list, then block remove
    	if (list.size() <= 1) {
			return;
		}
    	
    	list.remove(number);
   	
    	for (int i = 0; i < list.size(); i++) {
    		final int finalI = i;
    		Platform.runLater(() -> {
	    		Component component = (Component) list.get(finalI);
	    		component.setNumber(finalI);
    		});
		}
	}
    
} 