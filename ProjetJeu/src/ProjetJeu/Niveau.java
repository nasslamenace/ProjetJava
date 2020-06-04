package ProjetJeu;


public enum Niveau{
	facile("facile"),
	moyen("moyen"),
	difficile("difficile");
	
	
	   
	  private String name = "";
	   
	  //Constructeur
	  Niveau(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}