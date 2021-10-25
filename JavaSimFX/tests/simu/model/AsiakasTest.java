package simu.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simu.model.Asiakas;

class AsiakasTest {

	//Luodaan testiasiakkaat
	private static Asiakas a = new Asiakas();
	private static Asiakas b = new Asiakas();
	
	//Asetetaan asiakkaalle arvoja joilla voidaan testata
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		a.setSaapumisaika(5);
		a.setPoistumisaika(17);
		a.lisaaOstos(2);
		a.lisaaOstos(3);
		
		b.setSaapumisaika(32);
		b.setPoistumisaika(97);
		b.lisaaOstos(12);
		b.lisaaOstos(30);
	}

	@Test
	@DisplayName("Poistumisajan testaus")
	void testGetPoistumisaika() {
		assertEquals(17, a.getPoistumisaika(), "Poistumisaika väärin. ");
	}


	@Test
	@DisplayName("Saapumisajan testaus")
	void testGetSaapumisaika() {
		assertEquals(5, a.getSaapumisaika(), "Saapumisaika väärin. ");
	}


	@Test
	@DisplayName("Asiakkaiden ID:n toiminnan testaus")
	void testGetId() {
		assertEquals(2, b.getId(), "Asiakkaiden ID:t ei jakaudu oikein. ");
	}

	
	@Test
	@DisplayName("Ostoskorin toiminnan testaus")
	void testGetOstoskori() {
		assertEquals(5, a.getOstoskori(), "Ostoskori ei toimi kunnolla. ");
	}
	
	@Test
	@DisplayName("Näkyykö raportit oikein")
	void testRaportti() {
		a.raportti();
		b.raportti();
	}

}
