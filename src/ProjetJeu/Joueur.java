package ProjetJeu;

import javax.swing.BoxLayout;

public class Joueur {
	
	public static int nbJoueurs = 100;
	
	private String nom;
	private int numero;
	private int score;
	private Etat etat;
	
	
	public Joueur(String nom) {	
		numero = nbJoueurs;
		score = 0;
		etat = Etat.enAttente;
		nbJoueurs += 10;
		this.setNom(nom);
	}
	
	
	public MyPanel afficher() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(new MyLabel("Numero : " + numero));
		container.add(new MyLabel("Score : " + score));
		container.add(new MyLabel("Etat: " + etat));
		
		
		return container;
	}
	
	public void MAJScore() {
		
	}
	
	
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Etat getEtat() {
		return etat;
	}
	public void changerEtat(Etat etat) {
		this.etat = etat;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
