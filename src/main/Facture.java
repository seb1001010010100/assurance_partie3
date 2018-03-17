package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class Facture {
	
	private static Extracteur extracteur = new Extracteur();
	private static String[][] tableauClients;
	private static String[][] tableauPlats;
	private static String [][] tableauCommandes;
	private static ArrayList<String> listeErreur = new ArrayList<String>();
	private static final double TPS = 0.05, TVQ = 0.10;
	

	public static void main(String[] args) {
		
		getExtracteur().extraireDonnees("fichierEntree.txt");
		verifierDonnees();
		creerFacture();

		
	}
	
	//remplis la liste des erreurs courants
	public static void verifierDonnees() {
		
		//tableau de string pour splitter les commande et plats
		String[] splitString;
		
		//verifier les clients
		for(Iterator<String> iter = getExtracteur().getListeClients().listIterator(); iter.hasNext();) {
			
			String currentClient = iter.next();
			
			if(currentClient.contains(" ")) {
				
				listeErreur.add("Le client : " + currentClient + ", n'est pas du bon format.");
				iter.remove();
				
			}
			
		}
		
		//verifier les plats
		for(Iterator<String> iter = getExtracteur().getListePlats().listIterator(); iter.hasNext();) {
			
			String currentPlat = iter.next();
			splitString = currentPlat.split(" ");
			if(splitString.length != 2) {
				
				listeErreur.add("Le plat : " + currentPlat + ", n'est pas du bon format.");
				iter.remove();
				
			}else{
				
				   try
				   {
					  double prix = Double.parseDouble(splitString[1]);
				      if(prix < 0) {
				    	  
						listeErreur.add("Le prix : " + splitString[1] + ", n'est pas du bon format.");
						iter.remove();
				    	  
				      }
				   }
				   catch( Exception e)
				   {
						listeErreur.add("Le prix : " + splitString[1] + ", n'est pas du bon format.");
						iter.remove();
				   }
				
			}
			
		}
		
		//verifier les commandes
		for(Iterator<String> iter = getExtracteur().getListeCommandes().listIterator(); iter.hasNext();) {
			
			String currentCommande = iter.next();
			splitString = currentCommande.split(" ");
			if(splitString.length != 3) {

				listeErreur.add("La commande : " + currentCommande + ", n'est pas du bon format.");
				iter.remove();
				
			}else {
				
				for(Iterator<String> iterCli = getExtracteur().getListeClients().listIterator(); iterCli.hasNext();) {
					
					String client = iterCli.next();
					if(client.equalsIgnoreCase(splitString[0])) {
						
						break;
						
					}else if(!client.equalsIgnoreCase(splitString[0]) 
							&& !iterCli.hasNext()) {

						listeErreur.add("Le client : " + splitString[0] + ", n'existe pas dans la liste des clients.");
						iter.remove();
						
					}
					
				}
				
				for(Iterator<String> iterPlat = getExtracteur().getListePlats().listIterator(); iterPlat.hasNext();) {
					
					String[] tableauPlats = iterPlat.next().split(" ");
					if(tableauPlats[0].equalsIgnoreCase(splitString[1])) {
						
						break;
						
					}else if(!tableauPlats[0].equalsIgnoreCase(splitString[1]) 
							&& !iterPlat.hasNext()) {

						listeErreur.add("Le plat : " + splitString[1] + ", n'existe pas dans la liste des plats.");
						iter.remove();
						
					}
					
				}
				
			   try
			   {
			      int prix = Integer.parseInt(splitString[2]);
			      if(prix < 0) {
			    	  
					listeErreur.add("Le nombre : " + splitString[2] + ", n'est pas du bon format.");
					iter.remove();
			    	  
			      }
			   }
			   catch(Exception e)
			   {
					listeErreur.add("Le nombre : " + splitString[2] + ", n'est pas du bon format.");
					iter.remove();
			   }
				
				
			}
			
		}

		
	}
	
	public static void creerFacture() {
		
		formaterTableau();
		for(int i = 0; i < tableauCommandes.length; i++) {
			
			double prixPlat = 0, prixTotal = 0, prixClient = 0, prixCommande;
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
					prixCommande = prixPlat * nombrePlat;
					prixTotal = prixCommande + (prixCommande * (TPS + TVQ));
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
		tableauCommandes = new String[getExtracteur().getListeCommandes().size()][3]; 
		for(int i = 0; i < getExtracteur().getListeCommandes().size(); i++) {
			
			tableauCommandes[i] = getExtracteur().getListeCommandes().get(i).split(" ");

		}
		
	}
	
	public static void ecrireFacture() {
		
		try {
			NumberFormat argentFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
			String separateur = "--------------------------";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			PrintWriter writer = new PrintWriter("src/factures/Facture-du-" + dateFormat.format(date) +".txt", "UTF-8");
			writer.println("Bienvenue chez Barette!");
			writer.println(separateur);
			System.out.println("Bienvenue chez Barette!\n" + separateur);
			
			if(!listeErreur.isEmpty()) {
				
				System.out.println("Erreurs:");
				writer.println("Erreurs:");
				
				for(Iterator<String> iter = listeErreur.iterator(); iter.hasNext(); ) {
					String erreur = iter.next();
					System.out.println(erreur);
					writer.println(erreur);
				}
				
				System.out.println(separateur);
				writer.println(separateur);
				
			}
			
			writer.println("Factures:");
			System.out.println("Factures:");
			for(int i = 0; i < tableauClients.length; i++) {
				
				double total = Double.parseDouble(tableauClients[i][1]);
						
				if(total > 0) {
					
					writer.println(tableauClients[i][0] + " " + argentFormat.format(total));
					System.out.println(tableauClients[i][0] + " " + argentFormat.format(total));
					
				}

				
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

	public static ArrayList<String> getListeErreur() {
		return listeErreur;
	}

	public static void setListeErreur(ArrayList<String> listeErreur) {
		Facture.listeErreur = listeErreur;
	}

	public static String[][] getTableauClients() {
		return tableauClients;
	}

	public static void setTableauClients(String[][] tableauClients) {
		Facture.tableauClients = tableauClients;
	}

	public static String[][] getTableauPlats() {
		return tableauPlats;
	}

	public static void setTableauPlats(String[][] tableauPlats) {
		Facture.tableauPlats = tableauPlats;
	}

	public static String [][] getTableauCommandes() {
		return tableauCommandes;
	}

	public static void setTableauCommandes(String [][] tableauCommandes) {
		Facture.tableauCommandes = tableauCommandes;
	}

}
