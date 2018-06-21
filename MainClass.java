import javax.swing.SwingUtilities;

import core.algs.loader.AlgorithmsChoicer;
import core.algs.loader.AlgorithmsChoicerModal;
import core.algs.logic.AbstractTranslateAlgorithms;
import core.database.DataDoublyLinkedList;
import core.database.loader.DatabaseChoicer;
import core.database.loader.DatabaseChoicerModal;
import core.main.MainWindow;

public class MainClass {
	
	public static void main(String ... strings) {
		AbstractTranslateAlgorithms Alg = AlgorithmsChoicer.getAlgorithm(new AlgorithmsChoicerModal(null));
		DataDoublyLinkedList data = DatabaseChoicer.getDictionary(new DatabaseChoicerModal(null));
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow a = new MainWindow(Alg, data);
				a.startTesting();
			}
		});
	}
}
