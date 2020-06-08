package ProjetJeu;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class QCM extends TypeQuestion{
	
	private ArrayList<String> reponsesPossibles;
	private String reponse;
	
	
	
	
	
	public QCM(String question, ArrayList<String>choix, String reponse) {
		
		ArrayList<JRadioButton> reponsesPossiblesRb = new ArrayList<JRadioButton>();
		
		this.question = question;
		this.reponsesPossibles = choix;
		this.reponse = reponse;
		
		for(int i = 0; i < choix.size(); i++) {
			if(i == 0)
				reponsesPossiblesRb.add(new JRadioButton(choix.get(i), true));
			else
				reponsesPossiblesRb.add(new JRadioButton(choix.get(i), false));
			
		}
		
	}
	
	
	public MyPanel afficher() {
		
		ArrayList<JRadioButton> reponsesPossiblesRb = new ArrayList<JRadioButton>();
		
		MyPanel p = new MyPanel();
		
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		p.add(new MyLabel(question));
		
		
		
		ButtonGroup choixBg = new ButtonGroup();
		
		

		for(int i = 0; i < reponsesPossiblesRb.size(); i++)
			choixBg.add(reponsesPossiblesRb.get(i));
		
		MyPanel champs = new MyPanel();
		

		
		champs.setLayout(new BoxLayout(champs, BoxLayout.PAGE_AXIS));
		
		champs.add(new MyLabel("Réponse : "));
		
		for (JRadioButton r : reponsesPossiblesRb) {
			champs.add(r);
		}
		
		
		
		p.add(champs);
		
		return p;
		
	}
	
	
	
	
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<String> getReponsesPossibles() {
		return reponsesPossibles;
	}

	public void setReponsesPossibles(ArrayList<String> reponsesPossibles) {
		this.reponsesPossibles = reponsesPossibles;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}


	@Override
	public String getSaisie() {
		ArrayList<JRadioButton> reponsesPossiblesRb = new ArrayList<JRadioButton>();
		
		for(int i = 0; i < reponsesPossiblesRb.size(); i++)
			if(reponsesPossiblesRb.get(i).isSelected())
				return reponsesPossiblesRb.get(i).getText();
		return null;
	}


	@Override
	public boolean isRight() {
		ArrayList<JRadioButton> reponsesPossiblesRb = new ArrayList<JRadioButton>();
		
		//System.out.println(reponse + "nn");
		
		for(int i = 0; i < reponsesPossiblesRb.size(); i++) {
			if(reponsesPossiblesRb.get(i).isSelected() && reponsesPossiblesRb.get(i).getText().equals(reponse))
				return true;
		}
		
		return false;
	}

}
