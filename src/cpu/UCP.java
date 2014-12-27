package cpu;

import binaryOPS.BinaryLogic;

public class UCP {
	
	public static String[] execute(boolean a){
		
		String [] valorRegistradores = new String[32];
		
		int index = BinaryLogic.binaryToInteger(Register.pc.getContent());
		String instruction = Memory.bytes[index] + Memory.bytes[index + 1] + Memory.bytes[index + 2] + Memory.bytes[index + 3];
		
		int indexRs = BinaryLogic.unsignedToInteger(instruction.substring(6, 11));
		int indexRt = BinaryLogic.unsignedToInteger(instruction.substring(11, 16));
		int indexRd = BinaryLogic.unsignedToInteger(instruction.substring(16, 21));
		String shamt = instruction.substring(21, 26);
		String immediate = instruction.substring(16, 32);
		
		switch (instruction.substring(0, 6)) {
			//instructions type R
			case "000000": 
				
				switch (instruction.substring(26, 32)){
					
				case "000000"://sll
					
					ULA.sll(Register.registers[indexRs], Register.registers[indexRd], shamt);
					break;
				case "000010"://srl
					
					ULA.srl(Register.registers[indexRs], Register.registers[indexRd], shamt);
					
					break;
				case "001000"://jr
					
					ULA.jr(Register.registers[indexRs]);
					
					break;
				case "100000"://add

					ULA.add(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100001"://addu
					
					ULA.addu(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100010"://sub
					
					ULA.sub(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100011"://subu
					
					ULA.subu(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100100"://and
					
					ULA.and(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100101"://or
					
					ULA.or(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100110"://xor
					
					ULA.xor(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "100111"://nor
					
					ULA.nor(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "101010"://slt
					
					ULA.slt(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				case "101011"://sltu
					
					ULA.sltu(Register.registers[indexRs], Register.registers[indexRt], Register.registers[indexRd]);
					
					break;
				default:
					break;
				}
				
				break;
			//instructions type I
			case "000100"://beq
				
				ULA.beq(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "000101"://bne
				
				ULA.bne(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001000"://addi
				
				ULA.addi(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001001"://addiu
				
				ULA.addiu(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001010"://slti
				
				ULA.slti(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001011"://sltiu
				
				ULA.sltiu(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001100"://andi
				
				ULA.andi(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001101"://ori
				
				ULA.ori(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001110"://xori
				
				ULA.xori(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "001111"://lui
				
				ULA.lui(Register.registers[indexRt], immediate);
				
				break;
			case "100000"://lb
				
				ULA.lb(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "100001"://lh
				
				ULA.lh(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "100011"://lw
				
				ULA.lw(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "101000"://sb
				
				ULA.sb(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "101001"://sh
				
				ULA.sh(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			case "101011"://sw
				
				ULA.sw(Register.registers[indexRs], Register.registers[indexRt], immediate);
				
				break;
			//instructions type J
			case "000010"://j
				
				immediate = instruction.substring(6, 32);

				ULA.j(immediate);
				
				break;
			case "000011"://jal
				
				immediate = instruction.substring(6, 32);
				
				ULA.jal(immediate);
				
				break;
			default:
				break;
		}
		
		if(a)
		for(int i=0;i<32;i++)
			valorRegistradores[i] = Register.registers[i].getHex();
		
		
		return valorRegistradores;
	}
	
	public static String[] executeAll(boolean a){
		
		String[] valorRegistradores = new String[32];
		int start = 0;
		int controle;
		int controle2;
		
		controle = BinaryLogic.unsignedToInteger(Register.pc.getContent());
		
		controle2 = BinaryLogic.unsignedToInteger(Memory.bytes[controle] + Memory.bytes[controle + 1] + 
				Memory.bytes[controle + 2] + Memory.bytes[controle + 3]);
		
		while(controle2 != 0){
			
			controle = BinaryLogic.unsignedToInteger(Register.pc.getContent());
			
			controle2 = BinaryLogic.unsignedToInteger(Memory.bytes[controle] + Memory.bytes[controle + 1] + 
					Memory.bytes[controle + 2] + Memory.bytes[controle + 3]);
			
			execute(a);
		}
		
		if(a)
			for(int i=0;i<32;i++){
				valorRegistradores[i] = Register.registers[i].getHex();
				System.out.println(valorRegistradores[i]);
			}
		
			return valorRegistradores;
	}
}
