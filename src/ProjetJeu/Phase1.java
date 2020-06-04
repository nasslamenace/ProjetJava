package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
			
		do {
			Joueur j = EnsJoueurs.selectionnerJoueur();
			if(joueurs.indexOf(j) == -1)
				joueurs.add(j);
		}while(joueurs.size() < 4);
		
	}
	
	public void annuler() {
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
						System.out.println("Fin de la phase 1");
						for(int i = 0; i < joueurs.size(); i++)
							System.out.println(joueurs.get(i).getScore());
						
						
						}
					
					compteurJoueur++;
					
				}
				
				
				
			});
			
			questionPan = q.afficher();
			
			this.add(questionPan, BorderLayout.CENTER);
			this.add(confirmBtn, BorderLayout.SOUTH);
			

		}
		else {
			annonceLbl.setOpaque(true);
			annonceLbl.setText("Veuillez ajouter des questions afin de pouvoir jouer");
			annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
			annonceLbl.setForeground(Color.red);
			annonceLbl.setBackground(Color.white);
		}
		
		
		
		
	}

}
