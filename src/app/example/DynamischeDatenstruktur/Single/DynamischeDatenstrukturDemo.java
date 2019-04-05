package app.example.DynamischeDatenstruktur.Single;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class DynamischeDatenstrukturDemo extends Application {
	
	DoubleNodeMitBenutzer list = addData();
	
	public DynamischeDatenstrukturDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

        // Top
        Label labelTitleMain = new Label("Dynamische Datenstruktur");   
        labelTitleMain.setStyle("-fx-font-weight: bold");
        
        Label labelMain = new Label("0 -> Dummy\n" + "1 -> Inge\n" + "2 -> Ute\n" + "3 -> Jens\n");   
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("single.PNG")));
        
        // Left
        Label labelTitleLeft = new Label("SingleNode");   
        labelTitleLeft.setStyle("-fx-font-weight: bold");
        
        Label labelTextLeft = new Label(""); 
        
        Button btnLeftNext = new Button(">");
        btnLeftNext.setOnAction(e -> {
        	if (list != null) {
				labelTextLeft.setText(list.wert + " -> " + list.benutzerName);
				System.out.println(list.wert + " -> " + list.benutzerName);
				list = list.next;	
			} else {
				btnLeftNext.setDisable(true);
			}

        });
        
        VBox left = new VBox();
        left.setSpacing(10);
        left.setAlignment(Pos.CENTER);
        left.getChildren().addAll(labelTitleLeft, labelTextLeft, btnLeftNext);
        
//        // Right
//        Label labelTitleRight = new Label("DoubleNode");   
//        labelTitleRight.setStyle("-fx-font-weight: bold");
//        
//        Label labelTextRight = new Label("");   
//        
//        Button btnRightPrev = new Button("<");
//        btnRightPrev.setOnAction(e -> {
//			System.out.println(list.wert + " -> " + list.benutzerName);
//			list = list.prev;	
//        });
//        
//        Button btnRightNext = new Button(">");
//        btnRightNext.setOnAction(e -> {
//			System.out.println(list.wert + " -> " + list.benutzerName);
//			list = list.next;	
//        });
//              
//        HBox buttonBox = new HBox();
//        buttonBox.setAlignment(Pos.CENTER);
//        buttonBox.getChildren().addAll(btnRightPrev, btnRightNext);
//        
//        VBox right = new VBox();
//        right.setSpacing(10);
//        right.setAlignment(Pos.CENTER);
//        right.getChildren().addAll(labelTitleRight, labelTextRight, buttonBox);
        
        
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(left);
        content.setAlignment(Pos.CENTER);
        
        VBox main = new VBox();
        main.setSpacing(10);
        main.getChildren().addAll(labelTitleMain, labelMain, img, content);
        main.setAlignment(Pos.TOP_CENTER);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().add(main);
        AnchorPane.setTopAnchor(main, 10.0);
        AnchorPane.setLeftAnchor(main, 10.0);
        AnchorPane.setBottomAnchor(main, 10.0);
        AnchorPane.setRightAnchor(main, 10.0);
        
        primaryStage.setTitle("Single");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    
    private DoubleNodeMitBenutzer addData() {
		
    	DoubleNodeMitBenutzer list = new DoubleNodeMitBenutzer(0, "Dummy");
		DoubleNodeMitBenutzer start = list;

		list.next = new DoubleNodeMitBenutzer(1, "Inge");
		list.next.prev = list;
		list = list.next;

		list.next = new DoubleNodeMitBenutzer(2, "Ute");
		list.next.prev = list;
		list = list.next;

		list.next = new DoubleNodeMitBenutzer(3, "Jens");
		list.next.prev = list;
		list = list.next;

		return list = start;
		
	}
    
    class SingleNode{
    	int wert;
    	SingleNode next = null;
    	public SingleNode(int w){
    		this.wert = w;
    	}
    }

    class DoubleNode{
    	int wert;
    	DoubleNode next = null;
    	DoubleNode prev = null;
    	public DoubleNode(int w){
    		this.wert = w;
    	}
    }

    class DoubleNodeMitBenutzer{
    	int wert;
    	String benutzerName;
    	DoubleNodeMitBenutzer next = null;
    	DoubleNodeMitBenutzer prev = null;
    	
    	public DoubleNodeMitBenutzer(int w, String benutzerName){
    		this.wert = w;
    		this.benutzerName = benutzerName;
    	}
    }
    
    
}
