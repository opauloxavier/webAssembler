package printClasses;

import java.util.ArrayList;

public class InsAndBinary {
	
	private String cod;
	private String ins;
	
	public InsAndBinary(String codigoBinario,String textoOriginal) {
		this.setCod(codigoBinario);
		this.setIns(textoOriginal);
	}
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getIns() {
		return ins;
	}
	public void setIns(String ins) {
		this.ins = ins;
	}
	
}
