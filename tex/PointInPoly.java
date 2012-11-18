import java.math.*;
import java.util.Scanner;

public class PointInPoly {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n;
		while((n=sc.nextInt())>0){
			double[] x = new double [n+1];
			double[] y = new double [n+1];
			for(int i=1;i<=n;i++){
				x[i] = sc.nextDouble();
				y[i] = sc.nextDouble();
			}
				x[0] = x[n];y[0] = y[n];
				System.out.println(PunktInPoly(x, y, sc.nextDouble(), sc.nextDouble()));
		}
	}

	
	
	/**
	 * -1: A->R schneidet BC (ausser unterer Endpunkt) 
	 *  0: A auf BC 
	 * +1: sonst
	 */
	public static int KreuzProdTest(double ax, double ay, double bx, double by,
			double cx, double cy) {
		if (ay == by && by == cy) {
			if ((bx <= ax && ax <= cx) || (cx <= ax && ax <= bx))
				return 0;
			else
				return +1;
		}
		if(by>cy){double tmpx=bx;double tmpy=by; bx=cx;by=cy;cx=tmpx;cy=tmpy;}
		if(ay==by && ax==bx) return 0;
		if(ay<=by || ay>cy) return +1;
		double delta = (bx-ax)*(cy-ay)-(by-ay)*(cx-ax);
		if(delta>0)return -1; else if(delta<0)return +1;else return 0;
	}
	/**
	 * Input: P[i] (x[i],y[i]); P[0]:=P[n] 
	 * -1: Q ausserhalb Polygon
	 *  0: Q auf Polygon
	 * +1: Q innerhalt des Polygons
	 */
	public static int PunktInPoly(double[] x,double[] y, double qx,double qy){
		int n = x.length - 1;
		int t = -1;
		for (int i = 0; i <= n - 1; i++) {
			t = t * KreuzProdTest(qx, qy, x[i], y[i], x[i + 1], y[i + 1]);
		}
		return t;
	}

}
