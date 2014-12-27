package buscador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.AssemblerMips;

public class Buscador {

	public static String pesquisarInstrucao(String a){

		String resultadoPesquisa;
		int tamanhoVetor = 31;
		String operator = new String();
		
		Pattern pesquisas = Pattern.compile("\\w+");
		Matcher buscaPesquisa = pesquisas.matcher(a);
			
		if(buscaPesquisa.find()){

			operator = buscaPesquisa.group();
			
			for(int i=0; i<tamanhoVetor; i++){
					
				if(operator.equals(Pesquisa.pesquisa[i].getNome())){
					
					resultadoPesquisa=Pesquisa.pesquisa[i].getNome()+"-"+Pesquisa.pesquisa[i].getSintaxe()+"-"+Pesquisa.pesquisa[i].getDescricao();
					return resultadoPesquisa;
				}
			}
				
		}

		return "Erro-Não há-Não foi possivel obter resultados para essa pesquisa!";	
	}
}
