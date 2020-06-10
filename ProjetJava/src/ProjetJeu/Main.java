package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;




public class Main extends JFrame{
  
  
  String chosenTheme;
  
  //MENU COMPONENT
  private MyButton displayThemes = new MyButton("Afficher les themes");
  private MyButton addQuestion = new MyButton("Ajouter une question");
  private MyButton displayQuestion = new MyButton("Afficher les questions");
  private MyButton displayPlayers = new MyButton("Afficher les joueurs");
  private MyButton startGame = new MyButton("Lancer le jeu");
  
  
  //ADD QUESTION COMPONENT
  private JComboBox<String> questionTypeBox = new JComboBox<String>();
  private JComboBox<String> themesBox = new JComboBox<String>();
  private JComboBox<String> niveauBox = new JComboBox<String>();
  private JTextArea enonceTf = new JTextArea();
  
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
  
  //DISPLAY QUESTIONS COMPONENT
  
  private JComboBox choixNiveau = new JComboBox();
  MyPanel questionsPan;
  
  //Phase de jeu
  
  private Phase1 phase1 = new Phase1();
  
  
  //-------------------------------Affichage des joueurs-----------------------------------------
  private MyPanel playersPan = new MyPanel();
  
  
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
    
    this.setSize(500,470);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
  public void initComponent() {
    
    cancelBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
    	phase1.annuler();
    	
    	phase1 = null;
    	
    	phase1 = new Phase1();
    	
    	phase1.revalidate();
    	phase1.repaint();
    	mainContainer.add(phase1, "Jeu");
    	mainContainer.revalidate();
    	mainContainer.repaint();
    	
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
     
     
    
    
    //-------------------------------PANEL DU MENU-----------------------------------------
    
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
    
    Border blackline = BorderFactory.createLineBorder(Color.black);
      
    enonceTf.setBorder(blackline);
      
    
  //-------------------------------PANEL AFFICHAGE THEMES-----------------------------------------
    
    MyPanel themesPan = new MyPanel();
    
    themesPan.setLayout(new BoxLayout(themesPan, BoxLayout.PAGE_AXIS));
    themesPan.add(Theme.afficher());
    
  //-------------------------------PANEL AFFICHAGE JOUEURS-----------------------------------------
    

    
    playersPan.setLayout(new BoxLayout(playersPan, BoxLayout.PAGE_AXIS));
    playersPan.add(EnsJoueurs.afficher());
    
  //-------------------------------PANEL AFFICHAGE QUESTIONS-----------------------------------------
    
    questionsPan = new MyPanel();
    
    questionsPan.setLayout(new BoxLayout(questionsPan, BoxLayout.PAGE_AXIS));
    
    
  //-------------------------------PANEL AJOUT DE QUESTIONS-----------------------------------------
    
    MyPanel addQuestionPan = new MyPanel();
    
    addQuestionPan.setLayout(new BoxLayout(addQuestionPan, BoxLayout.PAGE_AXIS));
    addQuestionPan.add(cancelContainer);
    addQuestionPan.add(new MyLabel("Choisir le theme : "));
    addQuestionPan.add(questionTypeBox);
    
    trueBtn.setForeground(Color.white);
    falseBtn.setForeground(Color.white);
    
    choixBg.add(trueBtn);
    choixBg.add(falseBtn);
    
    for(int i = 0; i < Theme.themes.size(); i++)
    	themesBox.addItem(Theme.themes.get(i));
    
    	questionTypeBox.addItem("QCM");
    	questionTypeBox.addItem("Vrai/Faux");
        questionTypeBox.addItem("Réponse courte");
        
        niveauBox.addItem(Niveau.facile.toString());
        niveauBox.addItem(Niveau.moyen.toString());
        niveauBox.addItem(Niveau.difficile.toString());
        
        reponsesBox.addItem("reponse 1");
        reponsesBox.addItem("reponse 2");
        reponsesBox.addItem("reponse 3");
        
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));
        
        
        questionContainer.add(new MyLabel("Choisir le theme : "));
        questionContainer.add(themesBox);
        questionContainer.add(new MyLabel("Choisir le niveau de difficulté : "));
        questionContainer.add(niveauBox);
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
      
      
      addQuestionPan.add(new JScrollPane(questionContainer));
      
      addBtn.addActionListener(new AddNewQuestionListener());
      
      

      
    //-------------------------------GESTION DU CARD LAYOUT-----------------------------------------
    
    mainContainer.add(menuPan, "Menu");
    mainContainer.add(themesPan, "Theme");
    mainContainer.add(playersPan, "Players");
    mainContainer.add(addQuestionPan, "AddQuestion");
    mainContainer.add(phase1, "Jeu");
    
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
    	
		String[] liste = new String[10];
		
		for(int i = 0; i < liste.length; i++)
			liste[i] = Theme.themes.get(i);
      
	  chosenTheme = (String) JOptionPane.showInputDialog(null, "Veuillez choisir un theme :",
				"Choix du theme", JOptionPane.QUESTION_MESSAGE, null, liste, liste[9]);
      
      wholeContainer.add(cancelContainer, BorderLayout.NORTH);
      revalidate();
      repaint();
      
    }
      
      
      
    }
    
    
    
    public class AddNewQuestionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
    	
      ArrayList<Question> questions = FileManager.retrieveQuestions((String)themesBox.getSelectedItem());
      
      switch((String)questionTypeBox.getSelectedItem()) {
        case "QCM":
          if(enonceTf.getText().isEmpty() || reponse1Tf.getText().isEmpty() || reponse2Tf.getText().isEmpty() || reponse3Tf.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs !", "error",
                JOptionPane.ERROR_MESSAGE);
          else {
            
            ArrayList<String> choix = new ArrayList<String>();
            
            choix.add(reponse1Tf.getText());
            choix.add(reponse2Tf.getText());
            choix.add(reponse3Tf.getText());
            
            Question q = null;
            switch((String)niveauBox.getSelectedItem()){
              case "facile":
                
                switch((String)reponsesBox.getSelectedItem()) {
                case "reponse 1":
                  q = new Question<QCM>(Niveau.facile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse1Tf.getText()));
                  break;
                case "reponse 2":
                  q = new Question<QCM>(Niveau.facile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse2Tf.getText()));
                  break;
                case "reponse 3":
                  q = new Question<QCM>(Niveau.facile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse3Tf.getText()));
                  break;
                }
                
                
                break;
              case "moyen":
                  switch((String)reponsesBox.getSelectedItem()) {
                  case "reponse 1":
                    q = new Question<QCM>(Niveau.moyen, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse1Tf.getText()));
                    break;
                  case "reponse 2":
                    q = new Question<QCM>(Niveau.moyen, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse2Tf.getText()));
                    break;
                  case "reponse 3":
                    q = new Question<QCM>(Niveau.moyen, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse3Tf.getText()));
                    break;
                  }
                break;
              case "difficile":
                  switch((String)reponsesBox.getSelectedItem()) {
                  case "reponse 1":
                    q = new Question<QCM>(Niveau.difficile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse1Tf.getText()));
                    break;
                  case "reponse 2":
                    q = new Question<QCM>(Niveau.difficile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse2Tf.getText()));
                    break;
                  case "reponse 3":
                    q = new Question<QCM>(Niveau.difficile, (String)themesBox.getSelectedItem(), new QCM(enonceTf.getText(), choix, reponse3Tf.getText()));
                    break;
                  }
                break;
            }
            
            questions.add(q);
            
            FileManager.updateQuestion(questions, (String)themesBox.getSelectedItem());
            JOptionPane.showMessageDialog(null, "La question a été ajouté avec succès", "Succès",
                    JOptionPane.INFORMATION_MESSAGE);
            
            mainLayout.show(mainContainer, "Menu");
            wholeContainer.remove(cancelContainer);
          }
          break;
        case "Vrai/Faux":
        	
            if(enonceTf.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs !", "error",
                    JOptionPane.ERROR_MESSAGE);
              else {
                
                
                
                Question q = null;
                
                switch((String)niveauBox.getSelectedItem()){
                  case "facile":
                	  q = new Question<VF>(Niveau.facile, (String)themesBox.getSelectedItem(), new VF(enonceTf.getText(), trueBtn.isSelected()));
                    break;
                  case "moyen":
                	  q = new Question<VF>(Niveau.moyen, (String)themesBox.getSelectedItem(), new VF(enonceTf.getText(), trueBtn.isSelected()));
                    break;
                  case "difficile":
                	  q = new Question<VF>(Niveau.difficile, (String)themesBox.getSelectedItem(), new VF(enonceTf.getText(), trueBtn.isSelected()));
                    break;
                }
                
                questions.add(q);
                
                FileManager.updateQuestion(questions, (String)themesBox.getSelectedItem());
                JOptionPane.showMessageDialog(null, "La question a été ajouté avec succès", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                
                mainLayout.show(mainContainer, "Menu");
                wholeContainer.remove(cancelContainer);
              }
        	
          break;
        case "Réponse courte":
            if(enonceTf.getText().isEmpty() || reponseTf.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs !", "error",
                    JOptionPane.ERROR_MESSAGE);
              else {
                
                
                Question q = null;
                
                switch((String)niveauBox.getSelectedItem()){
                  case "facile":
                	  q = new Question<RC>(Niveau.facile, (String)themesBox.getSelectedItem(), new RC(enonceTf.getText(), reponseTf.getText()));
                    break;
                  case "moyen":
                	  q = new Question<RC>(Niveau.moyen, (String)themesBox.getSelectedItem(), new RC(enonceTf.getText(), reponseTf.getText()));
                    break;
                  case "difficile":
                	  q = new Question<RC>(Niveau.difficile, (String)themesBox.getSelectedItem(), new RC(enonceTf.getText(), reponseTf.getText()));
                    break;
                }
                
                questions.add(q);
                
                FileManager.updateQuestion(questions, (String)themesBox.getSelectedItem());
                JOptionPane.showMessageDialog(null, "La question a été ajouté avec succès", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                
                mainLayout.show(mainContainer, "Menu");
                wholeContainer.remove(cancelContainer);
              }
          break;
      }
      enonceTf.setText("");
      reponseTf.setText("");
      reponse1Tf.setText("");
      reponse2Tf.setText("");
      reponse3Tf.setText("");
      
    }
      
      
      
    }
    
    public class displayPlayersListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      
      
        
      playersPan.removeAll();
      playersPan.add(EnsJoueurs.afficher());
      
      playersPan.revalidate();
      playersPan.repaint();
      
      
      
      mainLayout.show(mainContainer, "Players");
      wholeContainer.add(cancelContainer, BorderLayout.NORTH);
      revalidate();
      repaint();
      
    }
      
      
      
    }
    
    public class startGameListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      
      phase1.selectionnerJoueurs();
      phase1.phaseDeJeu();
      
      mainLayout.show(mainContainer, "Jeu");
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