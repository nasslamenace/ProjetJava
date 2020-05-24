package ProjetJeu;

public enum Etat {
	
	selectione("Selectionné"),
	gagnant("gagnant :)"),
	superGagnant("super gagnant ;)"),
	elimine("éliminé :("),
	enAttente("en attente...");
	
	  private String name = "";
	   
	  //Constructeur
	  Etat(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
	
	
	

}
