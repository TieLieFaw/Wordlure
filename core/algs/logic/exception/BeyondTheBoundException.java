package core.algs.logic.exception;
/**
 * The class embody exception of going beyond the {@link DataDoublyLinkedList}. It's thrown if the {@code getNext()} method return null
 * @author Kirill https://github.com/TieLieFaw 
 *
 */
@SuppressWarnings("serial")
public class BeyondTheBoundException extends Exception {
	public BeyondTheBoundException(String message) {
		super(message);
	}
}
