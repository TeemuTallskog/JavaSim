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
	
	public static double getTimeSum() {
		return sum;
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	
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
	
	public int getOstoskori() {
		return this.ostoskori;
	}
	
	public void lisaaOstos(int amount) {
		this.ostoskori += amount;
	}
	
	public boolean isKahvi() {
		return kahvi;
	}

	public void setKahvi(boolean kahvi) {
		this.kahvi = kahvi;
	}

	public boolean isLiha() {
		return liha;
	}
	
	@Override
	public String toString() {
		return "Asiakas " + this.id + " (" + this.ostoskori + ")";
	}

}
