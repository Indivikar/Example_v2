package app.example.FXML.TextBlocksWithFXMLPlaceHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FXUtil {

	private static String placeHolderKey = "#";
	
	private static Map<String, TextBlock> map = new HashMap<String, TextBlock>();
	private static ObservableMap<String, TextBlock> nodes = FXCollections.observableMap(map);

	public static ObservableMap<String, TextBlock> getAllChildren(Parent root) {
		nodes.clear();
	    addAllDescendents(root);
	    return nodes;
	}
	
	public static Set<Entry<String, TextBlock>> getDBTextBlock() {
		ObservableMap<String, TextBlock> keys = FXCollections.observableMap(map);

		keys.put("#button_1", new TextBlock("", "1", null, "Button", "Tab 1", "defaultText", "new Button Text", "", "new ToolTip Button 1", false));	
		keys.put("#button_2", new TextBlock("", "1", null, "Button", "Tab 1", "defaultText", "new Button Text", "", "new ToolTip Button 2", false));	
		keys.put("#buttonBar", new TextBlock("", "1", null, "ButtonBar", "Tab 1", "", "", "", "new ToolTip ButtonBar", false));
		keys.put("#checkBox", new TextBlock("", "1", null, "CheckBox", "Tab 1", "defaultText", "new CheckBox Text", "", "new ToolTip CheckBox", false));
		keys.put("#choiceBox", new TextBlock("", "1", null, "ChoiceBox", "Tab 1", "", "", "", "new ToolTip ChoiceBox", false));
		keys.put("#colorPicker", new TextBlock("", "1", null, "ColorPicker", "Tab 1", "", "", "", "new ToolTip ColorPicker", false));
		keys.put("#comboBox", new TextBlock("", "1", null, "ComboBox", "Tab 1", "defaultText", "", "new ComboBox PromptText", "new ToolTip ComboBox", false));
		keys.put("#datePicker", new TextBlock("", "1", null, "DataPicker", "Tab 1", "defaultText", "", "new DataPicker PromptText", "new ToolTip DataPicker", false));		
		keys.put("#label", new TextBlock("", "1", null, "Label", "Tab 1", "defaultText", "new Text", "", "new ToolTip Label", false));	
		keys.put("#label_2", new TextBlock("", "1", null, "Label 2", "Tab 1", "defaultText", "", "", "new ToolTip Label 2", false));
		keys.put("#listView", new TextBlock("", "1", null, "ListView", "Tab 1", "", "", "", "new ToolTip ListView", false));
		keys.put("#hyperLink", new TextBlock("", "1", null, "HyperLink", "Tab 1", "", "", "", "new ToolTip HyperLink", false));		
		keys.put("#menuButton", new TextBlock("", "1", null, "MenuButton", "Tab 1", "defaultText", "new MenuButton Text", "", "new ToolTip MenuButton", false));
		keys.put("#menuBar", new TextBlock("", "1", null, "MenuBar", "Tab 1", "", "", "", "new ToolTip MenuBar", false));
		keys.put("#menu_1", new TextBlock("", "1", null, "Menu", "Tab 1", "defaultText", "new Menu Edit", "", "", false));
		keys.put("#menuItem_1", new TextBlock("", "1", null, "MenuItem", "Tab 1", "defaultText", "new MenuItem Delete", "", "", false));
		keys.put("#passwordField", new TextBlock("", "1", null, "PasswordField", "Tab 1", "defaultText", "new PasswordField Text", 
				"new PasswordField PromptText", "new ToolTip PasswordField", false));
		keys.put("#progressBar", new TextBlock("", "1", null, "ProgressBar", "Tab 1", "", "", "", "new ToolTip ProgressBar", false));	
		keys.put("#progressIndicator", new TextBlock("", "1", null, "ProgressIndicator", "Tab 1", "", "", "", "new ToolTip ProgressIndicator", false));	
		keys.put("#radioButton", new TextBlock("", "1", null, "RadioButton", "Tab 1", "defaultText", "new RadioButton Text", "", "new ToolTip RadioButton", false));	
		keys.put("#spinner", new TextBlock("", "1", null, "Spinner", "Tab 1", "", "", "", "new ToolTip Spinner", false));
		keys.put("#tab1", new TextBlock("", "1", null, "Tab", "Tab 1", "defaultText", "Nodes", "", "new ToolTip Tab 1", false));	
		keys.put("#tableView", new TextBlock("", "1", null, "TableView", "Tab 1", "", "", "", "new ToolTip TableView", false));
		keys.put("#C1", new TextBlock("", "1", null, "TableColumn", "Tab 1", "defaultText", "Col 1", "", "", false));	
		keys.put("#C2", new TextBlock("", "1", null, "TableColumn", "Tab 1", "defaultText", "Col 2", "", "", true));
		keys.put("#textArea", new TextBlock("", "1", null, "TextArea", "Tab 1", "defaultText", "new TextArea Text", "new TextArea PromptText", "new ToolTip TextArea", false));
		keys.put("#textField", new TextBlock("", "1", null, "TextField", "Tab 1", "defaultText", "new TextField Text", "new TextField PromptText", "new ToolTip TextField", false));
		keys.put("#toggleButton", new TextBlock("", "1", null, "ToggleButton", "Tab 1", "defaultText", "new ToggleButton Text", 
				"new ToggleButton PromptText", "new ToolTip ToggleButton", false));		
		keys.put("#titlePane_1", new TextBlock("", "1", null, "TitlePane", "Tab 1", "defaultText", "TitlePane 1", "", "new ToolTip TitlePane 1", false));
		keys.put("#titlePane_2", new TextBlock("", "1", null, "TitlePane", "Tab 1", "defaultText", "TitlePane 2", "", "new ToolTip TitlePane 2", false));
		keys.put("#titlePane_3", new TextBlock("", "1", null, "TitlePane", "Tab 1", "defaultText", "TitlePane 3", "", "new ToolTip TitlePane 3", false));
		
	    return keys.entrySet();
	}
	
	

	private static void addAllDescendents(Parent parent) {

	    for (Node node : parent.getChildrenUnmodifiable()) {	    	
	    	instances(node);	            
	    }
	}
	
	public static <T> T castOrNull(Object obj, Class<T> clazz) {
	    if ( clazz.isAssignableFrom(obj.getClass()) ) {
	        return clazz.cast(obj);
	    } else {
	        return null;
	    }
	}

	private static <T> void instances(Node node) {

        if (node instanceof Accordion) {       	
        	Accordion accordion = (Accordion) node;         	     	
        	Tooltip tooltip = accordion.getTooltip();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = accordion.getTooltip().getText();
        		accordion.setTooltip(getNewToolTip(placeHolderToolTip));
			}       
        	       	
        	for (Node item : accordion.getPanes()) {		
    			if (item instanceof TitledPane) {    				
    				instances(item);
    			}
    		}
		}
    	

        if (node instanceof Button) {       	
        	Button button = (Button) node;  
        	Tooltip tooltip = button.getTooltip();
        	String placeHolderText = button.getText();

        	// if ToolTip set, search placeholder
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		// if placeholder founded true -> set text from list
        		// if placeholder founded false -> do nothing
        		String placeHolderToolTip = button.getTooltip().getText();
        		button.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				// search in the list, if there is a tooltip 
				// if true -> then add a tooltip
				// if false -> do nothing
				if (placeHolderText.startsWith(placeHolderKey)) {
					button.setTooltip(getNewToolTip(placeHolderText));
				}	
			}        	
        	button.setText(getNewText(placeHolderText));
		}
       
        if (node instanceof ButtonBar) {
        	ButtonBar buttonBar = (ButtonBar) node;  
        	Tooltip tooltip = buttonBar.getTooltip();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = buttonBar.getTooltip().getText();
        		buttonBar.setTooltip(getNewToolTip(placeHolderToolTip));
			}

        	for (Node item : buttonBar.getButtons()) {
				instances(item);
			}
		}
        
        if (node instanceof CheckBox) {
        	CheckBox checkBox = (CheckBox) node;  
        	Tooltip tooltip = checkBox.getTooltip();
        	String placeHolderText = checkBox.getText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = checkBox.getTooltip().getText();
        		checkBox.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					checkBox.setTooltip(getNewToolTip(placeHolderText));
				}
			}        	
        	checkBox.setText(getNewText(placeHolderText));	
		}
        
        if (node instanceof ChoiceBox<?>) {
        	ChoiceBox<?> choiceBox = (ChoiceBox<?>) node;  
        	Tooltip tooltip = choiceBox.getTooltip();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = choiceBox.getTooltip().getText();
        		choiceBox.setTooltip(getNewToolTip(placeHolderToolTip));
			}
		}
        
        if (node instanceof ColorPicker) {
        	ColorPicker colorPicker = (ColorPicker) node;  
        	Tooltip tooltip = colorPicker.getTooltip();
        	String placeHolderPromptText = colorPicker.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = colorPicker.getTooltip().getText();
        		colorPicker.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderPromptText.startsWith(placeHolderKey)) {
					colorPicker.setTooltip(getNewToolTip(placeHolderPromptText));
				}
			}      	
        	colorPicker.setPromptText(getNewPromptText(placeHolderPromptText));	
		}

        if (node instanceof ComboBox) {
        	ComboBox<?> comboBox = (ComboBox<?>) node;    
        	Tooltip tooltip = comboBox.getTooltip();
        	String placeHolderPromptText = comboBox.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = comboBox.getTooltip().getText();
        		comboBox.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderPromptText.startsWith(placeHolderKey)) {
					comboBox.setTooltip(getNewToolTip(placeHolderPromptText));
				}
			}
        	
        	comboBox.setPromptText(getNewPromptText(placeHolderPromptText));	
		}
