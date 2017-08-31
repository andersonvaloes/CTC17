import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ConjuntoCidades {
	ArrayList<Cidade> colecao;
	PriorityQueue<Cidade> pQC = new PriorityQueue<Cidade>(734, new CidadePrioridade1());
	
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
		c.prev = 0;
		c.visited = true;
		Cidade cidade = null;
		Resultado r = new Resultado();
		r.caminho = "";
		r.distancia = 0;
		pQC.add(c);
//		if(c.id == dest.id) {
//			r.caminho = " " + c.id;
//			r.encontrou = true;
//			r.distancia = 0;
//			return r;
//		}
		
		while(!pQC.isEmpty()) {
			cidade = pQC.poll();
			if(cidade.id == dest.id) break;
			//System.out.println(cidade.id);
			//System.out.println(dest.id);
			for(int i = 0; i < cidade.colecao.size(); i++){
				if(!cidade.colecao.get(i).visited){
					cidade.colecao.get(i).visited = true;
					cidade.colecao.get(i).prev = cidade.id;
					pQC.add(cidade.colecao.get(i));
				}
			}
		}
		cidade = colecao.get(600-1);
		while(cidade.prev != 0) {
			r.caminho = cidade.id + "=>" + r.caminho;
			r.distancia = r.distancia + cidade.distancia(colecao.get(cidade.prev-1));
			cidade = colecao.get(cidade.prev-1);
		}
		r.caminho = cidade.id + "=>" + r.caminho;
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
		FileReader r = new FileReader("/home/spider/git/CTC17/Projeto1Buscas1/src/entrada");
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
	    r = new FileReader("/home/spider/git/CTC17/Projeto1Buscas1/src/entrada");
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
	    
	    for(int i = 0; i < cc.colecao.size(); i++) {
	    	cc.colecao.get(i).visited = false;
	    	cc.colecao.get(i).destination = cc.colecao.get(600-1);
	    }
	    
	    //cc.printAllId();
	    //Resultado res = new Resultado();
	    //res = cc.greedyBusca(cc.colecao.get(203-1), cc.colecao.get(600-1));
	    //System.out.println(res.caminho + "  "+ res.distancia);
	    //System.out.println(res.distancia);
	    
	    Resultado res2 = new Resultado();
	    res2 = cc.greedyBusca2(cc.colecao.get(203-1), cc.colecao.get(600-1));
	    System.out.println(res2.caminho + "  "+ res2.distancia);
	    System.out.println(res2.distancia);
	    br.close();
	}
	
}
