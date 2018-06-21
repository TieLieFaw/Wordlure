package core.database;

import core.database.Link;
import core.exception.EmptyListException;
import core.base.partofspeech;
import core.base.Word;
/**
 * The class DataDoublyLinkedList is a doubly linked list that stores {@link Link} objects.
 * @author Kirill https://github.com/TieLieFaw
 * @author Robert Lafore: "Data Structures and Algorithms in Java". 
 * The doubly linked list is based on the example given in the book
 *
 */
public final class DataDoublyLinkedList {
	private Link first;
	private Link last;
	
	public synchronized Link getFirst() {
		return first;
	}
	
	public synchronized Link getLast() {
		return last;
	}
	
	public synchronized void setFirst(final Link link) {
		first = link;
	}
	
	public synchronized void setLast(final Link link) {
		last = link;
	}
	
	public DataDoublyLinkedList() {
		first = null;
		last = null;
	}
	
	public synchronized boolean isEmpty() {
		return first == null;
	}
	/**
	 * Inserts a word to the top of the list
	 * @param word - {@link Word} to be inserted into the list
	 */
	public synchronized void insertFirst(final Word word) {
		Link newLink = new Link(word);
		if(isEmpty())
			last = newLink;
		else
			first.setPrevious(newLink);
		newLink.setNext(first);
		first = newLink;
	}
	/**
	 * Inserts a word to the top of the list
	 * @param PartOfSpeech enum {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public synchronized void insertFirst(final partofspeech PartOfSpeech, final String value, final String translate) {
		Link newLink = new Link(PartOfSpeech, value, translate);
		if(isEmpty())
			last = newLink;
		else
			first.setPrevious(newLink);
		newLink.setNext(first);
		first = newLink;
	}
	/**
	 * Inserts a word to the top of the list
	 * @param PartOfSpeech The String value {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public synchronized void insertFirst(final String PartOfSpeech, final String value, final String translate) {
		Link newLink = new Link(PartOfSpeech, value, translate);
		if(isEmpty())
			last = newLink;
		else
			first.setPrevious(newLink);
		newLink.setNext(first);
		first = newLink;
	}
	
	/**
	 * Inserts words at the end of the list
	 * @param word - {@link Word} to be inserted into the list
	 */
	public synchronized void insertLast(final Word word) {
		Link newLink = new Link(word);
		if(isEmpty())
			first = newLink;
		else {
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}
	
	/**
	 * Inserts words at the end of the list
	 * @param PartOfSpeech enum {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public synchronized void insertLast(final partofspeech PartOfSpeech, final String value, final String translate) {
		Link newLink = new Link(PartOfSpeech, value, translate);
		if(isEmpty())
			first = newLink;
		else {
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}
	
	/**
	 * Inserts words at the end of the list
	 * @param PartOfSpeech The String value {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public synchronized void insertLast(final String PartOfSpeech, final String value, final String translate) {
		Link newLink = new Link(PartOfSpeech, value, translate);
		if(isEmpty())
			first = newLink;
		else {
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}
	
	/**
	 * The method deletes the element at the beginning of the list
	 * @return Deletion element
	 * @throws EmptyListException The method thrown the {@link EmptyListException} if the list is empty
	 */
	public synchronized Link deleteFirst() throws EmptyListException {
		Link temp = first;
		if(temp == null) throw new EmptyListException("The List is empty");
		if(first.getNext() == null)
			last = null;
		else
			first.getNext().setPrevious(null);
		first = first.getNext();
		return temp;
	}
	
	/**
	 * The method deletes the element at the end of the list
	 * @return Deletion element
	 * @throws EmptyListException The method thrown the {@link EmptyListException} if the list is empty
	 */
	public synchronized Link deleteLast() throws EmptyListException {
		Link temp = last;
		if(temp == null) throw new EmptyListException("The List is empty");
		if(first.getNext() == null)
			first = null;
		else
			last.getPrevious().setNext(null);
		last = last.getPrevious();
		return temp;
	}
	
	public synchronized void displayForward() {
		System.out.println("List (first --> last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.getNext();
		}
		System.out.println("");		
	}
	/**
	 * The method removes the element from the list with this key(Word value)
	 * @param ValueKey key
	 * @return Deleted element
	 * @throws EmptyListException The method thrown the {@link EmptyListException} if the list is empty
	 */
	public synchronized Link deleteValueKey(final String ValueKey) throws EmptyListException {
		Link current = first;
		if(current == null) throw new EmptyListException("The List is empty");
			while(current.getWord().getValue() != ValueKey) {
				current = current.getNext();
				if(current == null)
					return null;
			}
		if(current == first)
			first = current.getNext();
		else
			current.getPrevious().setNext(current.getNext());
		
		if(current == last)
			last = current.getPrevious();
		else
			current.getNext().setPrevious(current.getPrevious());
		return current;
	}
	
	/**
	 * The method removes the element from the list with this key(Word Translate)
	 * @param TranslateKey key
	 * @return Deleted element
	 * @throws EmptyListException The method thrown the {@link EmptyListException} if the list is empty
	 */
	
	public synchronized Link deleteTranslateKey(final String TranslateKey) throws EmptyListException {
		Link current = first;
		if(current == null) throw new EmptyListException("The List is empty");
			while(current.getWord().getTranslate() != TranslateKey) {
				current = current.getNext();
				if(current == null)
					return null;
			}
		if(current == first)
			first = current.getNext();
		else
			current.getPrevious().setNext(current.getNext());
			
		if(current == last)
			last = current.getPrevious();
		else
			current.getNext().setPrevious(current.getPrevious());
		return current;	
	}
}