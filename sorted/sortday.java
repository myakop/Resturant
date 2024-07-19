package sorted;
import java.io.Serializable;
import java.util.Comparator;

import Model.Delivery;

public class sortday implements Comparator<Delivery> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Delivery o1, Delivery o2) {
	if(o1.getDateofdelivery().getDayOfMonth()>o2.getDateofdelivery().getDayOfMonth())
		return 1;
	if(o1.getDateofdelivery().getDayOfMonth()<o2.getDateofdelivery().getDayOfMonth())
		return -1;
	if(o1.getDateofdelivery().getDayOfMonth()==o2.getDateofdelivery().getDayOfMonth()) {
		if(o2.getId()>o1.getId())
			return 1;
		if(o2.getId()<o1.getId())
			return -1;
	}
		return 0;
	}
	

}
