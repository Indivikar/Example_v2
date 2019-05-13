package app.example.TextField.TextFormatter;

import java.util.regex.Pattern;

import javafx.scene.control.TextField;

// https://stackoverflow.com/questions/22382213/javafx-custom-cellfactory-with-decimal-only-input

public class TextField_1 extends TextField {

	final Pattern pattern;
//	final Pattern pattern = Pattern.compile("^\\d*\\.?\\d*$");

	public TextField_1(double prefWidth, String regex) {
		this.pattern = Pattern.compile(regex);
		setPrefWidth(prefWidth);		
	}
	
   @Override
   public void replaceText(int start, int end, String text) {
       String newText = getText().substring(0, start)+text+getText().substring(end);
        if (pattern.matcher(newText).matches()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        int start = getSelection().getStart();
        int end = getSelection().getEnd();
        String newText = getText().substring(0, start)+text+getText().substring(end);
        if (pattern.matcher(newText).matches()) {
            super.replaceSelection(text);
        }
    }
	
}
