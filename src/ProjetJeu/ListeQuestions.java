package ProjetJeu;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;



public class ListeQuestions {
	
	
	public ArrayList<Question> questions;
	
	
	
	
	public ListeQuestions(ArrayList<Question> question, String theme) {
		
		this.questions = question;
	}
	
	public ListeQuestions(String theme) {
		ArrayList<Question> mesQuestions = new ArrayList<Question>();
		
		for(int i = 0; i < 3; i++) {
			int nb1 = (int)(Math.random() * (10));
			int nb2 = (int)(Math.random() * (10));
			mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new RC(nb1 +  "*" + nb2, Integer.toString(nb1*nb2))));
		}
		mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new VF("Le chat est un mammifère", true)));
		
		this.questions = mesQuestions;
	}
	
	public MyPanel afficherListe() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		
		container.add(new MyLabel("Liste des joueurs"));
		
		String[] liste = new String[20];
		
		for(int i = 0; i < questions.size(); i++)
			liste[i] = questions.get(i).getEnonce().getQuestion();
		
		
		
		
		container.add(new JList(liste));
		
		return container;
		
	}
	
	public void ajouterQuestion(Question q) {
		this.questions.add(q);
	}
	
	public void supprimerQuestion(Question q) {
		
		if(this.questions.indexOf(q) == -1)
			JOptionPane.showMessageDialog(null, "Cette question n'existe pas et ne peut donc pas être supprimé! ", "error",
					JOptionPane.ERROR_MESSAGE);
		else
			this.questions.remove(this.questions.indexOf(q));
			
		
	}
	
	public Question selectionnerQuestion(Niveau n) {
		
		
		/*
		//Stream<Question> questionStream = this.questions.stream();
		System.out.println(this.questions.size());
		try {
		
			return this.questions.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new Question(Niveau.facile, Theme.themes.get(Theme.selectionerThemePhase1()), new RC("2 + 2", "4"));
		}
		
		*/
		
		
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
