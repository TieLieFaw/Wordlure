package core.algs.logic;

import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.exception.BeyondTheBoundException;
import core.algs.logic.exception.HaveNotElementsException;
import core.base.Word;
import core.database.DataDoublyLinkedList;
/**
 * This interface defines methods for all word translation algorithms:
 * {@link AlgorithmRandomTranslateFromEnglishIntoRussian}, 
 * {@link AlgorithmRandomTranslateFromRussianIntoEnglish}, 
 * {@link AlgorithmTranslateFromEnglishIntoRussian}, 
 * {@link AlgorithmTranslateFromRussianIntoEnglish}
 * @author Kirill https://github.com/TieLieFaw
 * 
 */
public interface AbstractTranslateAlgorithms {
	/**
	 * The method checks the translation of a word
	 * @param w {@link Word} 
	 * @param userTranslate Translation({@link String}) received from the user
	 * @return true - if the translation is verified, else - false
	 */
	public boolean inspect(final Word w,final String userTranslate);
	/**
	 * The method returns a word from the {@link DataDoublyLinkedList} (Can be implemented based on a sequence or random sequence)
	 * @param data {@link DataDoublyLinkedList} - storage objects {@link Word}
	 * @return {@link Word} 
	 * @throws HaveNotElementsException thrown if in {@link DataDoublyLinkedList} doesn't have elements({@link Link})
	 * @throws BeyondTheBoundException It is thrown out in classes of algorithms based on the non-random sequence of output words
	 */
	public Word getWordFromData(DataDoublyLinkedList data) throws HaveNotElementsException, BeyondTheBoundException;
	/**
	 * @return {@link TypeOfAlgorithms } Type of embedded algorithm
	 */
	public TypeOfAlgorithms getTypeOfAlgorithms();
	/**
	 * The method resets the word counter to 0
	 */
	public void resetCounter();
}
