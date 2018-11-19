import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Trida {@code Main} je hlavni tridou programu.
 * Vytvori okno, jehoz obsah je nacten z fxml souboru.
 * 
 * @author Jiri Besta, Jan Matusik
 * @version 1.00
 */
public class Main extends Application {

	/** Hlavni okno aplikace */
	public Stage window;
	
	/** 
	 * Vstupni bod programu 
	 * @param args parametry prikazove radky, zde nevyuzite
	 */
	public static void main(String[] args) {
		launch(args);
		
	}

	/** 
	 * Nacte hlavni okno aplikace
	 * @param primaryStage, hlavni okno
	 */
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
