import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class Utils {
	public static InfList readFromJSON(String fileName) {
		Gson gson=new Gson();
		InfList list = new InfList();
		Reader reader;
		try {
			reader=new FileReader(new File(fileName));
			list=gson.fromJson(reader, InfList.class);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
