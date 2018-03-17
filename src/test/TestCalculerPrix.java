package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Facture;

class TestCalculerPrix {

	@Test
	void test() {
		Facture.setTableauClients(new String[][] {{"patrick", "0.00"}});
		Facture.setTableauPlats(new String[][] {{"patate", "10"}});
		Facture.setTableauCommandes(new String[][] {{"patrick", "patate", "1"}});
		Facture.calculerPrix();
		assertEquals("11.5", Facture.getTableauClients()[0][1]);
	}

}
