package app.example.Grafik.SVGRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

public class MainController implements Initializable {

    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BufferedImageTranscoder trans = new BufferedImageTranscoder();

        // In my case I have added a file "svglogo.svg" in my project source folder within the default package.
        // Use any SVG file you like! I had success with http://en.wikipedia.org/wiki/File:SVG_logo.svg
        try (InputStream file = getClass().getResourceAsStream("batikCandy.svg")) {
            TranscoderInput transIn = new TranscoderInput(file);
            try {
                trans.transcode(transIn, null);
                // Use WritableImage if you want to further modify the image (by using a PixelWriter)
                Image img = SwingFXUtils.toFXImage(trans.getBufferedImage(), null);
                imgView.setImage(img);
            } catch (TranscoderException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (IOException io) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, io);
        }
    }
}
