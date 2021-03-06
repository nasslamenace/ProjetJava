package ProjetJeu;

import java.io.Serializable;

import javax.swing.BoxLayout;

public class Question<T extends TypeQuestion> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2532280781541525712L;
	private Niveau niveau;
	private String theme;
	private T enonce;
	
	public Question(Niveau n, String theme, T enonce) {
		this.niveau = n;
		this.theme = theme;
		this.enonce = enonce;
	}
	
	public MyPanel afficher() {
		
		MyPanel enoncePan = enonce.afficher();
		
		MyPanel container = new MyPanel();
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(new MyLabel("Niveau de la question : " + niveau));
		container.add(enoncePan);
		
		return container;
		
		
	}
	
    @Override
    public String toString() {
        return "MA QUESTION";
    }

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public T getEnonce() {
		return enonce;
	}

	public void setEnonce(T enonce) {
		this.enonce = enonce;
	}

}
