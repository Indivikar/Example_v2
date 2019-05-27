package app.example.Grafik.SVGRenderer;

import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

public class SaveAsJPEG {

    public static void main(String[] args) throws Exception {

    	String uri = SaveAsJPEG.class.getResource("save_24px.svg").toExternalForm();
    	
        // Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                   new Float(.8));

        // Create the transcoder input.
//        String svgURI = new File(SaveAsJPEG.class.getResource("batikCandy.svg").getFile()).toURL().toString();
        TranscoderInput input = new TranscoderInput(uri);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        t.transcode(input, output);

        // Flush and close the stream.
        ostream.flush();
        ostream.close();
        System.exit(0);
    }
}
