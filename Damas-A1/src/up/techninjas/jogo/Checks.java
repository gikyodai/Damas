package up.techninjas.jogo;

import up.techninjas.pecas.Damas;
import up.techninjas.pecas.Pecas;
import up.techninjas.tabuleiro.Tabuleiro;

public class Checks {

	private boolean existe, destino;
	Tabuleiro tab = new Tabuleiro();
	int index, indexC;

	//Metodo que checa a pe�a escolhida para ser movida pelo jogador
	public boolean checkPecaEscolhida(int vez, int i, int j) {

		if (tab.checarTab(i, j) != "x") {//se a posi��o da pe�a definida por i e j tiver um valor diferente de 'x' que � um casa vazia as seguintes possibilidades ser�o verificadas:
			if (vez == 0 && tab.checarTab(i, j) == "A") {//se o valor dessa posi��o  for igual a 'A' que simboliza as pe�as do Jogador 1
				existe = true;//A nossa vari�vel booleana 'existe' ser� verdadeira, significando que a pe�a existe.
			} else if (vez == 1 && tab.checarTab(i, j) == "B") {//mesma coisa pra B
				existe = true;
			} else {//se n�o significa que a a pe�a selecionada n�o corresponde ao jogador daquela pe�a e exibe uma msg
				System.out.println("Esta peca nao e sua");
				existe = false;// ent�o existe ser� falso
			}
		} else {
			existe = false;
			System.out.println("Peca inexistente");//se nenhuma das pe�as for A ou B siginifica que aquela pe�a nao existe
		}
		return existe;
	}

