package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Facture {
	
	private static Extracteur extracteur = new Extracteur();
	private static String[][] tableauClients;
	private static String[][] tableauPlats;
	private static String [][] tableauCommandes;
	private static ArrayList<String> tableauErreur = new ArrayList<String>();
	private static final int TPS = 5, TVQ = 10;
	

	public static void main(String[] args) {
		
		getExtracteur().extraireDonnees("fichierEntree.txt");
		if(verifierDonnees()) {
			
			System.out.println("Le fichier ne respecte pas le format demandé !");
			
		}else {
			
			creerFacture();

		}
		
	}
	
	
	//retourne faux si il n'y a pas d'erreur
	public static boolean verifierDonnees() {
		
		//tableau de string pour splitter les commande et plats
		String[] splitString;
		
		//verifier les clients
		for(int i = 0; i < getExtracteur().getListeClients().size(); i++) {
			
			if(getExtracteur().getListeClients().get(i).contains(" ")) {
				
				return true;
				
			}
			
		}
		
		//verifier les plats
		for(int i = 0; i < getExtracteur().getListePlats().size(); i++) {
			
			splitString = getExtracteur().getListePlats().get(i).split(" ");
			if(splitString.length != 2) {
				
				return true;
				
			}else{
				
				   try
				   {
				      Double.parseDouble(splitString[1]);
				   }
				   catch( Exception e)
				   {
				      return true;
				   }
				
			}
			
		}
		
		//verifier les commandes
		for(int i = 0; i < getExtracteur().getListeCommande().size(); i++) {
			
			splitString = getExtracteur().getListeCommande().get(i).split(" ");
			if(splitString.length != 3) {

				return true;
				
			}else {
				
				for(int j = 0; j < getExtracteur().getListeClients().size(); j++) {
					
					if(getExtracteur().getListeClients().get(j).equalsIgnoreCase(splitString[0])) {
						
						break;
						
					}else if(!getExtracteur().getListeClients().get(j).equalsIgnoreCase(splitString[0]) 
							&& j == (getExtracteur().getListeClients().size() - 1)) {

						return true;
						
					}
					
				}
				
				for(int j = 0; j < getExtracteur().getListePlats().size(); j++) {
					
					String[] tableauPlats = getExtracteur().getListePlats().get(j).split(" ");
					if(tableauPlats[0].equalsIgnoreCase(splitString[1])) {
						
						break;
						
					}else if(!tableauPlats[0].equalsIgnoreCase(splitString[1]) 
							&& j == (getExtracteur().getListePlats().size() - 1)) {

						return true;
						
					}
					
				}
				
			   try
			   {
			      Integer.parseInt(splitString[2]);
			   }
			   catch(Exception e)
			   {
			      return true;
			   }
				
				
			}
			
		}
		
		
		return false;
		
	}
	
	public static void creerFacture() {
		
		formaterTableau();
		for(int i = 0; i < tableauCommandes.length; i++) {
			
			double prixPlat = 0, prixTotal = 0, prixClient = 0;
			int nombrePlat = 0;
			
			for(int j = 0; j < tableauClients.length; j++) {
				
				if(tableauCommandes[i][0].equalsIgnoreCase(tableauClients[j][0])) {
					
					nombrePlat = Integer.parseInt(tableauCommandes[i][2]);
					
					for(int f = 0; f < tableauPlats.length; f++) {
						
						if(tableauCommandes[i][1].equalsIgnoreCase(tableauPlats[f][0])) {
							
							prixPlat = Double.parseDouble(tableauPlats[f][1]);
							break;
						}
						
					}
					prixTotal = prixPlat * nombrePlat;
					prixClient = Double.parseDouble(tableauClients[j][1]);
					tableauClients[j][1] = Double.toString(prixClient + prixTotal);
					break;
				}
				
			}
			
		}
		ecrireFacture();
		
		
	}
	
	public static void formaterTableau() {
		
		//mettre les clients dans un array
		tableauClients = new String[getExtracteur().getListeClients().size()][2];
		for(int i = 0; i < getExtracteur().getListeClients().size(); i++) {
			
			tableauClients[i][0] = getExtracteur().getListeClients().get(i);
			tableauClients[i][1] = "0.00";
			
		}
		
		//mettre les plats dans une array
		tableauPlats = new String[getExtracteur().getListePlats().size()][2];
		for(int i = 0; i < getExtracteur().getListePlats().size(); i++) {
			
			tableauPlats[i] = getExtracteur().getListePlats().get(i).split(" ");
			
		}
		
		//mettre les commandes dans une array
		tableauCommandes = new String[getExtracteur().getListeCommande().size()][3]; 
		for(int i = 0; i < getExtracteur().getListeCommande().size(); i++) {
			
			tableauCommandes[i] = getExtracteur().getListeCommande().get(i).split(" ");

		}
		
	}
	
	public static void ecrireFacture() {
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			PrintWriter writer = new PrintWriter("src/factures/Facture-du-" + dateFormat.format(date) +".txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			System.out.println("Bienvenue chez Barette!");
			writer.println("Factures:");
			System.out.println("Factures:");
			for(int i = 0; i < tableauClients.length; i++) {
				
				writer.println(tableauClients[i][0] + " " + tableauClients[i][1] + "$");
				System.out.println(tableauClients[i][0] + " " + tableauClients[i][1] + "$");
				
			}
			
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			
		}
		
	}


	public static Extracteur getExtracteur() {
		return extracteur;
	}


	public static void setExtracteur(Extracteur extracteur) {
		Facture.extracteur = extracteur;
	}

}
