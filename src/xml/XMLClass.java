package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

public class XMLClass {
	
	public Document getXML() throws ParserConfigurationException {
		DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document= builder.newDocument();
		NamedNodeMap nodeMap=document.getAttributes();
		System.out.println(nodeMap.item(0));
		
		return null;
	}

}
