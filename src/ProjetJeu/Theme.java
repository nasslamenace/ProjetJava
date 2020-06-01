package ProjetJeu;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class Theme {
	
	
	public static ArrayList<String> themes = initialiserTheme();
			
	
	private static ArrayList<Integer> indicateurs = new ArrayList<Integer>();
	
	private static int currentTheme;
	
	private ListeQuestions mesQuestions;
	
	//private Iterator <String> indicateur;
	
	
	
	private static ArrayList<String> initialiserTheme(){
		ArrayList<String> tab = new ArrayList<String>();
		
		tab.addAll(Arrays.asList("CultureGe", "Histoire", "Geographie", "Science", "Maths", 
				  "Info", "Biologie", "Cinema", "Musique", "Pays"));
		
		return tab;
	}
	
	
	public Theme(String theme) {
		setMesQuestions(new ListeQuestions(theme));
	}
	
	
	public static void modifierTheme(String previousTheme, String newTheme) {
		if(themes.indexOf(previousTheme) == -1)
			JOptionPane.showMessageDialog(null, "Ce theme n'existe pas, vous ne pouvez pas le modifier ! ", "error",
					JOptionPane.ERROR_MESSAGE);
		else {
			themes.set(themes.indexOf(previousTheme), newTheme);
		}
	}
	
	public static int selectionerTheme(String theme) {
		
		if(themes.indexOf(theme) == -1) {
			JOptionPane.showMessageDialog(null, "Le theme " + theme + "n'existe pas, vous ne pouvez pas le modifier ! ", "error",
					JOptionPane.ERROR_MESSAGE);
			return currentTheme;
			
		}
		else {

			int temp = currentTheme;
			currentTheme = themes.indexOf(theme);
			
			indicateurs.add(temp);
			
			return temp;
		}
	}
	
	public static int selectionerThemePhase1() {

		int temp = currentTheme;
		if(currentTheme >= 9)
			currentTheme = 0;
		else
			currentTheme++;
		return temp;
	}
	
	
	
	public static ArrayList<String> selectionnerCinqThemes(){
		
		ArrayList<String> themesSelectionne = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int indice;
		
		for(int i = 0; i < 5; i++) {
			
			do {
				 indice = (int)(Math.random() * (10));
			}while(indices.indexOf(indice) != -1);
			
			
			themesSelectionne.add(themes.get(indice));
			indices.add(indice);
			
		}
		
		return themesSelectionne;
		
	}
	
	
	
	public static ArrayList<String> selectionnerSixThemes(){
		
		ArrayList<String> themesSelectionne = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int indice;
		
		for(int i = 0; i < 6; i++) {
			
			do {
				 indice = (int)(Math.random() * (10));
			}while(indices.indexOf(indice) != -1);
			
			
			themesSelectionne.add(themes.get(indice));
			indices.add(indice);
			
		}
		
		return themesSelectionne;
		
	}
	
	public static MyPanel afficher() {
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BorderLayout());
		
		container.add(new MyLabel("Voici les themes :"), BorderLayout.NORTH);
		
		
		String[] liste = new String[10];
		
		for(int i = 0; i < liste.length; i++)
			liste[i] = themes.get(i);

		
		container.add(new JList(liste), BorderLayout.CENTER); 
		
		container.add(new MyLabel("Le theme actuel est le theme :" + themes.get(currentTheme)), BorderLayout.SOUTH);
		
		return container;
		
	}
	
	public static void clearIndice() {
		indicateurs.clear();
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


	public ListeQuestions getMesQuestions() {
		return mesQuestions;
	}


	public void setMesQuestions(ListeQuestions mesQuestions) {
		this.mesQuestions = mesQuestions;
	}

}
