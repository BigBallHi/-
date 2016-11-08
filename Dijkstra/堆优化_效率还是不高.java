import java.util.*;

class Node implements Comparable<Node> {

	int dij = 1001;
	boolean isMinDij;
	Edge pre_e;
	List<Edge> neighbors = new LinkedList<Edge>();
	
	@Override
	public int compareTo(Node o) {
		if(this.dij<o.dij){
			return -1;
		}else if(this.dij == o.dij ){
			return 0;
		}else{
			return 1;
		}
	}
}
class Edge {
	int w;
	Node a;
	Node b;
	public Edge(Node a,Node b,int w){
		this.a = a;
		this.b = b;
		this.w = w;
	}
}
public class Main{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int e = in.nextInt();
		Node[] g = new Node[n+1];
		int a , b, c;
		for(int i = 1;i<=n;i++){
			g[i] = new Node();
		}
		for(int i = 1;i <= e;i++){
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			g[a].neighbors.add(new Edge(g[a],g[b],c));
			g[b].neighbors.add(new Edge(g[b],g[a],c));
		}
		in.close();
		System.out.println(dijkstra(g,n));
	}
	public static int dijkstra(Node[] g,int n){
		g[1].dij = 0;
		g[1].isMinDij = true;
		PriorityQueue<Node>  q = new PriorityQueue<Node>();
		int sum = 0;
		Node now = g[1];
		for(int i = 1;i<=n-1;i++){
			for(Edge e :now.neighbors){
				if(!e.b.isMinDij&&now.dij+e.w<=e.b.dij){
					e.b.dij = now.dij+e.w;
					e.b.pre_e = e;
					q.remove(e.b);
					q.add(e.b);
				}
			}
			now = q.poll();
			now.isMinDij = true;
			sum += now.pre_e.w;
		}
		return sum;
	}
	
	
}
