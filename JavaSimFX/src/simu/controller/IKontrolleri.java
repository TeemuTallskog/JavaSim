package simu.controller;

import simu.model.Asiakas;
import simu.model.Palvelupiste;
import simu.view.ISimulaattorinUI;

public interface IKontrolleri {
	
		// Rajapinta, joka tarjotaan  käyttöliittymälle:
	
		public void kaynnistaSimulointi();
		public void nopeuta();
		public void hidasta();
		
		
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		public void visualisoiAsiakas();
		public void updateView(Palvelupiste[] pp);
		public void updateResults(double[] results);
		public ISimulaattorinUI getUI();
		public void readyCustomer(Asiakas a);
		public void updateTime(double t);
		public void newCustomer();

}
