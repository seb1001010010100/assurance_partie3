package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.Facture;

class TestErreurFormat {

	@Test
	void verifierErreurNom() {

		Facture.getExtracteur().extraireDonnees("testNom.txt");
		Facture.verifierDonnees();
		assertEquals("Le client : Patrick Papineau, n'est pas du bon format.", Facture.getListeErreur().get(0));
		
	}
	@Test
	void verifierErreurPlat() {

		Facture.getExtracteur().extraireDonnees("testPlat.txt");
		Facture.verifierDonnees();
		assertEquals("Le plat : Poutine 10.5 degeulasse, n'est pas du bon format.", Facture.getListeErreur().get(0));
		
	}

}
