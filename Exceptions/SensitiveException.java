package Exceptions;




public class SensitiveException extends Exception {
	public SensitiveException(String  c , String d) {
		super( "Customer " + c + " is sensitive to one of the components in the dish "+ d + "!");
	}

}
