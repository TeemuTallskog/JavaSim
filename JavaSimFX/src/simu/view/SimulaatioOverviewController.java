package simu.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import simu.controller.IKontrolleri;
import simu.controller.Kontrolleri;
import simu.framework.Kello;
import simu.model.Asiakas;
import simu.model.DistributionTyyppi;

public class SimulaatioOverviewController implements ISimulaattorinUI {

	private IKontrolleri kontrolleri;
	
	@FXML
	private ListView<Asiakas> kahvilaJono;
	@FXML
	private ListView<Asiakas> kahvilaTiski;
	@FXML
	private ListView<Asiakas> lihaJono;
	@FXML
	private ListView<Asiakas> lihaTiski;
	@FXML
	private ListView<Asiakas> kaupanHyllyt;
	@FXML
	private ListView<Asiakas> kassaTiski;
	@FXML
	private ListView<Asiakas> kassaJono;
	@FXML
	private ListView<Asiakas> ipKassa;
	@FXML
	private ListView<Asiakas> ipJono;
	@FXML
	private ListView<String> keskPalveluAika;
	@FXML
	private ListView<String> keskLapimeno;
	@FXML
	private ListView<Asiakas> valmiitList;
	@FXML
	private TextField ajoAika;
	@FXML
	private ChoiceBox<Integer> viiveCH;
	@FXML
	private TextField viiveTF;
	@FXML
	private ChoiceBox<DistributionTyyppi> distributionBox;
	@FXML
	private Button startBtn;
	@FXML
	private Button hidastaBtn;
	@FXML
	private Button nopeutaBtn;
	@FXML
	private TextArea sysTime;
	@FXML
	private TextArea customerCount;
	@FXML
	private Label readyCustomersLabel;
	@FXML
	private Button resetBtn;
	
	public SimulaatioOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		kontrolleri = new Kontrolleri(this);
		distributionBox.getItems().addAll(DistributionTyyppi.values());
		distributionBox.setValue(DistributionTyyppi.Normal);
	}
	
	@Override
	public DistributionTyyppi getDistribution() {
		return distributionBox.getValue();
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
			String input = viiveTF.getText();
			long n = Long.parseLong(input);
			return n;
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
	private void hidasta() {
		kontrolleri.hidasta();
		try {
			String delay = viiveTF.getText();
			int n = Integer.parseInt(delay);
			n += 50;
			viiveTF.setText("" + n);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void nopeuta() {
		kontrolleri.nopeuta();
		try {
			String delay = viiveTF.getText();
			int n = Integer.parseInt(delay);
			if(n<51)throw new IllegalArgumentException();
			n -= 50;
			viiveTF.setText("" + n);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void startSimu() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		try {
			int i = Integer.parseInt(ajoAika.getText());
			if(i < 0)throw new IllegalArgumentException();
		}catch(Exception e) {
			alert.setContentText("Insert ajoaika.");
			alert.showAndWait();
			return;
		}
		try {
			int i = Integer.parseInt(viiveTF.getText());
			if(i < 0) throw new IllegalArgumentException();
		}catch(Exception e) {
			alert.setContentText("Insert viive.");
			alert.showAndWait();
			return;
		}
		kontrolleri.kaynnistaSimulointi();
		startBtn.setDisable(true);
	}
	


	@Override
	public void setKahvilaJono(Asiakas[] list) {
		kahvilaJono.getItems().clear();
		kahvilaJono.getItems().addAll(list);
	}

	@Override
	public void setKahvila(Asiakas[] list) {
		kahvilaTiski.getItems().clear();
		kahvilaTiski.getItems().addAll(list);
	}

	@Override
	public void setHyllyt(Asiakas[] list) {
		kaupanHyllyt.getItems().clear();
		kaupanHyllyt.getItems().addAll(list);	
	}

	@Override
	public void setLihaTiski(Asiakas[] list) {
		lihaTiski.getItems().clear();
		lihaTiski.getItems().addAll(list);	
	}

	@Override
	public void setLihaJono(Asiakas[] list) {
		lihaJono.getItems().clear();
		lihaJono.getItems().addAll(list);
	}

	@Override
	public void setIpKassa(Asiakas[] list) {
		ipKassa.getItems().clear();
		ipKassa.getItems().addAll(list);
	}
	
	@Override
	public void setIpKassaJono(Asiakas[] list) {
		ipJono.getItems().clear();
		ipJono.getItems().addAll(list);
	}

	@Override
	public void setKassa(Asiakas[] list) {
		kassaTiski.getItems().clear();
		kassaTiski.getItems().addAll(list);
	}

	@Override
	public void setKassaJono(Asiakas[] list) {
		kassaJono.getItems().clear();
		kassaJono.getItems().addAll(list);
	}

	@Override
	public void setDistribution(DistributionTyyppi distribution) {
		distributionBox.setValue(distribution);
	}

	@Override
	public ISimulaattorinUI getReference() {
		return this;
	}

	@Override
	public void setKeskLapiMeno(double time) {
		keskLapimeno.getItems().clear();
		keskLapimeno.getItems().add(String.format("%.2f", time));
	}

	@Override
	public void setKeskPalvAika(double time) {
		keskPalveluAika.getItems().clear();
		keskPalveluAika.getItems().add(String.format("%.2f", time)); 	
	}

	@Override
	public void addReadyCustomer(Asiakas a) {
		valmiitList.getItems().add(a);
		readyCustomersLabel.setText("Valmiit asiakkaat (" + valmiitList.getItems().size() + ")");
	}

	@Override
	public void updateTime(double t) {
		sysTime.setText("" + String.format("%.2f", t));
	}
	
	@Override
	public void newCustomer() {
		try {
			int i  = Integer.parseInt(customerCount.getText());
			i++;
			customerCount.setText("" + i);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
