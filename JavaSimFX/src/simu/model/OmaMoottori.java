package simu.model;

import controller.IKontrolleri;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
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

		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR1);

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
				ipKassaAsiakas++;
			} else {
				palvelupisteet[4].lisaaJonoon(a);
				kassaAsiakas++;
			}
			break;
		case DEP5:
			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			break;
		case DEP6:
			a = palvelupisteet[5].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
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
	}

}
