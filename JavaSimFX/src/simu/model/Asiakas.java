package simu.model;

import java.util.Random;

import simu.framework.Kello;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static double sum = 0.0;
	private int ostoskori;
	private boolean kahvi = false; //meneekö kahvilaan
	

	private boolean liha = false; //meneekö lihatiskille
	
	/**
	 * Asiakkaalle arvotaan mihin palvelupisteeseen se menee.
	 */
	public Asiakas(){
	    id = i++;
	    
		saapumisaika = Kello.getInstance().getAika();
		System.out.println("Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
		int i = new Random().nextInt(400); //antaa arvon 0-399
		if(i<100) {
			if(i<25) { //25% menee kahvilaan ja lihatiskille
				this.kahvi = true;
				this.liha = true;
			}else if(i<63) { //38% menee vain kahvilaan
				this.kahvi = true;
			}else if(i < 100) { //37% menee vain lihatiskille
				this.liha = true;
			}
		}
		
		
	}
	
	/**
	 * Palauttaa kokonaisen oleskeluajan.
	 * @return time
	 */
	public static double getTimeSum() {
		return sum;
	}

	/**
	 * palauttaa poistumisajan
	 * @return time
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * Asettaa poistumisajan
	 * @param poistumisaika
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	/**
	 * palauttaa saapumisajan
	 * @return
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * asettaa saapumisajan.
	 * @param saapumisaika
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	/**
	 * palauttaa asiakkaan id:n
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * tulostaa raportin asiakkaasta.
	 */
	public void raportti(){
		System.out.println( "\nAsiakas "+id+ " valmis! ");
		System.out.println("Asiakas "+id+ " saapui: " +saapumisaika);
		System.out.println("Asiakas "+id+ " poistui: " +poistumisaika);
		System.out.println("Asiakas "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		System.out.println("Asiakas " + id + " osti " + this.ostoskori + " tuotetta"); 
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}
	
	/**
	 * palauttaa ostoksien määrän asiakkaan ostoskorissa.
	 * @return
	 */
	public int getOstoskori() {
		return this.ostoskori;
	}
	
	/**
	 * lisää ostoskoriin ostoksia.
	 * @param määrä
	 */
	public void lisaaOstos(int amount) {
		this.ostoskori += amount;
	}
	
	/**
	 * Palauttaa true tai false jos asiakkaalle on arvottu kahvilassa käynti.
	 * @return true/false
	 */
	public boolean isKahvi() {
		return kahvi;
	}

	/**
	 * Asiakkaalle voidaan asettaa tarve käydä kahvilla.
	 * @param kahvi
	 */
	public void setKahvi(boolean kahvi) {
		this.kahvi = kahvi;
	}

	/**
	 * Palauttaa true tai false jos asiakkaalle on arvottu lihatiskillä käynti.
	 * @return
	 */
	public boolean isLiha() {
		return liha;
	}
	/**
	 * asiakas ID (Ostoskori)
	 */
	@Override
	public String toString() {
		return "Asiakas " + this.id + " (" + this.ostoskori + ")";
	}

}
