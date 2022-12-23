package LearnMain.Pizzamaker.Kitchen;

public class Ingredient {
	String name;
	boolean isIncluded=false;
	float cost;
	
	public Ingredient(String _name,float _cost) {
		this.name=_name;
		this.cost=_cost;
	}
	
	public void Include() {
		this.isIncluded=true;
	}
	
	public void Exclude() {
		this.isIncluded=false;
	}
	
	public static void ShowInformationAbout(Ingredient ingredient) {
		if(ingredient.isIncluded)
		System.out.println(ingredient.name+' '+ingredient.cost);
	}
	
	public float getCost() {
		return this.cost;
	}
}
