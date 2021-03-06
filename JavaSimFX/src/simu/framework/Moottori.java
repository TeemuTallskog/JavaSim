package simu.framework;

import javafx.application.Platform;
import simu.controller.IKontrolleri;
import simu.model.Asiakas;
import simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	
	protected volatile Tapahtumalista tapahtumalista;
	protected volatile Palvelupiste[] palvelupisteet;
	
	protected IKontrolleri kontrolleri;
	

	public Moottori(IKontrolleri kontrolleri){
		
		this.kontrolleri = kontrolleri;

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override // UUSI 
	public long getViive() {
		return viive;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive(); // UUSI
			System.out.println("\nA-vaihe:");
			kello.setAika(nykyaika());
			System.out.println("\nB-vaihe:");
			suoritaBTapahtumat();
			System.out.println("\nC-vaihe:");
			yritaCTapahtumat();
			System.out.println(kello.getAika());
			Platform.runLater(() ->{
					kontrolleri.updateView(palvelupisteet);
					kontrolleri.updateResults(resultSet());
					kontrolleri.updateTime(kello.getAika());
				});
		}
		tulokset();
		
	}
	
	
	/**
	 * Suorittaa simuloinnin B vaiheen tapahtumat.
	 */
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	/**
	 * Suorittaa simuloinnin c vaiheen tapahtumat.
	 */
	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	/**
	 * palauttaa seuraavan tapahtuman ajan.
	 * @return seuraavan tapahtuman aika.
	 */
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	/**
	 * true niin kauan kun simulointi on käynnissä.
	 * @return true/false
	 */
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	
	/**
	 * pysäyttää säikeen viiveen ajaksi.
	 */
	private void viive() { // UUSI
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSimulointiAika() {
		return this.simulointiaika;
	}
	
	protected abstract double[] resultSet();
	
	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}