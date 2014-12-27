package assembler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import printClasses.IndAndBytes;
import binaryOPS.BinaryLogic;
import cpu.Memory;
import cpu.Register;

public class AssemblerMips{
	
	public static String nomeArquivo = new String();
	public static String arquivoTemporario = new String("S:/temporario.txt");
	public static File selectedFile;
	
	public static ArrayList<String> codOriginal = new ArrayList<String>();
	public static ArrayList<String> binaryCode = new ArrayList<String>();
	public static ArrayList<String> onlyInstructions = new ArrayList<String>();
	public static Map<String, Integer> labelsFound = new HashMap<String, Integer>();
	public static ArrayList<IndAndBytes> printMemory = new ArrayList<IndAndBytes>();
	
	public static InstructionsRegisters instReg = new InstructionsRegisters();
	
	public static String op_regex = new String("\\w+ (?! *:)");
	public static String reg_regex = new String("[$]\\w+");
	public static String label_regex = new String("\\w+ *:");
	public static String immediate_regex = new String("[,] *[-]* *([0-9]+)");
	public static String immediateLabel_regex = new String("[,] *(\\w+)");
	public static String shift_regex = new String("[,] *([0-9]+)");
	public static String labelJump_regex = new String("\\w+ *$");
	public static String immediateJump_regex = new String(" +[0-9]+ *$");
	public static String findBits_regex = new String("[0-1]+[^ *]");
		
