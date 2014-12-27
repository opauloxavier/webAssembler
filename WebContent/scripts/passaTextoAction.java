package classesAction;
import java.util.ArrayList;

import printClasses.IndAndBytes;
import printClasses.InsAndBinary;
import assembler.AssemblerMips;
import buscador.Buscador;
import buscador.Pesquisa;
import cpu.UCP;

import com.opensymphony.xwork2.ActionSupport;

import cpu.Register;

public class passaTextoAction extends ActionSupport{
	
	private String textoEntrada;
	private String abc;
	private String textoPesquisa;
	public ArrayList<InsAndBinary> obj = new ArrayList<InsAndBinary>();
	public Register[] registradoresPrint;
	private Pesquisa resultadoPesquisa = Buscador.pesquisarInstrucao("add");
	public ArrayList<IndAndBytes> memoriaPrintavel =  new ArrayList<IndAndBytes>();
	
	
	public String converteTextoAction() {
		
		registradoresPrint = Register.registers;
		
		AssemblerMips.onlyInstructions.clear();
		AssemblerMips.codOriginal.clear();
		AssemblerMips.binaryCode.clear();
		AssemblerMips.printMemory.clear();
		
		String[] lines = getTextoEntrada().split(System.getProperty("line.separator"));
		
		for (int i = 0; i < lines.length; i++) {
			
			AssemblerMips.codOriginal.add(lines[i]);
			
		}
		
		AssemblerMips.converteInstrucao();
		memoriaPrintavel=AssemblerMips.printMemory;
		
		System.out.println(memoriaPrintavel.size());
		
		for (int i = 0; i < AssemblerMips.binaryCode.size(); i++) {
			InsAndBinary axu = new InsAndBinary(AssemblerMips.binaryCode.get(i), AssemblerMips.onlyInstructions.get(i));
			obj.add(axu);
		}
		
		return "SUCCESS";
	}
	
	public String efetuaPesquisaAction() {
		
		String a = new String(getTextoPesquisa());
		
		resultadoPesquisa=Buscador.pesquisarInstrucao(a);
		
		System.out.println(resultadoPesquisa.getNome());

		return "SUCCESS";
	}	
	
	public String executarUmAction(){
		
		UCP.execute();
		
		registradoresPrint = Register.registers;
		memoriaPrintavel = AssemblerMips.printMemory;
		
		return "SUCCESS";
	}
		
	
	public String getTextoEntrada() {
		return textoEntrada;
	}



	public void setTextoEntrada(String textoEntrada) {
		this.textoEntrada = textoEntrada;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}



	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}
	


	public String getAbc() {
		return abc;
	}



	public void setAbc(String abc) {
		this.abc = abc;
	}

	public Pesquisa getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(Pesquisa resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	
}