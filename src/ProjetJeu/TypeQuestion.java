package ProjetJeu;

public abstract class TypeQuestion {
	
	
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
