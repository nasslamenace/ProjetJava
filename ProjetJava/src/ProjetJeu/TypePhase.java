package ProjetJeu;

public enum TypePhase {
	
	phase1("phase 1"),
	phase2("phase 2"),
	phase3("phase 3");
	
	
	  private String name = "";
	   
	  //Constructeur
	  TypePhase(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }

}
