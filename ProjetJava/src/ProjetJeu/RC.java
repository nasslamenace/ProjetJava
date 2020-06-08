package ProjetJeu;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class RC extends TypeQuestion {
	
	
	
	private String bonneReponse;
	
	
	
	public RC(String question, String bonneReponse) {
		
		
		this.question = question;
		this.setBonneReponse(bonneReponse);
		
	}
	
	public MyPanel afficher() {
		JTextField reponseTf = new JTextField(10);
		
		MyPanel p = new MyPanel();
		
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		p.add(new MyLabel(question));
		
		MyPanel champs = new MyPanel();
		
		champs.setLayout(new FlowLayout(FlowLayout.CENTER));
		champs.add(new MyLabel("Votre réponse : "));
		champs.add(reponseTf);
		
		p.add(champs);
		
		return p;
		
	}
	
	
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}

	@Override
	public String getSaisie() {
		JTextField reponseTf = new JTextField(10);
		return reponseTf.getText();
	}

	@Override
	public boolean isRight() {
		JTextField reponseTf = new JTextField(10);
		String temp = reponseTf.getText();
		reponseTf.setText("");
		return temp.equals(this.bonneReponse);
	}

}
