package core.loader;

import core.base.Word;
import core.database.DataDoublyLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The class contains methods for loading the dictionary (various formats) into the program({@link DataDoublyLinkedList})
 * @author Kirill https://github.com/TieLieFaw
 *
 */
public final class TxtDictionaryLoader {
	/**
	 * The method loads a dictionary from a file
	 * @param Absolutepath path to Dictionary file(.txt)
	 * @return  DataDoublyLinkedList if method completed, else return null;
	 */
	public synchronized final static DataDoublyLinkedList getDataDoublyLinkedList(String Absolutepath) throws FileNotFoundException {
		
		DataDoublyLinkedList tempDataDoublyLinkedList = new DataDoublyLinkedList();
		
		try(BufferedReader bReader = new BufferedReader(new FileReader(new File(Absolutepath)))) {

			String currentWord = null;
			String delimeter = "/";
			
			while((currentWord = bReader.readLine()) != null) {
				String[] tempWord = currentWord.split(delimeter);
				tempDataDoublyLinkedList.insertFirst(tempWord[0], tempWord[1], tempWord[2]);
			}		
		} catch(IOException e) {
			StackTraceElement[] a = e.getStackTrace();
			for(StackTraceElement elem : a) {
				System.err.println("Source file: " + elem.getFileName() + ", Class: " + elem.getClassName() + ", Method: "+ elem.getMethodName() + " (Line: " + elem.getLineNumber() + " );");
			}
			System.err.println("****************************************************");
			System.err.println(e.getMessage());
			FileNotFoundException ex = new FileNotFoundException("Invalid file path");
			ex.initCause(e);
			throw ex;
		}
		return tempDataDoublyLinkedList;
	}
	/**
	 * The method loads a dictionary from a file and sorts the dictionary
	 * @param Absolutepath path to Dictionary file(.txt)
	 * @return  DataDoublyLinkedList if method completed, else return null;
	 */
	public synchronized final static DataDoublyLinkedList getSortedDataDoublyLinkedList(String Absolutepath) throws FileNotFoundException {
		
		ArrayList<Word> tempList = new ArrayList<Word>();
		DataDoublyLinkedList tempDataDoublyLinkedList = new DataDoublyLinkedList();
		
		try(BufferedReader bReader = new BufferedReader(new FileReader(new File(Absolutepath)))) {

			String currentWord = null;
			String delimeter = "/";
			
			while((currentWord = bReader.readLine()) != null) {
				String[] tempWord = currentWord.split(delimeter);
				Word word = new Word(tempWord[0], tempWord[1], tempWord[2]);
				tempList.add(word);
			}
		} catch(IOException e) {
			StackTraceElement[] a = e.getStackTrace();
			for(StackTraceElement elem : a) {
				System.err.println("Source file: " + elem.getFileName() + ", Class: " + elem.getClassName() + ", Method: "+ elem.getMethodName() + " (Line: " + elem.getLineNumber() + " );");
			}
			System.err.println("****************************************************");
			System.err.println(e.getMessage());
			FileNotFoundException ex = new FileNotFoundException("Invalid file p1ath");
			ex.initCause(e);
			throw ex;
		}
		
		Collections.sort(tempList);
		
		for(Word w : tempList) {
			tempDataDoublyLinkedList.insertLast(w);
		}
		
		return tempDataDoublyLinkedList;
	}
}