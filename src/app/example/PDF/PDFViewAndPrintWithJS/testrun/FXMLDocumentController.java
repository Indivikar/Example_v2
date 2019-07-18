/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.example.PDF.PDFViewAndPrintWithJS.testrun;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author VERNON
 */
public class FXMLDocumentController implements Initializable {
    
    
      private final ObservableList<Person> data =
            FXCollections.observableArrayList();
      
    @FXML
    private Button calculate;

    @FXML
    private TextField valueA;

    @FXML
    private TextField valueB;

    @FXML
    private TextField valueC;
    
    @FXML
    private TableView<Person> mytable;
    
    @FXML
    private TableView<Person> othertable;

    @FXML
    private TableColumn<Person, String> col1;

    @FXML
    private TableColumn<Person, String> col2;

    @FXML
    private TableColumn<Person, String> col3;

     @FXML
    private Button createpdf;
     
      @FXML
    private ImageView pdfImage;

    @FXML
    private Button loadpdfImage;

     
       @FXML
    private Button loadpdf;

           @FXML
    private WebView pdViewer;
    
           
     @FXML
    void LOADPDFIMAGE(ActionEvent event) {
           LoadPDFIMAGE();
    }       

    @FXML
    void PDFLOAD(ActionEvent event) {
          try {
              LoadPDF();
          } catch (IOException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
   
    @FXML
    void PDFCREATE(ActionEvent event) {
            CreatePDf();
    }

    @FXML
    void CalCULATE(ActionEvent event) {
            data.add(new Person(valueA.getText(), valueB.getText(),  valueC.getText()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     CalCU();
        setCol();
           setCol2();
    }
    
    public void setCol2(){
     
        col1.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        col2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
       
        col3.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        othertable.setItems(data);
        //othertable.getColumns().addAll(col1, col2, col3);
    
    }
    
    
    public void setCol(){
      TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        mytable.setItems(data);
        mytable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
    }

 public void CalCU(){
     valueB.setOnKeyReleased(new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
             double a1 = Double.parseDouble(valueA.getText());
             double a2 = Double.parseDouble(valueB.getText());
             double c2 = a1 * a2;
             
             try {
                  valueC.setText(String.valueOf(c2));
             } catch (NumberFormatException e) {
                  valueC.setText(String.valueOf(""));
             }
             
             
         
         }
     }
     );
 
    }   
    
 
 public void CreatePDf(){
          try {
              //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
              Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
             
              PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("TestData/file.pdf"));
              doc.open();
              
              Anchor anchorTarget = new Anchor("First page of the document.");
      anchorTarget.setName("BackToTop");
      Paragraph paragraph1 = new Paragraph();
 
      paragraph1.setSpacingBefore(50);
 
      paragraph1.add(anchorTarget);
      doc.add(paragraph1);
 
doc.add(new Paragraph("Some more text on the first page with different color and font type.", 
 
FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));

Paragraph title1 = new Paragraph("Chapter 1", 
 
   FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(0, 255, 255,17)));
    
Chapter chapter1 = new Chapter(title1, 1);
       
chapter1.setNumberDepth(0);

Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, 
    
       new CMYKColor(0, 255, 255,17)));
    
Section section1 = chapter1.addSection(title11);
 
Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
 
section1.add(someSectionText);
 
someSectionText = new Paragraph("Following is a 3 X 2 table.");
 
section1.add(someSectionText);

PdfPTable t = new PdfPTable(3);
 
      t.setSpacingBefore(25);
       
      t.setSpacingAfter(25);
       
      PdfPCell c1 = new PdfPCell(new Phrase("Header1"));  
       
      t.addCell(c1);
       
      PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
       
      t.addCell(c2);
       
      PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
       
      t.addCell(c3);
       
      t.addCell("1.1");
       
      t.addCell("1.2");
       
      t.addCell("1.3");
       
      section1.add(t);
      
  doc.add(chapter1);
 
doc.close();
              
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (DocumentException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }
 
 }

    private void LoadPDF() throws IOException {
        WebEngine engine = pdViewer.getEngine();
        String url = getClass().getResource("../web/viewer.html").toExternalForm();
        engine.setJavaScriptEnabled(true);
        engine.load(url);
        
//        byte[] data = FileUtils.readFileToByteArray(new File(getClass().getResource("../file.pdf").toExternalForm()));
//        byte[] data = FileUtils.readFileToByteArray(new File("TestData/file.pdf"));
//        String base64 = Base64.getEncoder().encodeToString(data);
//        engine.executeScript("openFileFromBase64('" + base64 + "')");
        
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
        		public void changed(ObservableValue ov, State oldState, State newState) {
	        		if (newState == engine.getLoadWorker().getState().SUCCEEDED) {
		        		byte[] data = null;
		        		try {
		        			data = FileUtils.readFileToByteArray(new File("TestData/file.pdf"));
		        		} catch (IOException ex) {
		        			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		        		}
		                
		                String base64 = Base64.getEncoder().encodeToString(data);
		                engine.executeScript("openFileFromBase64('" + base64 + "')");
	
	        		}
        		}

        });
  
    }
    
 private void LoadPDFIMAGE() {
        /*byte[] data = FileUtils.readFileToByteArray(new File(getClass().getResource(â€œweb/receipt.pdfâ€�).toExternalForm());
     new File(getClass().getResource("C:\\Users\\VERNON\\Documents\\NetBeansProjects\\TestRun\\src\\web\\receipt.pdf").toExternalForm());
        
     */
       
   
        }
}