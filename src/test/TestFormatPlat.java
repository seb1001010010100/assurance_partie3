package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestFormatPlat {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		//test sans prix
		listeErreurExpected.add("Le plat : Lapin, n'est pas du bon format.");
		//test avec plus qu'un mot
		listeErreurExpected.add("Le plat : Canard au pain 2, n'est pas du bon format.");
		//test avec espace avant plat
		listeErreurExpected.add("Le plat :  Jambe 3, n'est pas du bon format.");
		Facture.getExtracteur().extraireDonnees("testFormatPlat.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
