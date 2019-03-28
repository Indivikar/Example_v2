package app.controller;


import java.awt.Desktop;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Start;
import app.models.App;
import app.view.function.IWindowMax;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CDrawerContent implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	
	private SimpleObjectProperty<App> propSelectedItem = new SimpleObjectProperty<App>();
//	private App selectedItem;
	
	@FXML private Label labelBezeichnung;
	@FXML private Label labelKategorie;
	@FXML private Button buttonDrawerClose;
	@FXML private Button buttonRunDemo;
	@FXML private Button buttonShowSourceCode;
	@FXML private Label labelBeschreibung;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addListeners();
		addButtonActions();
	}

	private void addListeners() {
		propSelectedItem.addListener((ob, oldVal, newVal) -> {
			System.out.println("Listener");
			labelBezeichnung.setText(propSelectedItem.get().getBezeichnung());
			labelKategorie.setText(propSelectedItem.get().getKategorie());
			labelBeschreibung.setText(propSelectedItem.get().getReadMe());
		});
	}
	
	
	private void addButtonActions() {
		buttonDrawerClose.setOnAction(e -> {
			start.getDrawer().close();
		});		

		buttonRunDemo.setOnAction(e -> {
			runDemo();
		});	
		
		buttonShowSourceCode.setOnAction(e -> {
			showSourceCode();
		});	
		
	}
	
	private void showSourceCode() {
		
			String code = propSelectedItem.get().getSourceCode();
		
			if (code.startsWith("http://") || code.startsWith("https://")) {
				try {
				      URI uri = new URI(code);
				      Desktop desktop = null;
				      if (Desktop.isDesktopSupported()) {
				        desktop = Desktop.getDesktop();
				      }
		
				      if (desktop != null) {
				    	  desktop.browse(uri);
				      }
				        
			    } catch (IOException ioe) {
			      ioe.printStackTrace();
			    } catch (URISyntaxException use) {
			      use.printStackTrace();
			    }
			}
	}
	
	private void runDemo() {
    	try {
			String className = propSelectedItem.get().getClassName();
	//		System.out.println("className: " + className);
	//		String className = "app.examples.Events.MousePosition.DrawLineOnMouseDragDemo";
			if (className != null && !className.equals("")) {
				Class<?> clazz = Class.forName(className);
				Constructor<?> dogConstructor = clazz.getConstructor();
				Object dog = dogConstructor.newInstance();
			} else {
				System.err.println("es konnte kein Klassen-Name gefunden werden");
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Getter
	public App getSelectedItem() {return propSelectedItem.get();}
	
	// Setter
	public void setSelectedItem(App propSelectedItem) {this.propSelectedItem.set(propSelectedItem);}
	
	
	public void set(Start start, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;		
	}
	

}
