package cpu;

import binaryOPS.BinaryLogic;

public class Register {
	
	private String name = new String();
	private String id = new String();
	private String content = new String();
	private String hexContent = new String();
	
	public Register(String name, String id, String content){
		
		this.name = name;
		this.id = id;
		this.content = content;
		this.hexContent = hexValue(content);
	}
	
	public String getName(){
		
		return this.name;
	}
	
	public String getId(){
		
		return this.id;
	}
	
	public String getContent(){
		
		return this.content;
	}
	public String getHex(){
		
		return this.hexContent;
	}
	
	public static Register[] registers = { 
		new Register("$zero", BinaryLogic.integerToBinary(0, 5), BinaryLogic.integerToBinary(0)),
		new Register("$at", BinaryLogic.integerToBinary(1, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$v0", BinaryLogic.integerToBinary(2, 5), BinaryLogic.integerToBinary(0)),
		new Register("$v1", BinaryLogic.integerToBinary(3, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$a0", BinaryLogic.integerToBinary(4, 5), BinaryLogic.integerToBinary(0)),
		new Register("$a1", BinaryLogic.integerToBinary(5, 5), BinaryLogic.integerToBinary(0)),
		new Register("$a2", BinaryLogic.integerToBinary(6, 5), BinaryLogic.integerToBinary(0)),
		new Register("$a3", BinaryLogic.integerToBinary(7, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$t0", BinaryLogic.integerToBinary(8, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t1", BinaryLogic.integerToBinary(9, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$t2", BinaryLogic.integerToBinary(10, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t3", BinaryLogic.integerToBinary(11, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$t4", BinaryLogic.integerToBinary(12, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t5", BinaryLogic.integerToBinary(13, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$t6", BinaryLogic.integerToBinary(14, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t7", BinaryLogic.integerToBinary(15, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s0", BinaryLogic.integerToBinary(16, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s1", BinaryLogic.integerToBinary(17, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$s2", BinaryLogic.integerToBinary(18, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s3", BinaryLogic.integerToBinary(19, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s4", BinaryLogic.integerToBinary(20, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s5", BinaryLogic.integerToBinary(21, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s6", BinaryLogic.integerToBinary(22, 5), BinaryLogic.integerToBinary(0)),
		new Register("$s7", BinaryLogic.integerToBinary(23, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t8", BinaryLogic.integerToBinary(24, 5), BinaryLogic.integerToBinary(0)),
		new Register("$t9", BinaryLogic.integerToBinary(25, 5), BinaryLogic.integerToBinary(0)),
		new Register("$k0", BinaryLogic.integerToBinary(26, 5), BinaryLogic.integerToBinary(0)),
		new Register("$k1", BinaryLogic.integerToBinary(27, 5), BinaryLogic.integerToBinary(0)),
		new Register("$gp", BinaryLogic.integerToBinary(28, 5), BinaryLogic.integerToBinary(0)),
		new Register("$sp", BinaryLogic.integerToBinary(29, 5), BinaryLogic.integerToBinary(2048)),
		new Register("$fp", BinaryLogic.integerToBinary(30, 5), BinaryLogic.integerToBinary(0)), 
		new Register("$ra", BinaryLogic.integerToBinary(31, 5), BinaryLogic.integerToBinary(0))
		};
	
	public static Register pc = new Register("$pc", null, BinaryLogic.integerToBinary(0));
	
	public void editContent(String value){
		
		this.content = value;
		this.hexContent = hexValue(value);
	}
	
	private String hexValue(String content){
		
		String hex = new String();
	
		hex = Integer.toHexString(BinaryLogic.binaryToInteger(content));
			
		while(hex.length() < 8){
				
			hex = '0' + hex;  
		}
			
		hex = "0x" + hex;
	
		return hex;
	}
}
