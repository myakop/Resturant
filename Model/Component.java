package Model;

import java.io.Serializable;

public class Component implements Comparable<Component> ,  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private int id;
	private String componentName;
	private boolean hasLactose;
	private boolean hasGluten;
	private double price;
	
	public Component(String componentName, boolean hasLactose, boolean hasGluten, double price) {
		super();
		this.id = idCounter++;
		this.componentName = componentName;
		this.hasLactose = hasLactose;
		this.hasGluten = hasGluten;
		setPrice(price);
	}
	
	public Component(int id) {
		this.id = id;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Component.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public boolean isHasLactose() {
		return hasLactose;
	}

	public void setHasLactose(boolean hasLactose) {
		this.hasLactose = hasLactose;
	}

	public boolean isHasGluten() {
		return hasGluten;
	}

	public void setHasGluten(boolean hasGluten) {
		this.hasGluten = hasGluten;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price > 0.0)
			this.price = price;
		else
			price = 0.0;
	}

	
	@Override
	public String toString() {
		return "Component [id=" + id + ", componentName=" + componentName + ", price=" + price + "]";
	}
	

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Component o) { //sort by price and then by id
		if(this.price > o.price )
		return 1;
		if(this.price<o.price)
			return -1;
		if(this.price==o.price) {
			if(this.id > o.id)
				return 1;
			if(this.id < o.id) 
				return -1;
		}
		return 0;
	}
	
	
	
}
