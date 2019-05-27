package app.example.Mouse.BindObjectToMouse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.* ; // MouseEvent, MouseButton
import javafx.scene.* ;
import javafx.stage.Stage;
import javafx.geometry.* ; // Point2D
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse ;
import javafx.beans.property.* ; // DoubleProperty etc.

public class BindObjectToMouseDemo extends Application {
	
   private Group group_for_ellipses = new Group() ;

   private Ellipse large_ellipse ;
   private Ellipse left_small_ellipse, middle_small_ellipse, right_small_ellipse ;

   // The values of the DoubleProperty objects will be updated when
   // the position of the mouse cursor changes. Then, after these values
   // change, the positions of the ellipses will be adjusted accordingly.

   private DoubleProperty event_position_x  =  new SimpleDoubleProperty() ;
   private DoubleProperty event_position_y  =  new SimpleDoubleProperty() ;

   public BindObjectToMouseDemo() {
		Platform.runLater(() -> {
			start(new Stage());
		});
   }
   
   public void start( Stage stage ) {
      stage.setTitle( "MouseDemoFX.java" ) ;

      Scene scene = new Scene( group_for_ellipses, 800, 600 ) ;

      scene.setFill( Color.LAVENDER ) ;

      // The large ellipse will be in the middle of the Scene at
      // the beginning.

      large_ellipse = new Ellipse( scene.getWidth() / 2,
                                   scene.getHeight() / 2, 80, 105 ) ;

      // The small ellipses, which represent the mouse buttons,
      // are defined without a location.

      left_small_ellipse   = new Ellipse( 16, 30 ) ;
      middle_small_ellipse = new Ellipse( 16, 30 ) ;
      right_small_ellipse  = new Ellipse( 16, 30 ) ;

      // The small ellipses are not visible when the program starts running.

      left_small_ellipse.visibleProperty().setValue( false ) ;
      middle_small_ellipse.visibleProperty().setValue( false ) ;
      right_small_ellipse.visibleProperty().setValue( false ) ;

      large_ellipse.setFill( Color.GRAY ) ;

      large_ellipse.setStroke( Color.BLACK ) ;

      left_small_ellipse.setStroke( Color.BLACK ) ;
      middle_small_ellipse.setStroke( Color.BLACK ) ;
      right_small_ellipse.setStroke( Color.BLACK ) ;


      // The following statements add listeners which specify how the
      // ellipses are re-located when the mouse cursor position changes.
      // The listeners are made with Lambda expressions.

      event_position_x.addListener(

         ( observable_value, value, new_value ) ->
         {
            large_ellipse.setCenterX( new_value.doubleValue() ) ;

            left_small_ellipse.setCenterX( new_value.doubleValue() - 40 ) ;
            middle_small_ellipse.setCenterX( new_value.doubleValue() ) ;
            right_small_ellipse.setCenterX( new_value.doubleValue() + 40 ) ;
         }
         
      ) ;

      event_position_y.addListener(

         ( observable_value, value, new_value ) ->
         {
            large_ellipse.setCenterY( new_value.doubleValue() + 75 ) ;

            left_small_ellipse.setCenterY( new_value.doubleValue() + 35 ) ;
            middle_small_ellipse.setCenterY( new_value.doubleValue() + 25 ) ;
            right_small_ellipse.setCenterY( new_value.doubleValue() + 35 ) ;
         }
         
      ) ;


      group_for_ellipses.getChildren().addAll( large_ellipse,
                                               left_small_ellipse,
                                               middle_small_ellipse,
                                               right_small_ellipse ) ;


      // The color of the Scene, i.e., the window backround, is different
      // depending on whether or not the mouse cursor is over the window.

      scene.setOnMouseEntered( ( MouseEvent event ) ->
      {
         scene.setFill( Color.LIGHTSKYBLUE ) ;
      } ) ;

      scene.setOnMouseExited( ( MouseEvent event ) ->
      {
         scene.setFill( Color.LAVENDER ) ;
      } ) ;


      scene.setOnMousePressed( ( MouseEvent event ) ->
      {
         event_position_x.setValue( event.getSceneX() ) ;
         event_position_y.setValue( event.getSceneY() ) ;

         // A small ellipse is made visible when a mouse button is pressed.
   
         if ( event.getButton()  ==  MouseButton.PRIMARY ) // Left
         {
            left_small_ellipse.visibleProperty().setValue( true ) ;
         }
         else if ( event.getButton()  ==  MouseButton.MIDDLE )
         {
            middle_small_ellipse.visibleProperty().setValue( true ) ;
         }
         else if ( event.getButton()  ==  MouseButton.SECONDARY ) // Right
         {
            right_small_ellipse.visibleProperty().setValue( true ) ;
         }
   
         //  The small ellipses will be shown with a different color
         //  if either the Ctrl or the Shift key is pressed down.

         if ( event.isControlDown() ==  true  ||
              event.isShiftDown()   ==  true )
         {
            left_small_ellipse.setFill( Color.MAGENTA ) ;
            middle_small_ellipse.setFill( Color.MAGENTA ) ;
            right_small_ellipse.setFill( Color.MAGENTA ) ;
         }
         else
         {
            left_small_ellipse.setFill( Color.YELLOW ) ;
            middle_small_ellipse.setFill( Color.YELLOW ) ;
            right_small_ellipse.setFill( Color.YELLOW ) ;
         }
      } ) ;


      //  The following statement specifies the actions when the mouse
      //  is moved without pressing its buttons.

      scene.setOnMouseMoved( ( MouseEvent event ) ->
      {
         event_position_x.setValue( event.getSceneX() ) ;
         event_position_y.setValue( event.getSceneY() ) ;
      } ) ;

      //  Dragging of the mouse means that it is moved while simultaneously
      //  pressing one or more of its buttons.

      scene.setOnMouseDragged( ( MouseEvent event ) ->
      {
         event_position_x.setValue( event.getSceneX() ) ;
         event_position_y.setValue( event.getSceneY() ) ;
      } ) ;

      scene.setOnMouseReleased( ( MouseEvent event ) ->
      {
         if ( event.getButton()  ==  MouseButton.PRIMARY ) // Left
         {
            left_small_ellipse.visibleProperty().setValue( false ) ;
         }
         else if ( event.getButton()  ==  MouseButton.MIDDLE )
         {
            middle_small_ellipse.visibleProperty().setValue( false ) ;
         }
         else if ( event.getButton()  ==  MouseButton.SECONDARY )
         {
            right_small_ellipse.visibleProperty().setValue( false ) ;
         }
      } ) ;

      stage.setScene( scene ) ;
      stage.show();
   }


   public static void main( String[] command_line_parameters )
   {
      launch( command_line_parameters ) ;
   }
}

