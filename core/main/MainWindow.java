package core.main;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.*;
import core.database.DataDoublyLinkedList;
@SuppressWarnings("serial")
/**
 * The class implements the main program window
 * @author Kirill https://github.com/TieLieFaw
 *
 */
public final class MainWindow extends JFrame {

	private AbstractTranslateAlgorithms programLogic;
	private DataDoublyLinkedList dictionary;
	private TranslatePanel panel;
	
	/**
	 * Standart class constructor<br>
	 * Example:<br>
	 * <p>
	 * {@code AbstractTranslateAlgorithms Alg = AlgorithmsChoicer.getAlgorithm(new AlgorithmsChoicerModal(null));} <br>
	 * {@code DataDoublyLinkedList data = DatabaseChoicer.getDictionary(new DatabaseChoicerModal(null));} <br>
	 * {@code MainWindow a = new MainWindow(Alg, data);} <br>
	 * </p>
	 * 
	 * 
	 * @param programLogic {@link AbstractTranslateAlgorithms}
	 * @param dictionary {@link DataDoublyLinkedList}
	 */
	public MainWindow(AbstractTranslateAlgorithms programLogic, DataDoublyLinkedList dictionary) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		this.programLogic = programLogic;
		this.dictionary = dictionary;
		
		panel = new TranslatePanel(this);
		add(panel, BorderLayout.CENTER);
		
		setResizable(false);
		setTitle("Wordlure");
		setSize(550, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(MainWindow.this, "Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) System.exit(0);
			}
		});
		
	}
	
	synchronized AbstractTranslateAlgorithms getProgramLogic() {
		return programLogic;
	}
	
	synchronized DataDoublyLinkedList getDictionary() {
		return dictionary;
	}
	
	synchronized void setDictionary(DataDoublyLinkedList data) {
		this.dictionary = data;
	}
	
	
	public void startTesting() {
		this.setVisible(true);
		if(programLogic.getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromEnglishIntoRussian) ||
				programLogic.getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish)) 
		panel.startAlgorithmRandomTranslate(programLogic, dictionary);
		else if(programLogic.getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromEnglishIntoRussian) ||
				programLogic.getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromRussianIntoEnglish))
			panel.startAlgorithmTranslate(programLogic, dictionary);
	}
}
