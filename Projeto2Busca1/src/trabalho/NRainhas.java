package trabalho;
import java.util.Random;

public class NRainhas {
	
	public final int N = 5;
	int[][] vet;
	int[][] pesos;
	
	public NRainhas() {
		vet = new int[N][N];
		pesos = new int[N][N];
	}
	
	public int pesoHeuristica(int[][] v){
		int p = 0, q = 0;
		for(int j = 0; j < this.N; j++) { //Coluna
			for(int i = 0; i < this.N; i++) {
				if(v[i][j] == -1) q++;
			}
			p = p + q*(q-1)/2;
			q = 0;
		}
		//System.out.println(p + "Coluna");
		
		
		for(int i = 0; i < this.N; i++) {//Diagonal \ inferior
			for(int j = 0; i + j < this.N; j++) {
				if(v[i+j][j] == -1) q++;
			}
			p = p + q*(q-1)/2;
			q = 0;
		}
		//System.out.println(p + "Diagonal \\ inferior");
		
		for(int j = 1; j < this.N; j++) {//Diagonal \ superior
			for(int i = 0; i + j < this.N; i++) {
				if(v[i][i+j] == -1) q++;
			}
			p = p + q*(q-1)/2;
			q = 0;
		}
		//System.out.println(p + "Diagonal \\ superior");
		
		for(int i = 0; i < this.N; i++) {//Diagonal / superior
			for(int j = 0; i >= j; j++) {
				if(v[i-j][j] == -1) q++;
			}
			p = p + q*(q-1)/2;
			q = 0;
		}
		//System.out.println(p + "Diagonal / superior");
		
		for(int j = 1; j < this.N; j++) {//Diagonal / inferior
			for(int i = 0; j + i < this.N; i++) {
				if(v[this.N - i - 1][j + i] == -1) q++;
			}
			p = p + q*(q-1)/2;
			q = 0;
		}
		//System.out.println(p + "Diagonal / inferior");
		return p;
	}
	
	
	
	public void generateRandomBoard() {
		Random r = new Random();
		int rand;
		for(int i = 0; i < this.N; i++) {
			rand = r.nextInt(this.N);
			//System.out.println(rand);
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
				if(this.vet[i][j] == -1) System.out.print("1 ");
				else System.out.print(this.vet[i][j] + " ");	
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		NRainhas nr = new NRainhas();
		nr.eraseAll();
		nr.generateRandomBoard();
		nr.printBoard();
		System.out.println(nr.pesoHeuristica(nr.vet));
	}
	
}