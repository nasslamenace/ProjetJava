package ProjetJeu;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EnsJoueurs {
	

	
	public static ArrayList<Joueur> joueurs = creer();
	
	
	public EnsJoueurs() {
		
	}
	
	private static ArrayList<Joueur> creer() {
		
		
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		for(int i = 0; i < 20; i++) 
			joueurs.add(new Joueur(Character.toString((char) (i + 65))));
		
		
		return joueurs;
	}
	
	
	 
	public static MyPanel afficher() {
		
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		
		container.add(new MyLabel("Liste des joueurs"));
		
		String[] liste = new String[20];
		
		for(int i = 0; i < joueurs.size(); i++)
			liste[i] = joueurs.get(i).getNom();
		
		
		
		
		container.add(new JList(liste));
		
		return container;
		
	}
	
	public static void reinitialiserEtat() {
		for(int i = 0; i < joueurs.size(); i++)
			joueurs.get(i).changerEtat(Etat.enAttente);
	}
	
	public static void modifJoueur(Joueur joueur) {
		
		System.out.println(joueur.getNom());
		for(int j = 0; j < joueurs.size(); j++)
		{
			if(joueurs.get(j).getNumero() == joueur.getNumero())
				joueurs.set(j, joueur);
		}
	}
	
	public static Joueur selectionnerJoueur() {
		
		Stream<Joueur> joueurStream = joueurs.stream();
		
		
		ArrayList<Joueur> i = (ArrayList<Joueur>) joueurStream.filter(x -> x.getEtat() == Etat.enAttente)
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
			i.get(nb).changerEtat(Etat.selectione);
			
			return i.get(nb);
			}
		

		
	}

	
}
