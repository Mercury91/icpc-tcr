public class D_Manha {
	static class Node {
		ArrayList<Node> out = new ArrayList<Node>();
		ArrayList<Node> in = new ArrayList<Node>();
		int var;
		boolean explored = false;
		boolean discovered = false;
		int CCC;
		public Node(int v, String n) {
			var = v;
			name = n;
		}
	}
	static void impl(Node x, Node y){
		x.out.add(y);
		y.in.add(x);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n-- > 0) {
			ArrayList<Node> graph; //TODO : implikationsgraph
			// Kosaraju
			S = new ArrayList<Node>();
			for (Node v : graph) {
				if (!v.explored) {
					DFS(v);
				}
			}
			for (Node v : graph) {
				v.explored = false;
				v.discovered = false;
			}
			int CCCidx = 0;
			do {
				ArrayList<Node> CCC = new ArrayList<Node>();
				DFSTrans(S.get(S.size()-1), CCC, CCCidx++);
				S.removeAll(CCC);
			} while (!S.isEmpty());

			boolean possible = true;
			for (int i = 1; i <= s; i++) {
				if (st.get(i).CCC == sf.get(i).CCC) {
					possible = false;
				}
			}
			for (int i = 1; i <= a; i++) {
				if (at.get(i).CCC == af.get(i).CCC) {
					possible = false;
				}}
			if (possible) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}}}
	static ArrayList<Node> S;
	public static void DFS(Node v) {
		v.discovered = true;
		for (Node u : v.out) {
			if (!u.discovered) {
				DFS(u);
			}}
		v.explored = true;
		S.add(v);
	}
	public static void DFSTrans(Node v, ArrayList<Node> CCC, int CCCidx) {
		v.discovered = true;
		for (Node u : v.in) {
			if (!u.discovered) {
				DFSTrans(u, CCC, CCCidx);
			}}
		v.explored = true;
		CCC.add(v);
		v.CCC = CCCidx;
	}}