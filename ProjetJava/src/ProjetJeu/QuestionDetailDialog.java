package ProjetJeu;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class QuestionDetailDialog extends JDialog {
	
	private Question question;
	private MyPanel container = new MyPanel();
	
	public QuestionDetailDialog(JFrame parent, String title, boolean modal, Question question) {
		super(parent, title, modal);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.question = question;
		initComponent();
		container.add(question.afficher());
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void initComponent(){
		
	}

}
