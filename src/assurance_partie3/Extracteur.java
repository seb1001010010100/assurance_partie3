package assurance_partie3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Extracteur {
	
	private String nomFichier;
	private ArrayList<String> listeClients = new ArrayList<String>();
	private ArrayList<String> listePlats = new ArrayList<String>();
	private ArrayList<String> listeCommande = new ArrayList<String>();
	
	public Extracteur(){
		
		this.nomFichier = "fichierEntree.txt";
		
	}
	
	public Extracteur(String nomFichier) {
		
		this.nomFichier = nomFichier;
		
	}
	
	public void setListeClients(ArrayList<String> listeClients) {
		
		this.listeClients = listeClients;
		
	}
	
	public ArrayList<String> getListeClients(){
		
		return this.listeClients;
		
	}
	
	public void setListePlats(ArrayList<String> listePlats) {
		
		this.listePlats = listePlats;
		
	}
	
	public ArrayList<String> getListePlats(){
		
		return this.listePlats;
		
	}
	
	public void setListeCommande(ArrayList<String> listeCommande) {
		
		this.listeCommande = listeCommande;
		
	}
	
	public ArrayList<String> getListeCommande(){
		
		return this.listeCommande;
		
	}

	
	public void extraireDonnees() {
		
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/assurance_partie3/" + nomFichier)); 
            String ligne;
            //String qui change celon le type de donnee lue (clients/plats/commande)
            String estUn = null;

            while((ligne = bufferedReader.readLine()) != null) {
            	            	
                if(ligne.equalsIgnoreCase("Clients :")) {
                	
                	estUn = "client";
                	
                }else if(ligne.equalsIgnoreCase("Plats :")) {
                	
                	estUn = "plat";
                	
                }else if(ligne.equalsIgnoreCase("Commandes :")) {
                	
                	estUn = "commande";
                	
                }else if(ligne.equalsIgnoreCase("Fin")){
                	
                	break;
                	
                }else{
                	
                	if(estUn.equalsIgnoreCase("client")) {
                		
                		listeClients.add(ligne);
                		
                	}else if(estUn.equalsIgnoreCase("plat")) {
                		
                		listePlats.add(ligne);
                		
                	}else if(estUn.equalsIgnoreCase("commande")) {
                		
                		listeCommande.add(ligne);
                		
                	}
                	
                }
                

                
            }   
            
            bufferedReader.close();         
        }
        
        catch(FileNotFoundException ex) {
        	
            System.out.println("Impossible d'ouvrir le fichier: " + nomFichier);   
            
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
