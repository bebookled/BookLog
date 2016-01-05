package application;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	//Loads main application stage.
	@Override
	public void start(Stage stage) {
			
			try{
		        Parent root = FXMLLoader.load(getClass().getResource("/view/BookGUI.fxml"));
		        Scene scene = new Scene(root);
		        stage.setTitle("Book Log");
		        stage.setScene(scene);
		        stage.show();		
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
	}

	public static Window getStage() {
		return null;
	}

	


	
	
}

