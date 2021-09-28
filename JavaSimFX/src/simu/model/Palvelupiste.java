package simu.model;

import java.util.LinkedList;
import java.util.Random;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();//
	private LinkedList<Asiakas> jonotonPalvelupiste = new LinkedList<Asiakas>();//Asiakkaat jonottomassa palvelupisteessä lisätään tänne.
	
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


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		if(this.palvelupisteenTyyppi == PalveluTyyppi.SHOP || this.palvelupisteenTyyppi == PalveluTyyppi.COFFEE_TABLE) {
			return jonotonPalvelupiste.poll();
		}
		return jono.poll();
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		Random rand = new Random();
		double palveluaika = generator.sample();
		varattu = true;
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		
		switch(palvelupisteenTyyppi) {
		case REGISTER:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + ". saapui kassalle ostoskori: " + jono.peek().getOstoskori());
			
			palveluaika = jono.peek().getOstoskori() * 1.2; //palveluaika on ostosten määrä * 1.2
			break;
			
		case SELFSERVICE:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + ". saapui itsepalvelukassalle ostoskori: " + jono.peek().getOstoskori());
			palveluaika = jono.peek().getOstoskori() * 1.8; //palveluaika on ostosten määrä * 1.8
			break;
			
		case MEAT:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + ". saapui lihatiskille");
			jono.peek().lisaaOstos(rand.nextInt(3) + 1);//lisää 1-3 ostosta asiakkaalle
			palveluaika = palveluaika/3; //Lihatiskin palveluaika on 1/3 tavallisesta palveluajasta.
			break;
			
		case SHOP:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() +". valitsee ostoksia.");
			jono.peek().lisaaOstos(rand.nextInt(30) + 2);
			varattu = false; //kaupan hyllyillä ei oli jonoa, joten monta asiakasta pystyy valitsemaan ostoksia saman aikaisesti, eikä ne ole ikinä varattuna.
			jonotonPalvelupiste.add(jono.poll());//asiakas siirretään jonosta jonottomaan palvelupisteeseen.
			break;
			
		case COFFEE:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + ". saapui Kahvilaan");
			palveluaika = palveluaika/3; //kahvilan palveluaika on 1/3 tavallisesta palveluajasta.
			jono.peek().setKahvi(rand.nextBoolean());//50% jää kahvilaan juomaan kahvin, 50% poistuu kahvin kanssa
			break;
			
		case COFFEE_TABLE:
			Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + ". päätti jäädä juomaan kahviaan kahvilaan.");
			palveluaika = palveluaika/2;//kahvin juonti aika on 1/2 palveluajasta.
			varattu = false; //Kahvilan pöydissä ei ole jonoa, monta asiakasta pystyy juomaan kahvia saman aikaisesti, joten pöydät eivät ole ikinä varattu.
			jonotonPalvelupiste.add(jono.poll());//asiakas siirretään jonosta jonottomaan palvelupisteeseen.
			break;
		}
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}

	
	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}

}