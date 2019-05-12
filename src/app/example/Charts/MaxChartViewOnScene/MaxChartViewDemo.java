package app.example.Charts.MaxChartViewOnScene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

// https://levelup.gitconnected.com/realtime-charts-with-javafx-ed33c46b9c8d

public class MaxChartViewDemo extends Application {

    public MaxChartViewDemo() {
		Platform.runLater(() ->  {
			try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX Max ChartView Demo");

        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Max Chart View Demo");
        lineChart.setAnimated(false); // disable animations

        //defining a series to display data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series");

        // add series to chart
        lineChart.getData().add(series);
        
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);
        
        Label labelMax = new Label("Max. Points on yAxis");
        TextField textFieldMax = new TextField();
        textFieldMax.setPrefWidth(70);
        
        
        // textFieldMax only numbers
        final char seperatorChar = ',';
        final Pattern p = Pattern.compile("[0-9" + seperatorChar + "]*");
        
        textFieldMax.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.isContentChange()) {
                return c; // no need for modification, if only the selection changes
            }
            String newText = c.getControlNewText();
            if (newText.isEmpty()) {
                return c;
            }
            if (!p.matcher(newText).matches()) {
                return null; // invalid change
            }

            // invert everything before the range
            int suffixCount = c.getControlText().length() - c.getRangeEnd();
            int digits = suffixCount - suffixCount / 4;
            StringBuilder sb = new StringBuilder();

            // insert seperator just before caret, if necessary
            if (digits % 3 == 0 && digits > 0 && suffixCount % 4 != 0) {
                sb.append(seperatorChar);
            }

            // add the rest of the digits in reversed order
            for (int i = c.getRangeStart() + c.getText().length() - 1; i >= 0; i--) {
                char letter = newText.charAt(i);
                if (Character.isDigit(letter)) {
                    sb.append(letter);
                    digits++;
                    if (digits % 3 == 0) {
                        sb.append(seperatorChar);
                    }
                }
            }

            // remove seperator char, if added as last char
            if (digits % 3 == 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.reverse();
            int length = sb.length();

            // replace with modified text
            c.setRange(0, c.getRangeEnd());
            c.setText(sb.toString());
            c.setCaretPosition(length);
            c.setAnchor(length);

            return c;
        }));
              
        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        
        Button button = new Button("add");
        button.setOnAction(e -> {
        	Integer random = ThreadLocalRandom.current().nextInt(10);       	
            Date now = new Date();
            // put random number with current time
            series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
            
            if (series.getData().size() > Integer.parseInt(textFieldMax.getText())) {
            	series.getData().remove(0);
            }
            	
        });
        
        
        hBox.getChildren().addAll(labelMax, textFieldMax, button);
        root.getChildren().addAll(hBox, lineChart);
        
        // setup scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // show the stage
        primaryStage.show();

    }

    
    
}