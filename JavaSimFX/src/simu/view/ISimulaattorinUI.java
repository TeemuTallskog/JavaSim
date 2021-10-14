package simu.view;

import simu.model.Asiakas;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	
	// Kontrolleri tarvitsee  
	public IVisualisointi getVisualisointi();
	public ISimulaattorinUI getReference();
	
	public void setKahvilaJono(Asiakas[] list);
	public void setKahvila(Asiakas[] list);
	public void setHyllyt(Asiakas[] list);
	public void setLihaTiski(Asiakas[] list);
	public void setLihaJono(Asiakas[] list);
	public void setIpKassa(Asiakas[] list);
	public void setIpKassaJono(Asiakas[] list);
	public void setKassa(Asiakas[] list);
	public void setKassaJono(Asiakas[] list);
	public void setDistribution(String distribution);
	public void setKeskLapiMeno(double time);
	public void setKeskPalvAika(double time);

}
