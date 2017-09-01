import java.util.Comparator;

public class CidadePrioridade2 implements Comparator<Cidade> {

	@Override
	public int compare(Cidade o1, Cidade o2) {
		if(o1.distancia(o1.destination) + o1.curDist >= o2.distancia(o2.destination) + o2.curDist) 
			return 1;	
		else
			return -1;
	}
}
