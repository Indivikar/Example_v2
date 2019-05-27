package app.example.Grafik.SVGColorPicker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class ColorPickerDemo extends Application {
   
	public ColorPickerDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ColorPickerSample");

        Scene scene = new Scene(new VBox(20), 300, 300);
        //scene.getStylesheets().add("colorpickersample/ControlStyle.css");
        scene.setFill(Color.web("#ccffcc"));
        VBox box = (VBox) scene.getRoot();

        ToolBar tb = new ToolBar();
        box.getChildren().add(tb);
        
        final ColorPicker colorPicker = new ColorPicker();  
        colorPicker.setValue(Color.CORAL);
        tb.getItems().addAll(colorPicker);

        StackPane stack = new StackPane();
        box.getChildren().add(stack);

        final SVGPath svg = new SVGPath();
        svg.setContent("M70,50 L90,50 L120,90 L150,50 L170,50"
            + "L210,90 L180,120 L170,110 L170,200 L70,200 L70,110 L60,120 L30,90"
            + "L70,50");
        svg.setStroke(Color.DARKGREY);
        svg.setStrokeWidth(2);
        svg.setEffect(new DropShadow());
        svg.setFill(colorPicker.getValue());
        stack.getChildren().addAll(svg);

        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                svg.setFill(colorPicker.getValue());                    
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}