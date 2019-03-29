package app.examples.Web.WebViewHintergrundPartikleEffekt;

import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
 * 		Config des Particles Effekt's
 *		auf der Website Configurieren "http://vincentgarreau.com/particles.js/" -> dann auf Download current config (json)
 *		Inhalt der "particlesjs-config.json" kopieren und in der Datei "app.js", in die Funktion "particlesJS()" einfügen und ersetzen
 *		Hintergrund-Farbe muss in der "Style.css" geändert werden
 *		Das Video dazu -> https://www.youtube.com/watch?v=hJqa_d6xtpg
 * */

public class WebViewHintergrundPartikleEffektDemo extends Application {
    private Scene scene;
    
    public WebViewHintergrundPartikleEffektDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
    
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");

        StackPane root = new StackPane();

        Browser browser = new Browser();
        Button button = new Button("Button");

        StackPane.setAlignment(button, Pos.CENTER);
        root.getChildren().addAll(browser, button);

        scene = new Scene(root,750,500);
        stage.setScene(scene);
//        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        final URL urlHello = getClass().getResource("index.html");
        webEngine.load(urlHello.toExternalForm());
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
