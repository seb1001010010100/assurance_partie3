package assurance_partie3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Facture {
	
	private static Extracteur extracteur = new Extracteur();
	private static String[][] tableauClients;
	private static String[][] tableauPlats;
	private static String [][] tableauCommandes;

	public static void main(String[] args) {
		
		extracteur.extraireDonnees();
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
		for(int i = 0; i < extracteur.getListeClients().size(); i++) {
			
			if(extracteur.getListeClients().get(i).contains(" ")) {
				
				return true;
				
			}
			
		}
		
		//verifier les plats
		for(int i = 0; i < extracteur.getListePlats().size(); i++) {
			
			splitString = extracteur.getListePlats().get(i).split(" ");
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
		for(int i = 0; i < extracteur.getListeCommande().size(); i++) {
			
			splitString = extracteur.getListeCommande().get(i).split(" ");
			if(splitString.length != 3) {

				return true;
				
			}else {
				
				for(int j = 0; j < extracteur.getListeClients().size(); j++) {
					
					if(extracteur.getListeClients().get(j).equalsIgnoreCase(splitString[0])) {
						
						break;
						
					}else if(!extracteur.getListeClients().get(j).equalsIgnoreCase(splitString[0]) 
							&& j == (extracteur.getListeClients().size() - 1)) {

						return true;
						
					}
					
				}
				
				for(int j = 0; j < extracteur.getListePlats().size(); j++) {
					
					String[] tableauPlats = extracteur.getListePlats().get(j).split(" ");
					if(tableauPlats[0].equalsIgnoreCase(splitString[1])) {
						
						break;
						
					}else if(!tableauPlats[0].equalsIgnoreCase(splitString[1]) 
							&& j == (extracteur.getListePlats().size() - 1)) {

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
		tableauClients = new String[extracteur.getListeClients().size()][2];
		for(int i = 0; i < extracteur.getListeClients().size(); i++) {
			
			tableauClients[i][0] = extracteur.getListeClients().get(i);
			tableauClients[i][1] = "0.00";
			
		}
		
		//mettre les plats dans une array
		tableauPlats = new String[extracteur.getListePlats().size()][2];
		for(int i = 0; i < extracteur.getListePlats().size(); i++) {
			
			tableauPlats[i] = extracteur.getListePlats().get(i).split(" ");
			
		}
		
		//mettre les commandes dans une array
		tableauCommandes = new String[extracteur.getListeCommande().size()][3]; 
		for(int i = 0; i < extracteur.getListeCommande().size(); i++) {
			
			tableauCommandes[i] = extracteur.getListeCommande().get(i).split(" ");

		}
		
	}
	
	public static void ecrireFacture() {
		
		try {
			
			PrintWriter writer = new PrintWriter("src/assurance_partie3/fichierSortie.txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			writer.println("Factures:");
			for(int i = 0; i < tableauClients.length; i++) {
				
				writer.println(tableauClients[i][0] + " " + tableauClients[i][1] + "$");
				
			}
			
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
