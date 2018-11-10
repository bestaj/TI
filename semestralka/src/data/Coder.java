package data;

public class Coder extends Aean13 {
		
	private byte[] vstup = new byte[12];
	private byte kontrolniCislice;
	private String vystup;
	private String binVystup;
	
	@Override
	public void setData(String vstup) {
		vystup = "";
		binVystup = "";
		for (int i = 0; i < vstup.length(); i++) {
			this.vstup[i] = Byte.parseByte(String.valueOf(vstup.charAt(i)));
		}
		kontrolniCislice();
		
		for (int j = 0; j < vstup.length(); j++) {
			vystup += this.vstup[j] + " ";
		}
		vystup += kontrolniCislice;
		binarniVystup();
	}
	
	public String getBinVystup() {
		return this.binVystup;
	}
	
	public void kontrolniCislice() {
		int soucetSudychPozic = 0;
		int soucetLichychPozic = 0;
		
		for (int i = 0; i < vstup.length; i++) {
			if (i % 2 == 0) {
				soucetLichychPozic += vstup[i];
			}
			else {
				soucetSudychPozic += vstup[i];
			}
		}
		int pomSudych = soucetSudychPozic * 3;
		int celkovySoucet = pomSudych + soucetLichychPozic;
		int zaokrouhleno = (int)(Math.ceil(celkovySoucet / 10.0) * 10);
		this.kontrolniCislice = (byte)(zaokrouhleno - celkovySoucet);
	}
	
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
	
	@Override
	public String toString() {
		return vystup;
	}
}
