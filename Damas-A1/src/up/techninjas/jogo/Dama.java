package up.techninjas.jogo;

import java.util.Scanner;

import up.techninjas.pecas.Damas;
import up.techninjas.pecas.Pecas;
import up.techninjas.tabuleiro.Tabuleiro;

public class Dama {

	int continuar = 1, linhaP, colunaP, linhaJ, colunaJ, index;
	boolean gameover;
	static String nome = "";
	int vez = 0;
	Tabuleiro tab = new Tabuleiro();
	Checks check = new Checks();
	Scanner scanner = new Scanner(System.in);
	public static Jogador jog1 = new Jogador(nome, 0);
	public static Jogador jog2 = new Jogador(nome, 0);
	int vencedor;

	public void jogar() {

		System.out.print("informe o nome do jogador 1: ");
		nome = scanner.next();
		jog1.setNome(nome);
		jog1.setPontos(0);
		System.out.print("informe o nome do jogador 2: ");
		nome = scanner.next();
		jog2.setNome(nome);
		jog2.setPontos(0);
		do {
			boolean pecaExiste = false;
			boolean casaCorreta = false;

			Tabuleiro.printTab();
			printPontos();
			printVez();

			while (!pecaExiste) {
				System.out.println("Informe a casa da peca que deseja mover");
				System.out.print("Linha: ");
				linhaP = scanner.nextInt() - 1;
				System.out.print("Coluna: ");
				colunaP = scanner.nextInt() - 1;

				pecaExiste = check.checkPecaEscolhida(vez, linhaP, colunaP);
			}
			while (!casaCorreta) {
				boolean dama = false;
				System.out.println("Informe a casa de destino");
				System.out.print("Linha: ");
				linhaJ = scanner.nextInt() - 1;
				System.out.print("Coluna: ");
				colunaJ = scanner.nextInt() - 1;
				
				for(Pecas peca: Tabuleiro.peca) {
					if(peca.getLinha() == linhaP && peca.getColuna() == colunaP) {
						if(peca instanceof Damas) {
							dama = ((Damas)peca).getStatus();
						}
					}
				}
				// Verificacao de destino e comer peca
				if(!dama) {
					casaCorreta = check.checkDestino(vez, linhaP, colunaP, linhaJ, colunaJ);
					check.checkDama(vez, casaCorreta);
				}else {
					casaCorreta = check.checkDestinoDama(vez, linhaP, colunaP, linhaJ, colunaJ);
				}	
			}

			gameover = check.checkPontos();
			vencedor = check.checkVitoria(gameover);

			if (vencedor != 3) {
				if (vencedor == 0) {
					System.out.println("------------------------");
					System.out.println("Vencedor: " + jog1.getNome());
					System.out.println("------------------------");
				} else if (vencedor == 1) {
					System.out.println("------------------------");
					System.out.println("Vencedor: " + jog2.getNome());
					System.out.println("------------------------");
				} else {
					System.out.println("------------------------");
					System.out.println("O jogo empatou!");
					System.out.println("------------------------");
				}
			}
			// Troca a vez de jogada
			if (vez == 0) {
				vez = 1;
			} else {
				vez = 0;
			}
		} while (!gameover);
	}

	public void printVez() {
		if (vez == 0) {
			System.out.println(" ");
			System.out.println("Vez de: " + jog1.getNome());
			System.out.println(" ");
		} else {
			System.out.println(" ");
			System.out.println("Vez de: " + jog2.getNome());
			System.out.println(" ");
		}
	}
	
	public void printPontos() {
		System.out.println("Pontos de " + jog1.getNome() + ": " + jog1.getPontos());
		System.out.println("Pontos de " + jog2.getNome() + ": " + jog2.getPontos());
	}

	public boolean continuar() {
		int op = 2;
		boolean continuar = false;
		System.out.println("Informe uma opcao: ");
		System.out.println("1 - Continuar");
		System.out.println("2 - Encerrar");
		op = scanner.nextInt();
		do {
			if (op == 1) {
				continuar = true;
			} else if (op == 2) {
				continuar = false;
			} else {
				System.out.println("Valor incorreto, informe 1 ou 2");
			}
		} while (op != 1 && op != 2);
		return continuar;
	}
}