//        
        if (node instanceof DatePicker) {
        	DatePicker datePicker = (DatePicker) node; 
        	Tooltip tooltip = datePicker.getTooltip();
        	String placeHolderPromptText = datePicker.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {              		
        		String placeHolderToolTip = datePicker.getTooltip().getText();
        		datePicker.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderPromptText.startsWith(placeHolderKey)) {
					datePicker.setTooltip(getNewToolTip(placeHolderPromptText));
				}
			}        	
        	datePicker.setPromptText(getNewPromptText(placeHolderPromptText));	
		}

        if (node instanceof Hyperlink) {
        	Hyperlink hyperlink = (Hyperlink) node;  
        	Tooltip tooltip = hyperlink.getTooltip();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = hyperlink.getTooltip().getText();
        		hyperlink.setTooltip(getNewToolTip(placeHolderToolTip));
			}
		}
        
        if (node instanceof Label) {
        	Label label = (Label) node;        
        	Tooltip tooltip = label.getTooltip();
        	String placeHolderText = label.getText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = label.getTooltip().getText();
        		label.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					label.setTooltip(getNewToolTip(placeHolderText));
				}	
			}        	
        	label.setText(getNewText(placeHolderText));      	
		}
           
        if (node instanceof MenuBar) {
        	MenuBar menuBar = (MenuBar) node;  
        	Tooltip tooltip = menuBar.getTooltip();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = menuBar.getTooltip().getText();
        		menuBar.setTooltip(getNewToolTip(placeHolderToolTip));
			} 
        	
        	System.out.println("MenuBar: " + menuBar.getMenus());
        	
        	for (Menu menu : menuBar.getMenus()) {
        		String placeHolderText = menu.getText();
        		menu.setText(getNewText(placeHolderText)); 
        		
        		for (MenuItem menuItem : menu.getItems()) {
            		String placeHolderText2 = menuItem.getText();
            		menuItem.setText(getNewText(placeHolderText2)); 
				}

			}
        	
        	
		}
        
        if (node instanceof MenuButton) {
        	MenuButton menuButton = (MenuButton) node;  
        	Tooltip tooltip = menuButton.getTooltip();
        	String placeHolderText = menuButton.getText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = menuButton.getTooltip().getText();
        		menuButton.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					menuButton.setTooltip(getNewToolTip(placeHolderText));
				}	
			}       	
        	menuButton.setText(getNewText(placeHolderText));		
		}

        if (node instanceof PasswordField) {
        	System.err.println("PasswordField");
        	PasswordField passwordField = (PasswordField) node;     
        	Tooltip tooltip = passwordField.getTooltip();
        	String placeHolderText = passwordField.getText();
        	String placeHolderPromptText = passwordField.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = passwordField.getTooltip().getText();
        		passwordField.setTooltip(getNewToolTip(placeHolderToolTip));      		
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					passwordField.setTooltip(getNewToolTip(placeHolderText));
				}	
			}       		
        	passwordField.setText(getNewText(placeHolderText));	
        	passwordField.setPromptText(getNewPromptText(placeHolderPromptText));
		}
        
        if (node instanceof ProgressBar) {
        	ProgressBar progressBar = (ProgressBar) node;  
        	Tooltip tooltip = progressBar.getTooltip();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = progressBar.getTooltip().getText();
        		progressBar.setTooltip(getNewToolTip(placeHolderToolTip));
			}
		}
        
        if (node instanceof ProgressIndicator) {
        	ProgressIndicator progressIndicator = (ProgressIndicator) node;  
        	Tooltip tooltip = progressIndicator.getTooltip();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = progressIndicator.getTooltip().getText();
        		progressIndicator.setTooltip(getNewToolTip(placeHolderToolTip));
			}
		}
        
        if (node instanceof RadioButton) {
        	RadioButton radioButton = (RadioButton) node;   
        	Tooltip tooltip = radioButton.getTooltip();
        	String placeHolderText = radioButton.getText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = radioButton.getTooltip().getText();
        		radioButton.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					radioButton.setTooltip(getNewToolTip(placeHolderText));
				}
			}
        	
        	radioButton.setText(getNewText(placeHolderText));	
		}

        if (node instanceof Spinner) {
        	Spinner spinner = (Spinner) node;         	     	
        	Tooltip tooltip = spinner.getTooltip();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = spinner.getTooltip().getText();
        		spinner.setTooltip(getNewToolTip(placeHolderToolTip));
			}   
		}
        
        
        if (node instanceof TextArea) {       	
        	TextArea textArea = (TextArea) node;       
        	Tooltip tooltip = textArea.getTooltip();
        	String placeHolderText = textArea.getText();
        	String placeHolderPromptText = textArea.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {              		
        		String placeHolderToolTip = textArea.getTooltip().getText();
        		textArea.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					textArea.setTooltip(getNewToolTip(placeHolderText));
				}					
			}       		
        	textArea.setText(getNewText(placeHolderText));	
        	textArea.setPromptText(getNewPromptText(placeHolderPromptText));
		}
        
        if (node instanceof TextField) {
        	TextField textField = (TextField) node;   
        	Tooltip tooltip = textField.getTooltip();
        	String placeHolderText = textField.getText();
        	String placeHolderPromptText = textField.getPromptText();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = textField.getTooltip().getText();        		
        		textField.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					textField.setTooltip(getNewToolTip(placeHolderText));
				}				
			}        		
        	textField.setText(getNewText(placeHolderText));	
        	textField.setPromptText(getNewPromptText(placeHolderPromptText));
		}
        
        if (node instanceof ToggleButton) {
        	ToggleButton toggleButton = (ToggleButton) node;    
        	Tooltip tooltip = toggleButton.getTooltip();
        	String placeHolderText = toggleButton.getText();
        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = toggleButton.getTooltip().getText();
        		toggleButton.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					toggleButton.setTooltip(getNewToolTip(placeHolderText));
				}	
			}      	
        	toggleButton.setText(getNewText(placeHolderText));	
      	}

        if (node instanceof TitledPane) {
        	TitledPane titledPane = (TitledPane) node;        
        	Tooltip tooltip = titledPane.getTooltip();
        	String placeHolderText = titledPane.getText();

        	
        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = titledPane.getTooltip().getText();
        		titledPane.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					titledPane.setTooltip(getNewToolTip(placeHolderText));
				}	
			}        	
        	
        	titledPane.setText(getNewText(placeHolderText));   
        	instances(titledPane.getContent());	
		}
        
        if (node instanceof TableView) {    	
        	TableView<?> tableView = (TableView<?>) node; 
        	Tooltip tooltip = tableView.getTooltip();

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {                		
        		String placeHolderToolTip = tableView.getTooltip().getText();
        		tableView.setTooltip(getNewToolTip(placeHolderToolTip));
			}         	      	
        	addTableColumns(tableView);
        }
       
        if (node instanceof TabPane) {
        	System.out.println("TabPane: " + node.getId());
        	TabPane tabPane = (TabPane) node;	        	
        	addTabs(tabPane.getTabs());
        }
        
