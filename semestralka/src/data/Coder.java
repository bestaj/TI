package data;

/**
 * Trida {@code Coder} reprezentuje koder, 
 * ktery se stara o samotne zakodovani do caroveho kodu
 * a spocteni kontrolni cislice.
 *  
 * @author Jiri Besta, Jan Matusik
 * @version 1.00
 */
public class Coder extends Aean13 {
	// Pole prvnich 12 znaku v kodu
	private byte[] vstup = new byte[12];
	private byte kontrolniCislice;
	// Vsech 13 cislic kodu
	private String vystup;
	// Kod v binarni podobe 
	private String binVystup;
	
	/**
	 * Provede zakodovani do caroveho kodu EAN-13
	 * @param vstup, 12 vstupnich cislic
	 */
	@Override
	public void setData(String vstup) {
		vystup = "";
		binVystup = "";
		for (int i = 0; i < vstup.length(); i++) {
			this.vstup[i] = Byte.parseByte(String.valueOf(vstup.charAt(i)));
		}
		
		
		for (int j = 0; j < vstup.length(); j++) {
			vystup += this.vstup[j] + " ";
		}
		kontrolniCislice = kontrolniCislice(this.vstup); 
		vystup += kontrolniCislice;
		
		binarniVystup();
	}
	
	/**
	 * Vrati carovy kod v binarni podobe
	 * @return binarni kod
	 */
	public String getBinVystup() {
		return this.binVystup;
	}
	
	/**
	 * Zjisti binarni podobu kodu
	 */
	private void binarniVystup() {
		String sady = kombinaceSad.get((int)vstup[0]);

		for (int i = 1; i < vstup.length; i++) {
			if (i < 7) {
				if (sady.charAt(i-1) == 'a') {
					binVystup += sadaA.get((int)vstup[i]);
				}
				else {
					binVystup += sadaB.get((int)vstup[i]);
				}
			}
			else {
				binVystup += sadaC.get((int)vstup[i]);
			}
		}
		binVystup += sadaC.get((int)kontrolniCislice);
	}
	
	/**
	 * Vrati vysledny kod (decimalne)
	 */
	@Override
	public String toString() {
		return vystup;
	}
}
