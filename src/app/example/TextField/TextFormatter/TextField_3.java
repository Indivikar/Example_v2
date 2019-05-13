package app.example.TextField.TextFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;

// https://stackoverflow.com/questions/22382213/javafx-custom-cellfactory-with-decimal-only-input

// more Pattern Example
// https://howtodoinjava.com/regex/java-regex-validate-email-address/

public class TextField_3 extends TextField {

	private final String regex;

	public TextField_3(double prefWidth, String regex) {
		this.regex = regex;
		setPrefWidth(prefWidth);		
	}
	
    @Override public void replaceText(int start, int end, String text) {
    	Matcher matcher = Pattern.compile(regex).matcher(text);
        if (matcher.matches()) {
            super.replaceText(start, end, text);
        }
    }
 
    @Override public void replaceSelection(String text) {
    	Matcher matcher = Pattern.compile(regex).matcher(text);
        if (!matcher.matches()) {
            super.replaceSelection(text);
        }
    }  

}
