package app.example.String.StringBreaker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class StringBreakerDemo extends Application {
    
	private String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
			+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
			+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
			+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
			+ "remaining essentially unchanged. "
			+ "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, "
			+ "and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	
	public StringBreakerDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    @Override
    public void start(Stage primaryStage) {
    	
    	Label labelOrginalTitle = new Label("Orginal Text");
    	labelOrginalTitle.setStyle("-fx-font-weight: bold;");
    	Label labelOrginal = new Label(text);
    	
    	Label labelBreakTitle = new Label("Breaked Text");
    	labelBreakTitle.setStyle("-fx-font-weight: bold;");
    	Label labelBreak = new Label(breakString(text, 50));
    	
    	Label labelCutTitle = new Label("Cut Text");
    	labelCutTitle.setStyle("-fx-font-weight: bold;");
    	Label labelCut = new Label(cutString(text, 50));
    	
        VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(labelOrginalTitle, labelOrginal, labelBreakTitle, labelBreak, labelCutTitle, labelCut);

        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // line break but not in the word
    private String breakString(String text, int lineSize) {
		if (text == null) {
			return "";
		}
        String newString = "";

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(text);
        
        while(m.find()) {
//                System.out.println(m.group().trim());   // Debug
                newString += m.group().trim() + "\n";
        }
        return removeLastLineBreaks(newString);
    }
    
    // cut the String but not in the word
    private String cutString(String text, int lineSize) {
		if (text == null) {
			return "";
		}
        String newString = "";

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(text);
        
        if (m.find()) {
        	newString = m.group().trim() + "...";
		}
        
        return newString;
    }
    
    // if the last character is a line break, remove the line break
    public String removeLastLineBreaks(String str) {
		  final char LF = '\n';
		  final char CR = '\r';
		  
	      if (isEmpty(str)) {
	          return str;
	      }

	      if (str.length() == 1) {
	          char ch = str.charAt(0);
	          if (ch == CR || ch == LF) {
	              return "";
	          }
	          return str;
	      }

	      int lastIdx = str.length() - 1;
	      char last = str.charAt(lastIdx);

	      if (last == LF) {
	          if (str.charAt(lastIdx - 1) == CR) {
	              lastIdx--;
	          }
	      } else if (last != CR) {
	          lastIdx++;
	      }
	      return str.substring(0, lastIdx);
	}
    
	public static boolean isEmpty(String str) {
	      return str == null || str.length() == 0;
	}
    
    public static void main(String[] args) {
        launch(args);
    }
}
