package core.base;

import java.util.Objects;

/**
 * The "Word" class encapsulates the word data in the object:
 * Part of the speech, the meaning of the word and its translation.
 * @author Kirill https://github.com/TieLieFaw
 */
public final class Word implements Comparable<Word>{
	private final partofspeech PartOfSpeech;
	private final String value;
	private final String translate;
	
	/**
	 * @param partOfSpeech The String value a part of speech({@link partofspeech})
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public Word(final String partOfSpeech, final String value, final String translate) {
		if(partOfSpeech == null)
			PartOfSpeech = partofspeech.None;
		else {
			switch(partOfSpeech) {
			case "Article":
				this.PartOfSpeech = partofspeech.Article;
				break;
			case "Noun":
				this.PartOfSpeech = partofspeech.Noun;
				break;
			case "Verb":
				this.PartOfSpeech = partofspeech.Verb;
				break;
			case "Adjective":
				this.PartOfSpeech = partofspeech.Adjective;
				break;
			case "Adverb":
				this.PartOfSpeech = partofspeech.Adverb;
				break;
			case "Pronoun":
				this.PartOfSpeech = partofspeech.Pronoun;
				break;
			case "Participle":
				this.PartOfSpeech = partofspeech.Participle;
				break;
			case "Preposition":
				this.PartOfSpeech = partofspeech.Preposition;
				break;
			case "Conjunction":
				this.PartOfSpeech = partofspeech.Conjunction;
				break;
			case "Interjection":
				this.PartOfSpeech = partofspeech.Interjection;
				break;
			case "Particle":
				this.PartOfSpeech = partofspeech.Particle;
				break;
			case "Numeral":
				this.PartOfSpeech = partofspeech.Numeral;
				break;
			default:
				this.PartOfSpeech = partofspeech.None;
				break;
			}
		}
		
		this.value = value;
		
		this.translate = translate;
		
	}
	
	/**
	 * @param partOfSpeech enum {@link partofspeech}
	 * @param value The String value of word
	 * @param translate The String value of translate this word
	 */
	public Word(final partofspeech PartOfSpeech, final String value, final String translate) {
		if(PartOfSpeech == null) {
			this.PartOfSpeech = partofspeech.None;
		} else
			this.PartOfSpeech = PartOfSpeech;
		
		this.value = value;
		this.translate = translate;
	}
	
	@Override
	public final synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ Word: ");
		builder.append(value);
		builder.append(", Translate: ");
		builder.append(translate);
		builder.append(", Part Of Speech: ");
		builder.append(PartOfSpeech);
		builder.append(" ]");
		return builder.toString();
	}
	
	@Override
	public synchronized final int hashCode() {
		return Objects.hash(PartOfSpeech, value, translate, PartOfSpeech.toString().length() , value.length() + 1, translate.length() * 3);
	}
	
	@Override
	public synchronized final boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		Word w = (Word) obj;
		return value == w.value && translate == w.translate && PartOfSpeech.toString() == w.PartOfSpeech.toString();
	}
	
	public final synchronized String getValue() {
		return value;
	}
	
	public final synchronized String getTranslate() {
		return translate;
	}
	
	public final synchronized String getPartOfSpeech() {
		return PartOfSpeech.toString();
	}
	
	@Override
	 public synchronized int compareTo(Word other) {
        return value.compareTo(other.value);
    }
}