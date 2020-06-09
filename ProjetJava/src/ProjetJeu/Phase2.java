package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Phase2 extends MyPanel implements Phase {
	
	private ArrayList<Joueur> joueurs;
	private ArrayList<Joueur> egalite = new ArrayList<Joueur>();
	private Theme theme;
	MyLabel annonceLbl = new MyLabel();
	private MyButton confirmBtn = new MyButton("Confirmer");
	
	Question q;
	
	MyPanel questionPan ;
	
	private int compteurJoueur;
	private int compteurQuestion;
	
	public Phase2() {
		this.setLayout(new BorderLayout());
		Theme.reinitialiser();
		joueurs = new ArrayList<Joueur>();
		compteurJoueur = 0;
		selectionnerJoueurs();
		phaseDeJeu();
	 
	}
	
	
	
	@Override
	public void selectionnerJoueurs() {
		
		
		//selection des joueurs gagnants de la phase 1
		for(int i = 0; i <= 2; i++) {
			Joueur j = EnsJoueurs.selectionnerJoueur(Etat.gagnant);
			j.resetTimer();
			if(j != null)
				joueurs.add(j);
			
			//System.out.println("--------> " + joueurs.get(i).getNom());
		}
		
		//choix des themes 
		for(int i = 0; i < joueurs.size(); i++) {
			
			for(int k = 0; k <= 1; k++) {
				ArrayList<String> themes = Theme.themeDisponibles();
				
				String[] liste = new String[10];
				
				for(int j = 0; j < themes.size(); j++)
					liste[j] = themes.get(j);
				
				 String chosenTheme = "";
				 do {
					 chosenTheme = (String) JOptionPane.showInputDialog(null, "Le joueur " + joueurs.get(i).getNom() + " doit choisir un theme",
							"Choix du theme", JOptionPane.QUESTION_MESSAGE, null, liste, liste[themes.size() - 1]);
				 }while(chosenTheme == null);
				 
				 Theme.selectionerThemePhase2(chosenTheme);
				 
				 joueurs.get(i).addTheme(Theme.getThemeCourant());
			}
		}
		
		
		
	}
	
	
	private void gestionEgalite() {
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
		
		String texte = "";
		for(int i = 0; i < joueurs.size(); i++) texte += ((i == 0) ? " " : " et ") + joueurs.get(i).getNom() + " " ;
		
		annonceLbl.setText("<html>Bienvenue dans la phase 2<br>" + 
				"" + texte + "<br>Vous avez chacun choisit 2 themes, vous aurez<br>"
						+ "Une question de difficulté moyenne par theme choisit<br> "
						+ "<strong> Bonne chance !!!</strong></html>");
		
		this.add(annonceLbl, BorderLayout.NORTH);
	    questionPan = new MyPanel();
	    
	    MyButton continuer = new MyButton("Commencer");
	    
	    questionPan.add(continuer);
	    
	    this.add(questionPan, BorderLayout.CENTER);
	    
	    
	    this.revalidate();
	    this.repaint();
	    
	    compteurQuestion = 0;
	    compteurJoueur = 0;
	    
	    MyButton confirmBtn = new MyButton("Confirmer");
	    
	    
	    continuer.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				add(confirmBtn, BorderLayout.SOUTH);
				
				revalidate();
				repaint();
				
				questionPan.removeAll();
				//Question q = new Question(Niveau.facile, Theme.themes.get(Theme.selectionerThemePhase1()), new RC("2 + 2", "4"));
				
				Theme currentTheme = new Theme(joueurs.get(compteurJoueur).getChosenTheme().get(compteurQuestion));
				
				q = currentTheme.getMesQuestions().selectionnerQuestion(Niveau.moyen);
				
				
				
				revalidate();
				repaint();
				
				if(q != null) {
					annonceLbl.setOpaque(true);
					annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
					annonceLbl.setForeground(new Color(61, 97, 166));
					annonceLbl.setBackground(Color.white);
					
					
					annonceLbl.setText("C'est au joueurs " + joueurs.get(compteurJoueur).getNom() + " de jouer");
					annonceLbl.setHorizontalAlignment(SwingConstants.CENTER);
					
					
					questionPan.add(q.afficher());
					
					questionPan.revalidate();
					questionPan.repaint();
					revalidate();
					repaint();
					
					confirmBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(q.getEnonce().isRight())
								joueurs.get(compteurJoueur).MAJScore(TypePhase.phase2);

							if(compteurJoueur >= joueurs.size() - 1 && compteurQuestion >= 1) {

								Collections.sort(joueurs);

								
								
								int min = joueurs.get(0).getScore();
								
								
								for(int i = 0; i < joueurs.size(); i++) {
									
									if(joueurs.get(i).getScore() == min) {
										egalite.add(joueurs.get(i));
									}
									else {
										joueurs.get(i).changerEtat(Etat.gagnant);
									}
									System.out.println(joueurs.get(i).getNom() + "   " + joueurs.get(i).getScore() + "   " + joueurs.get(i).getTime() );
								}
								
								if(egalite.size() >= 2) 
									gestionEgalite();
								else {
									joueurs.get(0).changerEtat(Etat.elimine);
									removeAll();
									//add(new Phase2(), BorderLayout.CENTER);
									
									revalidate();
									repaint();
								}
								
							}
							else {
								

								
								joueurs.get(compteurJoueur).stopTimer();
								

								if(compteurQuestion >= 1) {
									compteurJoueur++;
									compteurQuestion = 0;
								}
								else
									compteurQuestion++;
								
								
								if(compteurJoueur < joueurs.size()) {
									annonceLbl.setText("C'est au joueur " + joueurs.get(compteurJoueur).getNom() + " de jouer");
									joueurs.get(compteurJoueur).startTimer();
								}
								//questionPan.removeAll();
								
								revalidate();
								repaint();
								
								//remove(questionPan);
								 
								revalidate();
								repaint();
								
								questionPan.removeAll();
								if(compteurQuestion < 2 && compteurJoueur < joueurs.size()) {
									Theme currentTheme = new Theme(joueurs.get(compteurJoueur).getChosenTheme().get(compteurQuestion));
									q = currentTheme.getMesQuestions().selectionnerQuestion(Niveau.moyen);
								}
								
								
								if(q == null) {
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
								else {
									//System.out.println(q.getEnonce().getQuestion());
									questionPan.add(q.afficher());
									
									questionPan.revalidate();
									questionPan.repaint();
									
									//add(questionPan, BorderLayout.CENTER);
									questionPan.revalidate();
									questionPan.repaint();
									
									revalidate();
									repaint();
								}
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

}
