package by.bsu.famcs.z;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.bsu.famcs.z.hierarchy.*;

public class Utils {
	public static int random(int n) {
		return (int)(1+Math.random()*n);
	}
	
	public static short random(short n) {
		return (short)(1+Math.random()*n);
	}
	
	public static float random(float n) {
		return (float) (1+Math.random()*n);
	}
	
	public static List<Plane> readFromXML(File file) throws SAXException, ParserConfigurationException, IOException {
        List<Plane> resultList = new ArrayList<>();
        NodeList resultElements1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("Plane");
        for (int i = 0; i < resultElements1.getLength(); i++) {
            Node result = resultElements1.item(i);
            NamedNodeMap attributes = result.getAttributes();
            Plane temp=new Plane(Short.parseShort(attributes.getNamedItem("maxHeight").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("places").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("weight").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("rangeOfFlight").getNodeValue()),
            		Float.parseFloat(attributes.getNamedItem("fuel").getNodeValue()));
            resultList.add(temp);
        }
        
        NodeList resultElements2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("Airliner");
        for (int i = 0; i < resultElements2.getLength(); i++) {
            Node result = resultElements2.item(i);
            NamedNodeMap attributes = result.getAttributes();
            Airliner temp=new Airliner(Short.parseShort(attributes.getNamedItem("maxHeight").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("places").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("rangeOfFlight").getNodeValue()),
            		Float.parseFloat(attributes.getNamedItem("fuel").getNodeValue()),
            		Short.parseShort(attributes.getNamedItem("maxBaggage").getNodeValue()));
            resultList.add(temp);
        }
        
        NodeList resultElements3 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("Bomber");
        for (int i = 0; i < resultElements3.getLength(); i++) {
            Node result = resultElements3.item(i);
            NamedNodeMap attributes = result.getAttributes();
            Warplane temp=new Bomber(Short.parseShort(attributes.getNamedItem("maxHeight").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("places").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("rangeOfFlight").getNodeValue()),
            		Float.parseFloat(attributes.getNamedItem("fuel").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("maxSpeed").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("bombs").getNodeValue()));
            resultList.add(temp);
        }
        
        NodeList resultElements4 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("Fighter");
        for (int i = 0; i < resultElements4.getLength(); i++) {
            Node result = resultElements4.item(i);
            NamedNodeMap attributes = result.getAttributes();
            Warplane temp=new Fighter(Short.parseShort(attributes.getNamedItem("maxHeight").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("places").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("rangeOfFlight").getNodeValue()),
            		Float.parseFloat(attributes.getNamedItem("fuel").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("maxSpeed").getNodeValue()),
            		Integer.parseInt(attributes.getNamedItem("rockets").getNodeValue()));
            resultList.add(temp);
        }
        return resultList;
    }
	
	public static void saveToXML(List<Plane>list) {
		 try (FileWriter out = new FileWriter("output.xml")) {
	            out.write("<?xml version=\"1.0\"?>" + "\n");
	            out.write("<Planes>" + "\n");
	            for(Plane i:list) {
	                out.write(i.toXML() + "\n");
	            }
	            out.write("</Planes>");
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
