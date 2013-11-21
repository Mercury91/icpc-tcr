public class Flow {
	static class Edge {
		int c;
		int f = 0;
		Vertex s;
		Vertex d;
		Edge(int cap, Vertex source, Vertex dest) {
			c = cap;
			s = source;
			d = dest;
		}
		int res(Vertex v) {
			if (v == d) return f;
			else return c - f;
		}
	}
	static class Vertex {
		List<Edge> lks = new ArrayList<Edge>();
	}
	static int maxFlow(Vertex so, Vertex si) {
		ff: while (true) {
			HashMap<Vertex, Edge> etp = new HashMap<Vertex, Edge>();
			List<Vertex> fringe = new ArrayList<Vertex>();
			fringe.add(so);
			etp.put(so, null);
			int minRes = Integer.MAX_VALUE;
			boolean foundrp = false;
			bfs: while (!fringe.isEmpty()) {
				List<Vertex> newFringe = new ArrayList<Vertex>();
				for (Vertex v : fringe) {
					for (Edge e : v.lks) {
						Vertex child = (e.d == v) ? e.s : e.d;
						if (!etp.containsKey(child) && e.res(v) > 0) {
							etp.put(child, e);
							newFringe.add(child);
							minRes = Math.min(minRes, e.res(v));
							if (child == si) {
								foundrp = true;
								break bfs;
				}	}	}	}
				fringe = newFringe;
			}
			if (!foundrp) break ff;
			Vertex nxt = si;
			while (nxt != so) {
				Vertex prv = nxt;
				Edge edge = etp.get(prv);
				if (edge.s == prv) {
					edge.f = edge.f - minRes;
					nxt = edge.d;
				} else {
					edge.f = edge.f + minRes;
					nxt = edge.s;
				}
			}
		}
		int flow = 0;
		for (Edge e : so.lks) {
			flow += e.f;
		}
		return flow;
	}
}
