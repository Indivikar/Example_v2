package app.example.ComboBox.ComboBoxFilter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import app.example.ComboBox.ComboBoxFilter.controller.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MainDemo extends Application {

	// Config
	String propertiesFile = "config.properties";

	private Stage primaryStage;
	private boolean isFensterMax;
	private Double fensterPosX;
	private Double fensterPosY;
	File fileProperties;

	public MainDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	@Override
	public void init() throws Exception {
		System.out.println("--- vor Window-Start ---");

		// Start-Koordinaten für das Fenster
			fileProperties = new File(propertiesFile);
			erstellePropertiesFile(fileProperties);
	        if(fileProperties.exists()){
	            getStartKoordinatenPropertie();
	    	}
	}

	public void mainWindow(){
		System.out.println("--- Starte Window ---");
		try {
			FXMLLoader loader = new FXMLLoader(MainDemo.class.getResource("view/fxml/MainWindowView.fxml"));
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/fxml/MainWindowViewTableView.fxml"));
			AnchorPane root = loader.load();

			primaryStage.setTitle("Vorlage");
			primaryStage.getIcons().add(new Image(MainDemo.class.getResourceAsStream( "view/images/img.png" )));
			primaryStage.setMinWidth(400.00);
			primaryStage.setMinHeight(300.00);
//			primaryStage.initStyle(StageStyle.UNDECORATED); // ohne Fenster-Rahmen

			Scene scene = new Scene(root);
			scene.getStylesheets().add(MainDemo.class.getResource("view/css/MainWindowCSS.css").toExternalForm());

			// nur Verwenden, wenn es keinen FensterRahmen gibt
//	        ResizeListener listener = new ResizeListener(scene, primaryStage);
//	        scene.setOnMouseMoved(listener);
//	        scene.setOnMousePressed(listener);
//	        scene.setOnMouseDragged(listener);

			// letzte Position vom Fenster Speichern
			int ScreensPlusFensterWidth = getScreens(scene.getWidth());
			if(fensterPosX != null){
				if (ScreensPlusFensterWidth > fensterPosX) {

					if(isFensterMax){ // Wenn das Fenster Max. ist, muss man beim startpunkt 8 hinzugeben weil der Startpunkt sonst -8 ist
						fensterPosX = fensterPosX + 8;
						fensterPosY = fensterPosY + 8;
					}

					primaryStage.setX(fensterPosX);
					primaryStage.setY(fensterPosY);
				}

				primaryStage.setMaximized(isFensterMax);
			}

			MainWindowController mainWindowController = loader.getController();
			mainWindowController.setMainWindowStage(this, primaryStage);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("--- Window gestartet ---");
	}

	@Override
	public void stop() throws Exception {


		System.out.println("Vollbild: " + primaryStage.isMaximized());
        System.out.println("breite:" + primaryStage.getX());
        System.out.println("höhe:" + primaryStage.getY());

        if(fileProperties.exists()){
        	setStartKoordinatenPropertie(primaryStage.isMaximized(), primaryStage.getX(), primaryStage.getY());
        }

		System.out.println("--- Ausführen nachdem Window geschlossen wird ---");
		System.out.println("--- Ende ---");
	}


	public void setStartKoordinatenPropertie(boolean maxFenster, double x, double y)  {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(propertiesFile);

			// set the properties value
			prop.setProperty("maxFenster",  maxFenster + "");
			prop.setProperty("X", x + "");
			prop.setProperty("Y", y + "");

			prop.store( output, "Start-Koordinaten vom Fenster" );
			// save properties to project root folder

//			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public String getStartKoordinatenPropertie() throws IOException {

		String result = "";
		InputStream inputStream = null;

		try {
			Properties prop = new Properties();

			inputStream = new FileInputStream(propertiesFile);

			prop.load(inputStream);

			// get the property value and print it out
			String maxFenster = prop.getProperty("maxFenster");
			String x = prop.getProperty("X");
			String y = prop.getProperty("Y");

			isFensterMax = Boolean.valueOf(maxFenster);
			fensterPosX = Double.valueOf(x);
			fensterPosY = Double.valueOf(y);


		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

	private boolean erstellePropertiesFile(File file) {
        if (file != null) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating " + file.toString());
            }
            if (file.isFile() && file.canWrite() && file.canRead())
                return true;
        }
        return false;
    }

	private int getScreens(double sceneWidth) {

		ObservableList<Screen> screenList = Screen.getScreens();

		int countSizeX = 0;
		for (Screen screen : screenList) {
			countSizeX = (int) (countSizeX + screen.getVisualBounds().getWidth());
		}

		countSizeX = (int) (countSizeX - sceneWidth);
		return countSizeX;
    }

	public static void main(String[] args) {
		Locale.setDefault(Locale.GERMAN);
		launch(args);
	}
}
