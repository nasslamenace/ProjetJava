package ProjetJeu;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Phase1 extends MyPanel implements Phase {
	
	
	private ArrayList<Joueur> joueurs;
	private Theme theme;
	MyLabel annonceLbl = new MyLabel();
	private MyButton confirmBtn = new MyButton("Confirmer");
	
	private int compteurJoueur;
	
	public Phase1() {
		joueurs = new ArrayList<Joueur>();
		compteurJoueur = 0;
		selectionnerJoueurs();
		phaseDeJeu();
		
	}
	

	@Override
	public void selectionnerJoueurs() {
			
		do {
			Joueur j = EnsJoueurs.selectionnerJoueur();
			if(joueurs.indexOf(j) == -1)
				joueurs.add(j);
		}while(joueurs.size() < 4);
		
	}

	@Override
	public void phaseDeJeu() {
		
		theme = new Theme(Theme.themes.get(Theme.selectionerThemePhase1()));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		annonceLbl.setText("C'est au joueurs " + joueurs.get(compteurJoueur).getNom() + " de jouer");
		
		this.add(annonceLbl);
		
		MyPanel questionPan = new MyPanel();
		
		Question q = new Question(Niveau.facile, Theme.themes.get(Theme.selectionerThemePhase1()), new RC("2 + 2", "4"));
		
		questionPan = q.afficher();
		
		this.add(questionPan);
		this.add(confirmBtn);
		

			

		
		
		
		
	}

}
