package ProjetJeu;

public class ListeQuestions {
	
	private Question tete;

	
	private class Maillon{
		Question value;
		Question suivant;
		
		public Maillon(Question value, Question suivant) {
			this.value = value;
			this.suivant = suivant;
		}
	}
	
	public ListeQuestions() {
		
	}
	
	

}
