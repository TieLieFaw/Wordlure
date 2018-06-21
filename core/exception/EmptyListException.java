package core.exception;

/**
 * The class embody exception of the absence of elements in the {@link DataDoublyLinkedList}. It's thrown if the list is empty
 * @author Kirill https://github.com/TieLieFaw
 *
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception{
	
	public EmptyListException(String message) {
		super(message);
	}
}
