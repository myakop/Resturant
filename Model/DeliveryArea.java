package Model;


import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import Utils.Neighberhood;

public class DeliveryArea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private int id;
	private String areaName;
	private HashSet<DeliveryPerson> delPersons; // new hashsets that Prevents duplication
	private HashSet<Delivery> delivers;
	private HashSet<Neighberhood> neighberhoods;
	private final int deliverTime;
	
	public DeliveryArea(String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
		super();
		this.id = idCounter++;
		this.areaName = areaName;
		this.neighberhoods = neighberhoods;
		this.deliverTime = deliverTime;
		delPersons = new HashSet<>();
		delivers = new HashSet<>();
	}
	
	public DeliveryArea(int id) {
		this.id = id;
		this.deliverTime = 0;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		DeliveryArea.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Collection<DeliveryPerson> getDelPersons() {
		return Collections.unmodifiableCollection(delPersons);
	}
	public Collection<Delivery> getDelivers() {
		return  Collections.unmodifiableCollection(delivers);
	}
	public Collection<Neighberhood> getNeighberhoods() {
		return  Collections.unmodifiableCollection(neighberhoods);
	}
	public int getDeliverTime() {
		return deliverTime;
	}
	@Override
	public String toString() {
		return "DeliveryArea [id=" + id + ", areaName=" + areaName + ", neighberhoods=" + neighberhoods
				+ ", deliverTime=" + deliverTime + "]";
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
		DeliveryArea other = (DeliveryArea) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public boolean addDelPerson(DeliveryPerson dp) { //add delivery person 
		 if( dp==null) {
			return false;
		 }
		  return delPersons.add(dp);
		 
	 }
	 boolean removeDelPerson(DeliveryPerson dp) { // remove delivery person
		 if(delPersons.contains(dp))
			 return delPersons.remove(dp);
		 return false;
	 }
	 boolean addDelivery (Delivery d) { // add delivery
		 if(d==null)
			 return false;
		 return delivers.add(d);
		
	 }
	 boolean removeDelivery (Delivery d) { //remove delivery
		 if(delivers.contains(d))
			 return delivers.remove(d);
		 return false;
	 }

	 boolean addNeighberhood (Neighberhood neighberhood) { // add Neighborhood
		 if(neighberhood==null)
			 return false;
		 return neighberhoods.add(neighberhood);
			
	 }
	 boolean removeNeighberhood (Neighberhood neighberhood){ // remove Neighborhood
		 if(neighberhoods.contains(neighberhood))
			 return neighberhoods.remove(neighberhood);
		return false;
	 }

 

	


	
	
	
	
	
}
