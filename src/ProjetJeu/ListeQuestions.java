package ProjetJeu;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;



public class ListeQuestions {
	
	
	private ArrayList<Question> questions;
	
	private String theme;
	
	
	public ListeQuestions(ArrayList<Question> question, String theme) {
		
		this.questions = question;
		this.theme = theme;
		
	}
	
	public ListeQuestions(String theme) {
		ArrayList<Question> mesQuestions = new ArrayList<Question>();
		
		for(int i = 0; i < 15; i++) {
			int nb1 = (int)(Math.random() * (10));
			int nb2 = (int)(Math.random() * (10));
			mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new RC(nb1 +  "*" + nb2, "12")));
		}
	}
	
	public void afficherListe() {
		
	}
	
	public void ajouterQuestion(Question q) {
		this.questions.add(q);
	}
	
	public void supprimerQuestion(Question q) {
		
		if(this.questions.indexOf(q) == -1)
			JOptionPane.showMessageDialog(null, "Cette quesiton n'existe pas et ne peut donc pas être supprimé! ", "error",
					JOptionPane.ERROR_MESSAGE);
		else
			this.questions.remove(this.questions.indexOf(q));
			
		
	}
	
	public Question selectionnerQuestion(Niveau n) {
		
		Stream<Question> questionStream = this.questions.stream();
		
		
		
		
		
		switch(n) {
		case facile:
			ArrayList<Question> i = (ArrayList<Question>) questionStream.filter(x -> x.getNiveau() == Niveau.facile)
									.collect(Collectors.toList());
			
			if(i.size() < 1) {
				JOptionPane.showMessageDialog(null, "Il n'y a pas de question de niveau facile ", "error",
						JOptionPane.ERROR_MESSAGE);
				return null;
				}
			else
				return i.get((int)(Math.random() * (i.size())));
			
			
		case moyen:
			ArrayList<Question> j = (ArrayList<Question>) questionStream.filter(x -> x.getNiveau() == Niveau.moyen)
			.collect(Collectors.toList());

			if(j.size() < 1) {
					JOptionPane.showMessageDialog(null, "Il n'y a pas de question de niveau moyen", "error",
							JOptionPane.ERROR_MESSAGE);
					return null;
			}
			else
				return j.get((int)(Math.random() * (j.size())));
			
		case difficile:
			ArrayList<Question> k = (ArrayList<Question>) questionStream.filter(x -> x.getNiveau() == Niveau.moyen)
			.collect(Collectors.toList());

			if(k.size() < 1) {
				JOptionPane.showMessageDialog(null, "Il n'y a pas de question de niveau moyen", "error",
							JOptionPane.ERROR_MESSAGE);
				return null;
				}
			else
				return k.get((int)(Math.random() * (k.size())));
			
		default:
			return null;
			
		}
		
	}
		
	

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
	
	
	/*private Maillon tete;
	
	private Maillon currentQuestion;

	
	private class Maillon{
		public Question value;
		public Maillon suivant;
		
		
		
		public Maillon(Question value, Maillon suivant) {
			this.value = value;
			this.suivant = suivant;
		}
	}
	
	public ListeQuestions() {
		this.tete = null;
		
	}
	
	public void AjouterQuestion(Question q) {
		this.tete = new Maillon(q, this.tete);
	}
	
	public void AfficherListe() {
		
		Maillon debut = this.tete;
		
		while(debut.suivant != null) {
			System.out.println(tete.value);
			debut = debut.suivant;
		}
		
		
	}*/
	
	
	

}
