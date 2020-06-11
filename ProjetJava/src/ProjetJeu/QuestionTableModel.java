package ProjetJeu;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;



public class QuestionTableModel extends AbstractTableModel {
	
	private ArrayList <Question> questionsList;
	private String theme;
	
	private String titles[] = {"question", "reponse", "Afficher", "Supprimer"};
	
	public QuestionTableModel(ArrayList<Question> questionsList, String theme) {
		
		super();
		

  	  this.theme = theme;
		
		this.questionsList = questionsList;
	}
	
	public ArrayList<Question> getUsersList(){return questionsList;}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return false;
	}
	

	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if(aValue != null){
	        Question myQuestion = questionsList.get(rowIndex);
	 
	        switch(columnIndex){
	            case 0:
	                myQuestion.getEnonce().setQuestion(((String)aValue));
	                break;
	            case 1:
	            	myQuestion.getEnonce().setReponse(((String)aValue));
	                break;
	        }
	    }
	}

	@Override
	public int getRowCount() {
		return questionsList.size();
	}

	@Override
	public int getColumnCount() {
		
		return titles.length;
	}
	
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

	@Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return questionsList.get(rowIndex).getEnonce().question;
            case 1:
                return questionsList.get(rowIndex).getEnonce().getReponse();
            case 2:
            	final MyButton button = new MyButton("Afficher");
				button.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														new QuestionDetailDialog(null, "Check assigned appointment", true,
					questionsList.get(rowIndex));
													}
			});
			return button;
            case 3:
            	final MyButton suppButton = new MyButton("Supprimer");
				suppButton.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														questionsList.remove(rowIndex);
														
														FileManager.updateQuestion(questionsList, theme);
														fireTableRowsDeleted(rowIndex, rowIndex);
													}
			});
				suppButton.setForeground(Color.RED);
				
			return suppButton;
            default:
                return null; //should never happen
        }
    }
	
    public Class getColumnClass(int col){
    	
	      if(col != 2 || col != 3)
	    	  return MyLabel.class;
	      else
	    	  return MyButton.class;
	    }
 
    public void removeQuestion(int rowIndex) {
        questionsList.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    


    


}

