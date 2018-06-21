package core.database;

import core.base.Word;
import core.base.partofspeech;
/**
 * The "Link" class encapsulates data about the word object and contains references 
 * to the previous and subsequent elements
 * @author Kirill https://github.com/TieLieFaw
 *
 */
public final class Link {
	private final Word word;
	private Link next;
	private Link previous;
	
	public synchronized Link getNext() {
		return next;
	}
	
	public synchronized Link getPrevious() {
		return previous;
	}
	
	public synchronized void setNext(final Link next) {
		this.next = next;
	}
	
	public synchronized void setPrevious(final Link previous) {
		this.previous = previous;
	}
	
	public synchronized Word getWord() {
		return word;
	}
	/** 
	 * @param word - The "{@link Word} object" constructor
	 */
	public Link(final Word word) {
		this.word = word;
	}
	
	/**
	 * @param PartOfSpeech enum {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public Link(final partofspeech PartOfSpeech, final String value, final String translate) {
		this.word = new Word(PartOfSpeech, value, translate);
	}
	
	/**
	 * 
	 * @param PartOfSpeech The String value {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public Link(final String PartOfSpeech, final String value, final String translate) {
		this.word = new Word(PartOfSpeech, value, translate);
	}
	
	public synchronized void displayLink() {
		System.out.println(word.toString());
	}
}