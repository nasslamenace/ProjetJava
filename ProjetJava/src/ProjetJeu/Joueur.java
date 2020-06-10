package ProjetJeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Timer;

public class Joueur extends Thread implements Comparable{
	
	public static int numJoueurs = 100;
	
	private String nom;
	private int numero;
	private int score;
	private Etat etat;
	private Timer t;
	private int milliSecondes;
	private ArrayList<String> chosenThemes = new ArrayList<String>();
	
	
	public Joueur(String nom) {	
		numero = numJoueurs;
		score = 0;
		etat = Etat.enAttente;
		numJoueurs += 10;
		milliSecondes = 0; 
		this.setNom(nom);
		
		this.t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				milliSecondes += 1;
				
			}
			
		});
		this.t.setInitialDelay(0);
	}
	
	public void addTheme(String theme) {
		chosenThemes.add(theme);
	}
	
	
	public MyPanel afficher() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(new MyLabel("Numero : " + numero));
		container.add(new MyLabel("Nom : " + nom));
		container.add(new MyLabel("Score : " + score));
		container.add(new MyLabel("Etat: " + etat));
		
		
		return container;
	}
	
	@Override
	public void run() {
		this.startTimer();
	}
	

	
	public void startTimer() {
			t.start();
	}
	
	public void resetTimer() {
		t.stop();

		this.milliSecondes = 0;
	}
	
	public void stopTimer() {
		t.stop();
	}
	
	
	public int getTime() {
		return milliSecondes;
	}
	
	public void MAJScore(TypePhase p) {
		
		//System.out.println("---------->" + this.nom);
		
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
	
	public ArrayList<String> getChosenTheme(){
		return chosenThemes;
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
	          
	          if(Integer.compare(this.score, j.getScore()) == 0)
	              return Integer.compare(this.getTime(), j.getTime());
	          
	          return Integer.compare(this.score, j.getScore());
	       }
	       return -1;

	}
	
	

}
