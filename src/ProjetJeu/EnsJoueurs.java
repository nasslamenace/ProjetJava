package ProjetJeu;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class EnsJoueurs {
	

	
	private Joueur[] joueurs;
	
	
	public EnsJoueurs() {
		creer();
	}
	
	public void creer() {
		
		
		joueurs = new Joueur[20];
		
		for(int i = 0; i < joueurs.length; i++)
			joueurs[i] = new Joueur(Integer.toString(i + 41));
	}
	
	public MyPanel afficher() {
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		
		container.add(new MyLabel("Liste des joueurs"));
		
		String[] liste = new String[20];
		
		for(int i = 0; i < joueurs.length; i++)
			liste[i] = joueurs[i].getNom();
		
		
		
		
		container.add(new JList(liste));
		
		return container;
		
	}
	
	public Joueur selectionnerJoueur() {
		
		return joueurs[(int)(Math.random() * (joueurs.length))];
		
	}

	
}
