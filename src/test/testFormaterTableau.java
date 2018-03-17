package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Facture;

class testFormaterTableau {

	@Test
	void test() {
		
		Facture.getExtracteur().extraireDonnees("testExtracteur.txt");
		Facture.formaterTableau();
		assertEquals("patrick",Facture.getTableauClients()[0][0]);
		assertEquals("Poutine",Facture.getTableauPlats()[0][0]);
		assertEquals("10",Facture.getTableauPlats()[0][1]);
		assertEquals("patrick",Facture.getTableauCommandes()[0][0]);
		assertEquals("Poutine",Facture.getTableauCommandes()[0][1]);
		assertEquals("1",Facture.getTableauCommandes()[0][2]);
		
	}

}