	//metodo que checa o destino da pe�a selecionada
	public boolean checkDestino(int vez, int linhaP, int colunaP, int linhaJ, int colunaJ) {//P == pe�a e J == jogada
		if (linhaJ == linhaP || colunaJ == colunaP) {//verifica se a pe�a ser� movida na mesma linha ou na mesma coluna em que ela se encontra
			destino = false;//caso sim, o destino ser� falso visto que o jogado n�o estar� movendo a pe�a na diagonal
			System.out.println("Voce so pode jogar na diagonal");
		} else if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {//verifica se a linha e a coluna existem dentro do tabuleiro
			destino = false;//caso estejam fora, o destino ser� falso
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
		} else if ((linhaP - linhaJ == 1 || linhaP - linhaJ == -1)//checa se a pe�a se moveu na linha ou na coluna
				&& (colunaP - colunaJ == 1 || colunaP - colunaJ == -1)) {// Checa a distancia de 1 casa
			
			
			//CHECAGEM DE COMER PE�AS SENDO O JOGADOR DAS PE�AS A (jogador 1)
			if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "B") {//se a vez for do jogador 1 e na casa selecionada tiver uma pe�a do jogador 2 (B)
				if (linhaJ < linhaP && colunaJ > colunaP) {//checa se a pe�a quer se mover na coluna
					// CIMA-DIREITA
					if (linhaJ - 1 < 0 || colunaJ + 1 > 7) {//checa se a proxima casa depois da pe�a DEVORADA est� fora do tabuleiro
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ + 1) == "x") {//verifica se a proxima casa depois da pe�a devorada est� vazia
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da pe�a a ser movida cont�m o valor A (pe�a jogador 1)
							indexC = checkIndexComido(linhaJ, colunaJ);//seta como comidas as pe�as do advers�rio no caminho
							tab.setTabJogada(linhaJ - 1, colunaJ + 1, index, vez);//seta a nova posi��o da pe�a e passa a vez
							tab.setTabX(linhaJ, colunaJ);//seta como x a casa da pe�a comida
							removerPeca(indexC);//remove a pe�a comida 
							tab.setTabX(linhaP, colunaP);//seta como x a antiga posi��o da pe�a
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adicionando pontos ao jogador
							destino = true;//destino, portanto, ser� verdadeiro
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ < linhaP && colunaJ < colunaP) {
					// CIMA-ESQUERDA
					if (linhaJ - 1 < 0 || colunaJ - 1 < 0) {//verifica se a casa seguinte � casa da pe�a a ser devorada est� fora do tabuleiro
						destino = false;//caso esteja fora, destino ser� inv�lido
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ - 1) == "x") {//verifica se a proxima casa depois da pe�a devorada est� vazia
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da pe�a a ser movida cont�m o valor A (pe�a jogador 1)
							indexC = checkIndexComido(linhaJ, colunaJ);//seta como comidas as pe�as do advers�rio no caminho
							tab.setTabJogada(linhaJ - 1, colunaJ - 1, index, vez);//seta a nova posi��o da pe�a e passa a vez
							tab.setTabX(linhaJ, colunaJ);//seta como x a posi��o da pe�a comida
							removerPeca(indexC);//remove a pe�a comida 
							tab.setTabX(linhaP, colunaP);//seta como x a antiga posi��o da pe�a
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adicionando pontos ao jogador
							destino = true;//destino, portanto, ser� verdadeiro
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ < colunaP) {//mesma coisa do que acima, s� que pra diagonal baixo esquerda
					// BAIXO-ESQUERDA
					if (linhaJ + 1 > 7 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ > colunaP) {//mesma coisa do que acima, s� que pra diagonal baixo direita
					// BAIXO-DIREITA
					if (linhaJ + 1 > 7 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				}
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "A") {//se o turno for do jogador 1, verifica se a pe�a a ser comida n�o pertence a jogador a ele mesmo
				System.out.println("Voce nao pode comer a propria peca!");
				destino = false;//caso seja, destino ser� falso
				
				//CHECAGEM DE MOVER A PE�A SENDO O JOGADOR 'A' (SIMPLESMENTE ANDAR UMA CASA, CASO ESTEJA VAZIA)
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "x") {//se a vez for do jogador 1 e casa de destino for igual a 'x'(casa vazia)
				if(linhaJ > linhaP) {//se a linha da jogada do jogador A for abaixo da linha da pe�a dele, significa que ele est� andando para tr�s, portanto:
					destino = false;//o destino ser� invalido
					System.out.println("Voce nao pode andar para tras!");
				}else {
					index = checkIndex(linhaP, colunaP, "A");////verifica se na linha e na coluna da pe�a a ser movida cont�m o valor A (pe�a jogador 1)
					tab.setTabJogada(linhaJ, colunaJ, index, vez); //seta a nova posi��o da pe�a e passa a vez
					tab.setTabX(linhaP, colunaP);//seta como x a posi��o da pe�a comida
					destino = true;//destino portanto, ser� v�lido
				}
			}
			
			//CHECAGEM DE COMER PE�AS SENDO O JOGADOR DAS PE�AS B (jogador 2)
			if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "A") {
				if (linhaJ < linhaP && colunaJ > colunaP) {
					// CIMA-DIREITA
					if (linhaJ - 1 < 0 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ < linhaP && colunaJ < colunaP) {
					// CIMA-ESQUERDA
					if (linhaJ - 1 < 0 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
						//CHECAGEM DE MOVER A PE�A SENDO O JOGADOR 'B' (SIMPLESMENTE ANDAR UMA CASA, CASO ESTEJA VAZIA)
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ < colunaP) {
					// BAIXO-ESQUERDA
					if (linhaJ + 1 > 7 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ > colunaP) {
					// BAIXO-DIREITA
					if (linhaJ + 1 > 7 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				}
			} else if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "B") {
				System.out.println("Voce nao pode comer a propria peca!");
				destino = false;
			} else if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "x") {
				if(linhaJ < linhaP) {
					destino = false;
					System.out.println("Voce nao pode andar para tras!");
				}else {
					index = checkIndex(linhaP, colunaP, "B");
					tab.setTabJogada(linhaJ, colunaJ, index, vez);
					tab.setTabX(linhaP, colunaP);
					destino = true;
				}
			}
		} else {
			destino = false;
			System.out.println("Voce so pode andar 1 casa");
		}
		return destino;
	}

	public boolean checkDestinoDama(int vez, int linhaP, int colunaP, int linhaJ, int colunaJ) {
		boolean diagOk = false;//vari�vel que controla se a diagonal que a dama quer se mover est� ok
		int tipoDiag = 0;//tipo da diagonal, abriga qual � a diagonal em que a dama vai andar
		// 1 - CIMA ESQUERDA
		// 2 - BAIXO DIREITA
		// 3 - BAIXO ESQUERDA
		// 4 - CIMA DIREITA
		if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {//verifica se a(s) linha(s) e coluna(s) da jogada est�o fora do tabuleiro
			destino = false;//caso estejam, o destino da dama ser� falso, portanto inv�lido
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
			
			
			// Checa o tipo de diagonal de destino e se esta dentro dela
			// CIMA ESQUERDA
		} else if (linhaJ < linhaP && colunaJ < colunaP) {//se a dama quer mover diagonalmente de CIMA para a ESQUERDA
			for (int i = linhaP, j = colunaP; i >= 0 && j >= 0; i--, j--) {//percorre as linhas e colunas do tabuleiro na diagonal selecionada
				if (i == linhaJ && j == colunaJ) {//qundo a linha e coluna de destino (linhaJ, colunaJ) forem iguais a posi��o da dama
					diagOk = true;//a diagonal ter� sido v�lida
					destino = true;//o destino � valido
					tipoDiag = 1;//a diagonal ser� do tipo indicado
					break;//o la�o se encerrar�
				} else {//caso contr�rio nada acontece feijoada
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {//se a diagonal n�o for v�lida, exibe uma msg
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// BAIXO DIREITA
		} else if (linhaJ > linhaP && colunaJ > colunaP) {//se a dama quer mover diagonalmente de BAIXO para a CIMA
			for (int i = linhaP, j = colunaP; i < 8 && j < 8; i++, j++) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 2;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// BAIXO ESQUERDA
		} else if (linhaJ > linhaP && colunaJ < colunaP) {//se a dama quer mover diagonalmente de BAIXO para a ESQUERDA
			for (int i = linhaP, j = colunaP; i < 8 && j >= 0; i++, j--) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 3;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// CIMA DIREITA
		} else if (linhaJ < linhaP && colunaJ > colunaP) {//se a dama quer mover diagonalmente de CIMA para a DIREITA
			for (int i = linhaP, j = colunaP; i >= 0 && j < 8; i--, j++) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 4;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
		}
		// Checa o destino para comer e andar com a peca apos ter validado a diagonal
		if (diagOk && destino) {
			index = -1;//...
			if (vez == 0) {//se a vez for do jogador 1:
				if (tab.checarTab(linhaJ, colunaJ) == "x") {//checa pra ver se a casa da jogada est� vazia
					switch (tipoDiag) {//pra cada um dos tipos de diagonais
					case 1: {
						for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {//percorre as linhas e colunas da diagonal
							if(tab.checarTab(i , j) == "A") {//at� encontrar uma pe�a que perten�a ao jogador 1
								destino = false;//invalidando o destino
								System.out.println("Tem uma peca sua no meio");
								break;//quebrando o la�o
							}
						}
						if(destino) {//se o destino for verdadeiro
							for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {//percorre as linhas e colunas da diagonal
								if(tab.checarTab(i, j) == "B") {//se tiver uma pe�a do jogador 2
									indexC = checkIndexComido(i, j);//come a pe�a
									removerPeca(indexC);//remove a pe�a
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adiciona pontos ao jogador
									tab.setTabX(i, j);//seta com x a casa da pe�a comida
								}
							}
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da pe�a a ser movida cont�m o valor A (pe�a jogador 1)
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);//seta a nova posi��o da dama e passa a vez
							tab.setTabX(linhaP, colunaP);//seta como x a antiga casa da dama
						}
						break;//quebra o la�o
					}
					case 2: {//mesma coisa pro tipo de diagonal 2
						for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 3: {//mesma coisa pro tipo de diagonal 3
						for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 4: {//mesma coisa pro tipo de diagonal 4
						for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					default:
						System.out.println("Alguma merda deu");
						break;
					}
				}else {
					destino = false;
					System.out.println("Jogue em uma casa vazia!");
				}
			} else if(vez == 1){//faz todas as mesmas verifica��es para o jogador 2
				if (tab.checarTab(linhaJ, colunaJ) == "x") {
					switch (tipoDiag) {
					case 1: {
						for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 2: {
						for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 3: {
						for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 4: {
						for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					default:
						System.out.println("Alguma merda deu");
						break;
					}
				}else {
					destino = false;
					System.out.println("Jogue em uma casa vazia!");
				}
			}
		}

		return destino;
	}

	//Fun��o que verifica se uma pe�a virou uma dama e entao setando ela como dama
	public void checkDama(int vez, boolean correto) {
		boolean troca = false;//controla se a pe�a trocou para uma dama
		int i = 0, j = 0;
		if (correto) {
			if (vez == 0) {
				for (Pecas peca : Tabuleiro.peca) {
					if (peca.getJogador() == 0 && peca.getLinha() == 0) {
						index = peca.getIndex();
						i = peca.getLinha();
						j = peca.getColuna();
						troca = true;
					}
				}
				if (troca) {
					Tabuleiro.peca.remove(index);
					Pecas dama = new Damas(0, 0, i, j, "A", true);
					Tabuleiro.peca.add(dama);
					tab.setIndex();
				}
			} else {
				for (Pecas peca : Tabuleiro.peca) {
					if (peca.getJogador() == 1 && peca.getLinha() == 7) {
						index = peca.getIndex();
						i = peca.getLinha();
						j = peca.getColuna();
						troca = true;
					}
				}
				if (troca) {
					Tabuleiro.peca.remove(index);
					Pecas dama = new Damas(0, 1, i, j, "B", true);
					Tabuleiro.peca.add(dama);
					tab.setIndex();
				}
			}
		}
	}

	public boolean checkPontos() {//m�todo que verifica quantidade de pontos dos jogadores para finalizar o jogo
		boolean gameover;//controla o fim do jogo

		if (Dama.jog1.getPontos() == 12) {//se o jogador 1 tiver 12 pontos:
			gameover = true;// o jogo acaba
		} else if (Dama.jog2.getPontos() == 12) {//se o jogador 2 tiver 12 pontos:
			gameover = true;// o jogo acaba
		} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11){ //se n�o se os dois jogadores tiverem 11 pontos o jogo acaba por empate
			gameover = true;
		}else {
			gameover = false;
		}
		return gameover;
	}

	public int checkVitoria(boolean gameover) {//m�todo que verifica o vencedor de acordo com a quantidade de pontos
		int vencedor = 2;//controla quem ser� o vencedor
		if (gameover) {
			if (Dama.jog1.getPontos() == 12) {//se o jogador 1 tiver 12 pontos
				vencedor = 0;//ele sera o vencedor
			} else if (Dama.jog2.getPontos() == 12) {//se o jogador 2 tiver 12 pontos
				vencedor = 1;//ele ser� o vencedor
			} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11) {//se os dois tiverem 11 pontos
				vencedor = 2;//nenhum deles ser� o vencedor
			}
		} else {
			vencedor = 3;//...
		}
		return vencedor;
	}

	public int checkIndex(int linha, int coluna, String jogador) {//checa a posi��o das pe�as
		for (Pecas peca : Tabuleiro.peca) {
			if (peca.getPeca().equals(jogador) && peca.getLinha() == linha && peca.getColuna() == coluna) {
				index = peca.getIndex();
			}else if(peca instanceof Damas) {
				if(((Damas)peca).getPeca().equals(jogador) && ((Damas)peca).getLinha() == linha && ((Damas)peca).getColuna() == coluna) {
					index = ((Damas)peca).getIndex();
				}
			}
		}
		return index;
	}

	public int checkIndexComido(int linha, int coluna) {//checa a posi��o das pe�as comidas
		for (Pecas peca : Tabuleiro.peca) {
			if (peca.getLinha() == linha && peca.getColuna() == coluna) {
				indexC = peca.getIndex();
			}
		}
		return indexC;
	}

	public void removerPeca(int index) {//remove as pe�as
		Tabuleiro.peca.remove(index);
		tab.setIndex();
	}
}