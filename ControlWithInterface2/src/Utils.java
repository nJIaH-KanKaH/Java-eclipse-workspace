import java.io.File;
import java.io.IOException;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.RowFilter.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hierarchy.*;

public class Utils {

    public static List<Lamp> getFromFile(File file,int n) throws IOException {
        Scanner sc = new Scanner(file);
        String type=sc.next();
        List<Lamp>result=new ArrayList<>();
        if(type=="LampN")
        	while(sc.hasNext()) {
        		LampN tmp=new LampN(sc.next(),sc.nextInt(),sc.nextInt());
        		result.add(tmp);
        		n++;
        	}
        else if(type=="LampD")
        	while(sc.hasNext()) {
        		LampD tmp=new LampD(sc.next(), sc.nextInt(), sc.nextInt());
        		result.add(tmp);
        		n++;
        	}
        sc.close();
        return result;
    }

    public static List<Lamp> getFromXML(File file,int n) throws SAXException, ParserConfigurationException, IOException {
    	List<Lamp> resultList = new ArrayList<>();
        NodeList resultElements1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("LampN");
        for (int i = 0; i < resultElements1.getLength(); i++) {
            Node result = resultElements1.item(i);
            NamedNodeMap attributes = result.getAttributes();
            LampN temp=new LampN(attributes.getNamedItem("org").getNodeValue(),
            						Integer.parseInt(attributes.getNamedItem("P").getNodeValue()), 
            						Integer.parseInt(attributes.getNamedItem("time").getNodeValue()));
            resultList.add(temp);
            n++;
        }
        NodeList resultElements2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("LampD");
        for (int i = 0; i < resultElements2.getLength(); i++) {
            Node result = resultElements2.item(i);
            NamedNodeMap attributes = result.getAttributes();
            LampD temp=new LampD(attributes.getNamedItem("org").getNodeValue(),
            						Integer.parseInt(attributes.getNamedItem("P").getNodeValue()), 
            						Integer.parseInt(attributes.getNamedItem("number").getNodeValue()));
            resultList.add(temp);
            n++;
        }
        
        return resultList;
    }

    public static List<String> getData(List<Lamp> data) {
        List<String> result = new ArrayList<String>();
        for(Lamp i:data) {
        		result.add(i.toString());
        }
        return result;
    }
    
    public static List<String> getData2(List<Lamp>data,String name){
    	List<String>result=data.stream().sorted(new Lamp1()).collect(Collectors.toList()).stream().filter(p->p.getOrg().equals(name)).collect(Collectors.toList()).stream().map(e->e.toString()).collect(Collectors.toList());
    	return result;
    }
    
    public static List<String> getData3(List<Lamp>data){
    	List<String>result=data.stream().sorted(new Lamp2()).collect(Collectors.toList()).stream().map(e->e.toString()).collect(Collectors.toList());
    	return result;
    }
    
    public static List<String> getData4(List<Lamp>data){
    	Set<String>set=new TreeSet<>();
    	for(Lamp i:data) {
    		if(!set.contains(i.getOrg()))
    			set.add(i.getOrg());
    	}
    	List<String>result=new ArrayList<>();
    	for(String i:set) {
    		result.add(i);
    	}
    	return result;
    }
    
    public static List<String>getData5(List<Lamp>data){
    	List<LampD>temp=data.stream().filter(p->p.getClass()==LampD.class).collect(Collectors.toList()).stream().map(e->(LampD)e).collect(Collectors.toList());
    	OptionalInt max=temp.stream().mapToInt(e->e.getNumber()).max();
    	OptionalInt min=temp.stream().mapToInt(e->e.getNumber()).min();
    	int av=0;
    	if(!max.equals(OptionalInt.empty()) && !min.equals(OptionalInt.empty()))
    		av=(max.getAsInt()-min.getAsInt())/2;
    	LampD l=new LampD("", 1, 10000);
    	for(LampD i:temp) {
    		if(Math.abs(i.getNumber()-av)<Math.abs(l.getNumber()-av))
    			l=i;
    	}
    	List<String>result=new ArrayList<>();
    	result.add(l.toString());
    	
    	return result;
    }
}
