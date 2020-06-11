package ProjetJeu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



public class ListeQuestionPan extends MyPanel {
	
	
	private JComboBox niveaux = new JComboBox();
	
	private MyPanel facileContainer = new MyPanel();
	private MyPanel moyenContainer = new MyPanel();
	private MyPanel difficileContainer = new MyPanel();
	private MyPanel container = new MyPanel();
	
	private MyTable facileQuestionTable;
	private MyTable moyenQuestionTable;
	private MyTable difficileQuestionTable;
	
	private QuestionTableModel facileModel;
	private QuestionTableModel moyenModel;
	private QuestionTableModel difficileModel;
	
	

	
	public ListeQuestionPan(String theme) {
		
		niveaux.addItem(Niveau.facile);
		niveaux.addItem(Niveau.moyen);
		niveaux.addItem(Niveau.difficile);
		
		Theme myTheme = new Theme(theme);
		
		facileModel = new QuestionTableModel(myTheme.getMesQuestions().selectionnerQuestions(Niveau.facile), theme);
		
		facileQuestionTable = new MyTable(facileModel);
		facileQuestionTable.setCellSelectionEnabled(false);
		MyTable.applyDesign(facileQuestionTable);
		
		TableComponent facilebuttonRenderer = new TableComponent();
		facileQuestionTable.getColumn("Afficher").setCellRenderer(facilebuttonRenderer);
		facileQuestionTable.getColumn("Supprimer").setCellRenderer(facilebuttonRenderer);
		facileQuestionTable.addMouseListener(new JTableButtonMouseListener(facileQuestionTable));
		
		moyenModel = new QuestionTableModel(myTheme.getMesQuestions().selectionnerQuestions(Niveau.moyen), theme);
		
		moyenQuestionTable = new MyTable(moyenModel);
		moyenQuestionTable.setCellSelectionEnabled(false);
 
        moyenQuestionTable.setFillsViewportHeight(true);	
		MyTable.applyDesign(moyenQuestionTable);
		
		TableComponent buttonRenderer = new TableComponent();
		moyenQuestionTable.getColumn("Afficher").setCellRenderer(buttonRenderer);
		moyenQuestionTable.getColumn("Supprimer").setCellRenderer(buttonRenderer);
		moyenQuestionTable.addMouseListener(new JTableButtonMouseListener(moyenQuestionTable));
		
		difficileModel = new QuestionTableModel(myTheme.getMesQuestions().selectionnerQuestions(Niveau.difficile), theme);
		
		difficileQuestionTable = new MyTable(difficileModel);
		difficileQuestionTable.setCellSelectionEnabled(false);
		MyTable.applyDesign(difficileQuestionTable);
		
		TableComponent difficilebuttonRenderer = new TableComponent();
		difficileQuestionTable.getColumn("Afficher").setCellRenderer(difficilebuttonRenderer);
		difficileQuestionTable.getColumn("Supprimer").setCellRenderer(difficilebuttonRenderer);
		difficileQuestionTable.addMouseListener(new JTableButtonMouseListener(difficileQuestionTable));
		
		MyPanel boxContainer = new MyPanel();
		boxContainer.add(niveaux);
		
		container.setLayout(new BorderLayout());
		
		container.add(niveaux, BorderLayout.NORTH);
		container.add(new JScrollPane(facileQuestionTable), BorderLayout.CENTER);
		
		niveaux.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.removeAll();
				container.add(niveaux, BorderLayout.NORTH);
				switch((Niveau)niveaux.getSelectedItem()) {
				case facile:
					
					
					container.add(new JScrollPane(facileQuestionTable), BorderLayout.CENTER);
					break;
				case moyen:
					container.add(new JScrollPane(moyenQuestionTable), BorderLayout.CENTER);
					break;
				case difficile:
					container.add(new JScrollPane(difficileQuestionTable), BorderLayout.CENTER);
					break;
				}
				revalidate();
				repaint();
			}
			
		});
		
		this.add(container);
		
	}
	
	  public class JTableButtonMouseListener extends MouseAdapter {
		  private final JTable table;
				
		  public JTableButtonMouseListener(JTable table) {
		    this.table = table;
		  }

		  @Override public void mouseClicked(MouseEvent e) {
		    int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		    int row    = e.getY()/table.getRowHeight(); 

		    if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
		      Object value = table.getValueAt(row, column);
		      if (value instanceof MyButton) {
		        ((MyButton)value).doClick();
		      }
		    }
		  }
		}
	
    public class TableComponent extends DefaultTableCellRenderer {

    	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	    //Si la valeur de la cellule est un MyButton, on transtype cette valeur
    	    if (value instanceof MyButton)
    	      return (MyButton) value;
    	    else
    	      return this;
    	  }
    	}

}
