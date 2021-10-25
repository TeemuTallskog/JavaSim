package simu.controller;

import simu.model.Asiakas;
import simu.model.Palvelupiste;
import simu.view.ISimulaattorinUI;

public interface IKontrolleri {
		/**
		 * Käynnistää simulaattorin
		 */
		public void kaynnistaSimulointi();
		/**
		 * vähentää simulaattorin viivettä
		 */
		public void nopeuta();
		/**
		 * suurentaa simulaattorin viivettä.
		 */
		public void hidasta();
		
		/**
		 * Päivittää käyttöliittymän palvelupisteiden tiedoilla.
		 * @param Lista palvelupisteistä
		 */
		public void updateView(Palvelupiste[] pp);
		/**
		 * päivittää keskimääräiset palvelu ja läpimeno ajat käyttöliittymässä.
		 * @param [0] = kesk. palvelu aika [1]= kesk. läpimenoaika
		 */
		public void updateResults(double[] results);
		/**
		 * 
		 * @return palauttaa viitteen käyttöliittymään.
		 */
		public ISimulaattorinUI getUI();
		/**
		 * Vie käyttöliittymälle valmiit asiakkaat.
		 * @param Asiakas
		 */
		public void readyCustomer(Asiakas a);
		/**
		 * Vie käyttöjärjestelmälle simulaattorin kellon ajan.
		 * @param System time
		 */
		public void updateTime(double t);
		/**
		 * Antaa käyttöliittymälle tiedon uudesta saapuneesta asiakkaasta.
		 */
		public void newCustomer();

}
