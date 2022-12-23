import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//import jdk.internal.util.xml.SAXParser;

public class XmlFileSpecifications implements Serializable {

    private static String lexicographicallyFirstFileName;
    private static int totalChars;
    private static boolean processingCharCount;

    private String fileName;
    private String rootElementName;
    private int charCount;
    private int nestingDepth;

    public String toXml() {
        StringBuilder builder = new StringBuilder();
        builder.append("\t<XmlFileSpecifications fileName =\"" + fileName + "\">\n");
        builder.append("\t\t<rootElementName>" + rootElementName + "</rootElementName>\n");
        builder.append("\t\t<charCount>" + charCount + "</charCount>\n");
        builder.append("\t\t<nestingDepth>" + nestingDepth + "</nestingDepth>\n");
        builder.append("\t</XmlFileSpecifications>\n");
        return builder.toString();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRootElementName() {
        return rootElementName;
    }

    public void setRootElementName(String rootElementName) {
        this.rootElementName = rootElementName;
    }

    public int getCharCount() {
        return charCount;
    }

    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }

    public int getNestingDepth() {
        return nestingDepth;
    }

    public void setNestingDepth(int nestingDepth) {
        this.nestingDepth = nestingDepth;
    }

    public XmlFileSpecifications(String fileName, String rootElementName, int charCount, int nestingDepth) {
        this.fileName = fileName;
        this.rootElementName = rootElementName;
        this.charCount = charCount;
        this.nestingDepth = nestingDepth;
    }

    public static ArrayList<XmlFileSpecifications> readInstancesFromXml(File file) {
        ArrayList<XmlFileSpecifications> result = new ArrayList<XmlFileSpecifications>();
        try {
            NodeList instances = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
                    .getDocumentElement().getElementsByTagName("XmlFileSpecifications");
            for (int i = 0; i < instances.getLength(); i++) {
                Element element = (Element) instances.item(i);
                result.add(new XmlFileSpecifications(element.getAttribute("fileName"),
                        element.getElementsByTagName("rootElementName").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("charCount").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("nestingDepth").item(0).getTextContent())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<XmlFileSpecifications> readInstancesFromBinary(File file)
            throws ClassNotFoundException, IOException {
        ArrayList<XmlFileSpecifications> result = new ArrayList<XmlFileSpecifications>();
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        result = (ArrayList<XmlFileSpecifications>) stream.readObject();
        stream.close();
        return result;
    }

    public static void saveInstancesToXml(ArrayList<XmlFileSpecifications> instances, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(convertInstancesToXml(instances));
        writer.close();
    }

    public static String convertInstancesToXml(ArrayList<XmlFileSpecifications> instances) {
        StringBuilder builder = new StringBuilder("<InstancesOfXmlSpecifications>\n");
        for (XmlFileSpecifications instance : instances) {
            builder.append(instance.toXml());
        }
        builder.append("</InstancesOfXmlSpecifications>\n");
        return builder.toString();
    }

    public static void saveInstancesToBinary(ArrayList<XmlFileSpecifications> instances, File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(instances);
        stream.close();
    }

    public static String checkXmlConformity(File file) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(Thread.currentThread().getContextClassLoader().getResource("schema.xsd"));
            schema.newValidator().validate(new StreamSource(file));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Файл верный";
    }

    public static String calculateProperties(File file) {
        try {

            lexicographicallyFirstFileName = null;
            totalChars = 0;

            SAXParserFactory.newInstance().newSAXParser().parse(file, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String name, Attributes attributes)
                        throws SAXException {
                    super.startElement(uri, localName, name, attributes);

                    if (name.equals("XmlFileSpecifications")) {
                        if (lexicographicallyFirstFileName == null
                                || lexicographicallyFirstFileName.compareTo(attributes.getValue("fileName")) > 0) {
                            lexicographicallyFirstFileName = attributes.getValue("fileName");
                        }
                    }

                    if (name.equals("charCount")) {
                        processingCharCount = true;
                    }
                    
                }

                @Override
                public void endElement(String uri, String localName, String name) throws SAXException {
                    super.endElement(uri, localName, name);
                    if (name.equals("charCount")) {
                        processingCharCount = false;
                    }
                }
                
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if(processingCharCount)
                        totalChars += Integer.parseInt(new String(ch, start, length));
                }
            
            });
            
            return "Lexicographically smallest fileName: " + lexicographicallyFirstFileName + ",  Total characters: " + totalChars;
        }
        catch (Exception e) {
            return "Error";
        }
    }

    public static void saveInstancesAsHtml(ArrayList<XmlFileSpecifications> instances, File file)
            throws FileNotFoundException, TransformerException, IOException {
        file.createNewFile();
        Transformer trasform=TransformerFactory.newInstance().newTransformer(new StreamSource("html_transformation.xsl"));
        trasform.transform(new StreamSource(new StringReader(convertInstancesToXml(instances))), new StreamResult(file));
    }

}
