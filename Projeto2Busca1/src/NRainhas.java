import java.util.Random;

public class NRainhas {
	
	public final int N = 10;
	int[][] vet;
	int[][] pesos;
	
	public NRainhas() {
		vet = new int[N][N];
		pesos = new int[N][N];
	}
	
	public void pesoHeuristica(int[][] v){
		for(int i = 0; i < this.N; i++) {
			
		}
	}
	
	
	
	public void generateRandomBoard() {
		Random r = new Random();
		int rand;
		for(int i = 0; i < this.N; i++) {
			rand = r.nextInt(this.N);
			System.out.println(rand);
			this.vet[i][rand] = -1;
		}
	}
	
	public void eraseAll() {
		for(int i = 0; i < this.N; i++) {
			for(int j = 0; j < this.N; j++) {
				this.vet[i][j] = 0;
			}
		}
	}
	
	public void printBoard() {
		for(int i = 0; i < this.N; i++) {
			for(int j = 0; j < this.N; j++) {
				System.out.print(this.vet[i][j] + " ");	
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		NRainhas nr = new NRainhas();
		nr.eraseAll();
		nr.generateRandomBoard();
		nr.printBoard();
	}
	
}