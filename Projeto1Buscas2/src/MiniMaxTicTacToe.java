import java.util.Scanner;

public class MiniMaxTicTacToe {
	int[] tabuleiro = {0,0,0,0,0,0,0,0,0};
	int[] tabuleiroc = {0,0,0,0,0,0,0,0,0};
	int jogador = 0;
	Scanner s = new Scanner(System.in);
	public int ganhouJogo() {
		int[][] ganhar = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		for(int i = 0; i < 8; ++i) {
	        if(tabuleiro[ganhar[i][0]] != 0 &&
	        	tabuleiro[ganhar[i][0]] == tabuleiro[ganhar[i][1]] &&
	        	tabuleiro[ganhar[i][0]] == tabuleiro[ganhar[i][2]])
	            return tabuleiro[ganhar[i][2]];
	    }
	    return 0;
	}
	
	
	public void jogarIA() {
		int jogada = -1, pontuacao = -2, aux;
		for(int i = 0; i < 9; i++) {
			if(tabuleiro[i] == 0) {
				tabuleiro[i] = 1;
				aux = -1*miniMax(-1);
				tabuleiro[i] = 0;
				if(aux > pontuacao) {
					pontuacao = aux;
					jogada = i;
				}
			}
		}
		tabuleiro[jogada] = 1;
	}
	
	public int miniMax(int p) {
		if(ganhouJogo() != 0) return ganhouJogo()*p;
		int jogada = -1, pontuacao = -2, aux;
		for(int i = 0; i < 9; i++) {
			if(tabuleiro[i] == 0) {
				tabuleiro[i] = p;
				aux = -1*miniMax(-1*p);
				if(aux > pontuacao) {
					pontuacao = aux;
					jogada = 1;
				}
				tabuleiro[i] = 0;
			}
		}
		if(jogada == -1) return 0;
		return pontuacao;
		
	}
	
	public static void main(String[] args) {
		MiniMaxTicTacToe mm = new MiniMaxTicTacToe();
		System.out.println("IA: O, Jogador: X");
		System.out.print("Jogar Primeiro (s)? ");
		String num = mm.s.nextLine();
		if(num.equals("s"))	mm.jogador = 1;
		else mm.jogador = 2;
		for(int i = 0; i < 9 && mm.ganhouJogo() == 0; i++) {
			if((mm.jogador + i) % 2 == 0) {
				mm.jogarIA();
			}else {
				mm.desenha();
				mm.jogarJogador();
			}
		}
		
		if(mm.ganhouJogo() == 0) {
			System.out.println("Fim de jogo: Empate");
		}
		if(mm.ganhouJogo() == 1) {
			System.out.println("Fim de jogo: Empate");
		}
		if(mm.ganhouJogo() == -1) {
			System.out.println("Fim de jogo: Empate");
		}
		
		mm.s.close();
	}

	private void jogarJogador() {
		System.out.print("Faça a jogada: (1 até 9): ");
		int i = s.nextInt();
		while((i > 9 || i < 1) || tabuleiro[i-1] != 0) {
			System.out.print("Faça a jogada: (1 até 9): ");
			i = s.nextInt();
		}
		tabuleiro[i-1] = -1;
	}
	
	String caracterXO(int i) {
		if(i == 0) return " ";
		if(i == 1) return "X";
		if(i == -1) return "O";
		return " ";
	}
	
	private void desenha() {
		System.out.println();
		System.out.println(" " + caracterXO(tabuleiro[0]) + 
				" | " + caracterXO(tabuleiro[1]) + 
				" | " + caracterXO(tabuleiro[2]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + caracterXO(tabuleiro[3]) + 
				" | " + caracterXO(tabuleiro[4]) + 
				" | " + caracterXO(tabuleiro[5]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + caracterXO(tabuleiro[6]) + 
				" | " + caracterXO(tabuleiro[7]) + 
				" | " + caracterXO(tabuleiro[8]) + " ");
		System.out.println();
	}
}
