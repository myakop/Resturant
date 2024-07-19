package Exceptions;

import Model.Dish;

public class NoComponentsException extends Exception {

	public NoComponentsException(Dish d) {
		super("The dish "+ d +" is not include components!");
	
}
}
