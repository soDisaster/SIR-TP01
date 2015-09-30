import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class Ex1 {

	/* Nom du fichier utilisé, changer chemin si besoin */
	private String fichier = "Log-clients-themes.txt";

	/**
	 * Méthode permettant de creer un fichier avec le nom de chaque utilisateur.
	 * @param utilisateurs
	 */
	public void creerFichierUtilisateurs(Map<Integer, String> utilisateurs){

		String prenom;
		
		/* Lecture */
		
		try{
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			Integer i = 0;
			while ((ligne=br.readLine())!=null){
				String [] tab = ligne.split(";");
				prenom = tab[1];
				if(!(utilisateurs.containsValue(prenom))){
					utilisateurs.put(i, prenom);
				}
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		/* Ecriture */
		try {
			FileWriter fw = new FileWriter("personnes.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			for(String s : utilisateurs.values()){
				fichierSortie.println(s); 
			}
			fichierSortie.close();
			System.out.println("Le fichier personnes.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * Méthode permettant de creer un fichier avec le nom de chaque theme.
	 * @param themes
	 */
	public void creerFichierThemes(Map<Integer, String> themes){

		String theme;

		/* Lecture */
		
		try{
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			Integer i = 0;
			while ((ligne=br.readLine())!=null){
				String [] tab = ligne.split(";");
				theme = tab[2];
				if(!(themes.containsValue(theme))){
					themes.put(i, theme);
				}
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		/* Ecriture */
		
		try {
			FileWriter fw = new FileWriter("themes.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			for(String s : themes.values()){
				fichierSortie.println(s); 
			}
			fichierSortie.close();
			System.out.println("Le fichier themes.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * Méthode créant une matrice permettant de connaitre le nombre de consultations par themes
	 * pour chaque utilisateur et inversement.
	 * @param matrice
	 * @param themes
	 * @param utilisateurs
	 */
	public void creerMatrice(Map<String, Integer> matrice,Map<Integer, String> themes, Map<Integer, String> utilisateurs){{

		String prenom;
		String theme;
		
		/* Lecture */
		
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				String tab[] = ligne.split(";");
				prenom = tab[1];
				theme = tab[2];
				if(!(matrice.containsKey(prenom + "-" + theme))){
					matrice.put(prenom + "-" + theme, 1);
				}
				else{
					Integer nombreConsultations = matrice.get(prenom + "-" + theme);
					nombreConsultations++;
					matrice.put(prenom + "-" + theme, nombreConsultations);
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		/* Ecriture */
		
		try {
			FileWriter fw = new FileWriter("MUT.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw);
			for(String u : utilisateurs.values()){
				for(String t : themes.values()){
					Integer j = matrice.get(u + "-" + t);
					fichierSortie.print(j + " "); 
				}
				fichierSortie.println();
			}
			fichierSortie.close();
			System.out.println("Le fichier MUT.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	
	}
	}

	/**
	 * Main 
	 * Execution des trois méthodes
	 * @param args
	 */
	public static void main(String[] args){
		Map<String, Integer> matrice = new HashMap<String, Integer>();
		Map<Integer, String> utilisateurs = new HashMap<Integer, String>();
		Map<Integer, String> themes = new HashMap<Integer, String>();
		Ex1 ex1 = new Ex1();
		ex1.creerFichierUtilisateurs(utilisateurs);
		ex1.creerFichierThemes(themes);
		ex1.creerMatrice(matrice, themes, utilisateurs);
	}

}
