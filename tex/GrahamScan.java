	public static class Point implements Comparable<Point> {
		double x, y, r;
		Point p0;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Point p) {
			double s = ccw(p0, p, this);
			if (s != 0) return (int) Math.signum(s);
			else return (int) Math.signum(p.r - r);
		}
	public static double dist(Point a, Point b) {
		double x = a.x - b.x;
		double y = a.y - b.y;
		return Math.sqrt(x * x + y * y);
	}
	public static double ccw(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}
	static List<Point> graham(List<Point> P) {
		Point p0 = P.get(0);
		for (int i = 1; i < P.size(); i++) {
			Point p = P.get(i);
			if (p.y < p0.y || (p.y == p0.y && p.x < p0.x)) {
				p0 = p;
		}	}
		P.remove(p0);
		for (Point p : P) {
			p.r = dist(p0, p);
			p.p0 = p0;
		}
		Collections.sort(P);
		Iterator<Point> I = P.iterator();
		Point f = I.next();
		while (I.hasNext()) {
			Point p = I.next();
			if (ccw(p0, p, f) == 0) {
				I.remove();
			} else {
				f = p;
		}	}
		LinkedList<Point> S = new LinkedList<Point>();
		if (P.isEmpty()) {
			S.add(p0);
		}else{
			S.push(p0);
			S.push(P.get(0));
			for (int i = 1; i < P.size(); i++) {
				Point b = S.pop();
				Point a = S.peek();
				S.push(b);
				while (ccw(a, b, P.get(i)) <= 0) {
					S.pop();
					b = S.pop();
					a = S.peek();
					S.push(b);
				}
				S.push(P.get(i));
		}	}
		return S;
	}