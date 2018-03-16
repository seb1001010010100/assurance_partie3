package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestFormatCommande {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		//test sans plat ni prix
		listeErreurExpected.add("La commande : Roger, n'est pas du bon format.");
		//test sans prix
		listeErreurExpected.add("La commande : Roger Repas_Poulet, n'est pas du bon format.");
		//test avec trop de mots
		listeErreurExpected.add("La commande : Roger Poutine au poulet 2, n'est pas du bon format.");
		//test avec un espace avant la commande
		listeErreurExpected.add("La commande :  Roger Poutine 2, n'est pas du bon format.");
		Facture.getExtracteur().extraireDonnees("testFormatCommande.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
