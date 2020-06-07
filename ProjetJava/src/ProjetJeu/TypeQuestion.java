package ProjetJeu;

import java.io.Serializable;

public abstract class TypeQuestion implements Serializable{
	
	
	protected String question;
	
	abstract MyPanel afficher();
	
	
	public abstract String getSaisie();
	
	public abstract boolean isRight();
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
