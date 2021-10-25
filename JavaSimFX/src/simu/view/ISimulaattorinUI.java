package simu.view;

import simu.model.Asiakas;
import simu.model.DistributionTyyppi;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	
	// Kontrolleri tarvitsee  
	/**
	 * 
	 * @return palauttaa viittauksen SimulaattoriOverViewControlleriin.
	 */
	public ISimulaattorinUI getReference();
	/**
	 * 
	 * @return palauttaa käyttäjän valitseman jakauman
	 */
	public DistributionTyyppi getDistribution();
	/**
	 * 
	 * @return palauttaa käyttän jakaumaan valitseman argumentit arrayssa. 0 = 1st arg, 1 = 2nd arg.
	 */
	public int[] getDistributionArguments();
	
	/**
	 * Piirtää listan kahvilan jonoon
	 * @param asiakas lista
	 */
	public void setKahvilaJono(Asiakas[] list);
	/**
	 * Piirtää listan kahvilan tiskistä.
	 * @param asiakas lista
	 */
	public void setKahvila(Asiakas[] list);
	/**
	 * Piirtää listan Hyllyillä olevista asiakkaista.
	 * @param asiakas lista
	 */
	public void setHyllyt(Asiakas[] list);
	/**
	 * Piirtää listan Lihatiskille
	 * @param asiakas lista
	 */
	public void setLihaTiski(Asiakas[] list);
	/**
	 * Piirtää listan lihatiskin jonoon
	 * @param asiakas lista
	 */
	public void setLihaJono(Asiakas[] list);
	/**
	 * Piirtää listan itsepalvelutiskille
	 * @param asiakas lista
	 */
	public void setIpKassa(Asiakas[] list);
	/**
	 * Piirtää listan itsepalvelukassa jonoon
	 * @param asiakas lista
	 */
	public void setIpKassaJono(Asiakas[] list);
	/**
	 * Piirtää listan kassalle
	 * @param asiakas lista
	 */
	public void setKassa(Asiakas[] list);
	/**
	 * Piirtää listan kassan jonoon
	 * @param asiakas lista
	 */
	public void setKassaJono(Asiakas[] list);
	/**
	 * Asettaa jakauma tyypin
	 * @param Jakauma tyyppi
	 */
	public void setDistribution(DistributionTyyppi distribution);
	/**
	 * piirtää käyttöliittymään keskimääräisen läpimenoajan
	 * @param keskimääräinen läpimenoaika
	 */
	public void setKeskLapiMeno(double time);
	/**
	 * piirtää käyttöliittymään keskimääräisen palveluajan
	 * @param keskimääräinen palveluaika
	 */
	public void setKeskPalvAika(double time);
	/**
	 * vie valmiin asiakkaan valmiiden asiakkaiden listaan.
	 * @param asiakas
	 */
	public void addReadyCustomer(Asiakas a);
	/**
	 * Päivittää käyttöliittymässä näkyvän system timen
	 * @param System time
	 */
	public void updateTime(double t);
	/**
	 * Päivittää laskuria kaikista saapuneista asiakkaista.
	 */
	public void newCustomer();
	/**
	 * Piirtää listan kahvilan jonoon
	 * @param asiakas lista
	 */
	public void setDistributionArguments(int[] args);
	

}
