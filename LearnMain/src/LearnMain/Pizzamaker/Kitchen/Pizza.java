package LearnMain.Pizzamaker.Kitchen;

public class Pizza {
	String name;
	Ingredient[] listOfIngredients;
	boolean calzone=false;
	float resultCost=1.0f;
	
	
	static Ingredient[] basicListOfIngredients;
	//{"Tomato Paste",1.f}, {"Cheese",1.f} , {"Salami",1.5f} , {"Bacon",1.2f} , {"Garlic",0.3f} , {"Corn",0.7f} , {"Pepperoni",0.6f} , {"Olives",0.5f}

	public Pizza(String _name,Ingredient[] _list,boolean _calzone) {
		this.name=_name;
		this.listOfIngredients=_list;
		this.calzone=_calzone;
	}
	
	public float SummaryCost(Pizza one) {
		for(int i=0;i<one.listOfIngredients.length;i++) {
			one.resultCost+=one.listOfIngredients[i].getCost();
		}
		if(one.calzone)
			resultCost+=0.5f;
		return resultCost;
	}
	
}
