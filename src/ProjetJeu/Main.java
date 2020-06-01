package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class Main extends JFrame{
	
	
	
	//MENU COMPONENT
	private MyButton displayThemes = new MyButton("Afficher les themes");
	private MyButton addQuestion = new MyButton("Ajouter une question");
	private MyButton displayQuestion = new MyButton("Afficher les questions");
	private MyButton displayPlayers = new MyButton("Afficher les joueurs");
	private MyButton startGame = new MyButton("Lancer le jeu");
	
	
	//ADD QUESTION COMPONENT
	private JComboBox<String> questionTypeBox = new JComboBox<String>();
	private JTextField enonceTf = new JTextField();
	
	ButtonGroup choixBg = new ButtonGroup();
	JRadioButton trueBtn = new JRadioButton("Vrai", true);
	JRadioButton falseBtn = new JRadioButton("Faux", false);
	
	private MyLabel reponse1Lbl = new MyLabel("Réponse 1 : ");
	private MyLabel reponse2Lbl = new MyLabel("Réponse 2 : ");
	private MyLabel reponse3Lbl = new MyLabel("Réponse 3 : ");
	
	private JTextField reponse1Tf = new JTextField(30);
	private JTextField reponse2Tf = new JTextField(30);
	private JTextField reponse3Tf = new JTextField(30);
	
	private MyLabel choixReponseLbl = new MyLabel("Quelle est la bonne réponse a votre question ? ");
	private JComboBox<String> reponsesBox = new JComboBox<String>();
	
	
	private MyLabel reponseLbl = new MyLabel("Votre réponse : ");
	private JTextField reponseTf = new JTextField(10);
	
	
	private MyButton addBtn = new MyButton("Ajouter");
	
	
	
	//CONTAINER + LAYOUTS
	private CardLayout mainLayout = new CardLayout();
	
	private MyPanel menuContainer = new MyPanel();
	
	private MyPanel wholeContainer = new MyPanel();
	
	private MyPanel mainContainer = new MyPanel();
	
	private MyPanel questionContainer = new MyPanel();
	
	private MyButton cancelBtn = new MyButton(new ImageIcon("return.png"));
	
	private MyPanel cancelContainer = new MyPanel();
	
	
	public Main() {
		
		
		initComponent();
		
		
		this.setContentPane(wholeContainer);
		
		this.setSize(400,280);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initComponent() {
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(mainContainer, "Menu");
				wholeContainer.remove(cancelContainer);
				revalidate();
				repaint();
			}
			
		});
		
		
	   mainContainer.setLayout(mainLayout);
		  
	   cancelContainer = new MyPanel();
	   
	   cancelContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
		
	   cancelContainer.add(cancelBtn);
	   
	   
		
		
		
		MyPanel menuPan = new MyPanel();

		menuPan.setLayout(new BoxLayout(menuPan, BoxLayout.Y_AXIS));
		menuPan.add(Box.createVerticalStrut(15));
		menuPan.add(displayThemes);
		menuPan.add(Box.createVerticalStrut(15));
		menuPan.add(addQuestion);
		menuPan.add(Box.createVerticalStrut(15));
		menuPan.add(displayQuestion);
		menuPan.add(Box.createVerticalStrut(15));
		menuPan.add(displayPlayers);
		menuPan.add(Box.createVerticalStrut(15));
		menuPan.add(startGame);
		
		
		displayThemes.addActionListener(new displayThemesListener());
		addQuestion.addActionListener(new AddQuestionListener());
		displayQuestion.addActionListener(new displayQuestionListener());
		displayPlayers.addActionListener(new displayPlayersListener());
		startGame.addActionListener(new startGameListener());
		
		menuContainer.setLayout(new BorderLayout());

		menuContainer.add(new MyLabel("Bienvenue au jeu des question :"), BorderLayout.NORTH);
		menuContainer.add(new JScrollPane(menuPan), BorderLayout.CENTER);
		
		
		
		MyPanel themesPan = new MyPanel();
		
		themesPan.setLayout(new BoxLayout(themesPan, BoxLayout.PAGE_AXIS));
		themesPan.add(Theme.afficher());
		
		
		MyPanel addQuestionPan = new MyPanel();
		
		addQuestionPan.setLayout(new BoxLayout(addQuestionPan, BoxLayout.PAGE_AXIS));
		addQuestionPan.add(cancelContainer);
		addQuestionPan.add(questionTypeBox);
		
		choixBg.add(trueBtn);
		choixBg.add(falseBtn);
		
		
		questionTypeBox.addItem("QCM");
	    questionTypeBox.addItem("Vrai/Faux");
   	    questionTypeBox.addItem("Réponse courte");
   	    
   	    reponsesBox.addItem("reponse 1");
   	    reponsesBox.addItem("reponse 2");
   	    reponsesBox.addItem("reponse 3");
   	    
   	    questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));
   	    
   	    
   	    
   	    questionContainer.add(new MyLabel("Entrez l'énoncé de la question : "));
   	    questionContainer.add(enonceTf);


   	    
   	    
   	    questionContainer.add(reponse1Lbl);
   	    questionContainer.add(reponse1Tf);
   	    questionContainer.add(reponse2Lbl);
   	    questionContainer.add(reponse2Tf);
   	    questionContainer.add(reponse3Lbl);
   	    questionContainer.add(reponse3Tf);
   	    
   	    questionContainer.add(choixReponseLbl);
   	    questionContainer.add(reponsesBox);
   	    questionContainer.add(addBtn);
   	    
   		questionTypeBox.addActionListener(new QuestionTypeListener());
   		
   		addQuestionPan.add(questionContainer);
   		
   		addBtn.addActionListener(new AddNewQuestionListener());
		
		mainContainer.add(menuPan, "Menu");
		mainContainer.add(themesPan, "Theme");
		mainContainer.add(addQuestionPan, "AddQuestion");
		
		
		wholeContainer.setLayout(new BorderLayout());
		
		
		wholeContainer.add(mainContainer, BorderLayout.CENTER);
		
		
		
	}

	public static void main(String[] args) {
		
		Main m = new Main();
		
		
	}
	
	  public class displayThemesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			mainLayout.show(mainContainer, "Theme");
			wholeContainer.add(cancelContainer, BorderLayout.NORTH);
			revalidate();
			repaint();
			
		}
		  
		  
		  
	  }
	  
	  public class displayQuestionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
	
			wholeContainer.add(cancelContainer, BorderLayout.NORTH);
			revalidate();
			repaint();
			
		}
		  
		  
		  
	  }
	  
	  
	  
	  public class AddNewQuestionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
	
			
			
		}
		  
		  
		  
	  }
	  
	  public class displayPlayersListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
	
			wholeContainer.add(cancelContainer, BorderLayout.NORTH);
			revalidate();
			repaint();
			
		}
		  
		  
		  
	  }
	  
	  public class startGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
	
			wholeContainer.add(cancelContainer, BorderLayout.NORTH);
			revalidate();
			repaint();
		}
		  
		  
		  
	  }
	  
	  
	  
	  public class AddQuestionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainLayout.show(mainContainer, "AddQuestion");
	
			wholeContainer.add(cancelContainer, BorderLayout.NORTH);
			revalidate();
			repaint();
		}
		  
		  
		  
	  }
	  
	  public class QuestionTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = (String) questionTypeBox.getSelectedItem();
			
			switch(s) {
			
			case "QCM":
				
		   	      questionContainer.remove(trueBtn);
		   	      questionContainer.remove(falseBtn);
		   	      questionContainer.remove(addBtn);
		   	      
		   	      questionContainer.remove(choixReponseLbl);
		   	      questionContainer.remove(reponseTf);
				
				  questionContainer.add(addBtn);
				  questionContainer.add(reponse1Lbl);
				  questionContainer.add(reponse1Tf);
		   	      questionContainer.add(reponse2Lbl);
		   	      questionContainer.add(reponse2Tf);
		   	      questionContainer.add(reponse3Lbl);
		   	      questionContainer.add(reponse3Tf);
		   	    
		   	      questionContainer.add(choixReponseLbl);
		   	      questionContainer.add(reponsesBox);
		   	      questionContainer.add(addBtn);
		   	    

				  
		   	      questionContainer.revalidate();
		   	      questionContainer.repaint();
				  break;
			case "Vrai/Faux":
				  questionContainer.remove(addBtn);
				  questionContainer.remove(reponse1Lbl);
				  questionContainer.remove(reponse1Tf);
		   	      questionContainer.remove(reponse2Lbl);
		   	      questionContainer.remove(reponse2Tf);
		   	      questionContainer.remove(reponse3Lbl);
		   	      questionContainer.remove(reponse3Tf);
		   	    
		   	      questionContainer.remove(choixReponseLbl);
		   	      questionContainer.remove(reponsesBox);
		   	      
		   	      questionContainer.remove(choixReponseLbl);
		   	      questionContainer.remove(reponseTf);
		   	    
		   	      questionContainer.add(trueBtn);
		   	      questionContainer.add(falseBtn);
		   	      questionContainer.add(addBtn);
				  
		   	      questionContainer.revalidate();
		   	      questionContainer.repaint();
				  break;
			case "Réponse courte":
				  questionContainer.remove(addBtn);
				  questionContainer.remove(reponse1Lbl);
				  questionContainer.remove(reponse1Tf);
		   	      questionContainer.remove(reponse2Lbl);
		   	      questionContainer.remove(reponse2Tf);
		   	      questionContainer.remove(reponse3Lbl);
		   	      questionContainer.remove(reponse3Tf);
		   	    
		   	      questionContainer.remove(choixReponseLbl);
		   	      questionContainer.remove(reponsesBox);
		   	    
		   	      questionContainer.remove(trueBtn);
		   	      questionContainer.remove(falseBtn);
		   	      
				  
		   	      questionContainer.add(choixReponseLbl);
		   	      questionContainer.add(reponseTf);
		   	      questionContainer.add(addBtn);
		   	      questionContainer.revalidate();
		   	      questionContainer.repaint();
				  break;	
			
			}
			

				
	
			
		}
		  
		  
		  
	  }
	  

}

