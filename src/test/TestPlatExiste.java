package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestPlatExiste {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		listeErreurExpected.add("Le plat : Jambon, n'existe pas dans la liste des plats.");
		Facture.getExtracteur().extraireDonnees("testPlatExiste.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
