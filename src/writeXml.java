package Repository;

/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com

* 
* @description: create tree like structure in xml
*/
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class writeXml {
	public static String sourse_activity_latest_manifest="";
	public static String destination_activity_latest_manifest;
	public static String path;
	writeXml(String sourse_activity_latest_manifest,String destination_activity_latest_manifest,String path)
	{
	this.sourse_activity_latest_manifest=sourse_activity_latest_manifest;
	this.destination_activity_latest_manifest=destination_activity_latest_manifest;
	this.path=path;
	}
	writeXml(String destination_activity_latest_manifest,String path)
	{
	
	this.destination_activity_latest_manifest=destination_activity_latest_manifest;
	this.path=path;
	}

	public void start() {

	   try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc;
		
		Node staff;
		 File f = new File(path);
		  // FileReader fr = new FileReader(f);
		   if (!f.exists()){
				doc = docBuilder.newDocument();
				
		   }
		   else
		   {
			   doc = docBuilder.parse(path);
		   }
		   
			Element ele = doc.createElement(destination_activity_latest_manifest);
			
			System.out.println(sourse_activity_latest_manifest);
				if(doc.getElementsByTagName(sourse_activity_latest_manifest).getLength()==0)
				{
				     doc.appendChild(ele);
				     
				}
				else
				{
					staff=doc.getElementsByTagName(sourse_activity_latest_manifest).item(0);
					staff.appendChild(ele);
				}
				// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
	}
}
