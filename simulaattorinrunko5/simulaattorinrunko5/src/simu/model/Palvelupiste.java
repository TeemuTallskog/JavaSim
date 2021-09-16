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

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
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
		return jono.poll();
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		Random rand = new Random();
		double palveluaika = generator.sample();
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		switch(palvelupisteenTyyppi) {
		case REGISTER:
			System.out.println("Asiakas saapui kassalle ostoskori: " + jono.peek().getOstoskori());
			
			palveluaika = jono.peek().getOstoskori() * 2;
			break;
			
		case SELFSERVICE:
			System.out.println("Asiakas saapui itsepalvelukassalle ostoskori: " + jono.peek().getOstoskori());
			palveluaika = jono.peek().getOstoskori() * 5;
			break;
			
		case MEAT:
			break;
			
		case SHOP:
			System.out.println("Asiakas valitsee ostoksia.");
			jono.peek().lisaaOstos(rand.nextInt(30) + 2);
			break;
			
		case COFFEE:
			break;
		}
		varattu = true;
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}


	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}

}
