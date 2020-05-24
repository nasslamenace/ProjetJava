package ProjetJeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Theme {
	
	
	private ArrayList<String> themes = (ArrayList<String>) 
			Arrays.asList("CultureGe", "Histoire", "Geographie", "Science", "Maths", 
						  "Info", "Biologie", "Cinema", "Musique", "Pays");
	
	private ArrayList<Integer> indicateurs = new ArrayList<Integer>();
	
	private int currentTheme;
	
	//private Iterator <String> indicateur;
	
	
	public Theme() {
		
	}
	
	public void modifierTheme(String previousTheme, String newTheme) {
		if(themes.indexOf(previousTheme) == -1)
			JOptionPane.showMessageDialog(null, "Ce theme n'existe pas, vous ne pouvez pas le modifier ! ", "error",
					JOptionPane.ERROR_MESSAGE);
		else {
			themes.set(themes.indexOf(previousTheme), newTheme);
		}
	}
	
	public int selectionerTheme(String theme) {
		
		if(themes.indexOf(theme) == -1) {
			JOptionPane.showMessageDialog(null, "Le theme " + theme + "n'existe pas, vous ne pouvez pas le modifier ! ", "error",
					JOptionPane.ERROR_MESSAGE);
			return currentTheme;
			
		}
		else {

			int temp = this.currentTheme;
			this.currentTheme = themes.indexOf(theme);
			
			indicateurs.add(temp);
			
			return temp;
		}

	}
	
	public ArrayList<String> selectionnerCinqThemes(){
		
		ArrayList<String> themesSelectionne = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int indice;
		
		for(int i = 0; i < 5; i++) {
			
			do {
				 indice = (int)(Math.random() * (10));
			}while(indices.indexOf(indice) != -1);
			
			
			themesSelectionne.add(this.themes.get(indice));
			indices.add(indice);
			
		}
		
		return themesSelectionne;
		
	}
	
	

	public ArrayList<String> getThemes() {
		return themes;
	}

	public void setThemes(ArrayList<String> themes) {
		this.themes = themes;
	}


	public ArrayList<Integer> getIndicateurs() {
		return indicateurs;
	}


	public void setIndicateurs(ArrayList<Integer> indicateurs) {
		this.indicateurs = indicateurs;
	}

	public int getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(int currentTheme) {
		this.currentTheme = currentTheme;
	}

}
