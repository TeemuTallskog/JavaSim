package simu.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import simu.model.DatabaseAccessObject;
import simu.model.IDatabaseAccessObject;
import simu.model.Tulos;

public class DatabaseGUIController {
	
	@FXML
	private TableView<Tulos> table;
	
	@FXML
	private TableColumn<Tulos, Integer> id;
	
	@FXML
	private TableColumn<Tulos, String> distribution;
	
	@FXML
	private TableColumn<Tulos, Double> runTime;
	
	@FXML
	private TableColumn<Tulos, Integer> customersServed;
	
	@FXML
	private TableColumn<Tulos, Double> avrgPasstrough;
	
	@FXML
	private TableColumn<Tulos, Double> avrgServiceTime;
	
	@FXML
	private TableColumn<Tulos, Integer> allCustomers;
	
	@FXML
	private Button useDistributionBtn;
	
	@FXML
	private Button returnBtn;
	
	private ISimulaattorinUI ui;
	
	@FXML
	private void initialize() {
		IDatabaseAccessObject dbao = new DatabaseAccessObject();
		Tulos[] tulokset = dbao.haeTulokset();
		if(tulokset != null) setItems(tulokset);
		useDistributionBtn.setOnAction(e -> {
			pickDistribution();
		});
		returnBtn.setOnAction(e -> {
			returnToMain();
		});
		TableViewSelectionModel<Tulos> selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		distribution.setCellValueFactory(new PropertyValueFactory<>("distribution"));
		runTime.setCellValueFactory(new PropertyValueFactory<>("ajoAika"));
		customersServed.setCellValueFactory(new PropertyValueFactory<>("asiakasCount"));
		avrgPasstrough.setCellValueFactory(new PropertyValueFactory<>("keskPalvAika"));
		avrgServiceTime.setCellValueFactory(new PropertyValueFactory<>("keskLapimenoAika"));
		allCustomers.setCellValueFactory(new PropertyValueFactory<>("allCustomers"));
	}
	
	public DatabaseGUIController() {
	}
	
	/*
	 * Used to set a reference to the main application controller.
	 */
	public void setUI(ISimulaattorinUI ui) {
		this.ui = ui;
	}
	
	private void pickDistribution() {
		Tulos t = table.getSelectionModel().getSelectedItem();
		if(t == null) return;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ui.setDistribution(t.getDistribution());
			}
		});
		returnToMain();
	}
	
	private void setItems(Tulos[] tulokset) {
		for(Tulos t: tulokset) {
			table.getItems().add(t);
		}
	}
	
	private void returnToMain() {
		Stage stage = (Stage)returnBtn.getScene().getWindow();
		stage.close();
	}

}
