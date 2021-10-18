import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{ // Simulaattorin käynnistyspääohjelma
	
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
	

}
