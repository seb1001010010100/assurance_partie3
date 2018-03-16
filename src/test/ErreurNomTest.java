package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.Facture;

class ErreurNomTest {

	@Test
	void verifierNomTest() {

		Facture.getExtracteur().extraireDonnees("testNom.txt");
		boolean result = Facture.verifierDonnees();
		assertEquals(true, result);
		
	}

}
