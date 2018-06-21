package core.database.loader;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.loader.TxtDictionaryLoader;

@SuppressWarnings("serial")
class ChoicerPanel extends JPanel {
	private JLabel file;
	private JButton openFileButton;
	private JCheckBox checked;
	private JLabel sortingType;
	private JComboBox<String> selectSortingType;
	private String[] arrSelectSortingType = {"Sorted","Not Sorted"};
	private JButton nextButton;
	
	private DatabaseChoicerModal owner;
	private String path;
	
	ChoicerPanel(DatabaseChoicerModal owner) {
		this.owner = owner;
		file = new JLabel("Select a file: ");
		sortingType = new JLabel("Sorted/NotSorted: ");
		openFileButton = new JButton("Open File");
		nextButton = new JButton("Next");
		checked = new JCheckBox();
		checked.setEnabled(false);
		selectSortingType = new JComboBox<String>(arrSelectSortingType);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		add(file, new GBC(0,0,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		addButton(openFileButton,new OpenFileButtonActionListener(), new GBC(1,0,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(checked, new GBC(2,0,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(sortingType, new GBC(0,1,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(selectSortingType, new GBC(1,1,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		addButton(nextButton, new NextButtonActionListener() ,new GBC(2,2,1,1).setAnchor(GBC.CENTER).setWeight(100, 100));
	}
	
	private void addButton(JButton button, ActionListener listener, GBC gbc) {
		button.addActionListener(listener);
		add(button, gbc);
	}
	
	public JButton getRootButton() {
		return nextButton;
	}
	
	private class NextButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(checked.isSelected() != true) JOptionPane.showMessageDialog(ChoicerPanel.this, "Please, select a file", "Warning", JOptionPane.WARNING_MESSAGE);
			else {
				if(selectSortingType.getSelectedIndex() == 0) {
					try {
						owner.setDataDoublyLinkedList(TxtDictionaryLoader.getSortedDataDoublyLinkedList(path));
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(ChoicerPanel.this, "Please, select a file again", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				} else if(selectSortingType.getSelectedIndex() == 1) {
					try {
						owner.setDataDoublyLinkedList(TxtDictionaryLoader.getDataDoublyLinkedList(path));
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(ChoicerPanel.this, "Please, select a file again", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				owner.setVisible(false);
			}
		}
	}
	
	private class OpenFileButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select a file");
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileFilter filter = new FileNameExtensionFilter("txt", "txt");
			fileChooser.setFileFilter(filter);
			fileChooser.setMultiSelectionEnabled(false);
			int result = fileChooser.showOpenDialog(ChoicerPanel.this);
			if(result == JFileChooser.APPROVE_OPTION) {
				path = fileChooser.getSelectedFile().getAbsolutePath();
				checked.setSelected(true);
			}
		}
	}
	
}