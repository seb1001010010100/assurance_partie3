package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestChiffreErrone {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		listeErreurExpected.add("Le nombre : -10, n'est pas du bon format.");
		listeErreurExpected.add("Le nombre : deux, n'est pas du bon format.");
		listeErreurExpected.add("Le nombre : #30, n'est pas du bon format.");
		Facture.getExtracteur().extraireDonnees("testChiffresErrones.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
