package ProgramFiles;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AppendScreenshotsToHTML {
	
	public static String AppendNewDiv (String className) 
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		String pathToHTML = System.getProperty("user.dir") + "\\htmlReportsDir\\Screenshots.html";
		
		String pathToScreenShots = System.getProperty("user.dir") + "\\screenShots\\";
		
			System.out.println(" =========== ADD SCREENSHOTS TO HTML ========== ");
			
			// Creates document object and parses it to afford an appending code.

//			File input = new File(pathToHTML);
//			org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
//		    doc.body().append("<div>sdfsfsdgsgdgdf</div>");
//		    System.out.println(doc.body().html());


			File file=new File(pathToHTML);
			org.jsoup.nodes.Document doc= Jsoup.parse(file , "utf-8" );
			org.jsoup.nodes.Element resultID=((org.jsoup.nodes.Element) doc).getElementById("container");
			resultID.append("<div>sdfsfsdgsgdgdf</div>");
			FileWriter writer=new FileWriter(file);
			writer.write(doc.toString());
			writer.flush();
			writer.close();
			
			//Reads shot file names in directory
			File shotsFolder = new File(pathToScreenShots + className);
			File shotsList [] = shotsFolder.listFiles();
				for (File fileInDir : shotsList) {
					System.out.println(//Just for control
							"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+ "fileInDir.getName()\n" + fileInDir.getName() 
							+ "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//					fileInDir.getName();
				};//for

			
			System.out.println(//Just for control
					"********************************************************\n"
					+ "From AppendNewDiv\n" + doc.toString() 
					+ "\n********************************************************");
			
			
			WaitAndNotify.NotifyAll();
		return null;
	}; //AppendNewDiv
		
} // addScreenshotsToHTML
