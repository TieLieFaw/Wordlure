package core.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.AbstractTranslateAlgorithms;
import core.algs.logic.exception.BeyondTheBoundException;
import core.algs.logic.exception.HaveNotElementsException;
import core.base.Word;
import core.database.DataDoublyLinkedList;
import core.database.loader.DatabaseChoicer;
import core.database.loader.DatabaseChoicerModal;

@SuppressWarnings("serial")
class TranslatePanel extends JPanel{
	private MainWindow owner;
	private JLabel partofspeech;
	private JLabel wordValue;
	private JLabel isSuccessful;
	private JTextField translateField;
	private JButton nextButton;
	private JButton exitButton;
	private Word tempWord;
	
	
	TranslatePanel(MainWindow owner) {
		this.owner = owner;
		
		partofspeech = new JLabel();
		wordValue = new JLabel();
		isSuccessful = new JLabel();
		translateField = new JTextField(15);
		nextButton = new JButton("Next");
		exitButton = new JButton("Exit");
		
		Font mainFont = new Font("Arial", Font.BOLD, 20);
		Font localFont = new Font("Arial", Font.PLAIN, 15);
		
		
		isSuccessful.setFont(localFont);
		partofspeech.setFont(mainFont);
		wordValue.setFont(mainFont);
		translateField.setFont(mainFont);
		nextButton.setFont(mainFont);
		exitButton.setFont(mainFont);
		
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		add(isSuccessful, new GBC(0,0,8,1).setWeight(100, 35).setAnchor(GBC.NORTHEAST));
		add(partofspeech, new GBC(2,1,2,1).setWeight(100, 35).setAnchor(GBC.SOUTH));
		add(wordValue, new GBC(4,1,4,1).setWeight(100, 35).setAnchor(GBC.SOUTH));
		add(translateField, new GBC(2,2,6,1).setWeight(100, 35).setAnchor(GBC.CENTER));
		addButton(nextButton, new NextButtonActionListener(), new GBC(8,2,2,1).setWeight(100, 35).setAnchor(GBC.CENTER));
		addButton(exitButton, new ExitButtonActionListener(), new GBC(0,4,2,1).setWeight(100, 100).setAnchor(GBC.SOUTHEAST));
		
	}
	
	
	
	private void addButton(JButton button, ActionListener listener, GBC gbc) {
		button.addActionListener(listener);
		add(button, gbc);
	}
	
	MainWindow getOwner() {
		return owner;
	}
	
	synchronized void startAlgorithmRandomTranslate(AbstractTranslateAlgorithms alg, DataDoublyLinkedList data) {
		
		tempWord = null;
		
			try {
				tempWord = alg.getWordFromData(data);
			} catch (HaveNotElementsException | BeyondTheBoundException e1) {
				if(JOptionPane.showConfirmDialog(owner, "Dictionary file have been chosen is empty.\nDo you want to choose the dictionary file again?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					owner.setDictionary(DatabaseChoicer.getDictionary(new DatabaseChoicerModal(null)));
					try {
						tempWord = alg.getWordFromData(owner.getDictionary());
					} catch (HaveNotElementsException | BeyondTheBoundException e2) {
						JOptionPane.showConfirmDialog(owner, "Sorry, Restart the program.", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
				}else System.exit(0);
				
			}	finally {
				partofspeech.setText(tempWord.getPartOfSpeech());
				
				if(alg.getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromEnglishIntoRussian)) {
					wordValue.setText(tempWord.getValue());
				} else if(alg.getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish )) {
					wordValue.setText(tempWord.getTranslate());
				}
			}	
	}
	
	synchronized void startAlgorithmTranslate(AbstractTranslateAlgorithms alg, DataDoublyLinkedList data) {
		
		tempWord = null;
		
		try {
			tempWord = alg.getWordFromData(data);
		} catch (HaveNotElementsException e) {
			if(JOptionPane.showConfirmDialog(owner, "Dictionary file have been chosen is empty.\nDo you want to choose the dictionary file again?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				owner.setDictionary(DatabaseChoicer.getDictionary(new DatabaseChoicerModal(null)));
				try {
					tempWord = alg.getWordFromData(owner.getDictionary());
				} catch (HaveNotElementsException | BeyondTheBoundException e2) {
					JOptionPane.showConfirmDialog(owner, "Sorry, Restart the program.", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
			} else System.exit(0);
		} catch (BeyondTheBoundException e) {
			if(JOptionPane.showConfirmDialog(owner, "There are no words in the dictionary.\nDo you want to restart?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				alg.resetCounter();
				
				try {
					tempWord = alg.getWordFromData(data);
				} catch (HaveNotElementsException | BeyondTheBoundException e1) {
					JOptionPane.showConfirmDialog(owner, "Sorry, Restart the program.", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				
			} else System.exit(0);
			
		} finally {
			partofspeech.setText(tempWord.getPartOfSpeech());
			
			if(alg.getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromEnglishIntoRussian)) {
				wordValue.setText(tempWord.getValue());
			} else if(alg.getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromRussianIntoEnglish )) {
				wordValue.setText(tempWord.getTranslate());
			}
		}
	}
	
	private class NextButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromEnglishIntoRussian) ||
					owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromEnglishIntoRussian)) {
				if(owner.getProgramLogic().inspect(tempWord, translateField.getText())) {
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append("The Word: ");
					sBuilder.append("'");
					sBuilder.append(tempWord.getValue());
					sBuilder.append("'");
					sBuilder.append(" was translated correctly");
					isSuccessful.setText(sBuilder.toString());
					isSuccessful.setForeground(Color.GREEN);
				} else {
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append("The Word: ");
					sBuilder.append("'");
					sBuilder.append(tempWord.getValue());
					sBuilder.append("'");
					sBuilder.append(" wasn't translated correctly");
					isSuccessful.setText(sBuilder.toString());
					isSuccessful.setForeground(Color.RED);
				}
			} else if(owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish) ||
					owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromRussianIntoEnglish)) {
				if(owner.getProgramLogic().inspect(tempWord, translateField.getText())) {
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append("The Word: ");
					sBuilder.append("'");
					sBuilder.append(tempWord.getTranslate());
					sBuilder.append("'");
					sBuilder.append(" was translated correctly");
					isSuccessful.setText(sBuilder.toString());
					isSuccessful.setForeground(Color.GREEN);
				} else {
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append("The Word: ");
					sBuilder.append("'");
					sBuilder.append(tempWord.getTranslate());
					sBuilder.append("'");
					sBuilder.append(" wasn't translated correctly");
					isSuccessful.setText(sBuilder.toString());
					isSuccessful.setForeground(Color.RED);
				}
			}
			
			translateField.setText(null);
			
			if(owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromEnglishIntoRussian) ||
					owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish)) 
			startAlgorithmRandomTranslate(owner.getProgramLogic(), owner.getDictionary());
			else if(owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromEnglishIntoRussian) ||
					owner.getProgramLogic().getTypeOfAlgorithms().equals(TypeOfAlgorithms.TranslateFromRussianIntoEnglish))
				startAlgorithmTranslate(owner.getProgramLogic(), owner.getDictionary());	
		}
	}
	
	private class ExitButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(JOptionPane.showConfirmDialog(owner, "Do you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) System.exit(0);
			
		}
	}

}
