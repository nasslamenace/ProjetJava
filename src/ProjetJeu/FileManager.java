package ProjetJeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
	
	public static ArrayList<Question> retrieveQuestions(String theme){
		
		ArrayList<Question> questionList = new ArrayList<Question>();      
        try
        {
            FileInputStream fis = new FileInputStream(theme + ".txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            questionList = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        } 
        catch(FileNotFoundException fnfe){
        	System.out.println("il n'y a pas encore de liste de questions enregistré pour le theme : " + theme);
        	return questionList;
        }
        catch (IOException ioe)    
        {
            ioe.printStackTrace();
            return null;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("La classe n'est pas trouvée !");
            c.printStackTrace();
            return null;
        }
        
        return questionList;
         
	}
	
	public static void updateQuestion(ArrayList<Question> questionList, String theme) {
        try
        {
            FileOutputStream fos = new FileOutputStream(theme + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(questionList);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
	}

}
