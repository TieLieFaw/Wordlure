package core.algs.logic.exception;
/**
 * The class embody exception of the absence of elements in the {@link DataDoublyLinkedList}. It's thrown if the {@code getFirst()} method return null
 * @author Kirill https://github.com/TieLieFaw
 *
 */
@SuppressWarnings("serial")
public class HaveNotElementsException extends Exception{
	public HaveNotElementsException(String message) {
		super(message);
	}
}
