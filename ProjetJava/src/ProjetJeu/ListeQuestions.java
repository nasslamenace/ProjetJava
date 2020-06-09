package ProjetJeu;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;



public class ListeQuestions {
	
	
	private ArrayList<Question> questions;
	private String theme;
	
	
	
	
	public ListeQuestions(ArrayList<Question> question, String theme) {
		
		this.questions = question;
		this.theme = theme;
	}
	
	public ListeQuestions(String theme) {
		
		/*ArrayList<Question> mesQuestions = new ArrayList<Question>();
		
		for(int i = 0; i < 2; i++) {
			int nb1 = (int)(Math.random() * (10));
			int nb2 = (int)(Math.random() * (10));
			mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new RC(nb1 +  "*" + nb2, Integer.toString(nb1*nb2))));
		}
		
		mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new VF("est ce que le chien aboie ? ", true)));
		mesQuestions.add(new Question(Niveau.facile, theme, (TypeQuestion) new VF("est ce que le chat miaule ? ", true)));
		this.questions = mesQuestions;*/
		
		this.questions = FileManager.retrieveQuestions(theme);
		this.theme = theme;
	}
	
	public MyPanel afficherListe() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		
		container.add(new MyLabel("Liste des Question pour le theme" + theme + " :"));
		
		String[] liste = new String[20];
		
		for(int i = 0; i < questions.size(); i++)
			liste[i] = questions.get(i).getEnonce().getQuestion();
		
		
		
		
		container.add(new JList(liste));
		
		return container;
		
	}
	
	public MyPanel afficherListe(Niveau n) {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		
		container.add(new MyLabel("Liste des Question pour le theme" + theme + " :"));
		
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
		
		
		ArrayList<Question> i = (ArrayList<Question>) questionStream.filter(x -> x.getNiveau() == n)
				.collect(Collectors.toList());

		if(i.size() < 1) {
			JOptionPane.showMessageDialog(null, "Il n'y a pas de question de niveau "+ n +", pour le theme " + theme + " veuillez en ajouter ! ", "error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
			return i.get((int)(Math.random() * (i.size())));
		
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
