package app.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.functions.PfadErmitteln;
import javafx.beans.property.SimpleStringProperty;



public class App {

	private SimpleStringProperty bezeichnung;
	private SimpleStringProperty kurzBezeichnung;
	private SimpleStringProperty kategorie;
	private SimpleStringProperty startFile;
	private SimpleStringProperty className;
	private SimpleStringProperty readMe;
	private SimpleStringProperty sourceCode;
	
	
	public App(String bezeichnung, String kategorie, String startFile, String readMe) {
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
		
		this.kategorie = new SimpleStringProperty(kategorie);
		this.startFile = new SimpleStringProperty(startFile);
		
		this.className = new SimpleStringProperty(setClassName(startFile));
//		String rm = setReadme(startFile);
//		this.readMe = new SimpleStringProperty(rm);
//		this.kurzBezeichnung = new SimpleStringProperty(cutString(rm, 50));
		this.readMe = new SimpleStringProperty(readMe);
		this.kurzBezeichnung = new SimpleStringProperty(cutString(readMe, 50));
		this.sourceCode = new SimpleStringProperty(setSourceCode(startFile));
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
	
	private String setClassName(String className) {
		PfadErmitteln pe = new PfadErmitteln(new String[] {}, true, false);
		String newString = className.replace(pe.getErmittelterFile().getAbsolutePath(), "");
		String newStringWithoutClass = newString.replace(".class", "");
		String newStringWithPoints = newStringWithoutClass.replaceAll(Pattern.quote("\\"), ".");
		
		if (newStringWithPoints.startsWith(".")) {
			return newStringWithPoints.substring(1);
		}
		
		return newStringWithPoints;
	}
	
	private String setReadme(String startFile) {
//		System.out.println("setSourceCode: " + startFile);
		if (startFile != null && !startFile.equals("")) {
			File file = new File(startFile);
			
			String parent = file.getParent();
			File readmeFile = new File(parent + File.separator + "readme.txt");
//			System.out.println("setReadme: " + readmeFile);
//			System.out.println("Readme is exists: " + readmeFile.exists());
			if (readmeFile.exists()) {
				
				try {
					String content = new String(Files.readAllBytes(Paths.get(readmeFile.toURI())));
					return content;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
			} else {
				return "";
			}

			
//			PfadErmitteln pe = new PfadErmitteln(new String[] {}, true, false);
//			String newString = parent.replace(pe.getErmittelterFile().getAbsolutePath(), "");
//			String newStringWithSlash= newString.replaceAll(Pattern.quote("\\"), "/");
//			String newURL = "https://github.com/Indivikar/Example_v2/tree/master/src" + newStringWithSlash;
//			System.out.println("newURL: " + newURL);
//			return newURL;
		} else {
			return "";
		}
		
	}
	
	private String setSourceCode(String startFile) {
//		System.out.println("setSourceCode: " + startFile);
		if (startFile != null && !startFile.equals("")) {
			File file = new File(startFile);
			
			String parent = file.getParent();
			
			PfadErmitteln pe = new PfadErmitteln(new String[] {}, true, false);
			String newString = parent.replace(pe.getErmittelterFile().getAbsolutePath(), "");
			String newStringWithSlash= newString.replaceAll(Pattern.quote("\\"), "/");
			String newURL = "https://github.com/Indivikar/Example_v2/tree/master/src" + newStringWithSlash;
//			System.out.println("newURL: " + newURL);
			return newURL;
		} else {
			return "";
		}
	}
	
	// Getter
	public String getBezeichnung() {return bezeichnung.get();}
	public String getKurzBezeichnung() {return kurzBezeichnung.get();}
	public String getKategorie() {return kategorie.get();}
	public String getStartFile() {return startFile.get();}
	public String getClassName() {return className.get();}
	public String getReadMe() {return readMe.get();}
	public String getSourceCode() {return sourceCode.get();}
	
	// Setter
	public void setBezeichnung(String bezeichnung) {this.bezeichnung.set(bezeichnung);}
	public void setKurzBezeichnung(String kurzBezeichnung) {this.kurzBezeichnung.set(kurzBezeichnung);}
	public void setKategorie(String kategorie) {this.kategorie.set(kategorie);}
	public void setStartFile(String startFile) {this.kategorie.set(startFile);}
//	public void setStartFile(String startFile) {		
//		if (startFile == null || startFile.isEmpty()) {
//			this.kategorie.set("null");
//		} else {
//			this.kategorie.set(startFile);
//		}		
//	}
//	public void setClassName(String className) {this.className.set(className);}
//	public void setReadMe(String readMe) {this.readMe.set(readMe);}
//	public void setSourceCode(String sourceCode) {this.sourceCode.set(sourceCode);}
}
