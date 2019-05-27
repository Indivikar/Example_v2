package app.example.Grafik.SVGLoader;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

// https://github.com/skrb/SVGLoader

public class SVGLoaderDemo extends Application {

    @Override
    public void start(Stage stage) {
    	
        SVGContent content = SVGLoader.load(getClass().getResource("duke.svg").toString());
        Scene scene = new Scene(content, 1024, 768);

//        SVGContent content = SVGLoader.load(getClass().getResource("duke.svg").toString());
//        Group group = content.getGroup("0");
//        Scene scene = new Scene(group, 1024, 768);
        
//        SVGContent content = SVGLoader.load(getClass().getResource("duke.svg").toString());
//        Node node = content.getNode("0");        
//        Scene scene = new Scene(node, 1024, 768);
        
        stage.setScene(scene);
        stage.setTitle("SVGLoader Sample");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
