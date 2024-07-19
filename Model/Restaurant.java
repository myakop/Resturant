package Model;


import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;



import Exceptions.ConvertToExpressException;
import Exceptions.IllegalCustomerException;
import Exceptions.NoComponentsException;
import Exceptions.SensitiveException;
import Utils.Expertise;
import Utils.MyFileLogWriter;
import Utils.Neighberhood;
import application.Main;
import application.getDeliveryController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sorted.sortday;
import sorted.sortlocaldate;
import sorted.sortmachine;



public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Restaurant restaurant = null;

	private HashMap<Integer,Cook> cooks ;
	private HashMap<Integer,DeliveryPerson> deliveryPersons;
	private HashMap<Integer,Customer> customers;
	private HashMap<Integer,Dish> dishes;
	private HashMap<Integer,Component> componenets;
	private HashMap<Integer,Order> orders;
	private HashMap<Integer,Delivery> deliveries;
	private HashMap<Integer,DeliveryArea> areas;
	private HashMap<Customer, TreeSet<Order>> orderByCustomer;
	private HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea;
	private HashSet<Customer> blackList; 
     

	public static Restaurant getInstance()
	{
		if(restaurant == null)
			restaurant = new Restaurant();
		return restaurant;
	}

	private Restaurant() {
		cooks = new HashMap<Integer,Cook>();
		deliveryPersons = new HashMap<Integer,DeliveryPerson>();
		customers = new HashMap<Integer,Customer>();
		dishes = new HashMap<Integer,Dish>();
		componenets = new HashMap<Integer,Component>();
		orders = new HashMap<Integer,Order>();
		deliveries = new HashMap<Integer,Delivery>();
		areas = new HashMap<Integer,DeliveryArea>();
		orderByCustomer = new HashMap<Customer, TreeSet<Order>>();
		orderByDeliveryArea = new HashMap<DeliveryArea, HashSet<Order>>();
		blackList = new HashSet<Customer>();
		
	}
	
	public HashSet<Customer> getblacklist(){
		return blackList;
	}

	public HashMap<Integer,Cook> getCooks() {
		return cooks;
	}

	public void setCooks(HashMap<Integer,Cook> cooks) {
		this.cooks = cooks;
	}

	public HashMap<Integer,DeliveryPerson> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(HashMap<Integer,DeliveryPerson> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public HashMap<Integer,Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<Integer,Customer> customers) {
		this.customers = customers;
	}

	public HashMap<Integer,Dish> getDishes() {
		return dishes;
	}

	public void setDishes(HashMap<Integer,Dish> dishes) {
		this.dishes = dishes;
	}

	public HashMap<Integer,Component> getComponenets() {
		return componenets;
	}

	public void setComponenets(HashMap<Integer,Component> componenets) {
		this.componenets = componenets;
	}

	public HashMap<Integer,Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer,Order> orders) {
		this.orders = orders;
	}

	public HashMap<Integer,Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(HashMap<Integer,Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public HashMap<Integer,DeliveryArea> getAreas() {
		return areas;
	}

	public void setAreas(HashMap<Integer,DeliveryArea> areas) {
		this.areas = areas;
	}

	public boolean addCook(Cook cook) {
		if(cook == null || cooks.containsValue(cook))
			return false;
		 cooks.put(cook.getId(), cook);
		 return true;

	}

	public boolean addDeliveryPerson(DeliveryPerson dp, DeliveryArea da) {
		if(dp == null || !getAreas().containsValue(da) || deliveryPersons.containsValue(dp) )
			return false;
		deliveryPersons.put(dp.getId(), dp);

		 return da.addDelPerson(dp);		 
	}
	

	public boolean addCustomer(Customer cust) {
		
		if(cust == null || customers.containsValue(cust) )
			return false;
		 getCustomers().put(cust.getId(), cust);
		 return true;
		 
	}

	public boolean addDish(Dish dish) {
		if(dish == null || dishes.containsValue(dish))
			return false;
		for(Component c : dish.getComponenets()) {
			if(!getComponenets().containsValue(c))
				return false;
		}
		
		dishes.put(dish.getId(), dish);
		return  true;
		 
	}

	public boolean addComponent(Component comp) {
		if(comp == null || getComponenets().containsValue(comp))
			return false;

		 getComponenets().put(comp.getId(), comp);
		 return true;
	}

	public boolean addOrder(Order order) { // add order 
		if(order == null ||  !getCustomers().containsValue(order.getCustomer()) || orders.containsValue(order))
			return false;
		try {
	for(Dish d : order.getDishes()){// check if  contains all the dishes in this order
			if(!getDishes().containsValue(d))
				return false;
			for(Component comp : d.getComponenets()) // check  customer sensitive with the components and throw exception
				if((order.getCustomer().isSensitiveToGluten() && comp.isHasGluten()) || (order.getCustomer().isSensitiveToLactose() && comp.isHasLactose())) {
					String s = order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName();
						throw new SensitiveException(s,d.getDishName());
				}
		}
		orders.put(order.getId(), order);
		
		if(blackList.contains(order.getCustomer())) // check if customer one of the black list and throw an exception
			throw new IllegalCustomerException();
		
		
		
		}
		catch(SensitiveException  d) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("Customer is sensitive to one of the components in the dish");
			aler.showAndWait();
			return false;
		// catch one of the throws if One of the conditions was met
		} catch (IllegalCustomerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("customer is in blacklist");
			aler.showAndWait();
			return false;
		}
		
		
		 
		 return true;
	}
	public boolean addDelivery(Delivery delivery) {
		try {
		if(delivery == null || getDeliveries().containsKey(delivery.getId()) || !getDeliveryPersons().containsKey(delivery.getDeliveryPerson().getId()))
		{
		return false;
		}
		if(delivery instanceof RegularDelivery) {
		RegularDelivery rd = (RegularDelivery)delivery;
		for(Order o : rd.getOrdertree()){
		if(!getOrders().containsKey(o.getId()))
		return false;
		o.setDelivery(delivery);
		}
		if(rd.getOrdertree().size() ==1) {
		throw new ConvertToExpressException();
		}
		if(rd.getOrdertree().isEmpty())
		return false;
		}
		else {
		ExpressDelivery ed = (ExpressDelivery)delivery;
		if(!getOrders().containsKey(ed.getExorder().getId()))
		return false;
		ed.getExorder().setDelivery(delivery);
		}
		}catch(ConvertToExpressException e) {
		//Utils.MyFileLogWriter.println(e.getMessage());
		Alert a=new Alert(AlertType.CONFIRMATION);
		   a.setHeaderText("canot be a regular delivery cause the have jus 1 order");
		   a.showAndWait();
		   
		RegularDelivery rd = (RegularDelivery)delivery;
		delivery = new ExpressDelivery(rd.getDeliveryPerson(), rd.getArea(),rd.isDelivered(), rd.getOrdertree().first() ,rd.getDateofdelivery());
		}finally {
		delivery.getArea().addDelivery(delivery);
		if(delivery instanceof RegularDelivery) {
		RegularDelivery rg = (RegularDelivery)delivery;
		for(Order o: rg.getOrdertree()) {
		TreeSet<Order> orders = orderByCustomer.get(o);
		if(orders == null)
		orders = new TreeSet<>(new sortlocaldate());
		   orders.add(o);
		   orderByCustomer.put(o.getCustomer(), orders);
		}
		}
		else {
		ExpressDelivery ex = (ExpressDelivery)delivery;
		TreeSet<Order> orders = orderByCustomer.get(ex.getExorder());
		if(orders == null)
		orders = new TreeSet<>(new sortlocaldate());
		   orders.add(ex.getExorder());
		   orderByCustomer.put(ex.getExorder().getCustomer(), orders);
		}
		}
		return getDeliveries().put(delivery.getId(),delivery) ==null;
		}
	
	public boolean addDeliveryArea(DeliveryArea da) {
		if(da == null || areas.containsValue(da))
			return false;
		 getAreas().put(da.getId(), da);
		 return true;
	}
	public boolean addCustomerToBlackList(Customer c) { // add Complicated customers to data structure
		if(c==null)
			return false;
	return blackList.add(c);
	}

	public boolean removeCook(Cook cook) {
		if(cook == null || !getCooks().containsKey(cook.getId()))
			return false;
		return getCooks().remove(cook.getId(), cook);
	}

	public boolean removeDeliveryPerson(DeliveryPerson dp) {
		if(dp == null || !getDeliveryPersons().containsValue(dp))
			return false;
		for(Delivery d : getDeliveries().values()) {
			if(d.getDateofdelivery() != null   &&     d.getDeliveryPerson().equals(dp)) {
				d.setDeliveryPerson(null);
			}
		}
		deliveryPersons.remove(dp.getId());
		return  dp.getArea().removeDelPerson(dp);
	}

	public boolean removeCustomer(Customer cust) {
		if(cust == null || !getCustomers().containsValue(cust))
			return false;
		
		 getCustomers().remove(cust.getId());
		 return true;
	}

	public boolean removeDish(Dish dish) {
		if(dish == null || !getDishes().containsValue(dish))
			return false;
		
		for(Order ord : Main.res.getOrders().values()) {
			if(ord.getDelivery() == null) {
				ord.removeDish(dish);
			}
		}
		for(Delivery d : deliveries.values()) {
			if(!d.isDelivered()) {
				if(d instanceof RegularDelivery ) {
					if(!((RegularDelivery)d).getOrdertree().isEmpty())
				for(Order o : ((RegularDelivery)d).getOrdertree()) {
					if(o.getDishes().contains(dish) && !o.removeDish(dish))
						return false;
				}
			}
				if(d instanceof ExpressDelivery) {
					if(((ExpressDelivery)d).getExorder() != null) {
					if(((ExpressDelivery)d).getExorder().getDishes().contains(dish) && !((ExpressDelivery)d).getExorder().removeDish(dish) )
						return false;
					}
				}
			}
		}
		 getDishes().remove(dish.getId());
		 return true;
	}

	public boolean removeComponent(Component comp) { // remove component from Data Structure
		if(comp == null || !getComponenets().containsValue(comp))
			return false;
		try {
		for(Dish d : getDishes().values()) {
			if(d.getComponenets().contains(comp)) {
				d.removeComponent(comp);
				if(d.getComponenets().size() <= 0) // if we remove the last component throw exception 
						throw new NoComponentsException(d); 
			}
			
		}
		 return getComponenets().remove(comp.getId()) != null;
		}
		catch(NoComponentsException c){
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("this is the last comp in some dish ");
			aler.showAndWait();
			getComponenets().remove(comp.getId());
			
			
		}
//		finally {
//			getComponenets().remove(comp.getId());
//		}
		
		return true;
	}

	public boolean removeOrder(Order order) {
		
		if(order == null || !getOrders().containsValue(order) )
			return false;
		if(order.getDelivery() !=null) {
			if(order.getDelivery().isDelivered()) {
				return false;
			}
		
		
		if(order.getDelivery() instanceof RegularDelivery) {
			((RegularDelivery)order.getDelivery()).removeOrder(order);
		}
		if(order.getDelivery() instanceof ExpressDelivery) {
			((ExpressDelivery)order.getDelivery()).setExorder(null);
		}
		}
			
		getOrders().remove(order.getId());
		return true;
	}

	public boolean removeDelivery(Delivery delivery) {
		if(delivery == null || !getDeliveries().containsValue(delivery) )
			return false;
		if(delivery instanceof RegularDelivery ) {
		for(Order o : ((RegularDelivery)delivery).getOrdertree()) {
			o.setDelivery(null);
		}
		}
		
		if(delivery instanceof ExpressDelivery) {
			if(((ExpressDelivery)delivery).getExorder() != null){
			((ExpressDelivery)delivery).getExorder().setDelivery(null);
			}
		}
		getDeliveries().remove(delivery.getId());
		if(delivery.getArea() != null)
		return  delivery.getArea().removeDelivery(delivery);
		return false;
	}

	public boolean removeDeliveryArea(DeliveryArea oldArea, DeliveryArea newArea) {
		if(oldArea == null || newArea == null || !getAreas().containsValue(oldArea) || !getAreas().containsValue(newArea))
			return false;
		for(Neighberhood n : oldArea.getNeighberhoods()) {
			if(!newArea.getNeighberhoods().contains(n))
				newArea.addNeighberhood(n);
		}
		for (Delivery dv : oldArea.getDelivers()) {
			newArea.addDelivery(dv);
			dv.setArea(newArea);
		}
		for (DeliveryPerson dp : oldArea.getDelPersons()) {
			newArea.addDelPerson(dp);
			dp.setArea(newArea);
		}
		
		getAreas().remove(oldArea.getId());
		return true;
	}
	public boolean removedevarea(DeliveryArea oldArea) {
		if(oldArea == null || !getAreas().containsValue(oldArea))
			return false;
		getAreas().remove(oldArea.getId());
		return true;
	}

	public Cook getRealCook(int id) {
		
			return cooks.get(id);
		
	}

	public DeliveryPerson getRealDeliveryPerson(int id) {
	
		
		
		return deliveryPersons.get(id);
	}

	public Customer getRealCustomer(int id) {
		
		return customers.get(id);

	}

	public Dish getRealDish(int id) {
		
	return dishes.get(id);
	}

	public Component getRealComponent(int id) {
	        return componenets.get(id);
	}

	public Order getRealOrder(int id) {
		return orders.get(id);
	}

	public Delivery getRealDelivery(int id) {
		
      return deliveries.get(id);
	}

	public DeliveryArea getRealDeliveryArea(int id) {
	  return areas.get(id);
	}


	public Collection<Dish> getReleventDishList(Customer c){
		ArrayList<Dish> dishList = new ArrayList<>();
		if(!c.isSensitiveToGluten() && !c.isSensitiveToLactose())
			return getDishes().values();
		for(Dish d : getDishes().values()) {
			boolean isValid = true;
			for(Component comp : d.getComponenets()) {
				if(c.isSensitiveToGluten() && c.isSensitiveToLactose()) {
					if(comp.isHasGluten() || comp.isHasLactose()) {
						isValid = false;
						break;
					}
				}
				else if(c.isSensitiveToGluten() && comp.isHasGluten()) {
					isValid = false;
					break;
				}
				else if(c.isSensitiveToLactose() && comp.isHasLactose()) {
					isValid = false;
					break;
				}
			}
			if(isValid)
				dishList.add(d);
		}
		return dishList;
	}

	public void deliver(Delivery d) {
		d.setDelivered(true);
//		if(d instanceof RegularDelivery)
//		for(Order o : ((RegularDelivery)d).getOrdertree()) {
//			
//			MyFileLogWriter.println(o+" had reached to Customer "+o.getCustomer());
//		}
//		if(d instanceof ExpressDelivery) {
//			MyFileLogWriter.println(((ExpressDelivery)d).exorder+" had reached to Customer "+((ExpressDelivery)d).exorder.getCustomer());
//		}
	}

	public ArrayList<Cook> GetCooksByExpertise(Expertise e){
		ArrayList<Cook> cooks = new ArrayList<>();
		for(Cook c : getCooks().values()) {
			if(c.getExpert().equals(e))
				cooks.add(c);
		}
		return cooks;
	}
	public TreeSet<Delivery> getDeliveriesByPerson(DeliveryPerson dp, int month){// return the delivers that delivery do in some month
		TreeSet<Delivery> tr =new TreeSet<Delivery>(new sortday());
		
		for(Delivery dev: deliveries.values()) {
			if(dev.getDeliveryPerson().equals(dp)) {
			if(dev.getDateofdelivery().getMonthValue()==month) {
				tr.add(dev);
			}
			}
			
				
				
		}
		return tr;
	}
	public  HashMap<String,Integer> getNumberOfDeliveriesPerType(){ // return how much they are delivery express and regular
		
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
	LocalDate date = LocalDate.now();
		for (Delivery dev : deliveries.values()) {
			if(dev.getDateofdelivery().getYear()==date.getYear()) {
				if(dev instanceof RegularDelivery) {
					if(!hash.containsKey("Regular delivery")) {// how much they are regular delivery
						hash.put("Regular delivery", 1);
					}
					else {
						int x =hash.get("Regular delivery");
						x++;
						hash.replace("Regular delivery", x);
					}
				}
			if(dev instanceof ExpressDelivery) {
				if(!hash.containsKey("Express delivery")) { // how much they are express delivery
					hash.put("Express delivery",1);
				}
				else {
					int y = hash.get("Express delivery");
					y++;
					hash.replace("Express delivery",y);
				}
			}
				
					
			}
			
			
		}
		return hash;
		
		
	}
	 public double revenueFromExpressDeliveries() { // returns the revenue from express delivery
	 double revenue=0;
		 HashSet<Customer> hashs = new HashSet<Customer>();
		 
		 for (Delivery dev : deliveries.values()) {
			 if(dev instanceof ExpressDelivery) {
				 if(hashs.add(((ExpressDelivery)dev).exorder.getCustomer())){
				revenue= revenue+ ((ExpressDelivery)dev).exprice+(30);
				 }
				 else {
					 revenue = revenue + ((ExpressDelivery)dev).exprice;
				 }
					
			 }
			 
		 }
		 return revenue;
	
		
	 }
	public  LinkedList<Component> getPopularComponents(){ //return the popular components in the dishes  
		LinkedList<Component> link = new LinkedList<Component>();
		HashMap<Component,Integer> tr = new HashMap<Component,Integer>();
		
		for (Dish dish : dishes.values()) { // set every component in hash map with how much they are appeared

			for(Component compcheck :dish.getComponenets()) {
				
					if(!tr.containsKey(compcheck)){
						tr.put(compcheck, 1);
						link.add(compcheck); // add the component to the list
					}
					else {
						int x=tr.get(compcheck);
						x++;
						tr.replace(compcheck, x);
					}	
		}
			link.sort(new Comparator<>() { //sort the list by how much they appeared

				
				public int compare(Component o1, Component o2) {
					if(tr.get(o1) < tr.get(o2))
						return 1;
					if(tr.get(o1) > tr.get(o2))
					return -1;
					if(tr.get(o1) == tr.get(o2)) {
						if(o1.getId()<o2.getId())
							return 1;
						if(o1.getId()>o2.getId())
							return -1;
					}
					return 0;
				}
				
			});
			
			
			}
		return link;
		}
	 public TreeSet<Dish> getProfitRelation(){// return a sort dishes that sorted by the relation between price and time to make
		Comparator<Dish> comp = (Dish dish1,Dish dish2) -> {
		if(dish1.getPrice()/dish1.getTimeToMake() < dish2.getPrice()/dish2.getTimeToMake())
		
		return 1;
		
		if(dish1.getPrice()/dish1.getTimeToMake() > dish2.getPrice()/dish2.getTimeToMake())
	    
		return -1;
	     
		if(dish1.getPrice()/dish1.getTimeToMake() == dish2.getPrice()/dish2.getTimeToMake()) {
		       if(dish1.getId() < dish2.getId())
							return 1;
				if(dish1.getId() > dish2.getId())
							return -1;
						
					}
					return 0;
				  };
		TreeSet<Dish> Relation = new TreeSet<Dish>(comp);
		Relation.addAll(dishes.values());
					return Relation;

		}
		 
	
	
	  public TreeSet<Delivery> createAIMacine(DeliveryPerson dp, DeliveryArea da, TreeSet<Order> orders){ //returns delivers that we make
		  TreeSet<Delivery> tr = new  TreeSet<Delivery>( new sortmachine());
		  TreeSet<Order> tr2 = new  TreeSet<Order>();
		  
		  
		  LocalDate local = LocalDate.now();
		  if(orders.size() < 3) { // if we have less than 3 orders -> create a express delivery for every one
			  for(Order ord :orders) {
				  Delivery dev1 = new ExpressDelivery(dp, da ,false,ord,local);
				  tr.add(dev1);
				 
			  }
				  return tr;
			  
		  }
		  else{
			  
			  for(Order lorder : orders) { // make express delivery for every customer with sensitive and one  regular delivery for the other 
			  if(lorder.getCustomer().isSensitiveToGluten() || lorder.getCustomer().isSensitiveToLactose()) {
				  Delivery dev = new ExpressDelivery(dp, da ,false,lorder,local);
				  tr.add(dev);
				   
			  }
			  else {
				  tr2.add(lorder);
			  }  
		}
			  if(tr2.size() <2) {
					 Delivery dev = new ExpressDelivery(dp, da ,false,tr2.first(),local);
						  tr.add(dev);
					  }
		  if(tr2!= null) {
		  Delivery d = new RegularDelivery(tr2,dp,da,false,local);
		  tr.add(d);
		  
		  }
		  }
		  return tr;
		 
	 }
	  
	  
		
		
		}
	
		
		
		
	



	


