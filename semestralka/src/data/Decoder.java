package data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Trida {@code Aean13} reprezentuje dekoder,
 * ktery se stara o dekodovani vstupniho retezce v binarni podobe
 * a detekovani naskytlych chyb behem dekodovani.
 *  
 * @author Jiri Besta, Jan Matusik
 * @version 1.00
 */
public class Decoder extends Aean13 {
	// Binarni vstup pro dekodovani
	private String vstup;
	private String vystup;
	private boolean chyba;
	// Indikuje, zda je na vstupu spravna kontrolni cislice
	private boolean nespravnaKontrolniCislice;
	// Seznam chyb, ktere nastaly behem dekodovani
	private String vypisChyb;
	// Spravna kontrolni cislice
	private byte kontCislice;
	private byte[] vystupniCislice = new byte[13];
	// Pole znaku zakodovanych binarne
	private String[] binZnaky = new String[12];;
	private boolean[] chybneZnaky;
	
	/**
	 * Provede dekodovani vstupniho retezce
	 * @vstup vstupni binarni retezec 84bin. cislic (7bin = 1dec)
	 */
	@Override
	public void setData(String vstup) {
		vystup = "";
		vypisChyb = "";
		int cislo;
		chyba = false;
		nespravnaKontrolniCislice = false;
		chybneZnaky = new boolean[12];
		this.vstup = vstup;
		int subStart = 0;
		int subEnd = 7;
		// Rozdeleni vstupniho retezce po 7 znacich
		// 7 binarnich znaku = 1 decimalni cislo 
		for (int i = 0; i < binZnaky.length; i++) {
			binZnaky[i] = vstup.substring(subStart, subEnd);
			subStart += 7;
			subEnd += 7;
		}
	
		String sady = getKombinaceSad();
		if (kombinaceSad.containsValue(sady)) {
			vystupniCislice[0] = (byte)getKeyByValue(kombinaceSad, sady);
			vystup += vystupniCislice[0] + " ";
		}
		else {
			vypisChyb += "Chyba: vstupu neodpovídá žádná kombinace sad\n";
			vystup += "? ";
		}
		
		for (int j = 0; j < binZnaky.length; j++) {
			if (j < 6) {
				if (chybneZnaky[j] == true) {
					vystup += "? ";
					continue;
				}
				else {
					if (sady.charAt(j) == 'a') {
						cislo = getKeyByValue(sadaA, binZnaky[j]);
						vystup += cislo + " ";
						vystupniCislice[j+1] = (byte)cislo;
					}
					else {
						cislo = getKeyByValue(sadaB, binZnaky[j]);
						vystup += cislo + " ";
						vystupniCislice[j+1] = (byte)cislo;
					}
				}	
			}
			else {
				if (sadaC.containsValue(binZnaky[j])) {
					cislo = getKeyByValue(sadaC, binZnaky[j]);
					vystup += cislo + " ";
					vystupniCislice[j+1] = (byte)cislo;
				}
				else {
					chyba = true;
					chybneZnaky[j] = true;
					vystup += "? ";
				}
			}
		}
		// pokud vsechny znaky na vstupu jsou z jednotlivych sad, 
		// tak se jeste overi kontrolni cislice
		if (!chyba) {
			kontCislice = kontrolniCislice(Arrays.copyOf(vystupniCislice, 12));
			if (kontCislice != vystupniCislice[12]) {
				nespravnaKontrolniCislice = true;
			}
		}
	}
	
	/**
	 * Zjisti odpovidaji kombinace sad pro prvnich 6 znaku
	 * @return kombinace sad
	 */
	private String getKombinaceSad() {
		String kombinace = "";
		for (int i = 0; i < 6; i++) {
			if (sadaA.containsValue(binZnaky[i])) {
				kombinace += 'a';
				continue;
			}
			if (sadaB.containsValue(binZnaky[i])) {
				kombinace += 'b';
				continue;
			}
			chybneZnaky[i] = true;
			chyba = true;
		}
		return kombinace;
	}
	
	/** 
	 * Vrati klic tabulky podle hodnoty
	 * @param map, tabulka, ve ktere ma hledat
	 * @param value, hodnota, podle ktere ma hledat
	 * @return klid, odpovidaji hodnote
	 */
	private int getKeyByValue(HashMap<Integer, String> map, String value) {
	    for (Entry<Integer, String> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return -1;
	}
	
	/**
	 * Vraci existenci chyby pri dekodovani
	 * @return chyba, true-nastala chyba pri dekodovani, false-zadna chyba
	 */
	public boolean chybneDekodovani() {
		return chyba;
	}
	
	/** Vraci true, pokud je na vstupu chybna kontrolni cislice a naopak
	 * @return nespravnaKontrolniCislice, true je chybna, false je spravna
	 */
	public boolean chybnaKontrolniCislice() {
		return nespravnaKontrolniCislice;
	}

	/** 
	 * Vraci seznam chyb
	 * @return vypisChyb, seznam chyb, ktere se vyskytly behem dekodovani 
	 */
	public String chybovyVypis() {
		for (int i = 0; i < chybneZnaky.length; i++) {
			if (chybneZnaky[i]) {
				vypisChyb += "Chybný " + (i+2) + ". znak (" + binZnaky[i] + ")\n"; 
			}
		}
		return vypisChyb;
	}
	
	public String getSpravnaKontrolniCislice() {
		return (kontCislice + " (" + sadaC.get((int)kontCislice) + ")");
	}
	
	/**
	 * Vrati vysledek dekodovani
	 */
	@Override
	public String toString() {
		return vystup;
	}

	
}
