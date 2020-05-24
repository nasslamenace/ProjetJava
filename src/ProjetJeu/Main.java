package ProjetJeu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	
	public Main() {
		
		
		this.setContentPane(new Phase1());
		
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		Main m = new Main();
		
		
	}

}
