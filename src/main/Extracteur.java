package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Extracteur {
	
	private ArrayList<String> listeClients = new ArrayList<String>();
	private ArrayList<String> listePlats = new ArrayList<String>();
	private ArrayList<String> listeCommandes = new ArrayList<String>();
	private int numTab;
	
	
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
	
	public void setListeCommandes(ArrayList<String> listeCommande) {
		
		this.listeCommandes = listeCommande;
		
	}
	
	public ArrayList<String> getListeCommandes(){
		
		return this.listeCommandes;
		
	}
	
	public void setTable(int numTab){
		
		this.numTab = numTab;
		
	}
	
	public int getTable() {
		
		return this.numTab;
		
	}

	
	public void extraireDonnees(String nomFichier) {
		
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/commandes/" + nomFichier)); 
            String ligne;
            //String qui change celon le type de donnee lue (clients/plats/commande)
            String estUn = null;

            while((ligne = bufferedReader.readLine()) != null) {
            	
            	if(ligne.equalsIgnoreCase("Table :")) {
            		
            		estUn = "table";
            		
            	}else if(ligne.equalsIgnoreCase("Clients :")) {
                	
                	estUn = "client";
                	
                }else if(ligne.equalsIgnoreCase("Plats :")) {
                	
                	estUn = "plat";
                	
                }else if(ligne.equalsIgnoreCase("Commandes :")) {
                	
                	estUn = "commande";
                	
                }else if(ligne.equalsIgnoreCase("Fin")){
                	
                	break;
                	
                }else{
                	
                	if(estUn.equalsIgnoreCase("table")) {
                		
                		numTab = Integer.parseInt(ligne);
                		
                	}else if(estUn.equalsIgnoreCase("client")) {
                		
                		listeClients.add(ligne);
                		
                	}else if(estUn.equalsIgnoreCase("plat")) {
                		
                		listePlats.add(ligne);
                		
                	}else if(estUn.equalsIgnoreCase("commande")) {
                		
                		listeCommandes.add(ligne);
                		
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
