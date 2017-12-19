package Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: find parent of two manifest files given from tree
*/
public class findParent {
	public static ArrayList<String> array=new ArrayList<>();
	public static ArrayList<String> array1=new ArrayList<>();
	public String getParent(String manifest1, String manifest2, String treeXmlPath)
	{
		 try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc;
				    doc = docBuilder.parse(treeXmlPath);
					Element ele = (Element)doc.getElementsByTagName(manifest1).item(0);
					if(manifest1.equals("manifest-1.txt")||manifest2.equals("manifest-1.txt"))
					{
						return "manifest-1.txt";
					}
					do
					{
						ele=(Element)ele.getParentNode();
						
						if(ele.getNodeName().contains("manifest-"))
						{
							
							array.add(ele.getNodeName());
						}
					
					}while(ele.getNodeName()!="manifest-1.txt");
					  Element ele2 = (Element)doc.getElementsByTagName(manifest2).item(0);
					do
					{
						ele2=(Element)ele2.getParentNode();
						if(ele2.getNodeName().contains("manifest-"))
						{
							array1.add(ele2.getNodeName());
						}
					}while(ele2.getNodeName()!="manifest-1.txt");
				String m=getCommonManifest();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("E:\\software Project\\repo\\activity\\tree.xml"));
				transformer.transform(source, result);
                 return m;
			   } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			   } catch (TransformerException tfe) {
				tfe.printStackTrace();
			   } catch (IOException ioe) {
				ioe.printStackTrace();
			   } catch (SAXException sae) {
				sae.printStackTrace();
			   }
		return null;
	}
	private static String getCommonManifest() {
		for (int i = 0; i < array.size(); i++) {
					if(array1.contains(array.get(i)))
			{
			return array.get(i);
			}
		}
		return null;
	}

}
