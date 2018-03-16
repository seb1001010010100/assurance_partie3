package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestPrixErrone {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		listeErreurExpected.add("Le prix : -1, n'est pas du bon format.");
		listeErreurExpected.add("Le prix : deux, n'est pas du bon format.");
		listeErreurExpected.add("Le prix : 5$, n'est pas du bon format.");
		Facture.getExtracteur().extraireDonnees("testPrixErrones.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
