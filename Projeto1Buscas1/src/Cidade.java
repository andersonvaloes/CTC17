import java.util.ArrayList;

public class Cidade {
	int id, dest;
	int prev;
	Cidade destination;
	double x, y, curDist = 0;
	ArrayList<Cidade> colecao = new ArrayList<Cidade>();
	boolean visited  = false;
	
	public Cidade(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public int quantCidadesProx(){
		return colecao.size();
	}
	
	public double distancia(Cidade dest) {
		return Math.sqrt((dest.x-this.x)*(dest.x-this.x) + (dest.y-this.y)*(dest.y-this.y));
	}
	
	public void addCidade(Cidade c, Cidade dest) {
		if(colecao.size() == 0) colecao.add(c);
		else {
			for(int i = 0; i < colecao.size(); i++) {
				if(colecao.get(i).distancia(dest) > c.distancia(dest)) {
					colecao.add(i, c);
					return;
				}
			}
			colecao.add(c);
		}
	}

}