//      if (node instanceof BorderPane) {        	
//        	nodes.add(new TextBlock("", "", "BorderPane", "", "", "", true));	
//		}
        
        if (node instanceof Parent) {
        	addAllDescendents((Parent)node);
        }

	}

	private static Tooltip getNewToolTip(String placeHolderText) {
		Tooltip tooltip = new Tooltip();

		if (placeHolderText.startsWith(placeHolderKey)) {
			System.err.println(placeHolderText + " -> nicht null");
			for (Entry<String, TextBlock> item : getDBTextBlock()) {						
				if (item.getKey().equals(placeHolderText)) {
					
					if (item.getValue().getToolTip().isEmpty()) {
						return null;
					}
					
					tooltip.setText(item.getValue().getToolTip());
				}
			}
		} 
//		else {
//			System.err.println(placeHolderText + " -> null");
//			return null;
//		}
		
		if (tooltip.getText().isEmpty()) {
			tooltip = null;
		}
		
		return tooltip;
	}
	
	
	
	private static void addTabs(ObservableList<Tab> observableList) {
		for (Tab tab : observableList) {		
			System.out.println("Tab: " + tab.getId());
			Tooltip tooltip = tab.getTooltip();
			String placeHolderText = tab.getText();
			tab.setText(getNewText(placeHolderText));

        	if (tooltip != null && tooltip.getText().startsWith(placeHolderKey)) {               		
        		String placeHolderToolTip = tab.getTooltip().getText();
        		tab.setTooltip(getNewToolTip(placeHolderToolTip));
			} else {
				if (placeHolderText.startsWith(placeHolderKey)) {
					tab.setTooltip(getNewToolTip(placeHolderText));
				}	
			}

			// if Tab has a graphic, then add graphic
			if (tab.getGraphic() instanceof Parent) {				
				addAllDescendents((Parent) tab.getGraphic());
			}
			
			if (tab.getGraphic() instanceof Control) {				
				instances(tab.getGraphic());
			}
						
			addAllDescendents((Parent) tab.getContent());
		}
	}
	
	private static String getNewPromptText(String placeHolderText) {
		if (placeHolderText.startsWith(placeHolderKey)) {
			for (Entry<String, TextBlock> item : getDBTextBlock()) {						
				if (item.getKey().equals(placeHolderText)) {
										
					if (item.getValue().isDefault()) {
						return item.getValue().getDefaultText();
					} 
					
					return item.getValue().getPromptText();
				}
			}
		}	
		
		return placeHolderText;
	}

	private static String getNewText(String placeHolderText) {
		if (placeHolderText.startsWith(placeHolderKey)) {
			for (Entry<String, TextBlock> item : getDBTextBlock()) {						
				if (item.getKey().equals(placeHolderText)) {
										
					if (item.getValue().isDefault()) {
						return item.getValue().getDefaultText();
					} 
					
					return item.getValue().getNewText();
				}
			}
		}	
		
		return placeHolderText;
	}
	
	private static void addTitledPane(Accordion accordion) {
		for (Node node : accordion.getChildrenUnmodifiable()) {			
			if (node instanceof Parent) {
				addAllDescendents((Parent) node);
			}
		}
	}
	
	private static void addTableColumns(TableView<?> tableView) {
		for (TableColumn<?, ?> column : tableView.getColumns()) {
			TableColumn<?, ?> col = (TableColumn<?, ?>) column;
			col.setText(getNewText(col.getText()));
		}
	}
	


}
