package simu.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import simu.MainApp;
import simu.model.DatabaseAccessObject;

public class RootLayoutController {

	
	MainApp mainApp;
	
	@FXML
	private void openDatabaseView() {
		mainApp.openDatabaseView();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void emptyDB() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to empty your database?", ButtonType.YES,ButtonType.CANCEL);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.YES) new DatabaseAccessObject().truncateTables();
	}
	
}
