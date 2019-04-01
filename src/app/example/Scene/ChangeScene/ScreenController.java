package app.example.Scene.ChangeScene;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private VBox content;

    public ScreenController(VBox content) {
        this.content = content;
    }

    protected void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
    	content.getChildren().clear();
    	content.getChildren().add(screenMap.get(name));
    }
}