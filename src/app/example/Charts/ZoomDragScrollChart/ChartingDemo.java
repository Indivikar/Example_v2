package app.example.Charts.ZoomDragScrollChart;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.gillius.jfxutils.JFXUtil;
import org.gillius.jfxutils.chart.ChartPanManager;
import org.gillius.jfxutils.chart.FixedFormatTickFormatter;
import org.gillius.jfxutils.chart.JFXChartUtil;
import org.gillius.jfxutils.chart.StableTicksAxis;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ChartingDemo extends Application {
	
	public ChartingDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public static void main( String[] args ) {
		launch( args );
	}

	@Override
	public void start( Stage stage ) throws Exception {
		FXMLLoader loader = new FXMLLoader( getClass().getResource( "Charting.fxml" ) );
		Region contentRootRegion = (Region) loader.load();

		StackPane root = JFXUtil.createScalePane( contentRootRegion, 960, 540, false );
		Scene scene = new Scene( root, root.getPrefWidth(), root.getPrefHeight() );
		stage.setScene( scene );
		stage.setTitle( "Charting Example" );
		stage.show();
	}

}
