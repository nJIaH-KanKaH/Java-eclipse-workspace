package LearnMain.Pizzamaker.Client;

public class Client {
	String name;
	short showingCode;
	static short code=Short.MIN_VALUE;
	
	public Client(String _name) {
		this.name = _name;
		this.showingCode = code++;
	}
	public static void Update() {
		if(code==Short.MAX_VALUE)
			code=Short.MIN_VALUE;
	}
}
