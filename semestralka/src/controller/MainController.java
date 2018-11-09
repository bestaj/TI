package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.Coder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private TextField kodVstupTF;
	@FXML
	private Label kodVstupLbl, kodVystupLbl;
	@FXML
	private Button kodujBtn;
	@FXML
	private Canvas platno;
	
	private GraphicsContext gc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kodujBtn.setDisable(true);
		kodVstupLbl.setText("Zadejte 12 èíslic 0-9");
			kodVstupTF.setOnKeyReleased(e -> {
				
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
		
	}
	
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
	
	@FXML
	public void koduj() {
		Coder cod = new Coder(kodVstupTF.getText());
		kodVystupLbl.setText(cod.toString());
		zobrazKod(cod.getBinVystup());
	}

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
}
