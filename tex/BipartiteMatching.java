import java.util.*;

public class BipartiteMatching {
	//Vertex, own class for possible additional properties like names
	static class Vertex {
		List<Edge> links = new ArrayList<Edge>();
	}

	//Edge, saves capacity and saves flow, can compute residual
	static class Edge {
		int capacity;
		int flow = 0;
		Vertex source;
		Vertex dest;

		Edge(int c, Vertex s, Vertex d) {
			capacity = c;
			source = s;
			dest = d;
		}
		//For the on the fly residual graph
		int residualFrom(Vertex v) {
			if (v == dest) return flow;
			else return capacity - flow;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();

		while (cases-- > 0) {
			int nLeft = in.nextInt();
			int nRight = in.nextInt();
			Vertex source = new Vertex();
			Vertex sink = new Vertex();

			// read and add vertices to leftBi (left part of bipartite graph) and connect to source
			List<Vertex> leftBi = new ArrayList<Vertex>();
			for (int i = 0; i < nLeft; i++) {
				Vertex v = new Vertex();
				leftBi.add(capacity=1, source, v);
			}
			// read and add vertices to rightBi (right part of bipartite graph) and connect to source
			List<Vertex> rightBi = new ArrayList<Vertex>();
			for (int i = 0; i < nRight; i++) {
				Vertex v = new Vertex();
				rightBi.add(capacity=1, v, sink);
			}
			// add edges inbetween to both vertices, so that during the BFS
			// the residual flow can be found easily -- Vertex.links.add(Edge) - TODO

			// add all vertices to the flow Network
			List<Vertex> flowNet = new ArrayList<Vertex>();
			flowNet.add(source); flowNet.addAll(leftBi);
			flowNet.addAll(rightBi); flowNet.add(sink);

			//do Ford-Fulkerson
			ford_fulkerson: while (true) {
				// 1 - Find Augmenting Path in Residual Flow Network per BFS

				//HashMap for reconstructing the augmenting path
				HashMap<Vertex, Edge> edgeToParent = new HashMap<Vertex, Edge>();
				List<Vertex> fringe = new ArrayList<Vertex>();
				fringe.add(source);
				edgeToParent.put(source, null);
				int minResidual = Integer.MAX_VALUE;
				boolean foundResPath = false;

				bfs: while (!fringe.isEmpty()) {
					List<Vertex> newFringe = new ArrayList<Vertex>();
					for (Vertex v : fringe) {
						for (Edge e : v.links) {
							//determine the child node, since edges can be in both directions
							Vertex child = (e.dest == v) ? e.source : e.dest;
							//only handle, if this vertex has not been visited
							//and still has residual capacity
							if (!edgeToParent.containsKey(child) && e.residualFrom(v) > 0) {
								edgeToParent.put(child, e);
								newFringe.add(child);
								minResidual = Math.min(minResidual, e.residualFrom(v));
								if (child == sink) {
									foundResPath = true;
									break bfs;
								}
							}
						}
					}
					fringe = newFringe;
				}
				if (!foundResPath) break ford_fulkerson;

				// 2 - alter graph according to augmenting path
				Vertex nextVertex = sink;
				while (nextVertex != source) {
					Vertex prevVertex = nextVertex;
					Edge edge = edgeToParent.get(prevVertex);
					if (edge.source == prevVertex) {
						edge.flow = edge.flow - minResidual;
						nextVertex = edge.dest;
					} else {
						edge.flow = edge.flow + minResidual;
						nextVertex = edge.source;
					}
				}				
			}
			// check condition and print answer
		}
	}
}