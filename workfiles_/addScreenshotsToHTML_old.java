package ProgramFiles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class addScreenshotsToHTML {
	//Creates DOM representation of the document
	public static String createDOMrepresentationOfHTML (String pathToHTML, String containerId, String appendedData) 
		throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		System.out.println(" =========== ADD SCREENSHOTS TO HTML ========== ");
		
		System.out.println(//Just for control
				"********************************************************\n"
				+ "From createDOMrepresentationOfHTML\n" + pathToHTML + "\n" + containerId + "\n" + appendedData 
				+ "\n********************************************************");
		// Creates document object and parses it to afford an appending code.
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		org.w3c.dom.Document parsedDocument = documentBuilder.parse(pathToHTML);
		
		//Fires a method below to iterate and find container div. I keep iteration more to use it in further coding as there is only one container.
		Element containerDivElem = findContainerDiv (containerId, parsedDocument.getDocumentElement());
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//try
		
		//Prepares an element to append
		Element elementToAppend = documentBuilder.parse(new ByteArrayInputStream(appendedData.getBytes())).getDocumentElement();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//try
		
		//Imports node
		Node importedNode = ((org.w3c.dom.Document) containerDivElem).importNode(elementToAppend, true);
//		Node importedNode = parsedDocument.importNode(elementToAppend, true);

		
		
		//Appends new element as a child
//		containerDivElem.appendChild(elementToAppend);
		containerDivElem.appendChild(importedNode);
		
		
		return domBackToString(parsedDocument);
	};//createDOMrepresentationOfHTML
	
	
	//Describes a div in the DOM to contain children divs with screenshots
	public static Element findContainerDiv (String divId, Element domElement) {
		
		if (divId.equals(domElement.getAttribute("id"))) {
			return domElement;
		};
		
		NodeList childrenDivs = domElement.getElementsByTagName("div");
		
		for (int i = 0; i < childrenDivs.getLength(); i++) {
			
			Element currentElement = (Element) childrenDivs.item(i);
			
				if (divId.equals(currentElement.getAttribute("id"))) {
					return currentElement;
				}; //if
			
			Element childWithThisId = findContainerDiv (divId, currentElement);
			
				if (childWithThisId != null) {
					return childWithThisId;
				}; //if
			
		}; //for
		
		return null;
		
	}; //findContainerDiv
		
		
	//Converts parsed DOM back to string
	public static String domBackToString (org.w3c.dom.Document parsedDocument) 
		throws TransformerException {
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer newTransformer = transformerFactory.newTransformer();
			newTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			
		StringWriter stringWriter = new StringWriter();
		
		newTransformer.transform(new DOMSource(parsedDocument), new StreamResult(stringWriter));
		
		//Converts to string while removing new lines
		String backToStringResult = stringWriter.getBuffer().toString().replaceAll("\n|\r", "");
		
		return backToStringResult;			
	}; //domBackToString
		
		
} // addScreenshotsToHTML
