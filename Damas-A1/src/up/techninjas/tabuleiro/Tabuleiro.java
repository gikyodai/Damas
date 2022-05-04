package up.techninjas.tabuleiro;

import java.util.ArrayList;

//import up.techninjas.pecas.Damas;
import up.techninjas.pecas.Pecas;

public class Tabuleiro {
	
	 // criando tabuleiro
	public static String[][] tabuleiro = new String[8][8];
	
	public static ArrayList<Pecas> peca = new ArrayList<>();
	
	public void criarPecas() {
		peca.clear();
		// 	Cria os objetos das pecas
		for(int i = 0; i < 8; i++) {
			if(i % 2 != 0) {
				Pecas pecas = new Pecas(0, 1, 0, i-1, "B");
				peca.add(pecas);
				Pecas pecas1 = new Pecas(0, 1, 2, i-1, "B");
				peca.add(pecas1);
			}else if(i % 2 != 1) {
				Pecas pecas = new Pecas(0, 1, 1, i+1, "B");
				peca.add(pecas);
			}
		}
		for(int i = 0; i < 8; i++) {
			if(i % 2 != 0) {
				Pecas pecas = new Pecas(0, 0, 5, i, "A");
				peca.add(pecas);
				Pecas pecas1 = new Pecas(0, 0, 7, i, "A");
				peca.add(pecas1);
			}else if(i % 2 != 1) {
				Pecas pecas = new Pecas(0, 0, 6, i, "A");
				peca.add(pecas);
			}
		}
		setIndex();
	}
	
	// FUNCAO PARA TESTES
	public static void printIndex() {
		for(Pecas pecas: peca) {
			System.out.println(pecas.toString());
		}
	}
	
	public void criarTab() {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				tabuleiro[i][j] = "x";
			}
		}
		for(Pecas peca: peca) {
			if(peca.getPeca().equals("A")) {
				tabuleiro[peca.getLinha()][peca.getColuna()] = "A";
			}else {
				tabuleiro[peca.getLinha()][peca.getColuna()] = "B";
			}
		}
	}
	
	public static void printTab() {
		int linhas;
		
		System.out.println("\n     1 2 3 4 5 6 7 8");
		System.out.println("   -------------------");
		for(int i = 0; i < 8; i++) {
			linhas = i + 1;
			for(int j = 0; j < 8; j++) {
				if(j == 0) {
					System.out.print(linhas + " |  ");
				}
				System.out.print(tabuleiro[i][j] + " ");
				if(j == 7) {
					System.out.print(" | " + linhas + "\n");
				}
			}
		}
		System.out.println("   -------------------");
		System.out.println("     1 2 3 4 5 6 7 8\n");
		//printIndex(); <-- PARA TESTES
	}
	
	public String checarTab(int i, int j) {
		return tabuleiro [i][j];
	}
	
	public void setTabJogada(int i, int j, int index, int vez) {
		if(vez == 0) {
			peca.get(index).setLinha(i);
			peca.get(index).setColuna(j);
			tabuleiro[peca.get(index).getLinha()][peca.get(index).getColuna()] = "A";
		}else if(vez == 1) {
			peca.get(index).setLinha(i);
			peca.get(index).setColuna(j);
			tabuleiro[peca.get(index).getLinha()][peca.get(index).getColuna()] = "B";
		}
	}
	public void setTabX(int i, int j) {
		tabuleiro[i][j] = "x";
	}
	
	public void setIndex() {
		int index = 0;
		for(Pecas pecas: peca) {
			pecas.setIndex(index);
			index++;
		}
	}
}
