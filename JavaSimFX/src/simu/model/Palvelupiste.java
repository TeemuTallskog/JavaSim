package simu.model;

import java.util.LinkedList;
import java.util.Random;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();//
	private LinkedList<Asiakas> jonotonPalvelupiste = new LinkedList<Asiakas>();//Asiakkaat jonottomassa palvelupisteessä lisätään tänne.
	private static int customerCount = 0;
	private static double serviceLenght = 0;
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	private PalveluTyyppi palvelupisteenTyyppi;
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, PalveluTyyppi palv){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palvelupisteenTyyppi = palv;
	}


	public synchronized void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public synchronized Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		if(this.palvelupisteenTyyppi == PalveluTyyppi.SHOP || this.palvelupisteenTyyppi == PalveluTyyppi.COFFEE_TABLE) {
			return jonotonPalvelupiste.poll();
		}
		return jono.poll();
	}

	public synchronized void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		Random rand = new Random();
		double palveluaika = generator.sample();
		varattu = true;
		System.out.println("Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		
		switch(palvelupisteenTyyppi) {
		case REGISTER:
			System.out.println( "Asiakas " + jono.peek().getId() + ". saapui kassalle ostoskori: " + jono.peek().getOstoskori());
			
			palveluaika = jono.peek().getOstoskori() * 1.4; //Kassan palveluaika määräytyy asiakkaan ostoksien määrän mukaan.
			break;
			
		case SELFSERVICE:
			System.out.println("Asiakas " + jono.peek().getId() + ". saapui itsepalvelukassalle ostoskori: " + jono.peek().getOstoskori());
			palveluaika = jono.peek().getOstoskori() * 2.8; //Itsepalvelussa palveluaika on 2x kassan palveluaika per ostos.
			break;
			
		case MEAT:
			System.out.println("Asiakas " + jono.peek().getId() + ". saapui lihatiskille");
			jono.peek().lisaaOstos(rand.nextInt(3) + 1);//lisää 1-3 ostosta asiakkaalle
			palveluaika = palveluaika/2; //Lihatiskin palveluaika on 1/3 tavallisesta palveluajasta.
			break;
			
		case SHOP:
			System.out.println( "Asiakas " + jono.peek().getId() +". valitsee ostoksia.");
			int n = rand.nextInt(30) + 2;//asiakkaille arvottu ostosten määrä hyllyiltä
			jono.peek().lisaaOstos(n);
			palveluaika = palveluaika * 6 * n/10; //palveluaika määräytyy ostosten määrän mukaisesti
			varattu = false; //kaupan hyllyillä ei oli jonoa, joten monta asiakasta pystyy valitsemaan ostoksia saman aikaisesti, eikä ne ole ikinä varattuna.
			jonotonPalvelupiste.add(jono.poll());//asiakas siirretään jonosta jonottomaan palvelupisteeseen.
			break;
			
		case COFFEE:
			System.out.println("Asiakas " + jono.peek().getId() + ". saapui Kahvilaan");
			palveluaika = palveluaika/2;
			jono.peek().setKahvi(rand.nextBoolean());//50% jää kahvilaan juomaan kahvin, 50% poistuu kahvin kanssa
			break;
			
		case COFFEE_TABLE:
			System.out.println("Asiakas " + jono.peek().getId() + ". päätti jäädä juomaan kahviaan kahvilaan.");
			palveluaika = palveluaika * 1.5;//kahvin juonti aika on 1/2 palveluajasta.
			varattu = false; //Kahvilan pöydissä ei ole jonoa, monta asiakasta pystyy juomaan kahvia saman aikaisesti, joten pöydät eivät ole ikinä varattu.
			jonotonPalvelupiste.add(jono.poll());//asiakas siirretään jonosta jonottomaan palvelupisteeseen.
			break;
		}
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		customerCount++;
		serviceLenght += palveluaika;	
	}

	
	public synchronized boolean onVarattu(){
		return varattu;
	}
	
	/*
	 * returns an array of cutsomers who are in que for a service point. If no one is in que it returns an empty array.
	 */
	public synchronized Asiakas[] getJono(){
		if(this.palvelupisteenTyyppi == PalveluTyyppi.SHOP || this.palvelupisteenTyyppi == PalveluTyyppi.COFFEE_TABLE) {
			Asiakas[] arr = this.jono.toArray(new Asiakas[this.jono.size()]);
			return arr;
		}else if(this.jono.size() > 1) {
			Asiakas[] arr = new Asiakas[this.jono.size() - 1];
			for(int i = 1; i < this.jono.size(); i++) {
				arr[i - 1] = this.jono.get(i);
			}
			return arr;
		}else return new Asiakas[0];
	}
	
	/*
	 * returns an array of customers who are being serviced. If no one is being serviced it returns an empty array.
	 */
	public synchronized Asiakas[] getPalvellaan(){
		if(this.palvelupisteenTyyppi == PalveluTyyppi.SHOP || this.palvelupisteenTyyppi == PalveluTyyppi.COFFEE_TABLE) {
			return this.jonotonPalvelupiste.toArray(new Asiakas[this.jonotonPalvelupiste.size()]);
		}else if(this.jono.size() > 0) {
			Asiakas[] arr = new Asiakas[1];
			arr[0] = this.jono.peek();
			return arr;
		}else return new Asiakas[0];
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}
	
	public PalveluTyyppi getTyyppi() {
		return this.palvelupisteenTyyppi;
	}
	
	public double getAvrgPalvAika() {
		return serviceLenght / customerCount;
	}

}