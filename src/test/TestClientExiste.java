package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestClientExiste {

	@Test
	void test() {
		ArrayList<String> listeErreurExpected = new ArrayList<String>();
		listeErreurExpected.add("Le client : Patrick, n'existe pas dans la liste des clients.");
		Facture.getExtracteur().extraireDonnees("testClientExiste.txt");
		Facture.verifierDonnees();
		assertEquals(listeErreurExpected, Facture.getListeErreur());
	}

}
