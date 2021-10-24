package simu.model;

import eduni.distributions.Gamma;
import eduni.distributions.Logistic;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.controller.IKontrolleri;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	//Muuttuja-laskureita eri pisteiden kävijöiden määrän mittaamiseen.
	static int asiakas = 0;
	//Asiakas[] asiakasLista = new Asiakas[asiakas];
	
	static int kahvilaAsiakas = 0;
	static int kahvilaPoytaAsiakas = 0;
	static int lihatiskiAsiakas = 0;
	static int kassaAsiakas = 0;
	static int ipKassaAsiakas = 0;

	private Saapumisprosessi saapumisprosessi;

	public OmaMoottori(IKontrolleri kontrolleri) {

		super(kontrolleri);

		palvelupisteet = new Palvelupiste[6];

		palvelupisteet[0] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.DEP1, // kahvila
				PalveluTyyppi.COFFEE);
		palvelupisteet[1] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.DEP2, // Kahvilan pöydät
				PalveluTyyppi.COFFEE_TABLE);
		palvelupisteet[2] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.DEP3, // Liha tiski
				PalveluTyyppi.MEAT);
		palvelupisteet[3] = new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.DEP4, // Kaupan hylly
				PalveluTyyppi.SHOP);
		palvelupisteet[4] = new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.DEP5, // Kassa
				PalveluTyyppi.REGISTER);
		palvelupisteet[5] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.DEP6, // itsepalvelu
																										// kassa
				PalveluTyyppi.SELFSERVICE);

		generateSaapumisprosessi();

	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		
		Asiakas a;
		switch (t.getTyyppi()) {

		case ARR1:
			a = new Asiakas();
			asiakas++;
			this.kontrolleri.newCustomer();
			// Lisää asiakkaan oikeaan palvelupisteeseen asiakkaalle generoitujen tarpeiden
			// mukaan.
			if (a.isKahvi()) {
				palvelupisteet[0].lisaaJonoon(a);
				kahvilaAsiakas++;
			} else if (a.isLiha()) {
				palvelupisteet[2].lisaaJonoon(a);
				lihatiskiAsiakas++;
			} else
				palvelupisteet[3].lisaaJonoon(a);
			saapumisprosessi.generoiSeuraava();
			break;
		case DEP1:
			a = palvelupisteet[0].otaJonosta();
			// lisää asiakkaan kahvilan pöytään jos asiakas päättää jäädä juomaan kahvin
			// kahvilaan, muuten katsoo jos asiakas haluaa lihatiskille,
			// jos ei niin vie asiakkaan kaupan hyllyille valitsemaan ostokisa
			if (a.isKahvi()) {
				palvelupisteet[1].lisaaJonoon(a);
				kahvilaPoytaAsiakas++;
			} else if (a.isLiha()) {
				palvelupisteet[2].lisaaJonoon(a);
			} else
				palvelupisteet[3].lisaaJonoon(a);
			break;
		case DEP2:
			a = palvelupisteet[1].otaJonosta();
			if (a.isLiha()) {
				palvelupisteet[2].lisaaJonoon(a);
			} else
				palvelupisteet[3].lisaaJonoon(a);
			break;
		case DEP3:
			a = palvelupisteet[2].otaJonosta();
			palvelupisteet[3].lisaaJonoon(a);
			break;
		case DEP4:
			a = palvelupisteet[3].otaJonosta();
			if (a.getOstoskori() < 8) {
				palvelupisteet[5].lisaaJonoon(a);
			} else {
				palvelupisteet[4].lisaaJonoon(a);
			}
			break;
		case DEP5:
			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			kassaAsiakas++;
			a.raportti();
			this.kontrolleri.readyCustomer(a);
			break;
		case DEP6:
			a = palvelupisteet[5].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			ipKassaAsiakas++;
			a.raportti();
			this.kontrolleri.readyCustomer(a);
			break;
		}
	}

	public static int getAsiakas() {
		return asiakas;
	}

	public static int getKahvilaAsiakas() {
		return kahvilaAsiakas;
	}

	public static int getKahvilaPoytaAsiakas() {
		return kahvilaPoytaAsiakas;
	}

	public static int getLihatiskiAsiakas() {
		return lihatiskiAsiakas;
	}

	public static int getKassaAsiakas() {
		return kassaAsiakas;
	}

	public static int getIpKassaAsiakas() {
		return ipKassaAsiakas;
	}
	
	public int getCompletedCustomers() {
		return ipKassaAsiakas + kassaAsiakas;
	}
	
	public Palvelupiste[] getPalvPisteet() {
		return this.palvelupisteet;
	}
	
	@Override
	protected void tulokset() {
		System.out.println("\n// Simulointi päättyi kello " + Kello.getInstance().getAika() + " //");
		System.out.println("\nTulokset: ");
		System.out.println("Asiakkaita yhteensä: " + getAsiakas());
		System.out.println("Asiakkaat, jotka kävivät lihatiskillä: " + getLihatiskiAsiakas());
		System.out.println("Asiakkaat, jotka joivat kahvin pöydässä: " + getKahvilaPoytaAsiakas());
		System.out.println("Asiakkaat, jotka ostivat kahvin: " + getKahvilaAsiakas());
		System.out.println("\nItsepalvelukassan läpi kulki " + getIpKassaAsiakas() + " asiakasta.");
		System.out.println("Normaalin kassan läpi kulki " + getKassaAsiakas() + " asiakasta.");
		Tulos tulos = new Tulos();
		tulos.setDistribution(this.kontrolleri.getUI().getDistribution());
		tulos.setAjoAika(this.getSimulointiAika());
		tulos.setAsiakasCount(this.getCompletedCustomers());
		tulos.setKeskLapimenoAika(Asiakas.getTimeSum() / this.getCompletedCustomers());
		IDatabaseAccessObject dbao = new DatabaseAccessObject();
		tulos.setKeskPalvAika(palvelupisteet[1].getAvrgPalvAika());
		tulos.setAllCustomers(asiakas);
		dbao.vieTulos(tulos);
	}
	
	@Override
	protected double[] resultSet() {
		double[] results = new double[2];
		results[0] = palvelupisteet[1].getAvrgPalvAika();
		results[1] = Asiakas.getTimeSum() / this.getCompletedCustomers();
		return results;
	}
	
	public void generateSaapumisprosessi() {
		switch(kontrolleri.getUI().getDistribution()) {
		case NegExp:
			saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR1);
			break;
		case Normal:
			saapumisprosessi = new Saapumisprosessi(new Normal(15, 5), tapahtumalista, TapahtumanTyyppi.ARR1);
			break;
		case Gamma:
			saapumisprosessi = new Saapumisprosessi(new Gamma(15,5), tapahtumalista, TapahtumanTyyppi.ARR1);
			break;
		case Logistic:
			saapumisprosessi = new Saapumisprosessi(new Logistic(15, 5), tapahtumalista, TapahtumanTyyppi.ARR1);
			break;
		}
	}

}
