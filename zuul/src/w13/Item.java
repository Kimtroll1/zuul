package w13;

public class Item {
	private String name;
	private String description;
	private int weight;
	
	public Item (String name, String dec, int weight) {
		this.name = name;
		this.description = dec;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getWeight() {
		return weight;
	}
	
	public String getLongDescription() {
		return name + " (" + weight + "Kg, " + description + ")";
	}
}
