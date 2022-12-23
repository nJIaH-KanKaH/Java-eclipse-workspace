import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class Utils {
	public static Object[][] getData(List<Tour>list){
		Object[][]data=new Object[list.size()+1][5];
		Object[]first= {new ImageIcon(),new String(),new String(),0,false};
		data[0]=first;
		for(int i=0;i<list.size();i++) {
			Object[]arr= {list.get(i).getCountryIcon(),list.get(i).getCountryName(),list.get(i).getCountryCapital(),list.get(i).getPrice(),false};
			data[i+1]= arr;
		}
		return data;
	}
	
	public static Map<String, ImageIcon> getData1(List<Tour>list){
		Map<String,ImageIcon> data=new TreeMap<>();
		for(int i=0;i<list.size();i++) {
			data.put(list.get(i).getCountryName(), list.get(i).getCountryIcon());		}
		return data;
	}
	
	public static void readList(String folderPath,List<Tour>list) {
		File folder = new File(folderPath);
		for (File file : folder.listFiles())
		{
			if(file.getName().contains("flag_")) {
				ImageIcon countryIcon=new ImageIcon(file.getAbsolutePath());
				String[]words=file.getName().split("_");
				String countryName="";
				StringBuilder builder=new StringBuilder(words[words.length-1]);
				builder.delete(builder.length()-4, builder.length());
				words[words.length-1]=builder.toString();
				for(int i=1;i<words.length;i++) {
					countryName+=words[i];
					countryName+=" "; 
				}
				String countryCapital="capital of "+countryName;
				int price=(int) (Math.random()*1000);
				Tour tour=new Tour(countryIcon, countryName, countryCapital, price);
				list.add(tour);
			}
		}
	}
}
