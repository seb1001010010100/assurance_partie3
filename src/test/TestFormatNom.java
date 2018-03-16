package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestFormatNom {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		//test avec un deuxieme nom
		listeErreurExpected.add("Le client : patrick papineau, n'est pas du bon format.");
		//test avec quatre noms
		listeErreurExpected.add("Le client : patrick le grand papineau, n'est pas du bon format.");
		//test avec un espace avant le nom
		listeErreurExpected.add("Le client :  patrick, n'est pas du bon format.");
		Facture.getExtracteur().extraireDonnees("testFormatNom.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
