package simu.model;

public interface IDatabaseAccessObject {

	/**
	 * Palauttaa tietokannasta listan tuloksista.
	 * @return
	 */
	public Tulos[] haeTulokset();
	
	/**
	 * vie listaan tuloksen
	 * @param tulos
	 * @return true false
	 */
	public boolean vieTulos(Tulos tulos);
	
	/**
	 * tyhjentää tietokannan
	 * @return true/false
	 */
	public boolean truncateTables();
}
