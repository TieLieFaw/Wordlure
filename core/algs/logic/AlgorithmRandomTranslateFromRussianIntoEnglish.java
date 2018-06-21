package core.algs.logic;

import core.base.Word;
import core.database.DataDoublyLinkedList;
import core.database.Link;
import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.exception.HaveNotElementsException;
/**
 * This class embody an algorithm for translating words from Russian into English(random sequence)
 * The class implements the {@link AbstactTranslateAlgorithms} interface
 * @author Kirill https://github.com/TieLieFaw
 *
 */
public final class AlgorithmRandomTranslateFromRussianIntoEnglish implements AbstractTranslateAlgorithms {
	private int counter = 0;
	private final TypeOfAlgorithms type = TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish;
	/**
	 * AlgorithmRandomTranslateFromRussianIntoEnglish class constructor
	 */
	public AlgorithmRandomTranslateFromRussianIntoEnglish() {
		
	}
	/**
	 * 
	 * @return @return {@link TypeOfAlgorithms } Type of embedded algorithm
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
	 * The method returns a word from the {@link DataDoublyLinkedList}(random sequence)
	 */
	@Override
	public synchronized Word getWordFromData(DataDoublyLinkedList data) throws HaveNotElementsException {
		int nElems = 0;
		if(data.getFirst() != null) nElems = 1;
		else throw new HaveNotElementsException("There are no items in the LinkedList");
		Link count = data.getFirst();
		while(count.getNext() != null) {
			nElems++;
			count = count.getNext();
		}
		int nWord = (int) (Math.random() * nElems);	
		
		count = data.getFirst();
		for(int k = 0; k < nWord; k++)
			count = count.getNext();
		counter++;
		return count.getWord();
	}
	/**
	 * The method resets the word counter to 0
	 */
	public synchronized void resetCounter() {
		counter = 0;
	}
}
