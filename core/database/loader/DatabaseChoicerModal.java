package core.database.loader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.database.DataDoublyLinkedList;
/**
 * Class is a dialog box in which you can select the file from which the dictionary will be loaded into the program
 * @author Kirill https://github.com/TieLieFaw
 *
 */
@SuppressWarnings("serial")
public class DatabaseChoicerModal extends JDialog {
	private DataDoublyLinkedList data;
	private ChoicerPanel mainPanel;
	
	public DatabaseChoicerModal(JFrame owner) {
		super(owner, "Selecting dictionary", true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		setSize(400,350);
		setTitle("Selecting a dictionary");
		mainPanel = new ChoicerPanel(this);
		add(mainPanel);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(DatabaseChoicerModal.this, "Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) System.exit(0);
			}
		});
		getRootPane().setDefaultButton(mainPanel.getRootButton());
	}
	
	public DataDoublyLinkedList getData() {
		return data;
	}
	
	void setDataDoublyLinkedList(DataDoublyLinkedList data) {
		this.data = data;
	}
}
