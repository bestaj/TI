package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.Coder;
import data.Decoder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Trida {@code MainController} se stara o veskerou
 * funkcionalitu uzivatelskeho rozhrani
 *  
 * @author Jiri Besta, Jan Matusik
 * @version 1.00
 */
public class MainController implements Initializable {

	@FXML
	private TextField kodVstupTF, dekodVstupTF;
	@FXML
	private TextArea dekodErrorTA;
	@FXML
	private Label kodVstupLbl, kodVystupLbl, dekodVystupLbl;
	@FXML
	private Button kodujBtn, dekodujBtn;
	@FXML
	private Canvas platno;
	
	private GraphicsContext gc;
	private Coder cod;
	private Decoder decod;
	
	/** Inicializuje gui */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cod = new Coder();
		decod = new Decoder();
		kodujBtn.setDisable(true);
		dekodujBtn.setDisable(true);
		kodVstupLbl.setText("Zadejte 12 èíslic 0-9");
		kodVstupTF.setOnKeyReleased(e -> {
			// Testuje spravnou delku vstupniho retezce pro kodovani
			if (kodVstupTF.getLength() == 12) {
				if (jeSpravnaHodnota()) {
					kodujBtn.setDisable(false);
					kodVstupLbl.setText("");
				}
			}
			else {
				kodujBtn.setDisable(true);
				kodVstupLbl.setText("Zadejte 12 èíslic 0-9");
			}
		});
		dekodVstupTF.setOnKeyReleased(e -> {
			if (dekodVstupTF.getLength() == 84) {
				dekodujBtn.setDisable(false);
			}
			else {
				dekodujBtn.setDisable(true);
			}
		});
	}
	
	/**
	 * Testuje, zda je korektni vstup pro kodovani
	 * Otestuje, zda jsou vsechny zadane znaky cislice
	 * 
	 * @return true, pokud je vstupni retezec akceptovan, false pokud neni
	 */
	private boolean jeSpravnaHodnota() {
		String text = kodVstupTF.getText();
		
		for (int i = 0; i < text.length(); i++) {
			if (!(Character.isDigit(text.charAt(i)))) {
				kodVstupLbl.setText("Chybný vstup: zadány neplatné znaky!");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Provede kodovani vstupniho retezce do caroveho kodu EAN-13.
	 * Spocte kontrolni cislici a vykresli carovy kod 
	 * a prevede carovy kod do binarni podoby, ktery pak umisti na vstup pro dekodovani
	 */
	@FXML
	private void koduj() {
		cod.setData(kodVstupTF.getText());
		kodVystupLbl.setText(cod.toString());
		zobrazKod(cod.getBinVystup());
		dekodVstupTF.clear();
		dekodVstupTF.setText(cod.getBinVystup());
		dekodujBtn.setDisable(false);
	}

	/**
	 * Metoda se stara o vykresleni caroveho kodu EAN-13
	 * @param binCod, retezec binarnich cislic, ktery reprezentuje carovy kod, 
	 * 1 - cerna cara, 0 - bila mezera
	 */
	private void zobrazKod(String binCod) {
		gc = platno.getGraphicsContext2D();
		String okrajoveZnaky = "101";
		String deliciZnak = "01010";
		String pole1 = binCod.substring(0,42);
		String pole2 = binCod.substring(42);
	
		int x = 20;
		int y1 = 10;
		int y2 = 90;
		
		gc.setLineWidth(3.0);
		
		for (int i = 0; i < okrajoveZnaky.length(); i++) {
			if (okrajoveZnaky.charAt(i) == '1') {
				gc.strokeLine(x, y1, x, y2 + 10);
			}
			x += 3;
		}
		for (int j = 0; j < pole1.length(); j++) {
			if (pole1.charAt(j) == '1') {
				gc.strokeLine(x, y1, x, y2);
			}
			x += 3;
		}
		for (int k = 0; k < deliciZnak.length(); k++) {
			if (deliciZnak.charAt(k) == '1') {
				gc.strokeLine(x, y1, x, y2 + 10);
			}
			x += 3;
		}
		for (int l = 0; l < pole2.length(); l++) {
			if (pole2.charAt(l) == '1') {
				gc.strokeLine(x, y1, x, y2);
			}
			x += 3;
		}
		for (int m = 0; m < okrajoveZnaky.length(); m++) {
			if (okrajoveZnaky.charAt(m) == '1') {
				gc.strokeLine(x, y1, x, y2 + 10);
			}
			x += 3;
		}
	}
	
	/**
	 * Provede dekodovani caroveho kodu
	 * a detekuje pripadne chyby.
	 * Detekovane chyby vypise.
	 */
	@FXML
	private void dekoduj() {
		decod.setData(dekodVstupTF.getText());
		dekodVystupLbl.setText("");
		if (decod.chybnaKontrolniCislice()) {
			dekodErrorTA.setVisible(true);
			dekodErrorTA.setText("Neplatná kontrolní èíslice na vstupu.\nPoslední èíslice musí být " + decod.getSpravnaKontrolniCislice() + ".");
		}
		else {
			dekodVystupLbl.setText(decod.toString());
			if (decod.chybneDekodovani()) {
				dekodErrorTA.setVisible(true);
				dekodErrorTA.setText(decod.chybovyVypis());
			}
			else {
				dekodErrorTA.setVisible(false);
			}
		}
	}
}
