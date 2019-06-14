package app.example.FXML.TextBlocksWithFXMLPlaceHolder;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public class TextBlock {

	private SimpleStringProperty key;
	private SimpleStringProperty  ID;
	private Node node;
	private SimpleStringProperty  nodeCat;
	private SimpleStringProperty  specification;
	private SimpleStringProperty  defaultText;
	private SimpleStringProperty  newText;
	private SimpleStringProperty  promptText;
	private SimpleStringProperty  toolTip;
	private SimpleBooleanProperty  setDefault;
	
	public TextBlock(String key, String ID, Node node, String nodeCat, String specification, 
			String defaultText, String newText, String promptText, String toolTip, boolean setDefault) {
		this.key = new SimpleStringProperty(key);
		this.ID = new SimpleStringProperty(ID);
		this.node = node;	
		this.nodeCat = new SimpleStringProperty(nodeCat);			
		this.specification = new SimpleStringProperty(specification);
		this.defaultText = new SimpleStringProperty(defaultText);
		this.newText = new SimpleStringProperty(newText);
		this.promptText = new SimpleStringProperty(promptText);
		this.toolTip = new SimpleStringProperty(toolTip);
		this.setDefault = new SimpleBooleanProperty(setDefault);
	}

	// Getter
	public String getKey() {return key.get();}
	public String getID() {return ID.get();}
	public Node getNode() {return node;}
	public String getNodeCat() {return nodeCat.get();}
	public String getSpecification() {return specification.get();}
	public String getDefaultText() {return defaultText.get();}
	public String getNewText() {return newText.get();}
	public String getPromptText() {return promptText.get();}
	public String getToolTip() {return toolTip.get();}
	public boolean isDefault() {return setDefault.get();}

	// Setter
	public void setKey(String key) {this.key.set(key);}
	public void setID(String ID) {this.ID.set(ID);}
	public void setNode(Node node) {this.node = node;}
	public void setNodeCat(String nodeCat) {this.nodeCat.set(nodeCat);}
	public void setSpecification(String specification) {this.specification.set(specification);}
	public void setDefaultText(String defaultText) {this.defaultText.set(defaultText);}
	public void setNewText(String newText) {this.newText.set(newText);}
	public void setPromptText(String promptText) {this.promptText.set(promptText);}
	public void setToolTip(String toolTip) {this.toolTip.set(toolTip);}
	public void setDefault(boolean setDefault) {this.setDefault.set(setDefault);}

	@Override
	public String toString() {
		return "TextBlock [ID=" + ID + ", node=" + node + ", nodeCat=" + nodeCat + ", specification=" + specification
				+ ", defaultText=" + defaultText + ", newText=" + newText + ", setDefault=" + setDefault + "]";
	}
	
	
	
}
