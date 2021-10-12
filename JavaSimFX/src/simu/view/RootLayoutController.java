package simu.view;

import javafx.fxml.FXML;
import simu.MainApp;

public class RootLayoutController {

	
	MainApp mainApp;
	
	@FXML
	private void openDatabaseView() {
		mainApp.openDatabaseView();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
}
