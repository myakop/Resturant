package Model;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class RegularDelivery extends Delivery implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeSet<Order> ordertree = new TreeSet<Order>();
	
	public RegularDelivery(TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered ,LocalDate date  ) { // Constructor
		super(deliveryPerson,area,isDelivered,date);
		this.ordertree=orders;
		
	}
	// setters and getters
	public SortedSet<Order> getOrdertree() {
		return   Collections.unmodifiableSortedSet(ordertree);
	}
	
	 public boolean addOrder(Order o) {
		 if(o==null)
			 return false;
		return  ordertree.add(o);
	 }
	  public boolean removeOrder(Order o) {
		  if(o==null || !ordertree.contains(o))
			  return false;
		return  ordertree.remove(o);
		  
	  }
	  @Override
		public String toString() {
			return "Delivery [id=" + getId() + ", deliveryPerson=" + getDeliveryPerson() + ", area=" + getArea() + ", isDelivered="
					+ isDelivered() + "]";
		}

	  
	  


	
	

}

