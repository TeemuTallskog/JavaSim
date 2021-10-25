package simu.framework;

public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	/**
	 * Asettaa simulaattorille ajan, jonka kuluttua simulaatio loppuu.
	 * @param aika
	 */
	public void setSimulointiaika(double aika);
	/**
	 * Asettaa viiveen simulaattorille.
	 * @param aika
	 */
	public void setViive(long aika);
	/**
	 * Palauttaa simulaattorin viiveen
	 * @return viive
	 */
	public long getViive();
	/**
	 * palauttaa simuloinnin ajan.
	 * @return simulointiaika
	 */
	public double getSimulointiAika();
}
