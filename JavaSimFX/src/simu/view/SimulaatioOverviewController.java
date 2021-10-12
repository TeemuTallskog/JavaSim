package simu.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import simu.controller.IKontrolleri;
import simu.controller.Kontrolleri;
import simu.model.Asiakas;

public class SimulaatioOverviewController implements ISimulaattorinUI {

	private IKontrolleri kontrolleri;
	
	@FXML
	private ListView<String> kahvilaJono;
	@FXML
	private ListView<String> kahvilaTiski;
	@FXML
	private ListView<String> lihaJono;
	@FXML
	private ListView<String> lihaTiski;
	@FXML
	private ListView<String> kaupanHyllyt;
	@FXML
	private ListView<String> kassaTiski;
	@FXML
	private ListView<String> kassaJono;
	@FXML
	private ListView<String> ipKassa;
	@FXML
	private ListView<String> ipJono;
	@FXML
	private TextField ajoAika;
	@FXML
	private ChoiceBox<Integer> viiveCH;
	@FXML
	private ChoiceBox<String> distributionBox;
	@FXML
	private Button startBtn;
	
	public SimulaatioOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		kontrolleri = new Kontrolleri(this);
		viiveCH.getItems().add(1);
		viiveCH.getItems().add(100);
		viiveCH.getItems().add(500);
		viiveCH.getItems().add(1000);
		distributionBox.getItems().add("Normal");
	}

	@Override
	public double getAika() {
		try {
			return Double.valueOf(ajoAika.getText());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public long getViive() {
		try {
			return viiveCH.getSelectionModel().getSelectedItem();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		// TODO Auto-generated method stub
	}

	@Override
	public IVisualisointi getVisualisointi() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@FXML
	private void startSimu() {
		kontrolleri.kaynnistaSimulointi();
		startBtn.setDisable(true);
	}

	@Override
	public void setKahvilaJono(Asiakas[] list) {
		kahvilaJono.getItems().clear();
		for(Asiakas a: list) {
			kahvilaJono.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setKahvila(Asiakas[] list) {
		kahvilaTiski.getItems().clear();
		for(Asiakas a: list) {
			kahvilaTiski.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setHyllyt(Asiakas[] list) {
		kaupanHyllyt.getItems().clear();
		for(Asiakas a: list) {
			kaupanHyllyt.getItems().add("Asiakas " + a.getId());
		}
		
	}

	@Override
	public void setLihaTiski(Asiakas[] list) {
		lihaTiski.getItems().clear();
		for(Asiakas a: list) {
			lihaTiski.getItems().add("Asiakas " + a.getId());
		}
		
	}

	@Override
	public void setLihaJono(Asiakas[] list) {
		lihaJono.getItems().clear();
		for(Asiakas a: list) {
			lihaJono.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setIpKassa(Asiakas[] list) {
		ipKassa.getItems().clear();
		for(Asiakas a: list) {
			ipKassa.getItems().add("Asiakas " + a.getId());
		}
	}
	
	@Override
	public void setIpKassaJono(Asiakas[] list) {
		ipJono.getItems().clear();
		for(Asiakas a: list) {
			ipJono.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setKassa(Asiakas[] list) {
		kassaTiski.getItems().clear();
		for(Asiakas a: list) {
			kassaTiski.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setKassaJono(Asiakas[] list) {
		kassaJono.getItems().clear();
		for(Asiakas a: list) {
			kassaJono.getItems().add("Asiakas " + a.getId());
		}
	}

	@Override
	public void setDistribution(String distribution) {
		distributionBox.setValue(distribution);
	}

	@Override
	public ISimulaattorinUI getReference() {
		return this;
	}	
}
