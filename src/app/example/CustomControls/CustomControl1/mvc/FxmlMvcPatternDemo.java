package app.example.CustomControls.CustomControl1.mvc;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMvcPatternDemo extends Application
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Application.launch(FxmlMvcPatternDemo.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load
        (
            FxmlMvcPatternDemo.class.getResource("MainView.fxml"),
            ResourceBundle.getBundle(FxmlMvcPatternDemo.class.getPackage().getName()+".MainView")/*properties file*/
        );

        stage.setScene(new Scene(root));
        stage.show();
    }
}
