package data;

import java.util.HashMap;

/**
 * Abstraktni trida {@code Aean13} slouzici pro ulozeni 
 * jednotlivych kodovacich sad A, B, C a pro tabulku 
 * s kombinacemi pouziti sad A, B na prvnich sest znaku v kodu. 
 * 
 * @author Jiri Besta, Jan Matusik
 * @version 1.00
 */
public abstract class Aean13 {

	protected static final HashMap<Integer, String> kombinaceSad;
	protected static final HashMap<Integer, String> sadaA;
	protected static final HashMap<Integer, String> sadaB;
	protected static final HashMap<Integer, String> sadaC;
	
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
	
	public abstract void setData(String vstup);
	
	public abstract String toString();
	
}
