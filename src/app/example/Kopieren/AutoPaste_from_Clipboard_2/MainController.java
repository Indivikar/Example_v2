package app.example.Kopieren.AutoPaste_from_Clipboard_2;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MainController implements Initializable {

	@FXML private TextArea textArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.addFlavorListener(listener -> {
			try {
				String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
				clipboard.setContents(new StringSelection(clipboardText), null);
				
				textArea.setText(clipboardText);
				System.out.println("The clipboard contains: " + clipboardText);
			} catch (UnsupportedFlavorException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
    }

}
