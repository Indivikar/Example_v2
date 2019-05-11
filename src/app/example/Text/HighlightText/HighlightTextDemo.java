package app.example.Text.HighlightText;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HighlightTextDemo extends Application {

    public static final int TEXT_WIDTH = 214;
    private TextField regex;
    private TextArea text;
    private HTMLEditor result;

    public HighlightTextDemo() {
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
    public void start(Stage stage) throws Exception {

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(4);

        Label regexLbl = new Label("Regex");
        regex = new TextField("text");
        regex.setMinHeight(25);
        regex.setMinWidth(TEXT_WIDTH);

        root.add(regexLbl, 0, 0);
        root.add(regex, 1, 0);

        Label textLbl = new Label("Text");
        text = new TextArea("it's my text");
        text.setMinHeight(50);
        text.setPrefRowCount(10);
        text.setMinWidth(TEXT_WIDTH);

        root.add(textLbl, 0, 1);
        root.add(text, 1, 1);

        result = new HTMLEditor();

        root.add(result, 0, 2, 2, 1);

        Button match = new Button("Match");
        match.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                matchRegExp();

            }
        });

        root.add(match, 0, 3);

        Button dumpHtml = new Button("Dump html");
        dumpHtml.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.err.println(result.getHtmlText());
            }
        });

        root.add(dumpHtml, 1, 3);

        matchRegExp();
        
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void matchRegExp() {
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(regex.getText());

        } catch (PatternSyntaxException psex) {
            result.setHtmlText(String.format(
                    "<html><body><p>" +
                            "<span style=\"color:Red;\">%s</span>" +
                            "</p></body></html>",
                    psex.getMessage()));
            return;
        }

        Matcher matcher = pattern.matcher(text.getText());

        boolean found = false;
        StringBuilder sb = new StringBuilder();

        class Tuple {
            final int start, end;

            Tuple(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "Tuple{start=" + start + ", end=" + end + '}';
            }
        }

        List<Tuple> positions = new ArrayList<Tuple>();

        while (matcher.find()) {
            positions.add(new Tuple(matcher.start(), matcher.end()));
            found = true;
        }
        if (!found) {
            sb.append(String.format("No match found.%n"));
        } else {
            System.out.println(positions);
            int pos = 0;
            for (int i = 0; i < positions.size(); i++) {

                sb.append(text.getText().substring(pos, positions.get(i).start))
                        .append("<span style=\"color:ForestGreen;font-size:20px;text-decoration:underline\">")
                        .append(text.getText().substring(positions.get(i).start, positions.get(i).end))
                        .append("</span>");

                pos = positions.get(i).end;
            }

            if (pos > 0) {
                sb.append(text.getText().substring(pos));
            }
        }

        String html = String.format("" +
                "<html><body><p>%s</p></body></html>", sb.toString());

        result.setHtmlText(html);
    }
}
