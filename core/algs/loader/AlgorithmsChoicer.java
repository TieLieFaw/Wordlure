package core.algs.loader;

import core.algs.base.TypeOfAlgorithms;
import core.algs.logic.*;
/**
 * The class contains methods for selecting algorithms 
 * @author Kirill https://github.com/TieLieFaw
 * @see AbstractTranslateAlgorithms
 * 
 */
public class AlgorithmsChoicer {
	/**
	 * The method calls a dialog box for selecting the parameters of the testing algorithm and returns the corresponding class of the algorithm
	 * @param choicer {@link AlgorithmsChoicerModal} - dialog box
	 * @return {@link AbstractTranslateAlgorithms}
	 */
	public synchronized static AbstractTranslateAlgorithms getAlgorithm(AlgorithmsChoicerModal choicer) {
		choicer.setVisible(true);
		TypeOfAlgorithms type = choicer.getTypeOfAlgotithms();
		switch(type.toString()) {
		case "RandomTranslateFromEnglishIntoRussian":
			return new AlgorithmRandomTranslateFromEnglishIntoRussian();
		case "RandomTranslateFromRussianIntoEnglish":
			return new AlgorithmRandomTranslateFromRussianIntoEnglish();
		case "TranslateFromEnglishIntoRussian":
			return new AlgorithmTranslateFromEnglishIntoRussian();
		case "TranslateFromRussianIntoEnglish":
			return new AlgorithmTranslateFromRussianIntoEnglish();
		}
		return null;
	}
}
