import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		window.setMinWidth(520);
		window.setMinHeight(650);
		window.getIcons().add(new Image(getClass().getResourceAsStream("/images/bar-code.png")));
		Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
		window.setScene(new Scene(root));
		window.show();
	}

}
