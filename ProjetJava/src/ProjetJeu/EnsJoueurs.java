package ProjetJeu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ProjetJeu.ListeQuestionPan.JTableButtonMouseListener;
import ProjetJeu.ListeQuestionPan.TableComponent;

public class EnsJoueurs {
	
	

	
	private static ArrayList<Joueur> joueurs = creer();
	
	private static JoueursTableModel joueursModel;
	
	
	public EnsJoueurs() {
		
	}
	
	private static ArrayList<Joueur> creer() {
		
		
		ArrayList<Joueur> joueurs = FileManager.retrieveJoueurs();
		
		if(joueurs.isEmpty()) {
			for(int i = 0; i < 20; i++) 
				joueurs.add(new Joueur(Character.toString((char) (i + 65))));
		}
		return joueurs;
	}
	
	
	 
	public static MyPanel afficher() {
		
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		joueursModel = new JoueursTableModel(joueurs);
		
		MyTable joueursTable = new MyTable(joueursModel);
		joueursTable.setCellSelectionEnabled(false);
 
        joueursTable.setFillsViewportHeight(true);	
		MyTable.applyDesign(joueursTable);
		

		joueursTable.addMouseListener(new JTableButtonMouseListener(joueursTable));
		
		
		container.add(new MyLabel("Liste des joueurs"));
		
		String[] liste = new String[20];
		
		for(int i = 0; i < joueurs.size(); i++)
			liste[i] = joueurs.get(i).getNom();
		
		
		
		
		container.add(new JScrollPane(joueursTable));
		
		return container;
		
	}
	
	public static void reinitialiserEtat() {
		for(int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getEtat() != Etat.elimine)
				joueurs.get(i).changerEtat(Etat.enAttente);
			joueurs.get(i).setScore(0);
			joueurs.get(i).resetTimer();
			//System.out.println(joueurs.get(i).getEtat());
		}
	}
	
	public static void modifJoueur(Joueur joueur) {
		
		System.out.println(joueur.getNom());
		for(int j = 0; j < joueurs.size(); j++)
		{
			if(joueurs.get(j).getNumero() == joueur.getNumero())
				joueurs.set(j, joueur);
		}
	}
	
	public static ArrayList<Joueur> selectionnerCategorie(Etat e){
		
		Stream<Joueur> joueurStream = joueurs.stream();
		
		
		ArrayList<Joueur> i = (ArrayList<Joueur>) joueurStream.filter(x -> x.getEtat() == Etat.elimine)
				.collect(Collectors.toList());
		
		return i;
		
	}
	
	public static Joueur selectionnerJoueur(Etat e) {
		
		for(int i = 0; i < joueurs.size(); i++)
			System.out.println(joueurs.get(i).getEtat());
		
		Stream<Joueur> joueurStream = joueurs.stream();
		
		
		ArrayList<Joueur> i = (ArrayList<Joueur>) joueurStream.filter(x -> x.getEtat() == e)
				.collect(Collectors.toList());
		
		System.out.println("SELECTIONER ENS JOUEURS :");
		for(int j = 0; j < i.size(); j++)
			System.out.println(i.get(j).getNom() + "    " + i.get(j).getEtat());
		System.out.println("SELECTIONER ENS JOUEURS FIN");

		if(i.size() < 1) {
			JOptionPane.showMessageDialog(null, "Il n'y a plus de joueur en attente", "error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else {
			int nb = (int)(Math.random() * (i.size()));
			//joueurs.get(nb).changerEtat(Etat.selectione);
			

			if(e != Etat.superGagnant)
				i.get(nb).changerEtat(Etat.selectione);
			
			return i.get(nb);
			}
		

		
	}
	
    public static class TableComponent extends DefaultTableCellRenderer {

  	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
  	    //Si la valeur de la cellule est un MyButton, on transtype cette valeur
  	    if (value instanceof MyButton)
  	      return (MyButton) value;
  	    else
  	      return this;
  	  }
  	}
	
	  public static class JTableButtonMouseListener extends MouseAdapter {
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
	


	
}
