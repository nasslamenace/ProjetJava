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
	private ArrayList<Joueur> egalite = new ArrayList<Joueur>();
	private Theme theme;
	private MyLabel annonceLbl = new MyLabel();
	private MyButton confirmBtn = new MyButton("Confirmer");
	
	private Question q;
	
	private MyPanel questionPan ;
	
	private int compteurJoueur;
	private int compteurQuestion;
	
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
			Joueur j = EnsJoueurs.selectionnerJoueur(Etat.enAttente);
			
			if(j != null)
				joueurs.add(j);
			

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
		compteurQuestion = 0;
		EnsJoueurs.reinitialiserEtat();
		
		removeAll();

		
		
	}
	
	public void gestionEgalite() {
		
		
		
		int max = egalite.get(egalite.size() - 1).getTime();
		ArrayList<Joueur> egaliteTime = new ArrayList<Joueur>();
		
		for(int i = 0; i < egalite.size(); i++) {
			if(egalite.get(i).getTime() == max)
				egaliteTime.add(egalite.get(i));
			else
				egalite.get(i).changerEtat(Etat.gagnant);
		}
		
		
		if(egaliteTime.size() >= 2) {
			
			egalite.clear();
			
			egalite.addAll(egaliteTime);
			
			this.removeAll();
			this.revalidate();
			this.repaint();
			
			String texte = "";
			for(int i = 0; i < egalite.size(); i++) texte += ((i == 0) ? " " : " et ") + egalite.get(i).getNom() + " " ;
			
			
			
			annonceLbl.setText("<html>Il y a égalité entre les joueurs : <br>" + 
					"" + texte + "<br>Il va falloir les departager ! <br><br> "
							+ "chacun va répondre a 3 question de niveau facile sur le theme : <strong>" + theme + "</strong></html>");
			
			this.add(annonceLbl, BorderLayout.NORTH);
			
			
			
			
		    questionPan = new MyPanel();
		    
		    MyButton continuer = new MyButton("Commencer");
		    
		    questionPan.add(continuer);
		    
		    this.add(questionPan, BorderLayout.CENTER);
		    
		    
		    this.revalidate();
		    this.repaint();
		    
		    compteurQuestion = 1;
		    compteurJoueur = 0;
		    
		    MyButton confirmEgalite = new MyButton("Confirmer");
		    
		    
		    
		    continuer.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					
		
					add(confirmEgalite, BorderLayout.SOUTH);
					
					revalidate();
					repaint();
					
					questionPan.removeAll();
					//Question q = new Question(Niveau.facile, Theme.themes.get(Theme.selectionerThemePhase1()), new RC("2 + 2", "4"));
					q = theme.getMesQuestions().selectionnerQuestion(Niveau.facile);
					
					revalidate();
					repaint();
					
					if(q != null) {
						
						annonceLbl.setOpaque(true);
						annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
						annonceLbl.setForeground(new Color(61, 97, 166));
						annonceLbl.setBackground(Color.white);
						
						
						annonceLbl.setText("C'est au joueurs " + egalite.get(compteurJoueur).getNom() + " de jouer");
						annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
						
						
						questionPan.add(q.afficher());
						
						questionPan.revalidate();
						questionPan.repaint();
						revalidate();
						repaint();
						
						//add(questionPan, BorderLayout.CENTER);
						
						
						revalidate();
						repaint();
						
						confirmEgalite.addActionListener(new ActionListener() {
				
							@Override
							public void actionPerformed(ActionEvent e) {
								
								
								
	
	
								
								
								
								if(compteurJoueur >= egalite.size()) {
									for(int i = 0; i < egalite.size(); i++)
										System.out.println(egalite.get(i).getScore() + "   " + egalite.get(i).getNom());
									System.out.println("Fin de la phase 1");
									System.out.println(egalite);
									Collections.sort(egalite);
									System.out.println(egalite);
									for(int i = 0; i < egalite.size(); i++)
										System.out.println(egalite.get(i).getScore() + "   " + egalite.get(i).getNom());
									
									
									int min = egalite.get(0).getScore();
									
									ArrayList<Joueur> egalite2 = new ArrayList<Joueur>();
									
									
									
									for(int i = 0; i < egalite.size(); i++) {
										if(egalite.get(i).getScore() == min) {
											egalite2.add(egalite.get(i));
										}
										else {
											egalite.get(i).changerEtat(Etat.gagnant);
										}
									}
									
									if(egalite2.size() >= 2) {
										for(int i = 0; i < egalite2.size() - 1; i++) {
											int nb = (int)(Math.random() * (egalite2.size()));
											egalite2.get(nb).changerEtat(Etat.gagnant);
											egalite2.remove(nb);
										}
										
										egalite2.get(0).changerEtat(Etat.elimine);
										removeAll();
										add(new Phase2(), BorderLayout.CENTER);
										revalidate();
										repaint();
									}
									else {
										removeAll();
										add(new Phase2(), BorderLayout.CENTER);
										revalidate();
										repaint();
									}
									
								}
								else {
									
									if(q.getEnonce().isRight())
										egalite.get(compteurJoueur).MAJScore(TypePhase.phase1);
									
									egalite.get(compteurJoueur).stopTimer();
									
	
									if(compteurQuestion >= 3) {
										compteurJoueur++;
										compteurQuestion = 1;
									}
									else
										compteurQuestion++;
									
									
									if(compteurJoueur < egalite.size()) {
										annonceLbl.setText("C'est au tour de " + egalite.get(compteurJoueur).getNom() + " de jouer");
										egalite.get(compteurJoueur).startTimer();
									}
									//questionPan.removeAll();
									
									revalidate();
									repaint();
									
									//remove(questionPan);
									 
									revalidate();
									repaint();
									
									questionPan.removeAll();
									
									q = theme.getMesQuestions().selectionnerQuestion(Niveau.facile);
									//System.out.println(q.getEnonce().getQuestion());
									questionPan.add(q.afficher());
									
									questionPan.revalidate();
									questionPan.repaint();
									
									add(questionPan, BorderLayout.CENTER);
									questionPan.revalidate();
									questionPan.repaint();
									
									revalidate();
									repaint();
									
									
	
	
								}
								
							}
							
							
							
						});
						
	
						
	
					}
					else {
						removeAll();
						add(annonceLbl);
						annonceLbl.setOpaque(true);
						annonceLbl.setText("Veuillez ajouter des questions afin de pouvoir jouer");
						annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
						annonceLbl.setForeground(Color.red);
						annonceLbl.setBackground(Color.white);
						
						revalidate();
						repaint();
						
						
					}
					
					
					
				}
		    	
		    });
			
			
		
		
		}
		else {
			egalite.get(egalite.size() - 1).changerEtat(Etat.elimine);
			removeAll();
			add(new Phase2(), BorderLayout.CENTER);
			revalidate();
			repaint();
			//for(int i = 0; i < EnsJoueurs.joueurs.size(); i++)
				//System.out.println("-----------------\n" + EnsJoueurs.joueurs.get(i).getNom() + "     " + EnsJoueurs.joueurs.get(i).getEtat());
		}
		
		
		
		
	}
	

	@Override
	public void phaseDeJeu() {
		
		revalidate();
		repaint();
		
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
			
			
			annonceLbl.setText("C'est au joueur  " + joueurs.get(compteurJoueur).getNom() + " de jouer");
			annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
			
			confirmBtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					

					
					
					
					if(q.getEnonce().isRight())
						joueurs.get(compteurJoueur).MAJScore(TypePhase.phase1);
					
					joueurs.get(compteurJoueur).stopTimer();
					compteurJoueur++;
					
					if(compteurJoueur <= 3) {
						annonceLbl.setText("C'est au joueur " + joueurs.get(compteurJoueur).getNom() + " de jouer");
						joueurs.get(compteurJoueur).startTimer();
						}
					
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
					
					if(compteurJoueur > 3) {

						Collections.sort(joueurs);
						for(int i = 0; i < joueurs.size(); i++)
							System.out.println(joueurs.get(i).getScore() + "   " + joueurs.get(i).getNom() + "    " + joueurs.get(i).getTime());
						
						
						int min = joueurs.get(0).getScore();
						
						
						
						for(int i = 0; i < joueurs.size(); i++) {
							if(joueurs.get(i).getScore() == min) {
								egalite.add(joueurs.get(i));
							}
							else {
								joueurs.get(i).changerEtat(Etat.gagnant);
							}
						}
						
						if(egalite.size() >= 2)
							gestionEgalite();
						else {
							removeAll();
							add(new Phase2(), BorderLayout.CENTER);
							revalidate();
							repaint();
						}
						
					}
					else {
						joueurs.get(compteurJoueur).stopTimer();
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
