package ProjetJeu;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VF extends TypeQuestion{
	
	private String question;
	private boolean isTrue;
	
	
	JRadioButton trueBtn = new JRadioButton("Vrai", true);
	JRadioButton falseBtn = new JRadioButton("Faux", false);
	
	
	
	
	public VF(String question, boolean isTrue) {
		
		this.question = question;
		this.isTrue = isTrue;
		
	}
	
	
	public MyPanel afficher() {
		
		MyPanel p = new MyPanel();
		
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		p.add(new MyLabel(question));
		
		
		
		ButtonGroup choixBg = new ButtonGroup();
		
		

		
		choixBg.add(trueBtn);
		choixBg.add(falseBtn);
		
		MyPanel champs = new MyPanel();
		
		champs.setLayout(new BoxLayout(champs, BoxLayout.LINE_AXIS));
		
		champs.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		champs.add(new MyLabel("Réponse : "));
		champs.add(trueBtn);
		champs.add(falseBtn);
		
		
		p.add(champs);
		
		return p;
		
	}
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

}
