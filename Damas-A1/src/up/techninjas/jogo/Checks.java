package up.techninjas.jogo;

import up.techninjas.pecas.Damas;
import up.techninjas.pecas.Pecas;
import up.techninjas.tabuleiro.Tabuleiro;

public class Checks {

	private boolean existe, destino;
	Tabuleiro tab = new Tabuleiro();
	int index, indexC;

	//Metodo que checa a peça escolhida para ser movida pelo jogador
	public boolean checkPecaEscolhida(int vez, int i, int j) {

		if (tab.checarTab(i, j) != "x") {//se a posição da peça definida por i e j tiver um valor diferente de 'x' que é um casa vazia as seguintes possibilidades serão verificadas:
			if (vez == 0 && tab.checarTab(i, j) == "A") {//se o valor dessa posição  for igual a 'A' que simboliza as peças do Jogador 1
				existe = true;//A nossa variável booleana 'existe' será verdadeira, significando que a peça existe.
			} else if (vez == 1 && tab.checarTab(i, j) == "B") {//mesma coisa pra B
				existe = true;
			} else {//se não significa que a a peça selecionada não corresponde ao jogador daquela peça e exibe uma msg
				System.out.println("Esta peca nao e sua");
				existe = false;// então existe será falso
			}
		} else {
			existe = false;
			System.out.println("Peca inexistente");//se nenhuma das peças for A ou B siginifica que aquela peça nao existe
		}
		return existe;
	}

