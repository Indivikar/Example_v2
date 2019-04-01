package app.example.Charts.Pie_Charts;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_Pie_ChartsDemo extends Application {
 
    /**
     * @param args the command line arguments
     */
	
	public JavaFX_Pie_ChartsDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com");
        Group root = new Group();
         
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                    new PieChart.Data("January", 100),
                    new PieChart.Data("February", 200),
                    new PieChart.Data("March", 50),
                    new PieChart.Data("April", 75),
                    new PieChart.Data("May", 110),
                    new PieChart.Data("June", 300),
                    new PieChart.Data("July", 111),
                    new PieChart.Data("August", 30),
                    new PieChart.Data("September", 75),
                    new PieChart.Data("October", 55),
                    new PieChart.Data("November", 225),
                    new PieChart.Data("December", 99));
         
        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("PieChart");
         
        root.getChildren().add(pieChart);
 
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