	public static void converteInstrucao(){

		String operation = new String();
		String binary = new String();

		
		for(int i = 0 ; i < codOriginal.size(); i++){
			
			Pattern operadores = Pattern.compile(op_regex);
			Matcher buscaOperadores = operadores.matcher(codOriginal.get(i));
			
			Pattern registradores = Pattern.compile(reg_regex);
			Matcher buscaRegistradores = registradores.matcher(codOriginal.get(i));
			
			Pattern imediato = Pattern.compile(immediate_regex);
			Matcher buscaImediato = imediato.matcher(codOriginal.get(i));
			
			Pattern shift = Pattern.compile(shift_regex);
			Matcher buscaShift = shift.matcher(codOriginal.get(i));
			
			Pattern imedLabel = Pattern.compile(immediateLabel_regex);
			Matcher buscaImedLabel = imedLabel.matcher(codOriginal.get(i));
			
			Pattern label = Pattern.compile(label_regex);
			Matcher buscaLabel = label.matcher(codOriginal.get(i));
			
			Pattern imediatoJump = Pattern.compile(immediateJump_regex);
			Matcher buscaImedJump = imediatoJump.matcher(codOriginal.get(i));
			
			Pattern labelJump = Pattern.compile(labelJump_regex);
			Matcher buscaLabelJump = labelJump.matcher(codOriginal.get(i));
			
			if(buscaLabel.find()){
				
				String novaLabel = buscaLabel.group();
				String labelCorrect = new String();
				
				for(int k = 0; k < novaLabel.length(); k++)
					if(novaLabel.charAt(k) != ' ' & novaLabel.charAt(k) != ':')
						labelCorrect += novaLabel.charAt(k);
				
				labelsFound.put(labelCorrect, binaryCode.size());
			}
			
			if(buscaOperadores.find()){
				
				operation = buscaOperadores.group().substring(0, (buscaOperadores.group().length() - 1));
				
				System.out.println(operation);
				
				onlyInstructions.add(codOriginal.get(i));
				
				if(instReg.instructionsTypeR.get(operation) != null){
							
					if(!operation.equals("sll") & !operation.equals("srl") & !operation.equals("jr")){
						
						operation = instReg.instructionsTypeR.get(operation);
						
						String[] reg = new String[3];
						int counter = 0;
						
						while(buscaRegistradores.find()){
							
							reg[counter] = buscaRegistradores.group();
							
							if(instReg.registers.get(reg[counter]) != null)
								reg[counter] = instReg.registers.get(reg[counter]);
							
							counter++;
						}
						
						binary = "000000" + reg[1] + reg[2] + reg[0] + "00000" + operation;
						binaryCode.add(binary);
					}
					else if(operation.equals("sll") | operation.equals("srl")){
						
						operation = instReg.instructionsTypeR.get(operation);
						
						String shamt = new String();
						String shamtCorrect = new String();
						String[] reg = new String[2];
						int shamtValue;
						int counter = 0;
						
						while(buscaRegistradores.find()){
							
							reg[counter] = buscaRegistradores.group();
							
							if(instReg.registers.get(reg[counter]) != null)
								reg[counter] = instReg.registers.get(reg[counter]);
							
							counter++;
						}
						
						if(buscaShift.find()){
							
							shamt = buscaShift.group();
							
							//System.out.println(shamt);
							
							for(int k = 0; k < shamt.length(); k++)
								if(shamt.charAt(k) != ' ' & shamt.charAt(k) != ',')
									shamtCorrect += shamt.charAt(k);
							
						
							shamtValue = Integer.parseInt(shamtCorrect);
						
							binary = "000000" + reg[1] + "00000" + reg[0] + BinaryLogic.integerToBinary(shamtValue, 5) + operation;
						
							binaryCode.add(binary);
						}
						
					}
					else if(operation.equals("jr")){
						
						operation = instReg.instructionsTypeR.get(operation);
						String reg = new String();
						
						
						if(buscaRegistradores.find()){
							
							reg = buscaRegistradores.group();
							
							if(reg.equals("$ra")){
								
								binary = "000000" + instReg.registers.get(reg) + "00000" + "00000" + "00000" + operation;
								
								binaryCode.add(binary);
							}
						}
					}
				}
				else if(instReg.instructionsTypeI.get(operation) != null){
					
					if(!operation.equals("beq") & !operation.equals("bne") & !operation.equals("lui")){
						
						operation = instReg.instructionsTypeI.get(operation);
						
						String[] reg = new String[2];
						String immediate = new String();
						String immediateCorrect = new String();
						int immediateValue;
						int counter = 0;
						
						while(buscaRegistradores.find()){	
							
							reg[counter] = buscaRegistradores.group();
							
							if(instReg.registers.get(reg[counter]) != null)
								reg[counter] = instReg.registers.get(reg[counter]);
							
							counter++;
						}
						
						if(buscaImediato.find()){
							
							immediate = buscaImediato.group();
							
							for(int k = 0; k < immediate.length(); k++){
								
								if(immediate.charAt(k) != ' ' & immediate.charAt(k) != ',')
									immediateCorrect += immediate.charAt(k);
							}
							
							immediateValue = Integer.parseInt(immediateCorrect);
							System.out.println(immediateValue);
							
							binary = operation + reg[0] + reg[1] + BinaryLogic.integerToBinary(immediateValue, 16);
							
							binaryCode.add(binary);
						}
						
					}
					else if(operation.equals("lui")){
						
						operation = instReg.instructionsTypeI.get(operation);
						
						String reg = new String();
						String immediate = new String();
						String immediateCorrect = new String();
						int immediateValue;
						
						if(buscaRegistradores.find()){
							
							reg = buscaRegistradores.group();
							
							if(instReg.registers.get(reg) != null)
								reg = instReg.registers.get(reg);
						}
						if(buscaImediato.find()){
							
							immediate = buscaImediato.group();
							
							for(int k = 0; k < immediate.length(); k++){
								
								if(immediate.charAt(k) != ' ' & immediate.charAt(k) != ',')
									immediateCorrect += immediate.charAt(k);
							}
							
							immediateValue = Integer.parseInt(immediateCorrect);
							
							binary = operation + "00000" + reg + BinaryLogic.integerToBinary(immediateValue, 16);
							
							binaryCode.add(binary);
						}
						
					}
					else if(operation.equals("beq") | operation.equals("bne")){
						
						operation = instReg.instructionsTypeI.get(operation);
						
						String[] reg = new String[2];
						String immediate = new String();
						String immediateCorrect = new String();
						int immediateValue;
						int counter = 0;
						
						while(buscaRegistradores.find()){	
							
							reg[counter] = buscaRegistradores.group();
							
							if(instReg.registers.get(reg[counter]) != null)
								reg[counter] = instReg.registers.get(reg[counter]);
							
							counter++;
						}
						
						if(buscaImediato.find()){
							
							immediate = buscaImediato.group();
							
							for(int k = 0; k < immediate.length(); k++){
								
								if(immediate.charAt(k) != ' ' & immediate.charAt(k) != ',')
									immediateCorrect += immediate.charAt(k);
							}
							
							immediateValue = Integer.parseInt(immediateCorrect);
							
							binary = operation + reg[0] + reg[1] + BinaryLogic.integerToBinary(immediateValue, 16);
							
							binaryCode.add(binary);
						}
						else if(buscaImedLabel.find()){
							
							immediate = buscaImedLabel.group();
							
							for(int k = 0; k < immediate.length(); k++)
								if(immediate.charAt(k) != ' ' & immediate.charAt(k) != ',')
									immediateCorrect += immediate.charAt(k);
							
							
							binary = operation + reg[0] + reg[1] + ' ' + immediateCorrect;
							
							binaryCode.add(binary);
						}
					}
				}
				else if(instReg.instructionsTypeJ.get(operation) != null){
					
					operation = instReg.instructionsTypeJ.get(operation);
					
					String immediate = new String();
					String immediateCorrect = new String();
					int immediateValue;
					
					if(buscaImedJump.find()){
						
						immediate = buscaImedJump.group();
						
						for(int k = 0; k < immediate.length(); k++)
							if(immediate.charAt(k) != ' ')
								immediateCorrect += immediate.charAt(k);	
						
						immediateValue = Integer.parseInt(immediateCorrect); 
						
						binary = operation + BinaryLogic.integerToBinary(immediateValue, 26);
						
						binaryCode.add(binary);
					}
					else if(buscaLabelJump.find()){
						
						immediate = buscaLabelJump.group();
						
						for(int k = 0; k < immediate.length(); k++)
							if(immediate.charAt(k) != ' ')
								immediateCorrect += immediate.charAt(k);
						
						binary = operation + ' ' + immediateCorrect;
						
						binaryCode.add(binary);
					}
				}
				
			}
			
		}
		
		converteLabels();
	}
	
