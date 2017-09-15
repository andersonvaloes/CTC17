package trabalho;

public class MaxGlobal {
	public double functionMax(double x, double y) {
		return 4*Math.exp(-(x*x+y*y)) 
				+ Math.exp(-((x-5)*(x-5)+(y-5)*(y-5)))
				+ Math.exp(-((x+5)*(x+5)+(y-5)*(y-5)))
				+ Math.exp(-((x-5)*(x-5)+(y+5)*(y+5)))
				+ Math.exp(-((x+5)*(x+5)+(y+5)*(y+5)));
	}
	
	public static void main(String[] args) {
		MaxGlobal m = new MaxGlobal();
		System.out.println(m.functionMax(0, 0));
	}
}
