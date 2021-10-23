import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Simulaattorin käynnistyspääohjelma
 * 
 *
 */
public class Main extends Application{ 
	
	private Stage primaryStage;
	private BorderPane root;
	
	public static void main(String args[]) {
		launch(args);	
	}

	@Override
	public void start(Stage stage) {
		this.primaryStage = stage;
		this.primaryStage.setTitle("Simulaatio");
		
		initRoot();
		initOverview();	
	}
	
	
	public void initOverview() {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("simu/view/SimulaatioOverview.fxml"));
			AnchorPane simuOverview = (AnchorPane) loader.load();
			root.setCenter(simuOverview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("overview");
			e.printStackTrace();
		}
	}
	
	private void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("simu/view/RootLayout.fxml"));
			root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("root");
			e.printStackTrace();
		}
	}
	
	public void openDatabaseView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("DatabaseView.fxml").openStream());
			Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.showAndWait();
		} catch (IOException e) {
			System.out.println("DatabaseView couldn't be opened");
			e.printStackTrace();
		}
	}
	

}
