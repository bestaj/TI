import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public Stage window;
	
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("EAN-13");
		Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
		window.setScene(new Scene(root));
		window.show();
	}

}