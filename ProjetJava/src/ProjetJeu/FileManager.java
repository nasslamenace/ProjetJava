package ProjetJeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class FileManager {

	public static ArrayList<Question> retrieveQuestions(String theme){

		ArrayList<Question> questionList = new ArrayList<Question>();      
		/*try
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
        }*/

		BufferedReader reader;

		String buffer = "";


		ArrayList<Question> questionsList = new ArrayList<Question>();
		
		String question = "";
		String reponse = "";

		try {

			reader = new BufferedReader(new FileReader(theme +".txt"));
			
			Niveau n = null;
			
			buffer = reader.readLine();
			

			


			while (buffer != null) {
				
				switch(buffer) {
				case "facile":
					n = Niveau.facile;
					break;
				case "moyen":
					n = Niveau.moyen;
					break;
				case "difficile":
					n = Niveau.difficile;
					break;
				}
				
				buffer = reader.readLine();
				
				switch(buffer) {
				case "QCM":
					ArrayList<String> choixPossible = new ArrayList<String>();
					
					question = reader.readLine();
					choixPossible.add(reader.readLine());
					choixPossible.add(reader.readLine());
					choixPossible.add(reader.readLine());
					reponse = reader.readLine();
					buffer = reader.readLine();
					buffer = reader.readLine();
					
					questionsList.add(new Question(n, theme, new QCM(question, choixPossible, reponse)));
					break;
				case "RC":
					question = reader.readLine();
					reponse = reader.readLine();
					buffer = reader.readLine();
					buffer = reader.readLine();
					questionsList.add(new Question(n, theme, new RC(question, reponse)));
					break;
				case "VF":
					boolean isTrue;
					question = reader.readLine();
					isTrue = Boolean.parseBoolean(reader.readLine());
					buffer = reader.readLine();
					buffer = reader.readLine();
					questionsList.add(new Question(n, theme, new VF(question, isTrue)));
					break;
				default:
					buffer = reader.readLine();
					break;
				}

			}

			reader.close();
		} catch (IOException e) {
			return questionsList;
		}

		return questionsList;

	}



	public static void updateQuestion(ArrayList<Question> questionList, String theme) {
		/*try
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
        }*/

		OutputStreamWriter osw = null;
		FileOutputStream is = null;

		try {

			File file = new File(theme + ".txt");
			is = new FileOutputStream(file);
			osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);


			for (int i = 0; i < questionList.size(); i++) {
				
				w.write(questionList.get(i).getNiveau().toString());
				w.write("\n");

				if(questionList.get(i).getEnonce() instanceof QCM) {
					QCM qcm = (QCM)questionList.get(i).getEnonce();
					w.write("QCM");
					w.write("\n");
					w.write(qcm.getQuestion());
					w.write("\n");
					for(int j = 0; j < 3; j++)
					{
						w.write(qcm.getReponsesPossibles().get(j));
						w.write("\n");
					}
					w.write(qcm.getReponse());
					w.write("\n");
					w.write("fin_question");
					w.write("\n");

				}
				else if(questionList.get(i).getEnonce() instanceof VF) {
					VF vf =  (VF)questionList.get(i).getEnonce();
					w.write("VF");
					w.write("\n");
					w.write(vf.getQuestion());
					w.write("\n");
					w.write(vf.getReponse());
					w.write("\n");
					w.write("fin_question");
					w.write("\n");


				}
				else if(questionList.get(i).getEnonce() instanceof RC) {
					RC rc =  (RC)questionList.get(i).getEnonce();
					w.write("RC");
					w.write("\n");
					w.write(rc.getQuestion());
					w.write("\n");
					w.write(rc.getReponse());
					w.write("\n");
					w.write("fin_question");
					w.write("\n");
				}


			}
			w.close();
		} catch (IOException e) {
			System.err.println("Il y a un probleme dans l'ecriture de fichiers");
		}
	}



}
