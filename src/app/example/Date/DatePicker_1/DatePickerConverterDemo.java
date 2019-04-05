package app.example.Date.DatePicker_1;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class DatePickerConverterDemo extends Application {

	ObservableList<String> listeDatum = FXCollections.observableArrayList("24.7.2016", "12.7.2016", "14.7.2016");
	ObservableList<String> listeDatumAbgeschlossen = FXCollections.observableArrayList("12.7.2016", "14.7.2016");
 
	public DatePickerConverterDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        final Tooltip tooltip = new Tooltip();
                        tooltipStartTime(tooltip);
                        
                        // Setze Daten auf Grün, die in der liste "listeDatum" sind
                        for (String string : listeDatum) {
                        	
                        	String[] part = string.split("\\.");
                        	
                        	System.out.println(Integer.parseInt(part[0]) + " == " + item.getDayOfMonth() + " | " +
									Integer.parseInt(part[1]) + " == " + item.getMonthValue() + " | " +
									Integer.parseInt(part[2]) + " == " + item.getYear() );
                        	
							if(Integer.parseInt(part[0]) == item.getDayOfMonth() && 
									Integer.parseInt(part[1]) == item.getMonthValue() && 
									Integer.parseInt(part[2]) == item.getYear() ){
								
						
		                        tooltip.setText("in Bearbeitung");                       
		                        setTooltip(tooltip);
								
	                            setDisable(false);
	                            setStyle("-fx-background-color: #86DD96;");
	                            break;
	                        	
	                        } else {
	                            setDisable(true);
	                            setStyle("-fx-background-color: #FFD5DC;");
							}
						}

                        
                        // // Setze Daten auf Blau, die in der liste "listeDatumAbgeschlossen" sind
						for (String stringAbgesclossen : listeDatumAbgeschlossen) {
							String[] partAb = stringAbgesclossen.split("\\.");
							
							if(Integer.parseInt(partAb[0]) == item.getDayOfMonth() && 
									Integer.parseInt(partAb[1]) == item.getMonthValue() && 
									Integer.parseInt(partAb[2]) == item.getYear() ){
										
		                        tooltip.setText("Abgeschlossen");                       
		                        setTooltip(tooltip);
								
	                            setStyle("-fx-background-color: #86C3DD;");		                            			                            
	                            break;
							} 
							
						} 

                    }
                };
            }
        };
        return dayCellFactory;
    }
    
    public static void tooltipStartTime(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    @Override
    public void start(Stage stage) {

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(2016, 7, 25));
        datePicker.setShowWeekNumbers(true);
        datePicker.setEditable(false);
        
 
        // Factory to create Cell of DatePicker
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        FlowPane root = new FlowPane();
        root.getChildren().add(datePicker);
        root.setPadding(new Insets(10));

        stage.setTitle("DatePicker (o7planning.org)");
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}