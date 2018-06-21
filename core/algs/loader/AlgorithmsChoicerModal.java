package core.algs.loader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.algs.base.TypeOfAlgorithms;
/**
 * Class is a dialog box in which you can select the algorithm settings for testing
 * @author Kirill https://github.com/TieLieFaw
 *
 */
@SuppressWarnings("serial")
public class AlgorithmsChoicerModal extends JDialog {
	private TypeOfAlgorithms type;
	private ChoicerPanel choicerPanel;
	
	public AlgorithmsChoicerModal(JFrame owner) {
		super(owner, "Selecting test settings", true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		setSize(400,350);
		setTitle("Selecting testing settings");
		choicerPanel = new ChoicerPanel(this);
		add(choicerPanel);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(AlgorithmsChoicerModal.this, "Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) System.exit(0);
			}
		});
		getRootPane().setDefaultButton(choicerPanel.getRootButton());
	}
	
	void setTypeOfAlgorithms(TypeOfAlgorithms aType) {
		this.type = aType;
	}
	
	public TypeOfAlgorithms getTypeOfAlgotithms() {
		return type;
	}
}
