package buscador;

public class Pesquisa {

	private String nome;
	private String sintaxe;
	private String descricao;
	private int indice;
	
	
	public Pesquisa(String nome, String sintaxe, String descricao, int indice){
	
		this.nome = nome;
		this.sintaxe = sintaxe;
		this.descricao = descricao;
		this.indice = indice;
}
	
	
public static Pesquisa[] pesquisa = { 

		new Pesquisa("add", "rdst, rsrc1, rsrc2", "Adiciona dois registros, estende sinal de largura de registro.", 0),
		new Pesquisa("addu", "rdst, rsrc1, rsrc2", "Como add, mas sem extensão de sinal de largura de registro.", 1),
		new Pesquisa("sub", "rdst, rsrc1, rsrc2", "Subtrai dois registradores.", 2),
		new Pesquisa("subu", "rdst, rsrc1, rsrc2", "Como sub, mas sem extensão de sinal de largura de registro.", 3),
		new Pesquisa("addi", "rdst, rsrc1, const", "Soma um registrador a uma constante.", 4),
		new Pesquisa("addiu", "rdst, rsrc1, const", "Como addi, mas sem extensão de sinal de largura de registro.", 5),
		new Pesquisa("lw", "rdst, const(rsrc2)", "Carrega o termo armazenado a partir de: MEM[$s2+CONST] e os 3 bytes seguintes.", 6),
		new Pesquisa("lh", "rdst, const(rsrc2)", "Carrega  meio termo armazenado a partir de: MEM[$s2+CONST] e o byte seguinte, estende sinal de largura de registro.", 7),
		new Pesquisa("lb", "rdst, const(rsrc2)", "Carrega o byte armazenado: MEM[$s2+CONST].", 8),
		new Pesquisa("sw", "rdst, const(rsrc2)", "Armazena um termo em: MEM [$s2+CONST] e os seguintes 3 bytes.", 9),
		new Pesquisa("sh", "rdst, const(rsrc2)", "Armazena a primeira metade do um registo (uma meio termo) em: MEM[$s2+CONST] e o byte seguinte.", 10),
		new Pesquisa("sb", "rdst, const(rsrc2)", "Armazena o primeiro quarto de um registro (um byte) em: MEM [$s2+CONST].", 11),
		new Pesquisa("lui", "rdst, const", "Carrega um operador imediato de 16 bits para os 16 bits superiores do registro especificado.", 12),
		new Pesquisa("and", "rdst, rsrc1, rsrc2", "Executa a operação lógica AND bit a bit entre dois registradores.", 13),
		new Pesquisa("andi", "rdst, rsrc1, const", "Executa a operação lógica AND bit a bit entre um registrador e uma constante.", 14),
		new Pesquisa("or", "rdst, rsrc1, rsrc2", "Executa a operação lógica OR bit a bit entre dois registradores.", 15),
		new Pesquisa("ori", "rdst, rsrc1, const", "Executa a operação lógica OR bit a bit entre um registrador e uma constante.", 16),
		new Pesquisa("xor", "rdst, rsrc1, rsrc2", "Executa a operação lógica XOR bit a bit entre dois registradores.", 17),
		new Pesquisa("xori", "rdst, rsrc1, const", "Executa a operação lógica XOR bit a bit entre um registrador e uma constante.", 18),
		new Pesquisa("nor", "rdst, rsrc1, rsrc2", "Executa a operação lógica NOR bit a bit entre dois registradores.", 19),
		new Pesquisa("slt", "rdst, rsrc1, rsrc2", "Testa se o primeiro registrador é menor que o segundo.", 20),
		new Pesquisa("slti", "rdst, rsrc1, const", "Testa se o registrador é menor que uma constante.", 21),
		new Pesquisa("sll", "rdst, rsrc1, const", "Desvia para esquerda o número de casas da constante.", 22),
		new Pesquisa("srl", "rsrc1, rsrc2, const", "Desvia para direita o número de casas da constante.", 23),
		new Pesquisa("beq", "rsrc1, rsrc2, const", "Desvia o número de instruções definido pela constante se os dois registradores forem iguais.", 24),
		new Pesquisa("bne", "rsrc1, rsrc2, const", "Desvia o número de instruções definido pela constante se os dois registradores forem diferentes.", 25),
		new Pesquisa("j", "const", "Desvia o numero de linhas da constante ou vai para label desejadaPula o numero de linhas da constante ou vai para label desejada.", 26),
		new Pesquisa("jr", "rsrc1", "Desvia para o endereço do registrador.", 27),
		new Pesquisa("jal", "const", "Desvia o numero de instruções da constante ou vai para label desejada e altera o valor de $ra para PC + 4.", 28),
		new Pesquisa("sltiu", "rdst, rsrc1, const", "Testa se o registrador é menor que uma constante sem extenção de sinal.", 29),
		new Pesquisa("sltu", "rdst, rsrc1, rsrc2", "Testa se o primeiro registrador é menor que o segundo sem extenção de sinal.", 30),
		new Pesquisa("ERRO!!!", "", "Não foi encontrada instrução com esse nome.", 31),
		};


public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getSintaxe() {
	return sintaxe;
}
public void setSintaxe(String sintaxe) {
	this.sintaxe = sintaxe;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public int getIndice() {
	return indice;
}
public void setIndice(int indice) {
	this.indice = indice;
}
public static Pesquisa[] getPesquisa() {
	return pesquisa;
}
public static void setPesquisa(Pesquisa[] pesquisa) {
	Pesquisa.pesquisa = pesquisa;
}
}