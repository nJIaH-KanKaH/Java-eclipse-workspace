package Main;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Utils {

    public static List<Result> getFromFile(File file) throws IOException {
        Scanner sc = new Scanner(file);
        List<Result>result=new ArrayList<>();
        while(sc.hasNext()) {
        	Result temp=new Result(sc.nextLong(), sc.next(), sc.nextInt(), sc.nextInt());
            result.add(temp);
        }   
        sc.close();
        return result;
    }

    public static List<Result> getFromXML(File file) throws SAXException, ParserConfigurationException, IOException {
        List<Result> resultList = new ArrayList<>();
        NodeList resultElements = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement().getElementsByTagName("Result");
        for (int i = 0; i < resultElements.getLength(); i++) {
            Node result = resultElements.item(i);
            NamedNodeMap attributes = result.getAttributes();
            Result temp=new Result(Long.parseLong(attributes.getNamedItem("id").getNodeValue()),
            						attributes.getNamedItem("lastName").getNodeValue(), 
            						Integer.parseInt(attributes.getNamedItem("course").getNodeValue()), 
            						Integer.parseInt(attributes.getNamedItem("group").getNodeValue()));
            resultList.add(temp);
        }
        return resultList;
    }
    
    public static TreeSet<Result> generateTreeSet(List<Result>data){
    	TreeSet<Result>result=new TreeSet<>(new ResComparator());
    	for(Result i:data) {
    		boolean flaq=false;
            for(Result j:result) {
            	if(j.equals(i)) {flaq=true;j.inc();break;}
            }
            if(!flaq)
            result.add(i);
    	}
    	return result;
    }
    
    public static List<String>getDataList(List<Result>data){
    	List<String>list=data.stream().map(e->e.toString()).collect(Collectors.toList());
    	return list;
    }

    public static TreeSet<String> getData(TreeSet<Result> data) {
        TreeSet<String> result = new TreeSet<String>();
        for(Result i:data) {
        	//if(i.getCounter()>1)
        		result.add(i.toString());
        }
        return result;
    }
    
    public static List<String> getData1(List<Result>data){
    	Map<String, Integer>map=Utils.get(data);
    	List<String>result=new ArrayList<>();
    	for(Map.Entry<String, Integer> i:map.entrySet()) {
    		if(i.getValue()>1)
    			result.add(i.getKey());
    	}
    	return result;
    }
    
    public static Map<String, Integer> get(List<Result>data){
    	Map<String, Integer>map=new TreeMap<String, Integer>();
    	for(Result i:data) {
//    		if(!map.containsKey(i.getLastName()))
//    			map.put(i.getLastName(), new Integer(1));
//    		else {
    			map.merge(i.getLastName(), 1, Integer::sum);
    //		}
    	}
    	return map;
    }
}
