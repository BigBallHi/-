import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node{
	//顶点编号
	int val ;
	//dijkstra算法中的到当前已知到改点的最短路径
	int dij = 1001;
	//该点当前的dijkstra路径的前驱节点
	Edge dijpre;
	//是否已经找到最短路径
	boolean isMin;
	//该节点的最小边
	Edge minEdge;
	//邻接表，边表
	List<Edge> neighbors=new LinkedList<Edge>();
	
	public Node(int i){
		this.val = i;
	}
	public Node(){
		this.val = 1;
		this.dij = 0;
		this.isMin = true;
	}
}
class Edge{
	//该边所指向的点
	Node n;
	//该边的权值
	int w;
	public Edge(){}
	public Edge(Node n,int w){
		this.n = n;
		this.w =w;
	}
}
class Graph{
	int nodeSize;
	int edgeSize;
	//顶点，起始点
	Node firstNode;
	//顶点表
	List<Node> nodes = new LinkedList<Node>();
	//根据输入的数字初始化图
	public void add(int n1,int n2,int w){
		Node n11 = null;
		Node n22 = null;
		Edge e;
		for(Node n:nodes){
			if(n.val == n1){
				n11 = n;
				break;
			}	
		}
		for(Node n:nodes){
			if(n.val == n2){
				n22 = n;
				break;
			}		
		}
		e = new Edge(n22,w);
		n11.neighbors.add(e);
		e= new Edge(n11,w);
		n22.neighbors.add(e);
		//n11.neighbors.add(new Edge(n22,w));
		//n11.neighbors.add(new Edge(n11,w));
	}
	/*****************************************核心算法*********************************************************/
	public void dijkstra(){
		//dijkstra路径的总长度
		int sum = 0;
		this.firstNode = this.nodes.get(0);
//		for(Node n :this.nodes){
//			if(n.val==1){
//				this.firstNode = n;
//			}
//		}
		Node now = this.firstNode;
		Node next = null;
		for(int t = 1;t<this.nodeSize;t++){
			for(Edge e:now.neighbors){
				if(e.w+now.dij<=e.n.dij&&e.n.isMin==false){
					e.n.dij = e.w+now.dij;
					now.minEdge = e;
					e.n.dijpre = new Edge(now,e.w);
				}
			}
			int min = 1001;
			for(Node n:this.nodes){
				if(n.isMin==false&&n.dij<=min){
					min = n.dij;
					next = n;
				}
			}
			System.out.println(next.dijpre.n.val+"-->"+next.val);
			next.isMin = true;
			sum+=next.dijpre.w;
			now = next;
		}
		System.out.println(sum);
	}
}
public class Main{
	public static void main(String[] args) {
		Graph g = new Graph();
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int e = s.nextInt();
		g.edgeSize = e;
		g.nodeSize = n;
		g.nodes.add(new Node());
		for(int i = 2;i<=n;i++){
			g.nodes.add(new Node(i));
		}
		
		int a,b,c;
		for(int k = 1;k<=e;k++){
			a = s.nextInt();
			b = s.nextInt();
			c = s.nextInt();
			g.add(a,b,c);
		}

		g.dijkstra();
	}
}
第63行，注释掉的两行运行效率不如第一种。。。不知道为什么