	public static void converteLabels(){
		
		String labelFound = new String();
		String bitsInstruction = new String();
		int jumpSize;
		
		for(int i = 0; i < binaryCode.size(); i++){
			
			Pattern label = Pattern.compile(labelJump_regex);
			Matcher buscaLabel = label.matcher(binaryCode.get(i));
			
			Pattern bits = Pattern.compile(findBits_regex);
			Matcher buscaBits = bits.matcher(binaryCode.get(i));
			
			if(buscaLabel.find()){
				
				labelFound = buscaLabel.group();
				
				if(labelsFound.containsKey(labelFound)){
					
					jumpSize = labelsFound.get(labelFound);
					
					if(buscaBits.find()){
						
						bitsInstruction = buscaBits.group();
						
						if(bitsInstruction.length() == 16){
							
							jumpSize = jumpSize - i;
							
							bitsInstruction += BinaryLogic.integerToBinary(jumpSize, 16);
							
							binaryCode.set(i, bitsInstruction);
						}
						else if(bitsInstruction.length() == 6){
							
							bitsInstruction += BinaryLogic.integerToBinary(jumpSize, 26);
							
							binaryCode.set(i, bitsInstruction);
						}
					}
				}
			}
		}
		montar(true);
	}
	
	
	public static String[] montar(boolean a){
		
		Memory.clearMemory();
		String [] printableMemory = new String[1024];
		int j = 0;
		
		for(int i = 0; i < binaryCode.size(); i++){
			
			Memory.bytes[i * 4] = binaryCode.get(i).substring(0, 8);
			Memory.bytes[i * 4 + 1] = binaryCode.get(i).substring(8, 16);
			Memory.bytes[i * 4 + 2] = binaryCode.get(i).substring(16, 24);
			Memory.bytes[i * 4 + 3] = binaryCode.get(i).substring(24, 32);
			j = i;
			printableMemory[i]= Integer.toString(i * 4)+"-"+Memory.bytes[i * 4]+"-"+Memory.bytes[i * 4 + 1]+"-"+Memory.bytes[i * 4 + 2]+"-"+Memory.bytes[i * 4 + 3];
		}
		
			for(int i=binaryCode.size();i<1024;i++){
				
				printableMemory[i]= Integer.toString(i * 4)+"-"+Memory.bytes[i * 4]+"-"+Memory.bytes[i * 4 + 1]+"-"+Memory.bytes[i * 4 + 2]+"-"+Memory.bytes[i * 4 + 3];
			}
			
			System.out.println(printMemory.size());
		
		return printableMemory;
	}
	
	public void cleanRegisters(){
		for(int i = 0;i<31;i++)
			if(i!=29)
				Register.registers[i].editContent(BinaryLogic.integerToBinary(0));
			else
				Register.registers[i].editContent(BinaryLogic.integerToBinary(2048));
		
	}
	
	public void cleanPC(){
		Register.pc.editContent(BinaryLogic.integerToBinary(0));
	}
	
	public String[] montarTeste(String textoEntrada){
		
		cleanPC();
		cleanRegisters();
		Memory.clearMemory();
		AssemblerMips.onlyInstructions.clear();
		AssemblerMips.codOriginal.clear();
		AssemblerMips.binaryCode.clear();
		AssemblerMips.printMemory.clear();
		
		String[] lines = textoEntrada.split(System.getProperty("line.separator"));
		
		for (int i = 0; i < lines.length; i++) {
			AssemblerMips.codOriginal.add(lines[i]);
		}
		
		converteInstrucao();
		
		String[] insAndBinary = new String[AssemblerMips.binaryCode.size()];
		
		for (int i = 0; i <AssemblerMips.binaryCode.size(); i++) {
			insAndBinary[i]=AssemblerMips.onlyInstructions.get(i)+"?"+AssemblerMips.binaryCode.get(i);
			System.out.println(insAndBinary[i]);
		}
		
		return insAndBinary;
	}
}