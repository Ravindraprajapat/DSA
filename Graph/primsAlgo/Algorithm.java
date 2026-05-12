class Solution {
	
	class Pair {
		int node;
		int dist;
		Pair(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
	
	class Triplet implements Comparable<Triplet> {
		int node;
		int parent;
		int dist;
		Triplet(int node, int parent, int dist) {
			this.node = node;
			this.parent = parent;
			this.dist = dist;
		}
		
		public int compareTo(Triplet t) {
			if (this.dist == t.dist)
				return this.node - t.node;
			return this.dist - t.dist;
		}
	}
	
	public int spanningTree(int n, int[][] edges) {
		
		int sum = 0;
		boolean[] vis = new boolean[n];
		List<List<Pair>> adj = new ArrayList<>();
		for (int i = 0; i<n; i++) {
			adj.add(new ArrayList<Pair>());
		}
		
		for (int i = 0; i<edges.length; i++) {
			int u = edges[i][0], v = edges[i][1], dist = edges[i][2];
			adj.get(u).add(new Pair(v, dist));
			adj.get(v).add(new Pair(u, dist));
		}
		
		PriorityQueue<Triplet> pq = new PriorityQueue<>();
		pq.add(new Triplet(0,-1,0));
		while(pq.size() > 0){
		    Triplet top = pq.remove();
		    int node = top.node , parent = top.parent, dist = top.dist;
		    if(vis[node]) continue;
		    sum+=dist;
		    vis[node] = true;
		    for(Pair p : adj.get(node)){
		        int Node = p.node;
		        if(Node==parent) continue;
		        if(vis[Node]) continue;
		        pq.add(new Triplet(Node,node,p.dist));
		    }
		}
		return sum;
	}
}
