package app.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.NotificationPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import app.Start;
import app.functions.ReadExamplesFolder;
import app.models.App;
import app.view.function.IWindowMax;
import app.view.function.MyNotification;
import app.view.function.MyNotification.MyNotificationGraphic;
import app.view.function.MyNotificationPane;
import app.view.function.MyNotificationPane.MyNotificationPaneGraphic;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CContent implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	private JFXDrawersStack drawersStack;
	private JFXDrawer drawer;
	
	private MyNotification notification;
	private MyNotificationPane notificationPane;
	
	private ObservableList<App> dataAppMaster = FXCollections.observableArrayList();
	
	@FXML private AnchorPane shadowPane;
	@FXML private AnchorPane mainAnchorPane;
	
	@FXML private Button buttonWindowMin;
	@FXML private Button buttonWindowMax;
	@FXML private Button buttonWindowClose;
	
	@FXML private TextField textFieldBezeichnung;
	@FXML private Button buttonRemoveBezeichnung;
	@FXML private ComboBox<String> comboBoxKategorie;
	@FXML private Button buttonRemoveKategorie;
	
	@FXML private TableView<App> tableViewApps;
	@FXML private TableColumn<App, String> tableColumnBezeichnung;
	@FXML private TableColumn<App, String> tableColumnKategorie;
	
//	@FXML JFXDrawersStack drawersStack;
//	@FXML JFXDrawer drawer;
	
//	@FXML Button buttonDrawer;
	
	@FXML public void actionButtonWindowMin(){
		System.out.println("Window min");
		primaryStage.setIconified(true);
	}
	@FXML public void actionButtonWindowMax(){
		setWindowMaxIcon(primaryStage, buttonWindowMax, shadowPane, mainAnchorPane, Start.hasShadowPane);
	}
	@FXML public void actionButtonWindowClose(){
		primaryStage.close();
		Platform.exit();
		System.exit(0);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.notification = new MyNotification();
		this.notificationPane = new MyNotificationPane();
		
		addTableView();
	}
	
	
	private <T> void addTableView() {
		
		 dataAppMaster = new ReadExamplesFolder().getAppsList();
		 addFilterFunktion();
		
		tableColumnBezeichnung.setCellValueFactory(new PropertyValueFactory<App, String>("bezeichnung"));
		tableColumnKategorie.setCellValueFactory(new PropertyValueFactory<App, String>("kategorie"));
		
		tableViewApps.setOnMousePressed(new EventHandler<MouseEvent>() {
    	    @Override 
    	    public void handle(MouseEvent event) {
    	        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
    	        	
//    	        	start.getControllerDrawerContent().setDaten(tableViewDokumente.getSelectionModel().getSelectedItem());
    	        	
//    	        	Class c = null;
//					try {
//						c = Class.forName("app.examples.Events.MousePosition.DrawLineOnMouseDragDemo");
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} 
//    	        	Method m = null;
//					try {
//						m = c.getMethod("main", String.class, String.class, String.class);
//					} catch (NoSuchMethodException | SecurityException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} //The method has 3 String paramaters so I have to intialize it otherwise it will produce an error that the method was not found.
//    	        	Object t = null;
//					try {
//						t = c.newInstance();
//					} catch (InstantiationException | IllegalAccessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//    	        	try {
//						m.invoke(t, "Hallo");
//					} catch (IllegalAccessException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IllegalArgumentException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (InvocationTargetException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} //Now invoke the method with the value or paramaters.
    	        	
					String className = "app.examples.Events.MousePosition.DrawLineOnMouseDragDemo";
					try {
						Class<T> c = (Class<T>) Class.forName(className);
						T castToT = c.cast("main");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
    	        	
//    	        	if (drawer.isClosed()) {
//    	        		drawersStack.toggle(drawer);
//					}
    	        	
//    	            System.out.println(tableViewDokumente.getSelectionModel().getSelectedItem().getDokument());  
//    	            System.out.println(tableViewDokumente.getSelectionModel().getSelectedItem().getVorname());
//    	            System.out.println(tableViewDokumente.getSelectionModel().getSelectedItem().getNachname());
    	        }
    	    }
    	}); 
		
	}
	
	private void setFilter(FilteredList<App> filteredData) {
	      String bezeichnung = textFieldBezeichnung.getText();
	      String kategorie = comboBoxKategorie.getSelectionModel().getSelectedItem();

	      filteredData.setPredicate (app -> {
	    	  
		        boolean boolBezeichnung = false;
		        if (bezeichnung == null || bezeichnung.isEmpty()) {
		        	boolBezeichnung = true;
		        } else {
		        	boolBezeichnung = app.getBezeichnung().toLowerCase().contains(bezeichnung.toLowerCase());
		        }
		        
		        boolean boolKategorie = false;
		        if (kategorie == null) {
		        	boolKategorie = true;
		        } else {
		        	boolKategorie = app.getKategorie().toLowerCase().contains(kategorie.toLowerCase());	        	
		        }
		        
		        return boolBezeichnung && boolKategorie;
	      });
	    }
	    
	
	private SortedList<App> addFilterFunktion() {
		FilteredList<App> filteredData = new FilteredList<>(dataAppMaster, p -> true);
		
		
		
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<App> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableViewApps.comparatorProperty());
        tableViewApps.setItems(sortedData);
        
		
        textFieldBezeichnung.textProperty().addListener((observable, oldValue, newValue) -> {
			setFilter(filteredData);
			tableViewApps.refresh();
		});

		comboBoxKategorie.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			setFilter(filteredData);			
			tableViewApps.refresh();
	    });
				
		return sortedData;
	}
	
	private void addActionButtons() {
//		Platform.runLater(()->{
//			buttonDrawer.setOnAction(e -> {
//				drawersStack.toggle(drawer);				
//			});
//		});
			
	}
	
//	public void showNotificationPane(Stage stage) {
//	    Scene scene = stage.getScene();
//	    Parent pane = scene.getRoot();
//	    if (!(pane instanceof NotificationPane)){
//	        NotificationPane notificationPane = new NotificationPane(pane);
//	        
//	        scene = new Scene(notificationPane, scene.getWidth(), scene.getHeight());
//	        stage.setScene(scene);
//	        notificationPane.show();
//	    } else {
//	        ((NotificationPane)pane).show();
//	    }
//	}
	
	
	// Getter
	public Button getButtonWindowMax() {return buttonWindowMax;}
	
	// Setter
	public void set(Start start, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;		
		this.drawersStack = start.getControllerDrawersStack().getDrawersStack();
		this.drawer = start.getDrawer();

		addActionButtons();
		
	}

}
