package app.example.Mouse.MoveMouse;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MoveMouseDemo extends Application {

Scene scene;
VBox container;
Button moveMouse;
Button showHideCursor;
public static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
public static int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

@Override
public void start(Stage stage) throws Exception {

    // MoveMouse Button
    moveMouse = new Button("Move Cursor to the center of Screen");
    moveMouse.setOnAction(m -> {
        moveCursor(screenWidth/2, screenHeight/2);
    });

    // ShowHide Cursor
    showHideCursor = new Button("Show/Hide Cursor");
    showHideCursor.setCursor(Cursor.HAND);
    showHideCursor.setOnAction(m -> {
        if (scene.getCursor() != Cursor.NONE)
            scene.setCursor(Cursor.NONE);
        else
            scene.setCursor(Cursor.DEFAULT);
    });

    // Container
    container = new VBox();
    container.getChildren().addAll(moveMouse, showHideCursor);

    // Scene
    scene = new Scene(container, 500, 500);

    stage.setScene(scene);
    stage.show();
}

/**
 * Move the mouse to the specific screen position
 * 
 * @param x
 * @param y
 */
public void moveCursor(int screenX, int screenY) {
    Platform.runLater(() -> {
        try {
            Robot robot = new Robot();
            robot.mouseMove(screenX, screenY);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });
}

public static void main(String[] args) {
    launch(args);
}

}
