import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class MaximumFlow {
	public static void main(String[] args) {
		int source = 1;
		int sink = 4;
		DirectedGraph g = new DirectedGraph();
		g.addEdge(1, 2, 4);
		g.addEdge(1, 3, 2);
		g.addEdge(2, 4, 1);
		g.addEdge(2, 3, 3);
		g.addEdge(3, 4, 6);
		HashMap<Edge, Integer> flow = getMaxFlow(g, source, sink);
		System.out.println(getFlowSize(flow, g, source));
	}

	static HashMap<Edge, Integer> getMaxFlow(DirectedGraph g, Object source,
			Object sink) {
		LinkedList<Edge> path;
		HashMap<Edge, Integer> flow = new HashMap<Edge, Integer>();
		for (Edge e : g.getEdges()) {
			flow.put(e, 0);
		}

		while ((path = bfs(g, source, sink, flow)) != null) {
			int minCapacity = Integer.MAX_VALUE;
			Object lastNode = source;
			for (Edge edge : path) {
				int c;
				if (edge.getStart().equals(lastNode)) {
					c = edge.getCapacity() - flow.get(edge);
					lastNode = edge.getTarget();
				} else {
					c = flow.get(edge);
					lastNode = edge.getStart();
				}
				if (c < minCapacity) {
					minCapacity = c;
				}
			}

			lastNode = source;
			for (Edge edge : path) {
				if (edge.getStart().equals(lastNode)) {
					flow.put(edge, flow.get(edge) + minCapacity);
					lastNode = edge.getTarget();
				} else {
					flow.put(edge, flow.get(edge) - minCapacity);
					lastNode = edge.getStart();
				}
			}
		}
		return flow;
	}

	static int getFlowSize(HashMap<Edge, Integer> flow, DirectedGraph g,
			Object source) {
		int maximumFlow = 0;
		Node sourceNode = g.getNode(source);
		for (int i = 0; i < sourceNode.getOutLeadingOrder(); i++) {
			maximumFlow += flow.get(sourceNode.getEdge(i));
		}
		return maximumFlow;
	}

	static LinkedList<Edge> bfs(DirectedGraph g, Object start, Object target,
			HashMap<Edge, Integer> flow) {
		HashMap<Object, Edge> parent = new HashMap<Object, Edge>();
		LinkedList<Object> fringe = new LinkedList<Object>();
		parent.put(start, null);
		fringe.add(start);
		all: while (!fringe.isEmpty()) {
			LinkedList<Object> newFringe = new LinkedList<Object>();
			for (Object nodeID : fringe) {
				Node node = g.getNode(nodeID);
				for (int i = 0; i < node.getOutLeadingOrder(); i++) {
					Edge e = node.getEdge(i);
					if (e.getStart().equals(nodeID)
							&& !parent.containsKey(e.getTarget())
							&& flow.get(e) < e.getCapacity()) {
						parent.put(e.getTarget(), e);
						if (e.getTarget().equals(target)) {
							break all;
						}
						newFringe.add(e.getTarget());
					} else if (e.getTarget().equals(nodeID)
							&& !parent.containsKey(e.getStart())
							&& flow.get(e) > 0) {
						parent.put(e.getStart(), e);
						if (e.getStart().equals(target)) {
							break all;
						}
						newFringe.add(e.getStart());
					}
				}
			}
			fringe = newFringe;
		}

		if (fringe.isEmpty()) {
			return null;
		}
		Object node = target;
		LinkedList<Edge> path = new LinkedList<Edge>();
		while (!node.equals(start)) {
			Edge e = parent.get(node);
			path.addFirst(e);
			if (e.getStart().equals(node)) {
				node = e.getTarget();
			} else {
				node = e.getStart();
			}
		}

		return path;
	}

	public static class DirectedGraph {
		private HashMap<Object, Node> nodes = new HashMap<Object, Node>();
		private LinkedList<Edge> edges = new LinkedList<Edge>();

		void addEdge(Object startNodeID, Object endNodeID, int capacity) {
			Node startNode;
			Node endNode;
			if (!this.nodes.containsKey(startNodeID)) {
				startNode = new Node();
				this.nodes.put(startNodeID, startNode);
			} else {
				startNode = this.nodes.get(startNodeID);
			}
			if (!this.nodes.containsKey(endNodeID)) {
				endNode = new Node();
				this.nodes.put(endNodeID, endNode);
			} else {
				endNode = this.nodes.get(endNodeID);
			}
			Edge edge = new Edge(startNodeID, endNodeID, capacity);
			startNode.addEdge(edge);
			endNode.addEdge(edge);
			this.edges.add(edge);
		}

		Node getNode(Object nodeID) {
			return this.nodes.get(nodeID);
		}

		LinkedList<Edge> getEdges() {
			return this.edges;
		}
	}

	public static class Edge {

		private final Object target;
		private final Object start;
		private final int capacity;

		Edge(Object start, Object target, int capacity) {
			this.capacity = capacity;
			this.target = target;
			this.start = start;
		}

		Object getTarget() {
			return target;
		}

		Object getStart() {
			return start;
		}

		int getCapacity() {
			return capacity;
		}

		@Override
		public String toString() {
			return this.start + "->" + this.target + "(" + this.capacity + ")";
		}
	}

	public class Node {

		private ArrayList<Edge> edges = new ArrayList<Edge>();

		void addEdge(Edge edge) {
			this.edges.add(edge);
		}

		Edge getEdge(int number) {
			if (this.edges.size() <= number) {
				return null;
			} else {
				return this.edges.get(number);
			}
		}

		int getOutLeadingOrder() {
			return this.edges.size();
		}
	}
}
