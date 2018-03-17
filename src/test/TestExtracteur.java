package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import main.Extracteur;

class TestExtracteur {
	
	private Extracteur extracteur = new Extracteur();
	
	@Test
	void testExtractionDonnees() {
		
		extracteur.extraireDonnees("testExtracteur.txt");
		assertEquals("patrick", extracteur.getListeClients().get(0));
		assertEquals("Poutine 10", extracteur.getListePlats().get(0));
		assertEquals("patrick Poutine 1", extracteur.getListeCommandes().get(0));
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Test
	void testErreurFichier() {
		
		extracteur.extraireDonnees("fichierInexistant.txt");
		exception.expect(FileNotFoundException.class);
		
	}

}
