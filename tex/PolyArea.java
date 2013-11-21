static double area(List<Point> p) {
	double a = 0;
	Point q = p.get(p.size() - 1);
	Point r;
	for (Point r : p) {
		a += (q.x + r.x) * (q.y - r.y);
		q = r;
	}
	return a / -2;
}