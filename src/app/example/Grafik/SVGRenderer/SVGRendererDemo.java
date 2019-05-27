package app.example.Grafik.SVGRenderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SVGRendererDemo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
    	
    	String uri = getClass().getResource("save_24px.svg").toExternalForm();
    	InputStream file = getClass().getResourceAsStream("batikCandy.svg");
    	
    	MyTranscoder transcoder = new MyTranscoder();
    	TranscodingHints hints = new TranscodingHints();
    	hints.put(ImageTranscoder.KEY_WIDTH, 20f); //your image width
    	hints.put(ImageTranscoder.KEY_HEIGHT, 20f); //your image height
    	hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION,    SVGDOMImplementation.getDOMImplementation());
    	hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
    	hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
    	hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);

    	transcoder.setTranscodingHints(hints);
    	TranscoderInput input = new TranscoderInput(uri);
    	transcoder.transcode(input, null);
    	BufferedImage bufferedImage = transcoder.getImage();
    	
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    	JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(outputStream);
    	imageEncoder.encode(bufferedImage);

    	byte[] bytes = outputStream.toByteArray();
    	InputStream inputStream = new ByteArrayInputStream(bytes);
    	
    	//javafx.scene.image.Image
    	Image image = new Image(inputStream);
    	//javafx.scene.image.ImageView
    	ImageView imageView = new ImageView();
    	imageView.setImage(image);

        Scene scene = new Scene(new VBox(imageView));
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