	//metodo que checa o destino da peça selecionada
	public boolean checkDestino(int vez, int linhaP, int colunaP, int linhaJ, int colunaJ) {//P == peça e J == jogada
		if (linhaJ == linhaP || colunaJ == colunaP) {//verifica se a peça será movida na mesma linha ou na mesma coluna em que ela se encontra
			destino = false;//caso sim, o destino será falso visto que o jogado não estará movendo a peça na diagonal
			System.out.println("Voce so pode jogar na diagonal");
		} else if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {//verifica se a linha e a coluna existem dentro do tabuleiro
			destino = false;//caso estejam fora, o destino será falso
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
		} else if ((linhaP - linhaJ == 1 || linhaP - linhaJ == -1)//checa se a peça se moveu na linha ou na coluna
				&& (colunaP - colunaJ == 1 || colunaP - colunaJ == -1)) {// Checa a distancia de 1 casa
			
			
			//CHECAGEM DE COMER PEÇAS SENDO O JOGADOR DAS PEÇAS A (jogador 1)
			if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "B") {//se a vez for do jogador 1 e na casa selecionada tiver uma peça do jogador 2 (B)
				if (linhaJ < linhaP && colunaJ > colunaP) {//checa se a peça quer se mover na coluna
					// CIMA-DIREITA
					if (linhaJ - 1 < 0 || colunaJ + 1 > 7) {//checa se a proxima casa depois da peça DEVORADA está fora do tabuleiro
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ + 1) == "x") {//verifica se a proxima casa depois da peça devorada está vazia
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da peça a ser movida contém o valor A (peça jogador 1)
							indexC = checkIndexComido(linhaJ, colunaJ);//seta como comidas as peças do adversário no caminho
							tab.setTabJogada(linhaJ - 1, colunaJ + 1, index, vez);//seta a nova posição da peça e passa a vez
							tab.setTabX(linhaJ, colunaJ);//seta como x a casa da peça comida
							removerPeca(indexC);//remove a peça comida 
							tab.setTabX(linhaP, colunaP);//seta como x a antiga posição da peça
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adicionando pontos ao jogador
							destino = true;//destino, portanto, será verdadeiro
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ < linhaP && colunaJ < colunaP) {
					// CIMA-ESQUERDA
					if (linhaJ - 1 < 0 || colunaJ - 1 < 0) {//verifica se a casa seguinte à casa da peça a ser devorada está fora do tabuleiro
						destino = false;//caso esteja fora, destino será inválido
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ - 1) == "x") {//verifica se a proxima casa depois da peça devorada está vazia
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da peça a ser movida contém o valor A (peça jogador 1)
							indexC = checkIndexComido(linhaJ, colunaJ);//seta como comidas as peças do adversário no caminho
							tab.setTabJogada(linhaJ - 1, colunaJ - 1, index, vez);//seta a nova posição da peça e passa a vez
							tab.setTabX(linhaJ, colunaJ);//seta como x a posição da peça comida
							removerPeca(indexC);//remove a peça comida 
							tab.setTabX(linhaP, colunaP);//seta como x a antiga posição da peça
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adicionando pontos ao jogador
							destino = true;//destino, portanto, será verdadeiro
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ < colunaP) {//mesma coisa do que acima, só que pra diagonal baixo esquerda
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
				} else if (linhaJ > linhaP && colunaJ > colunaP) {//mesma coisa do que acima, só que pra diagonal baixo direita
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
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "A") {//se o turno for do jogador 1, verifica se a peça a ser comida não pertence a jogador a ele mesmo
				System.out.println("Voce nao pode comer a propria peca!");
				destino = false;//caso seja, destino será falso
				
				//CHECAGEM DE MOVER A PEÇA SENDO O JOGADOR 'A' (SIMPLESMENTE ANDAR UMA CASA, CASO ESTEJA VAZIA)
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "x") {//se a vez for do jogador 1 e casa de destino for igual a 'x'(casa vazia)
				if(linhaJ > linhaP) {//se a linha da jogada do jogador A for abaixo da linha da peça dele, significa que ele está andando para trás, portanto:
					destino = false;//o destino será invalido
					System.out.println("Voce nao pode andar para tras!");
				}else {
					index = checkIndex(linhaP, colunaP, "A");////verifica se na linha e na coluna da peça a ser movida contém o valor A (peça jogador 1)
					tab.setTabJogada(linhaJ, colunaJ, index, vez); //seta a nova posição da peça e passa a vez
					tab.setTabX(linhaP, colunaP);//seta como x a posição da peça comida
					destino = true;//destino portanto, será válido
				}
			}
			
			//CHECAGEM DE COMER PEÇAS SENDO O JOGADOR DAS PEÇAS B (jogador 2)
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
						//CHECAGEM DE MOVER A PEÇA SENDO O JOGADOR 'B' (SIMPLESMENTE ANDAR UMA CASA, CASO ESTEJA VAZIA)
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
		boolean diagOk = false;//variável que controla se a diagonal que a dama quer se mover está ok
		int tipoDiag = 0;//tipo da diagonal, abriga qual é a diagonal em que a dama vai andar
		// 1 - CIMA ESQUERDA
		// 2 - BAIXO DIREITA
		// 3 - BAIXO ESQUERDA
		// 4 - CIMA DIREITA
		if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {//verifica se a(s) linha(s) e coluna(s) da jogada estão fora do tabuleiro
			destino = false;//caso estejam, o destino da dama será falso, portanto inválido
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
			
			
			// Checa o tipo de diagonal de destino e se esta dentro dela
			// CIMA ESQUERDA
		} else if (linhaJ < linhaP && colunaJ < colunaP) {//se a dama quer mover diagonalmente de CIMA para a ESQUERDA
			for (int i = linhaP, j = colunaP; i >= 0 && j >= 0; i--, j--) {//percorre as linhas e colunas do tabuleiro na diagonal selecionada
				if (i == linhaJ && j == colunaJ) {//qundo a linha e coluna de destino (linhaJ, colunaJ) forem iguais a posição da dama
					diagOk = true;//a diagonal terá sido válida
					destino = true;//o destino é valido
					tipoDiag = 1;//a diagonal será do tipo indicado
					break;//o laço se encerrará
				} else {//caso contrário nada acontece feijoada
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {//se a diagonal não for válida, exibe uma msg
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
				if (tab.checarTab(linhaJ, colunaJ) == "x") {//checa pra ver se a casa da jogada está vazia
					switch (tipoDiag) {//pra cada um dos tipos de diagonais
					case 1: {
						for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {//percorre as linhas e colunas da diagonal
							if(tab.checarTab(i , j) == "A") {//até encontrar uma peça que pertença ao jogador 1
								destino = false;//invalidando o destino
								System.out.println("Tem uma peca sua no meio");
								break;//quebrando o laço
							}
						}
						if(destino) {//se o destino for verdadeiro
							for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {//percorre as linhas e colunas da diagonal
								if(tab.checarTab(i, j) == "B") {//se tiver uma peça do jogador 2
									indexC = checkIndexComido(i, j);//come a peça
									removerPeca(indexC);//remove a peça
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);//adiciona pontos ao jogador
									tab.setTabX(i, j);//seta com x a casa da peça comida
								}
							}
							index = checkIndex(linhaP, colunaP, "A");//verifica se na linha e na coluna da peça a ser movida contém o valor A (peça jogador 1)
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);//seta a nova posição da dama e passa a vez
							tab.setTabX(linhaP, colunaP);//seta como x a antiga casa da dama
						}
						break;//quebra o laço
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
			} else if(vez == 1){//faz todas as mesmas verificações para o jogador 2
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

	//Função que verifica se uma peça virou uma dama e entao setando ela como dama
	public void checkDama(int vez, boolean correto) {
		boolean troca = false;//controla se a peça trocou para uma dama
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

	public boolean checkPontos() {//método que verifica quantidade de pontos dos jogadores para finalizar o jogo
		boolean gameover;//controla o fim do jogo

		if (Dama.jog1.getPontos() == 12) {//se o jogador 1 tiver 12 pontos:
			gameover = true;// o jogo acaba
		} else if (Dama.jog2.getPontos() == 12) {//se o jogador 2 tiver 12 pontos:
			gameover = true;// o jogo acaba
		} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11){ //se não se os dois jogadores tiverem 11 pontos o jogo acaba por empate
			gameover = true;
		}else {
			gameover = false;
		}
		return gameover;
	}

	public int checkVitoria(boolean gameover) {//método que verifica o vencedor de acordo com a quantidade de pontos
		int vencedor = 2;//controla quem será o vencedor
		if (gameover) {
			if (Dama.jog1.getPontos() == 12) {//se o jogador 1 tiver 12 pontos
				vencedor = 0;//ele sera o vencedor
			} else if (Dama.jog2.getPontos() == 12) {//se o jogador 2 tiver 12 pontos
				vencedor = 1;//ele será o vencedor
			} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11) {//se os dois tiverem 11 pontos
				vencedor = 2;//nenhum deles será o vencedor
			}
		} else {
			vencedor = 3;//...
		}
		return vencedor;
	}

	public int checkIndex(int linha, int coluna, String jogador) {//checa a posição das peças
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

	public int checkIndexComido(int linha, int coluna) {//checa a posição das peças comidas
		for (Pecas peca : Tabuleiro.peca) {
			if (peca.getLinha() == linha && peca.getColuna() == coluna) {
				indexC = peca.getIndex();
			}
		}
		return indexC;
	}

	public void removerPeca(int index) {//remove as peças
		Tabuleiro.peca.remove(index);
		tab.setIndex();
	}
}