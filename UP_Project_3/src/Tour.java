import javax.swing.ImageIcon;

public class Tour {
	private ImageIcon countryIcon;
	private String countryName;
	private String countryCapital;
	private int price;
	private boolean isActive;
	public Tour(ImageIcon countryIcon, String countryName, String countryCapital, int price) {
		this.countryIcon = countryIcon;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
		this.price = price;
		this.isActive=false;
	}
	public ImageIcon getCountryIcon() {
		return countryIcon;
	}
	public void setCountryIcon(ImageIcon countryIcon) {
		this.countryIcon = countryIcon;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Tour [countryIcon=" + countryIcon + ", countryName=" + countryName + ", countryCapital="
				+ countryCapital + ", price=" + price + "]";
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
