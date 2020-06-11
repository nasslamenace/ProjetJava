package ProjetJeu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;


public class JoueursTableModel extends AbstractTableModel {
	
	private ArrayList <Joueur> joueursList;

	
	private String titles[] = {"Numero", "Nom(modifiable)", "Etat"};
	
	public JoueursTableModel(ArrayList<Joueur> joueursList) {
		
		super();
		


		
		this.joueursList = joueursList;
	}
	
	public ArrayList<Joueur> getUsersList(){return joueursList;}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if(columnIndex == 1)
			return true;
		return false;
	}
	

	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	    if(aValue != null){
	        Joueur myPlayer = joueursList.get(rowIndex);
	 
	        switch(columnIndex){
	            case 1:
	                myPlayer.setNom((String)aValue);
	                break;
	        }
	        
	        FileManager.updatePlayers(joueursList);
	        fireTableRowsUpdated(rowIndex, columnIndex);
	    }
	}

	@Override
	public int getRowCount() {
		return joueursList.size();
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
                return joueursList.get(rowIndex).getNumero();
            case 1:
                return joueursList.get(rowIndex).getNom();
            case 2:
            	return joueursList.get(rowIndex).getEtat();
            default:
                return null;
        }
    }
	
    

}