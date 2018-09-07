package Exceptions;

public class ElementCountMismatchException extends Exception {

	private static final long serialVersionUID = 1L;

	public ElementCountMismatchException() {
		super("Element count is not matching");
	}
}
