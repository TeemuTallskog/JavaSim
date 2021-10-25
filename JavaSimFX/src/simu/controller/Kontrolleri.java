package simu.controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.framework.Kello;
import simu.model.Asiakas;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri { // UUSI

	private IMoottori moottori;
	private ISimulaattorinUI ui;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	// Moottorin ohjausta:

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		// ui.getVisualisointi().tyhjennaNaytto();
		((Thread) moottori).start();
		// ((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() + 50));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() - 50));
	}

	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata
	// JavaFX-säikeeseen:



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateView(Palvelupiste[] pp) {
		for(int i = 0; i < pp.length; i++) {
			switch (pp[i].getTyyppi()) {
				case COFFEE:
					ui.setKahvila(pp[i].getPalvellaan());
					ui.setKahvilaJono(pp[i].getJono());
					break;
				case SHOP:
					ui.setHyllyt(pp[i].getPalvellaan());
					break;
				case MEAT:
					ui.setLihaTiski(pp[i].getPalvellaan());
					ui.setLihaJono(pp[i].getJono());
					break;
				case SELFSERVICE:
					ui.setIpKassa(pp[i].getPalvellaan());
					ui.setIpKassaJono(pp[i].getJono());
					break;
				case REGISTER:
					ui.setKassa(pp[i].getPalvellaan());
					ui.setKassaJono(pp[i].getJono());
					break;
				default:
					break;
			}
		}
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateResults(double[] results) {
		ui.setKeskLapiMeno(results[1]);
		ui.setKeskPalvAika(results[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISimulaattorinUI getUI() {
		return this.ui;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readyCustomer(Asiakas a) {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.addReadyCustomer(a);
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newCustomer() {
		this.ui.newCustomer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTime(double t) {
		ui.updateTime(t);
	}
	

}
