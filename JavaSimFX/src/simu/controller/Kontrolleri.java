package simu.controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
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

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisÃ¤ie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		// ui.getVisualisointi().tyhjennaNaytto();
		((Thread) moottori).start();
		// ((Thread)moottori).run(); // Ei missÃ¤Ã¤n tapauksessa nÃ¤in. Miksi?
	}

	@Override
	public void hidasta() { // hidastetaan moottorisÃ¤iettÃ¤
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisÃ¤iettÃ¤
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	// Simulointitulosten vÃ¤littÃ¤mistÃ¤ kÃ¤yttÃ¶liittymÃ¤Ã¤n.
	// Koska FX-ui:n pÃ¤ivitykset tulevat moottorisÃ¤ikeestÃ¤, ne pitÃ¤Ã¤ ohjata
	// JavaFX-sÃ¤ikeeseen:

	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(() -> ui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}

	@Override
	public void updateView(Palvelupiste[] pp) {
		//Päivittää käyttöliittymää niin että asiakkaat näkyvät oikeilla
		//palvelupisteillä.
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

	@Override
	public void updateResults(double[] results) {
		ui.setKeskLapiMeno(results[1]);
		ui.setKeskPalvAika(results[0]);
	}

}
