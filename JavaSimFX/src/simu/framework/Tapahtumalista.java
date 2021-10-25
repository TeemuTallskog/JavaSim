package simu.framework;

import java.util.PriorityQueue;

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){
	 
	}
	/**
	 * poistaa tapahtuman tapahtumalistasta ja palauttaa sen.
	 * @return tapahtuma
	 */
	public Tapahtuma poista(){
		System.out.println("Tapahtumalistasta poisto " + lista.peek().getTyyppi() + " " + lista.peek().getAika() );
		return lista.remove();
	}
	
	/**
	 * lisää tapahtuman tapahtumalistaan.
	 * @param tapahtuma
	 */
	public void lisaa(Tapahtuma t){
		System.out.println("Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}
	
	/**
	 * palauttaa seuraavan tapahtuman ajan
	 * @return
	 */
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
	/**
	 * palauttaa listan taphtumista.
	 * @return tapahtuma[]
	 */
	public Tapahtuma[] getEvents() {
		Tapahtuma[] arr = lista.toArray(new Tapahtuma[lista.size()]);
		return arr;
	}
	
	
}
