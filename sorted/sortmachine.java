package sorted;
import java.io.Serializable;
import java.util.Comparator;

import Model.Delivery;
import Model.ExpressDelivery;
import Model.RegularDelivery;
public class sortmachine implements Comparator<Delivery> , Serializable{

	@Override
	public int compare(Delivery o1, Delivery o2) {
		if(o1 instanceof RegularDelivery && o2 instanceof ExpressDelivery )
			return 1;
		if(o1 instanceof ExpressDelivery && o2 instanceof RegularDelivery )
			return -1;
		if(o1 instanceof RegularDelivery && o2 instanceof RegularDelivery || o2 instanceof ExpressDelivery && o1 instanceof ExpressDelivery  )
		if(o1.getId() >o2.getId())
			return 1;
		if(o1.getId()<o2.getId())
			return -1;
		return 0;
	}

}
