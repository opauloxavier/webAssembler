package cpu;

import binaryOPS.*;

public class ULA {
	
	public static void add(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryArithmetic.sum(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void sub(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryArithmetic.subtract(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void or(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryLogic.or(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void and(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryLogic.and(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void nor(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryLogic.nor(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void xor(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryLogic.xor(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void sll(Register rs, Register rd, String shamt){
		
		int shamtValue = BinaryLogic.unsignedToInteger(shamt);
		String base = new String();
		
		base = rs.getContent().substring(shamtValue, (rs.getContent().length()));
		 
		while(shamtValue > 0){
			 
			base = base + '0'; 
			shamtValue--;
		}
		
		 rd.editContent(base);
		 incrementPC(4);
	}
	
	public static void srl(Register rs, Register rd, String shamt){
		
		int shamtValue = BinaryLogic.unsignedToInteger(shamt);
		String base = new String();
		
		base = rs.getContent().substring(0, (rs.getContent().length() - shamtValue));
		
		while(shamtValue > 0){
			 
			base = '0' + base; 
			shamtValue--;
		}
		
		 rd.editContent(base);
		 incrementPC(4);
	}
	
	public static void addu(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryArithmetic.sumUnsigned(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void subu(Register rs, Register rt, Register rd){
		
		rd.editContent(BinaryArithmetic.subtractUnsigned(rs.getContent(), rt.getContent()));
		incrementPC(4);
	}
	
	public static void slt(Register rs, Register rt, Register rd){
		
		if(BinaryLogic.lessThan(rs.getContent(), rt.getContent()))
			rd.editContent(BinaryLogic.integerToBinary(1));
		else
			rd.editContent(BinaryLogic.integerToBinary(0));
		
		incrementPC(4);
	}
	
	public static void sltu(Register rs, Register rt, Register rd){
		
		if(BinaryLogic.lessThanUnsigned(rs.getContent(), rt.getContent()))
			rd.editContent(BinaryLogic.integerToBinary(1));
		else
			rd.editContent(BinaryLogic.integerToBinary(0));
		
		incrementPC(4);
	}
	
	public static void jr(Register rs){
		
		Register.pc.editContent(rs.getContent());
	}
	
	public static void addi(Register rs, Register rt, String immediate){
		
		rs.editContent(BinaryArithmetic.sum(rt.getContent(), immediate));
		incrementPC(4);
	}
	
	public static void addiu(Register rs, Register rt, String immediate){
		
		rs.editContent(BinaryArithmetic.sumUnsigned(rt.getContent(), immediate));
		incrementPC(4);
	}
	
	public static void slti(Register rs, Register rt, String immediate){
		
		if(BinaryLogic.lessThan(rt.getContent(), immediate))
			rs.editContent(BinaryLogic.integerToBinary(1));
		else
			rs.editContent(BinaryLogic.integerToBinary(0));
		
		incrementPC(4);
	}
	
	public static void sltiu(Register rs, Register rt, String immediate){
		
		if(BinaryLogic.lessThanUnsigned(rt.getContent(), immediate))
			rs.editContent(BinaryLogic.integerToBinary(1));
		else
			rs.editContent(BinaryLogic.integerToBinary(0));
		
		incrementPC(4);
	}
	
	public static void ori(Register rs, Register rt, String immediate){
		
		rs.editContent(BinaryLogic.or(rt.getContent(), immediate));
		
		incrementPC(4);
	}
	
	public static void andi(Register rs, Register rt, String immediate){
		
		rs.editContent(BinaryLogic.and(rt.getContent(), immediate));
		
		incrementPC(4);
	}
	
	public static void xori(Register rs, Register rt, String immediate){
		
		rs.editContent(BinaryLogic.xor(rt.getContent(), immediate));
		
		incrementPC(4);
	}
	
	public static void lb(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		rs.editContent(BinaryLogic.integerToBinary(0, 24) + Memory.bytes[index]);
		
		incrementPC(4);
	}
	
	public static void lh(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		rs.editContent(BinaryLogic.integerToBinary(0, 16) + Memory.bytes[index] + Memory.bytes[index + 1]);
		
		incrementPC(4);
	}
	
	public static void lw(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		rs.editContent(Memory.bytes[index] + Memory.bytes[index + 1] + Memory.bytes[index + 2] + Memory.bytes[index + 3]);
		
		incrementPC(4);
	}
	
	public static void sb(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		Memory.bytes[index] = rs.getContent().substring(24, 32);
		
		incrementPC(4);
	}
	
	public static void sh(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		Memory.bytes[index] = rs.getContent().substring(16, 24);
		Memory.bytes[index + 1] = rs.getContent().substring(24, 32);
		
		incrementPC(4);
	}
	
	public static void sw(Register rs, Register rt, String immediate){
		
		int index = BinaryLogic.binaryToInteger(rt.getContent()) + BinaryLogic.binaryToInteger(immediate);
		
		Memory.bytes[index] = rs.getContent().substring(0, 8);
		Memory.bytes[index + 1] = rs.getContent().substring(8, 16);
		Memory.bytes[index + 2] = rs.getContent().substring(16, 24);
		Memory.bytes[index + 3] = rs.getContent().substring(24, 32);
		
		incrementPC(4);
	}
	
	public static void beq(Register rs, Register rt, String immediate){
		
		if(BinaryLogic.equals(rs.getContent(), rt.getContent()))
			incrementPC(BinaryLogic.binaryToInteger(immediate + "00"));
		else
			incrementPC(4);		
	}
	
	public static void bne(Register rs, Register rt, String immediate){
		
		if(!BinaryLogic.equals(rs.getContent(), rt.getContent()))
			incrementPC(BinaryLogic.binaryToInteger(immediate + "00"));
		else
			incrementPC(4);		
	}
	
	public static void lui(Register rt, String immediate){
		
		rt.editContent(immediate + BinaryLogic.integerToBinary(0, 16));
		
		incrementPC(4);	
	}
	
	public static void j(String immediate){
		
		String jumpSize = Register.pc.getContent().substring(0, 4) + immediate + "00";
		
		Register.pc.editContent(jumpSize);
	}
	
	public static void jal(String immediate){
		
		String jumpSize = Register.pc.getContent().substring(0, 4) + immediate + "00";
		incrementPC(4);
		Register.registers[31].editContent(Register.pc.getContent());
		Register.pc.editContent(jumpSize);
	}
	
	private static void incrementPC(int value){
		
		Register.pc.editContent(BinaryArithmetic.sum(Register.pc.getContent(), BinaryLogic.integerToBinary(value)));
	}
	
	public static void main(String[] args) {
		
		String immediate = "00100";
		
		Register.registers[8].editContent(BinaryLogic.integerToBinary(4));
		
		ULA.sll(Register.registers[8], Register.registers[9], immediate);
		
		System.out.println(Register.registers[9].getContent());
		System.out.println(BinaryLogic.binaryToInteger(Register.registers[9].getContent()));
		
	}
}
