package simu.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

	private IDatabaseAccessObject dbao = new DatabaseAccessObject();
	private Tulos tulos = new Tulos();
	private Tulos[] results = null;
	
	@BeforeEach
	public void setUp() {
		System.out.println("Setup");
		tulos.setAjoAika(1000.0);
		tulos.setAllCustomers(10);
		tulos.setAsiakasCount(25);
		tulos.setDistribution(DistributionTyyppi.Normal);
		tulos.setFirstArg(15);
		tulos.setSecondArg(5);
		tulos.setKeskLapimenoAika(100);
		tulos.setKeskPalvAika(500);
		dbao.truncateTables();
	}
	
	
	
	@Order(1)
	@Test
	@DisplayName("Tuloksen lisääminen")
	void addtulos() {
		System.out.println(tulos.getAjoAika());
		assertTrue(dbao.vieTulos(tulos), "Tuloksen vienti ei onnistunut");
		assertEquals(1, dbao.haeTulokset().length, "tietokanta ei palauttanut oikeaa määrää tuloksia");
		Tulos[] result = dbao.haeTulokset();
		System.out.println(result[0].getAjoAika());
	}
	
	@Test
	@DisplayName("Tuloksien hakeminen")
	void getTulos(){
		assertTrue(dbao.vieTulos(tulos), "Tuloksen vienti ei onnistunut");
		assertTrue((results = dbao.haeTulokset()) != null, "tuloksien hakeminen ei onnistunut");
		assertEquals(1, results.length, "tietokanta ei palauttanut oikeaa määrää tuloksia");
		assertEquals(1000.0, results[0].getAjoAika(), "Ajoaika on väärin");
		assertEquals(10, results[0].getAllCustomers(), "AllCustomers on väärin");
		assertEquals(25, results[0].getAsiakasCount(), "AsiakasCount on väärin");
		assertEquals(DistributionTyyppi.Normal, results[0].getDistribution(), "Jakauma on väärin");
		assertEquals(15, results[0].getFirstArg(), "1st arg on väärin");
		assertEquals(5, results[0].getSecondArg(), "2nd arg on väärin");
		assertEquals(100, results[0].getKeskLapimenoAika(), "KeskLapimenoAika on väärin");
		assertEquals(500, results[0].getKeskPalvAika(), "KeskPalvAika on väärin");
	}
	
	@Order(2)
	@Test
	@DisplayName("Tietokannan tyhjentäminen")
	void emptyDB() {
		assertTrue(dbao.vieTulos(tulos), "Tuloksen vienti ei onnistunut");
		assertTrue(dbao.truncateTables(), "Tietokannan tyhjennyksessä tuli virhe");
		assertEquals(0, dbao.haeTulokset().length, "Tietokanta ei palauttanut tyhjää listaa.");
	}
}
