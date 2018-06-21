package core.database.loader;

import core.database.DataDoublyLinkedList;
/**
 * The class contains methods for selecting dictionary file
 * @author Kirill https://github.com/TieLieFaw
 * @see DataDoublyLinkedList
 * @see TxtDictionaryLoader
 */
public class DatabaseChoicer {
	/**
	 * The method calls a dialog for selecting a dictionary file and returns an object {@link DataDoublyLinkedList}
	 * @param choicer {@link DatabaseChoicerModal} - dialog box
	 * @return {@link DataDoublyLinkedList}
	 */
	public synchronized static DataDoublyLinkedList getDictionary(DatabaseChoicerModal choicer) {
		choicer.setVisible(true);
		return choicer.getData();
	}
}
