package simu.view;


import javafx.collections.ObservableList;
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
	@FXML
	private TextField firstArgument;
	@FXML
	private TextField secondArgument;
	private static int completedCount;
	
	public SimulaatioOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		kontrolleri = new Kontrolleri(this);
		distributionBox.getItems().addAll(DistributionTyyppi.values());
		distributionBox.setValue(DistributionTyyppi.Normal);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DistributionTyyppi getDistribution() {
		return distributionBox.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getAika() {
		try {
			return Double.valueOf(ajoAika.getText());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	
	/**
	 * Nostaa simuloinnin viivettä +50
	 */
	@FXML
	private void hidasta() {
		try {
			String delay = viiveTF.getText();
			int n = Integer.parseInt(delay);
			n += 50;
			viiveTF.setText("" + n);
			kontrolleri.hidasta();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * vähentää simuloinnin viivettä -50 jos viive on yli 51
	 */
	@FXML
	private void nopeuta() {
		try {
			String delay = viiveTF.getText();
			int n = Integer.parseInt(delay);
			if(n<51)throw new IllegalArgumentException();
			n -= 50;
			viiveTF.setText("" + n);
			kontrolleri.nopeuta();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * käynnistää simuloinnin
	 */
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
		try {
			int[] args = getDistributionArguments();
			if(args == null) throw new IllegalArgumentException();
			if(args[0] < 0 || args[1] < 0) throw new IllegalArgumentException();
		}catch(Exception e) {
			alert.setContentText("Insert only positive distribution arguments.");
			alert.showAndWait();
			return;
		}
		kontrolleri.kaynnistaSimulointi();
		startBtn.setDisable(true);
	}
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKahvilaJono(Asiakas[] list) {
		kahvilaJono.getItems().clear();
		kahvilaJono.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKahvila(Asiakas[] list) {
		kahvilaTiski.getItems().clear();
		kahvilaTiski.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHyllyt(Asiakas[] list) {
		kaupanHyllyt.getItems().clear();
		kaupanHyllyt.getItems().addAll(sliceArray(list));	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLihaTiski(Asiakas[] list) {
		lihaTiski.getItems().clear();
		lihaTiski.getItems().addAll(sliceArray(list));	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLihaJono(Asiakas[] list) {
		lihaJono.getItems().clear();
		lihaJono.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIpKassa(Asiakas[] list) {
		ipKassa.getItems().clear();
		ipKassa.getItems().addAll(sliceArray(list));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIpKassaJono(Asiakas[] list) {
		ipJono.getItems().clear();
		ipJono.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKassa(Asiakas[] list) {
		kassaTiski.getItems().clear();
		kassaTiski.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKassaJono(Asiakas[] list) {
		kassaJono.getItems().clear();
		kassaJono.getItems().addAll(sliceArray(list));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDistribution(DistributionTyyppi distribution) {
		distributionBox.setValue(distribution);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISimulaattorinUI getReference() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKeskLapiMeno(double time) {
		keskLapimeno.getItems().clear();
		keskLapimeno.getItems().add(String.format("%.2f", time));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKeskPalvAika(double time) {
		keskPalveluAika.getItems().clear();
		keskPalveluAika.getItems().add(String.format("%.2f", time)); 	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addReadyCustomer(Asiakas a) {
		completedCount++;
		if(valmiitList.getItems().size() > 20) valmiitList.getItems().remove(valmiitList.getItems().size() - 1);
		valmiitList.getItems().add(0, a);
		readyCustomersLabel.setText("Valmiit asiakkaat (" + completedCount + ")");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTime(double t) {
		sysTime.setText("" + String.format("%.2f", t));
	}
	/**
	 * {@inheritDoc}
	 */
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[] getDistributionArguments() {
		int[] args = new int[2];
		try {
			args[0] = Integer.parseInt(firstArgument.getText());
			args[1] = Integer.parseInt(secondArgument.getText());
		}catch(Exception e) {
			e.printStackTrace();
			args = null;
		}
		return args;
	}
	/**
	 * jos listassa on yli 20 asiakasta se poistaa vanhimmat listasta ja jättää 20 uusinta.
	 * @param asiakas lista
	 * @return palauttaa listan, jonka maksimi pituus on 20
	 */
	private Asiakas[] sliceArray(Asiakas[] list) {
		if(list.length < 21) return list;
		Asiakas[] arr = new Asiakas[20];
		System.arraycopy(list, 0, arr, 0, 20);
		return arr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDistributionArguments(int[] args) {
		firstArgument.setText("" + args[0]);
		secondArgument.setText("" + args[1]);
	}
}
