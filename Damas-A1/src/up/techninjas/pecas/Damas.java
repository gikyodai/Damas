package up.techninjas.pecas;

public class Damas extends Pecas{ //objeto das damas, herdando a classe de peças
	
	private boolean status;

	public Damas(int index, int jogador, int linha, int coluna, String peca, boolean status) {
		super(index, jogador, linha, coluna, peca);
		
		this.status = status;
		
	}

	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return super.toString() + " " + status;
	}
}