package core.algs.loader;

import core.algs.base.TypeOfAlgorithms;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
class ChoicerPanel extends JPanel {
	
	private JLabel type;
	private JLabel LanguageType;
	private JLabel RandomType;
	private JComboBox<String> SelectType;
	private String[] arrType = {"Translate"};
	private JComboBox<String> SelectLanguageType;
	private String[] arrSelectLanguageType = {"From Russian into English", "From English into Russian"};
	private JComboBox<String> SelectRandomType;
	private String[] arrSelectRandomType = {"Random", "Not random"};
	private JButton nextButton;
	
	private TypeOfAlgorithms typeAlgs;
	private AlgorithmsChoicerModal owner;
	
	ChoicerPanel(AlgorithmsChoicerModal owner) {
		this.owner = owner;
		type = new JLabel("Type of testing:");
		SelectType = new JComboBox<String>(arrType);
		LanguageType = new JLabel("From language into language:");
		SelectLanguageType = new JComboBox<String>(arrSelectLanguageType);
		RandomType = new JLabel("Random/Not random:");
		SelectRandomType = new JComboBox<String>(arrSelectRandomType);
		nextButton = new JButton("Next");
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		add(type, new GBC(0,0,1,1).setAnchor(GBC.CENTER).setWeight(50, 100));
		add(SelectType, new GBC(1,0,2,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(LanguageType, new GBC(0,1,1,1).setAnchor(GBC.CENTER).setWeight(50, 100));
		add(SelectLanguageType, new GBC(1,1,2,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(RandomType, new GBC(0,2,1,1).setAnchor(GBC.CENTER).setWeight(50, 100));
		add(SelectRandomType, new GBC(1,2,2,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		addButton(nextButton, new NextButtonActionListener(), new GBC(2,3,1,2).setAnchor(GBC.CENTER).setWeight(100, 100));
	}
	
	public JButton getRootButton() {
		return nextButton;
	}
	
	private void addButton(JButton button, ActionListener listener, GBC gbc) {
		button.addActionListener(listener);
		add(button, gbc);
	}
	
	private class NextButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String TYPE = SelectType.getItemAt(SelectType.getSelectedIndex());
			String LANGUAGETYPE = SelectLanguageType.getItemAt(SelectLanguageType.getSelectedIndex());
			String RANDOMTYPE = SelectRandomType.getItemAt(SelectRandomType.getSelectedIndex());
			
			if(TYPE.equalsIgnoreCase("Translate") & LANGUAGETYPE.equalsIgnoreCase("From Russian into English") & RANDOMTYPE.equalsIgnoreCase("Random")) {
				typeAlgs = TypeOfAlgorithms.RandomTranslateFromRussianIntoEnglish;
			} else if(TYPE.equalsIgnoreCase("Translate") & LANGUAGETYPE.equalsIgnoreCase("From English into Russian") & RANDOMTYPE.equalsIgnoreCase("Random")) {
				typeAlgs = TypeOfAlgorithms.RandomTranslateFromEnglishIntoRussian;
			} else if(TYPE.equalsIgnoreCase("Translate") & LANGUAGETYPE.equalsIgnoreCase("From Russian into English") & RANDOMTYPE.equalsIgnoreCase("Not random")) {
				typeAlgs = TypeOfAlgorithms.TranslateFromRussianIntoEnglish;
			} else if(TYPE.equalsIgnoreCase("Translate") & LANGUAGETYPE.equalsIgnoreCase("From English into Russian") & RANDOMTYPE.equalsIgnoreCase("Not random")) {
				typeAlgs = TypeOfAlgorithms.TranslateFromEnglishIntoRussian;
			} else typeAlgs = null;
			
			owner.setTypeOfAlgorithms(typeAlgs);
			owner.setVisible(false);
		}
	}
}
