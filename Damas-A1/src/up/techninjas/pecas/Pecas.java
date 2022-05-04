package up.techninjas.pecas;

public class Pecas {

	private int jogador; // Peca pertence a quem
	private int linha, coluna; // Coordenadas da peca
	private int index;
	private String peca;
	
	public Pecas(int index, int jogador, int linha, int coluna, String peca) {//construtor
		this.index = index;
		this.jogador = jogador;
		this.linha = linha;
		this.coluna = coluna;
		this.peca = peca;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getJogador() {
		return jogador;
	}

	public void setJogador(int jogador) {
		this.jogador = jogador;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public String getPeca() {
		return peca;
	}
	
	public String toString() {
		return index + " " + peca + " linha:" + linha + " coluna:" + coluna;
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}
}