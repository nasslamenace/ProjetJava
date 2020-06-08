package ProjetJeu;

import javax.swing.BoxLayout;
import javax.swing.Timer;

public class Joueur extends Thread implements Comparable{
	
	public static int numJoueurs = 100;
	
	private String nom;
	private int numero;
	private int score;
	private Etat etat;
	private Timer t;
	
	
	public Joueur(String nom) {	
		numero = numJoueurs;
		score = 0;
		etat = Etat.enAttente;
		numJoueurs += 10;
		this.setNom(nom);
		
		this.t = new Timer(10, null);
		this.t.setInitialDelay(0);
	}
	
	
	public MyPanel afficher() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(new MyLabel("Numero : " + numero));
		container.add(new MyLabel("Score : " + score));
		container.add(new MyLabel("Etat: " + etat));
		
		
		return container;
	}
	

	
	public void startTimer() {
		if(t.getDelay() > 0)
			t.restart();
		else
			t.start();
	}
	
	public void resetTimer() {
		t.stop();
		t.setDelay(0);
	}
	
	public void stopTimer() {
		t.stop();
	}
	
	
	public int getTime() {
		return t.getDelay();
	}
	
	public void MAJScore(TypePhase p) {
		switch(p) {
		case phase1:
			score += 2;
			break;
		case phase2:
			score += 3;
			break;
		case phase3:
			score += 5;
			break;
		}
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


	@Override
	public int compareTo(Object o) {
	      if(o.getClass().equals(Joueur.class)){
	          Joueur j = (Joueur)o;
	          return Integer.compare(this.score, j.getScore());
	       }
	       return -1;

	}
	
	

}
