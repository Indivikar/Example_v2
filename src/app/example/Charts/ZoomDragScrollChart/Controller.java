package app.example.Charts.ZoomDragScrollChart;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.gillius.jfxutils.chart.ChartPanManager;
import org.gillius.jfxutils.chart.FixedFormatTickFormatter;
import org.gillius.jfxutils.chart.JFXChartUtil;
import org.gillius.jfxutils.chart.StableTicksAxis;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Controller implements Initializable {

	private XYChart.Series<Number, Number> series;
	private long startTime;
	private Timeline addDataTimeline;
		
	@FXML private LineChart<Number, Number> chart;
	@FXML private Slider valueSlider;
	@FXML private Label outputLabel;
	
	@FXML void addSample() {
		series.getData().add( new XYChart.Data<Number, Number>( System.currentTimeMillis() - startTime,
		                                                        valueSlider.getValue() ) );
	}

	@FXML void autoZoom() {
		chart.getXAxis().setAutoRanging( true );
		chart.getYAxis().setAutoRanging( true );
	}

	@FXML void toggleAdd() {
		switch ( addDataTimeline.getStatus() ) {
			case PAUSED:
			case STOPPED:
				addDataTimeline.play();
				chart.getXAxis().setAutoRanging( true );
				chart.getYAxis().setAutoRanging( true );
				//Animation looks horrible if we're updating a lot
				chart.setAnimated( false );
				chart.getXAxis().setAnimated( false );
				chart.getYAxis().setAnimated( false );
				break;
			case RUNNING:
				addDataTimeline.stop();
				//Return the animation since we're not updating a lot
				chart.setAnimated( true );
				chart.getXAxis().setAnimated( true );
				chart.getYAxis().setAnimated( true );
				break;

			default:
				throw new AssertionError( "Unknown status" );
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startTime = System.currentTimeMillis();

		//Set chart to format dates on the X axis
		SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );
		format.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
		((StableTicksAxis) chart.getXAxis()).setAxisTickFormatter(
				new FixedFormatTickFormatter( format ) );

		series = new XYChart.Series<Number, Number>();
		series.setName( "Data" );

		chart.getData().add( series );

		addDataTimeline = new Timeline( new KeyFrame(
				Duration.millis( 250 ),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle( ActionEvent actionEvent ) {
						addSample();
					}
				}
		));
		addDataTimeline.setCycleCount( Animation.INDEFINITE );

		chart.setOnMouseMoved( new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent mouseEvent ) {
				double xStart = chart.getXAxis().getLocalToParentTransform().getTx();
				double axisXRelativeMousePosition = mouseEvent.getX() - xStart;
				outputLabel.setText( String.format(
						"%d, %d (%d, %d); %d - %d",
						(int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY(),
						(int) mouseEvent.getX(), (int) mouseEvent.getY(),
						(int) xStart,
						chart.getXAxis().getValueForDisplay( axisXRelativeMousePosition ).intValue()
				) );
			}
		} );

		//Panning works via either secondary (right) mouse or primary with ctrl held down
		ChartPanManager panner = new ChartPanManager( chart );
		panner.setMouseFilter( new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent mouseEvent ) {
				if ( mouseEvent.getButton() == MouseButton.SECONDARY ||
						 ( mouseEvent.getButton() == MouseButton.PRIMARY &&
						   mouseEvent.isShortcutDown() ) ) {
					//let it through
				} else {
					mouseEvent.consume();
				}
			}
		} );
		panner.start();

		//Zooming works only via primary mouse button without ctrl held down
		JFXChartUtil.setupZooming( chart, new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent mouseEvent ) {
				if ( mouseEvent.getButton() != MouseButton.PRIMARY ||
				     mouseEvent.isShortcutDown() )
					mouseEvent.consume();
			}
		} );

		JFXChartUtil.addDoublePrimaryClickAutoRangeHandler( chart );
		
	}
	
	
	
}
