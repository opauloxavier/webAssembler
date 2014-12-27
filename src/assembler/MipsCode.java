package assembler;

import java.util.Arrays;

public class MipsCode {
	
	private String code;
	
	public MipsCode(String code){
		
		this.code = executableCode(code);
	}
	
	public void setCode(String code){
		
		this.code = executableCode(code);
	}
	
	public String getCode(){
	
		return this.code;
	}
	
	public String executableCode(String code){
		
		String formatedCode = new String();
		boolean isCommented = false;
		
		for(int i = 0; i < code.length(); i++){
			
			if(code.charAt(i) != '\n' & code.charAt(i) != '#' & isCommented == false & code.charAt(i) != '\t' & code.charAt(i) != ' ' & code.charAt(i) != ','){
					
					formatedCode = formatedCode + code.charAt(i);
			}
			else if(code.charAt(i) == '#'){
				
				isCommented = true;
			}
			else if((code.charAt(i) == '\t' | code.charAt(i) == ' ' | code.charAt(i) == ',' | code.charAt(i) == '(' ) & isCommented == false){
				
				if(i < (code.length() - 1)){
					
					if(code.charAt(i + 1) != ' ' & code.charAt(i + 1) != '\t' & code.charAt(i + 1) != '\n' & code.charAt(i + 1) != '(' & code.charAt(i + 1) != ')' & code.charAt(i + 1) != ','){
						
						
					}
				}
			}
			else if(code.charAt(i) == '\n'){
				
				isCommented = false;
				
				if(i < (code.length() - 1)){
					
					if(code.charAt(i + 1) != '\n'){
						
						if(formatedCode.length() > 0)
							formatedCode = formatedCode + code.charAt(i);
					}
				}
			}
		}
		
		return formatedCode;
	}
	
	public static void main(String args[]){
		
		MipsCode m = new MipsCode("\n\n\n\n\n   add $t0, $a0, $v0 #Soma a0 e v0\n subi $t1,  $t2,       5      \n\n\n LABEL: \n \n \n Label_2 :  \n \n \n   ");
		
		System.out.print(Arrays.asList(m.getCode().split("\\p{Space}+")));
	}
}
