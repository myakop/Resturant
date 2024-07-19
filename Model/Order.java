package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Utils.MyFileLogWriter;

public class Order implements Comparable<Order> , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private int id;
	private Customer customer;
	private ArrayList<Dish> dishes;
	private Delivery delivery;
	
	// constructors 
	
	public Order(Customer customer, ArrayList<Dish> dishes, Delivery delivery) {
		super();
		this.id = idCounter++;
		this.customer = customer;
		this.dishes = dishes;
		this.delivery = delivery;
	}
	
	public Order(int id) {
		this.id = id;
	}
	
	// getters setters
	
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Order.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Dish> getDishes() {
		return Collections.unmodifiableCollection(dishes);

	}
	

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + "]";
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
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// methods
	
	public double calcOrderRevenue() {
		double revenue = 0.0;
		for(Dish d : getDishes()) {
			double price = d.getPrice();
			double cost = 0.0;
			for(Component c : d.getComponenets()) {
				cost += c.getPrice();
			}
			revenue += (price - cost);
		}
		MyFileLogWriter.println("Order Revenue = "+revenue);
		return revenue;
	}
	
	public int orderWaitingTime(DeliveryArea da) {
		int time = 0;
		time += da.getDeliverTime();
		for(Dish d : getDishes()) {
			time += d.getTimeToMake();
		}
		MyFileLogWriter.println("Time for order: "+this+" is "+time+" minutes");
		return time;
	}

	@Override
	public int compareTo(Order o) { //sort by id
		if(this.id>o.id)
			return 1;
		if(this.id<o.id)
			return -1;
		return 0;
	}
	public boolean removeDish(Dish d) {

		if(d==null || !dishes.contains(d))
			return false;
		return dishes.remove(d);
	}
}

	
