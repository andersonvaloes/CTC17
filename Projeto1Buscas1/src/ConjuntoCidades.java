import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConjuntoCidades {
	ArrayList<Cidade> colecao;
	
	public ConjuntoCidades() {
		colecao = new ArrayList<Cidade>();
	}
	
	public void addCidade(Cidade c) {
		colecao.add(c);
	}
	
	public int quantCidades() {
		return this.colecao.size();
	}
	
	public void addCidadeTo(int cidade, int cidadeAdd, int dest) {
		colecao.get(cidade-1).dest = dest;
		colecao.get(cidade-1).addCidade(colecao.get(cidadeAdd - 1), colecao.get(dest - 1));
	}
	
	public void printAll() {
		for(int i = 0; i < this.quantCidades(); i++) {
			System.out.print("Cidade " + this.colecao.get(i).id + "  "+
					this.colecao.get(i).x + "  " + this.colecao.get(i).y);
			for(int j = 0; j < colecao.get(i).quantCidadesProx(); j++) {
				System.out.print("  " + colecao.get(i).colecao.get(j).distancia(colecao.get(600-1)));
			}
			System.out.println("");
		}
	}
	public void printAllId() {
		for(int i = 0; i < this.quantCidades(); i++) {
			System.out.print("Cidade " + this.colecao.get(i).id + "  " +
					this.colecao.get(i).x + "  " + this.colecao.get(i).y);
			for(int j = 0; j < colecao.get(i).quantCidadesProx(); j++) {
				System.out.print("  " + colecao.get(i).colecao.get(j).id);
			}
			System.out.println("");
		}
	}
	
	public Resultado greedyBusca(Cidade c, Cidade dest) {
		Resultado r = new Resultado();
		Resultado raux = new Resultado();
		
		if(c.id == dest.id) {
			r.caminho =  ""+ c.id;
			r.distancia = 0;
			r.encontrou = true;
			return r;
		}
		
		if(c.visited) {
			return r;
		}
		
		c.visited = true;
		
		int i, j = 0;
		
		for(i = 0; i < c.colecao.size(); i++) {
			raux = greedyBusca(c.colecao.get(i), dest);
			j = i;
			if(raux.encontrou) break;
		}
		r.caminho =  c.id + "=>" + raux.caminho;
		r.distancia = raux.distancia + c.distancia(c.colecao.get(j));
		r.encontrou = raux.encontrou;
		return r;
	}
	
	public Resultado greedyBusca2(Cidade c, Cidade dest) {
		Resultado r = new Resultado();
		Resultado raux = new Resultado();
		
		if(c.id == dest.id) {
			r.caminho =  ""+ c.id;
			r.distancia = 0;
			r.encontrou = true;
			return r;
		}
		
		if(c.visited) {
			return r;
		}
		
		c.visited = true;
		
		int i, j = 0;
		
		for(i = 0; i < c.colecao.size(); i++) {
			raux = greedyBusca(c.colecao.get(i), dest);
			j = i;
			if(raux.encontrou) break;
		}
		r.caminho =  c.id + "=>" + raux.caminho;
		r.distancia = raux.distancia + c.distancia(c.colecao.get(j));
		r.encontrou = raux.encontrou;
		return r;
	}
	
	public Resultado aEstrela(Cidade c, Cidade dest) {
		Resultado r = new Resultado();
		Resultado raux = new Resultado();
		
		if(c.id == dest.id) {
			r.caminho =  ""+ c.id;
			r.distancia = 0;
			r.encontrou = true;
			return r;
		}
		
		if(c.visited) {
			return r;
		}
		
		c.visited = true;
		
		int i, j = 0;
		
		for(i = 0; i < c.colecao.size(); i++) {
			raux = greedyBusca(c.colecao.get(i), dest);
			j = i;
			if(raux.encontrou) break;
		}
		r.caminho =  c.id + "=>" + raux.caminho;
		r.distancia = raux.distancia + c.distancia(c.colecao.get(j));
		r.encontrou = raux.encontrou;
		return r;
	}
	
	public static void main(String[] args) throws IOException {
		ConjuntoCidades cc = new ConjuntoCidades();
		FileReader r = new FileReader("/home/spider/eclipse/workspace/Projeto1Buscas1/src/entrada");
	    BufferedReader br = new BufferedReader(r);
	    String line;
	    String[] parts1, parts2;
	    
	    
	    while((line = br.readLine()) != null) {
	    	parts1 = line.split(" ");
	    	parts2 = parts1[0].split(";");
	    	cc.addCidade(new Cidade(Integer.parseInt(parts2[0]), 
	    			Double.parseDouble(parts2[1]), 
	    			Double.parseDouble(parts2[2])));
	    }
		br.close();
	    r = new FileReader("/home/spider/eclipse/workspace/Projeto1Buscas1/src/entrada");
	    br = new BufferedReader(r);
	    while((line = br.readLine()) != null) {
	    	parts1 = line.split(" ");
	    	parts2 = parts1[0].split(";");
	    	for(int j = 3; j < parts2.length; j++) {
	    		cc.addCidadeTo(Integer.parseInt(parts2[0]), 
	    				Integer.parseInt(parts2[j]), 
	    				600);
	    	}
	    }
	    //cc.printAllId();
	    Resultado res = new Resultado();
	    res = cc.greedyBusca(cc.colecao.get(203-1), cc.colecao.get(600-1));
	    System.out.println(res.caminho + "  "+ res.distancia);
	    System.out.println(res.distancia);
	    
	    Resultado res2 = new Resultado();
	    res2 = cc.greedyBusca(cc.colecao.get(203-1), cc.colecao.get(600-1));
	    System.out.println(res2.caminho + "  "+ res2.distancia);
	    System.out.println(res2.distancia);
	    br.close();
	}
	
}
