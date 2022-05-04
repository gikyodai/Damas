package up.techninjas.jogo;

public class Jogador {//objeto do jogador

	String nome;
	Integer pontos;
	
	public Jogador(String nome, Integer pontos) {//construtor
		this.nome = nome;
		this.pontos = pontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
}
