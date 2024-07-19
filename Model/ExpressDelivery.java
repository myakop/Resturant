package Model;

import java.io.Serializable;
import java.time.LocalDate;


public class ExpressDelivery extends Delivery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 Order exorder;
	double exprice;
	
	public ExpressDelivery (DeliveryPerson deliveryPerson, DeliveryArea area,boolean isDelivered ,Order s , double price,LocalDate date) { //Constructor
		super(deliveryPerson,area,isDelivered,date);
		this.exorder=s;
		this.exprice=price;
		
		
	}
	public ExpressDelivery (DeliveryPerson deliveryPerson, DeliveryArea area,boolean isDelivered ,Order s ,LocalDate date) {// another Constructor
		super(deliveryPerson,area,isDelivered,date);
		this.exorder=s;
		this.exprice=5.0;
		
	}
	//setters and getters
	public Order getExorder() {
		return exorder;
	}
	public void setExorder(Order exorder) {
		this.exorder = exorder;
	}
	public double getExprice() {
		return exprice;
	}
	public void setExprice(double exprice) {
		this.exprice = exprice;
	}
	@Override
	public String toString() {
		return "Express delivery [id=" + getId() + ", deliveryPerson=" + getDeliveryPerson() + ", area=" + getArea() + ", isDelivered="
				+ isDelivered() + " postage=" + getExprice() + "]";
	}

	
	
	
	

}
