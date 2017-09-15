package trabalho;

import java.util.Random;

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
		Random g = new Random();
		double d = 0.001;
		double T = 1;
		double val = 0;
		double i = 10, j = 20, ia, ja;
		double cur = m.functionMax(i, j);
		double next;
		double dif;
		int inc = 0;
		while(true) {
			if(T < 0.0000000000000001) break;
			inc = g.nextInt(4);
			ia = i;
			ja = j;
			switch(inc) {
			case 0:
				i += d;
			case 1:
				i -= d;
			case 2:
				j += d;
			case 3: 
				j -= d;
			}
			System.out.println("i j ia ja: " + i + " " + j +" " + ia +" " + ja);
			next = m.functionMax(i, j);
			dif = m.functionMax(i, j) - m.functionMax(ia, ja);
			System.out.println(m.functionMax(i, j) + " " + dif);
			if(dif > 0) {
				cur = next;
			}else {
				System.out.println(Math.exp(dif/T));
				if(g.nextDouble() < Math.exp(dif/T)) {
					cur = next;
				}else {
					i = ia;
					j = ja;
				}
			}
			System.out.println("Atual : " + cur);
			T = T*0.9999999;
		}
		
		System.out.println("Atual: " + cur);
		System.out.println("T: " + T);
		System.out.println("i j : " + i + " " + j);
	}
}
