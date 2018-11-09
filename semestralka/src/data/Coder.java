package data;

import java.util.HashMap;


public class Coder {
	
	private static final HashMap<Integer, String> kombinaceSad;
	private static final HashMap<Integer, String> sadaA;
	private static final HashMap<Integer, String> sadaB;
	private static final HashMap<Integer, String> sadaC;
	
	static {
		kombinaceSad = new HashMap<>();
		sadaA = new HashMap<>();
		sadaB = new HashMap<>();
		sadaC = new HashMap<>();
		
		kombinaceSad.put(0, "aaaaaa");
		kombinaceSad.put(1, "aababb");
		kombinaceSad.put(2, "aabbab");
		kombinaceSad.put(3, "aabbba");
		kombinaceSad.put(4, "abaabb");
		kombinaceSad.put(5, "abbaab");
		kombinaceSad.put(6, "abbbaa");
		kombinaceSad.put(7, "ababab");
		kombinaceSad.put(8, "ababba");
		kombinaceSad.put(9, "abbaba");
		
		sadaA.put(0,"0001101");
		sadaA.put(1,"0011001");
		sadaA.put(2,"0010011");
		sadaA.put(3,"0111101");
		sadaA.put(4,"0100011");
		sadaA.put(5,"0110001");
		sadaA.put(6,"0101111");
		sadaA.put(7,"0111011");
		sadaA.put(8,"0110111");
		sadaA.put(9,"0001011");
		
		sadaB.put(0,"0100111");
		sadaB.put(1,"0110011");
		sadaB.put(2,"0011011");
		sadaB.put(3,"0100001");
		sadaB.put(4,"0011101");
		sadaB.put(5,"0111001");
		sadaB.put(6,"0000101");
		sadaB.put(7,"0010001");
		sadaB.put(8,"0001001");
		sadaB.put(9,"0010111");
		
		sadaC.put(0,"1110010");
		sadaC.put(1,"1100110");
		sadaC.put(2,"1101100");
		sadaC.put(3,"1000010");
		sadaC.put(4,"1011100");
		sadaC.put(5,"1001110");
		sadaC.put(6,"1010000");
		sadaC.put(7,"1000100");
		sadaC.put(8,"1001000");
		sadaC.put(9,"1110100");
	}
		
	private byte[] vstup = new byte[12];
	private byte kontrolniCislice;
	private String vystup = "";
	private String binVystup = "";
	
	public Coder(String vstup) {
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
