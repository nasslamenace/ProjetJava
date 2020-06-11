package ProjetJeu;

import java.io.Serializable;

public abstract class TypeQuestion implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5263496670563427639L;
	protected String question;
	
	abstract MyPanel afficher();
	
	
	public abstract String getSaisie();
	
	public abstract boolean isRight();
	
	public abstract String getReponse();
	public abstract void setReponse(String reponse);
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
