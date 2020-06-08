package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Phase1 extends MyPanel implements Phase {
	
	
	private ArrayList<Joueur> joueurs;
	private Theme theme;
	MyLabel annonceLbl = new MyLabel();
	private MyButton confirmBtn = new MyButton("Confirmer");
	
	Question q;
	
	MyPanel questionPan ;
	
	private int compteurJoueur;
	
	public Phase1() {
		joueurs = new ArrayList<Joueur>();
		compteurJoueur = 0;
		//selectionnerJoueurs();
		//phaseDeJeu();
	 
	}
	

	@Override
	public void selectionnerJoueurs() {
		
	
		System.out.println("SELECTIONER JOUEUR : ");
			
		for(int i = 0; i <= 3; i++) {
			Joueur j = EnsJoueurs.selectionnerJoueur();
			
			if(j != null)
				joueurs.add(j);
			
			joueurs.get(i).setNom("anannana");
			
			//EnsJoueurs.modifJoueur(joueurs.get(i));
		}
		
		
		
		joueurs.get(0).startTimer();
		
		for(int i = 0; i < joueurs.size(); i++)
			System.out.println(joueurs.get(i).getScore() + "   " + joueurs.get(i).getNom() + "   " + joueurs.get(i).getEtat());
	}
	
	
	public void annuler() {
		for(int i = 0; i < joueurs.size(); i++) {
			joueurs.get(i).resetTimer();
			joueurs.get(i).changerEtat(Etat.enAttente);
		}
		joueurs.clear();
		compteurJoueur = 0;
		


		
		
	}
	

	@Override
	public void phaseDeJeu() {
		
		theme = new Theme(Theme.themes.get(Theme.selectionerThemePhase1()));
		
		this.setLayout(new BorderLayout());
		
		

		
		this.add(annonceLbl, BorderLayout.NORTH);
		
	    questionPan = new MyPanel();
		
		//Question q = new Question(Niveau.facile, Theme.themes.get(Theme.selectionerThemePhase1()), new RC("2 + 2", "4"));
		q = theme.getMesQuestions().selectionnerQuestion(Niveau.facile);
		
		if(q != null) {
			
			annonceLbl.setOpaque(true);
			annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
			annonceLbl.setForeground(new Color(61, 97, 166));
			annonceLbl.setBackground(Color.white);
			
			
			annonceLbl.setText("C'est au joueurs " + joueurs.get(compteurJoueur).getNom() + " de jouer");
			annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
			
			confirmBtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					
					
					if(q.getEnonce().isRight())
						joueurs.get(compteurJoueur).MAJScore(TypePhase.phase1);
					
					
					annonceLbl.setText("C'est au joueurs " + joueurs.get(compteurJoueur).getNom() + " de jouer");
					
					//questionPan.removeAll();
					
					remove(questionPan);
					 
					
					
					q = theme.getMesQuestions().selectionnerQuestion(Niveau.facile);
					//System.out.println(q.getEnonce().getQuestion());
					questionPan = q.afficher();
					
					add(questionPan, BorderLayout.CENTER);
					questionPan.revalidate();
					questionPan.repaint();
					
					revalidate();
					repaint();
					
					if(compteurJoueur >= 3) {
						for(int i = 0; i < joueurs.size(); i++)
							System.out.println(joueurs.get(i).getScore() + "   " + joueurs.get(i).getNom());
						System.out.println("Fin de la phase 1");
						System.out.println(joueurs);
						Collections.sort(joueurs);
						System.out.println(joueurs);
						for(int i = 0; i < joueurs.size(); i++)
							System.out.println(joueurs.get(i).getScore() + "   " + joueurs.get(i).getNom());
						
						removeAll();
						
						for(int i = 0; i < joueurs.size(); i++) {
							
							
							
						}
						
						add(new Phase2(), BorderLayout.CENTER);
						revalidate();
						repaint();
						
					}
					else {
						joueurs.get(compteurJoueur).stopTimer();
						compteurJoueur++;
						joueurs.get(compteurJoueur).startTimer();
					}
					
				}
				
				
				
			});
			
			questionPan = q.afficher();
			
			this.add(questionPan, BorderLayout.CENTER);
			this.add(confirmBtn, BorderLayout.SOUTH);
			

		}
		else {
			this.removeAll();
			this.add(annonceLbl);
			annonceLbl.setOpaque(true);
			annonceLbl.setText("Veuillez ajouter des questions afin de pouvoir jouer");
			annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
			annonceLbl.setForeground(Color.red);
			annonceLbl.setBackground(Color.white);
			
			this.revalidate();
			this.repaint();
			
			
		}
		
		
		
		
	}
	

}
