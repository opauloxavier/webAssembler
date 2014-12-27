package assembler;

//import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

public class InstructionsRegisters {
	
	public Map<String, String> instructionsTypeR = new HashMap<String, String>();
	public Map<String, String> instructionsTypeI = new HashMap<String, String>();
	public Map<String, String> instructionsTypeJ = new HashMap<String, String>();
	public Map<String, String> registers  = new HashMap<String, String>();
	
	public InstructionsRegisters(){
		
		
		//Lista de registradores básicos
		registers.put("$zero", "00000");
		registers.put("$at", "00001");
		registers.put("$v0", "00010");
		registers.put("$v1", "00011");
		registers.put("$a0", "00100");
		registers.put("$a1", "00101");
		registers.put("$a2", "00110");
		registers.put("$a3", "00111");
		registers.put("$t0", "01000");
		registers.put("$t1", "01001");
		registers.put("$t2", "01010");
		registers.put("$t3", "01011");
		registers.put("$t4", "01100");
		registers.put("$t5", "01101");
		registers.put("$t6", "01110");
		registers.put("$t7", "01111");
		registers.put("$s0", "10000");
		registers.put("$s1", "10001");
		registers.put("$s2", "10010");
		registers.put("$s3", "10011");
		registers.put("$s4", "10100");
		registers.put("$s5", "10101");
		registers.put("$s6", "10110");
		registers.put("$s7", "10111");
		registers.put("$t8", "11000");
		registers.put("$t9", "11001");
		registers.put("$k0", "11010");
		registers.put("$k1", "11011");
		registers.put("$gp", "11100");
		registers.put("$sp", "11101");
		registers.put("$fp", "11110");
		registers.put("$ra", "11111");
		
		//Lista de operações do tipo R (opcode 31-26: 0)
		instructionsTypeR.put("sll", "000000");
		instructionsTypeR.put("srl", "000010");
		instructionsTypeR.put("jr", "001000");//
		instructionsTypeR.put("add", "100000");
		instructionsTypeR.put("addu", "100001");
		instructionsTypeR.put("sub", "100010");
		instructionsTypeR.put("subu", "100011");
		instructionsTypeR.put("and", "100100");
		instructionsTypeR.put("or", "100101");
		instructionsTypeR.put("xor", "100110");
		instructionsTypeR.put("nor", "100111");
		instructionsTypeR.put("slt", "101010");
		instructionsTypeR.put("sltu", "101011");
		
		//Lista de operações do tipo I
		instructionsTypeI.put("beq", "000100");
		instructionsTypeI.put("bne", "000101");
		instructionsTypeI.put("addi", "001000");
		instructionsTypeI.put("addiu", "001001");
		instructionsTypeI.put("slti", "001010");
		instructionsTypeI.put("sltiu", "001011");
		instructionsTypeI.put("andi", "001100");
		instructionsTypeI.put("ori", "001101");
		instructionsTypeI.put("xori", "001110");
		instructionsTypeI.put("lui", "001111");
		instructionsTypeI.put("lb", "100000");
		instructionsTypeI.put("lh", "100001");
		instructionsTypeI.put("lw", "100011");
		instructionsTypeI.put("sb", "101000");
		instructionsTypeI.put("sh", "101001");
		instructionsTypeI.put("sw", "101011");
		
		//Lista de operações do tipo J
		instructionsTypeJ.put("j", "000010");
		instructionsTypeJ.put("jal", "000011");
	}
}
