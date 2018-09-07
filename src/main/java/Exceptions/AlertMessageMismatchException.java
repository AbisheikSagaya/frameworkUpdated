package Exceptions;

public class AlertMessageMismatchException extends Exception{

	private static final long serialVersionUID = 1L;

	public AlertMessageMismatchException() {
		super("Alert message is not matching");
	}

}
