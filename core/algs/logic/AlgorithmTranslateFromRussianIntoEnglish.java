package core.algs.logic;

import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.exception.BeyondTheBoundException;
import core.algs.logic.exception.HaveNotElementsException;
import core.base.Word;
import core.database.DataDoublyLinkedList;
import core.database.Link;
/**
 * This class embody an algorithm for translating words from Russian into English(don't random sequence)
 * The class implements the {@link AbstactTranslateAlgorithms} interface
 * @author Kirill https://github.com/TieLieFaw
 */
public final class AlgorithmTranslateFromRussianIntoEnglish implements AbstractTranslateAlgorithms{
	private final TypeOfAlgorithms type = TypeOfAlgorithms.TranslateFromRussianIntoEnglish;
	private int counter = 0;
	private int nElems;
	/**
	 * AlgorithmTranslateFromRussianIntoEnglish class constructor
	 */
	public AlgorithmTranslateFromRussianIntoEnglish() {
		
	}
	/**
	 * 
	 * @return {@link TypeOfAlgorithms } Type of embedded algorithm
	 */
	public synchronized TypeOfAlgorithms getTypeOfAlgorithms() {
		return type;
	}
	/**
	 * The method checks the translation of a word. 
	 * The method receives an input object {@link Word} and a variant of the translation of the user (Translation into English)
	 */
	@Override
	public synchronized boolean inspect(final Word w,final String userTranslate) { 
		if(userTranslate == null || userTranslate.length() == 0) return false;
		if(w.getValue().compareToIgnoreCase(userTranslate) == 0) return true;
		return false;
	}
	/**
	 * The method returns a word from the {@link DataDoublyLinkedList}(don't random sequence)
	 */
	@Override
	public synchronized Word getWordFromData(DataDoublyLinkedList data) throws HaveNotElementsException, BeyondTheBoundException {
		nElems = countElements(data);
		Link current = data.getFirst();
		if(current == null) throw new HaveNotElementsException("There are no items in the LinkedList");
		for(int k = 0; k < counter; k++) {
			current = current.getNext();
		}
		if(counter < nElems)
			counter++;
		else
			throw new BeyondTheBoundException("Going beyond the bound linked list");
		return current.getWord();
	}
	/**
	 * The method resets the word counter to 0
	 */
	public synchronized void resetCounter() {
		counter = 0;
	}
	/**
	 * 
	 * @param data A {@link DataDoublyLinkedList} in which to count the number of elements
	 * @return Number of items in the list
	 * @throws HaveNotElementsException thrown if the {@code getFirst()} method return null
	 */
	private int countElements(DataDoublyLinkedList data) throws HaveNotElementsException {
		int c = 0;
		Link current = data.getFirst();
		if(current == null) throw new HaveNotElementsException("There are no items in the LinkedList");
		else c = 1;
		while(current.getNext() != null) {
			current = current.getNext();
			c++;
		}
		return c;
	}	
}
