package sorted;

import java.io.Serializable;
import java.util.Comparator;

import Model.Order;

public class sortlocaldate implements Comparator<Order> , Serializable{

	@Override
	public int compare(Order o1, Order o2) {
		if(o1.getDelivery().getDateofdelivery().isBefore(o2.getDelivery().getDateofdelivery()))
			return 1;
		if(o1.getDelivery().getDateofdelivery().isAfter(o2.getDelivery().getDateofdelivery()))
			return -1;
		return 0;
	}

}
