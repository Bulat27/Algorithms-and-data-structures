package grafoviBFS;

import java.util.LinkedList;
import java.util.Queue;

import grafoviDFS.VertexDFS;

public class Graph {
	//implementiracu ga staticki, odnosno, cvorovi ce se cuvati u nizu, to tako radim zato sto u knjizi definisu predstavu
	//preko liste susedstva tako da je to niz koji sadrzi |v| listi
	
	 private Vertex[] vertexArray;
	 

	public Graph(Vertex[] vertexArray) {
		super();
		this.vertexArray = vertexArray;
		
	}
	// uradicu ga tako da isprintuje svaki kroz koji prodje
	// head cvor nije pravi head s obziromd a head ni ne postoji u grafu, ali tako sam oznacio cvor od koga se krece
	public void breadthFirstSearch(Vertex head) throws Exception {
		if(vertexArray==null || vertexArray.length==0 || head==null) {
			throw new Exception("There are no vertices in the graph or head is null");
		}
		for (Vertex vertex : vertexArray) {
			if(vertex!=null) {// u ovom trenutku ovo provera nema puno smisla s obzirom da unapred punim, ali cisto zbog dobre prakse
			vertex.color='w';
			vertex.distance=Integer.MAX_VALUE;//stavi se da je beskonacno jer ako ga ne dovatis, odgovara ti da mu ostane beskonacno
			vertex.parent=null;
			}
		}
		head.color='g';
		head.distance=0;
		head.parent=null;
		Queue<Vertex> queue =  new LinkedList<>();
		queue.add(head);
		while(!queue.isEmpty()) {
			Vertex u = queue.remove();
			System.out.print(u.name + "->" + u.distance + "->" );//Ovo nema u pseudo kodu ali meni znaci da bih znao da li sam prosao kako sam zeleo
			if(u.parent!=null)System.out.println(u.parent.name);
			else System.out.println();
			for (Vertex vertex : u.neighbours) {
				if(vertex.color=='w') {
					vertex.color='g';
					vertex.distance=u.distance+1;
					vertex.parent=u;
					queue.add(vertex);
				}
			}
			u.color='b';
		}
		
	}
	
	//Algoritam sa 601. str
	public void printPath(Vertex s,Vertex v) throws Exception {
		if(s==null || v==null) {
			throw new Exception("s or v is null");
		}
		printPathRec(s,v);
	}
	
	
	
	
	
	
	private void printPathRec(Vertex s, Vertex v) {
		if(v==s) {
			System.out.println(s.name);
		}else if(v.parent==null) {
			System.out.println("v is unreachable from s");
		}else {
			printPathRec(s, v.parent);
			System.out.println(v.name);
		}
		
		
	}
	// dodavacu ih seljacki za sad s obzirom da je fiksno i unapred poznato
//	public void addVertex(Vertex v) {
//		
//	}
	
//	
//	public void depthFirstSearch() {
//		for (VertexDFS vertexDFS : vertexArray) {
//			vertexDFS.setColor('w');
//			vertexDFS.setParent(null);
//		}
//		time=0;
//		
//		for (VertexDFS vertexDFS : vertexArray) {
//			if(vertexDFS.getColor()=='w')
//				depthFirstSearchVisit(vertexDFS);
//		}
//	}
//
//
//	private void depthFirstSearchVisit(VertexDFS vertexDFS) {
//		if(vertexDFS!=null)System.out.println(vertexDFS.getName());
//		time++;
//		vertexDFS.setD(time);
//		vertexDFS.setColor('g');
//		for (VertexDFS v : vertexDFS.getNeighbours()) {
//			if(v.getColor()=='w') {
//				v.setParent(vertexDFS);
//				depthFirstSearchVisit(v);
//			}
//		}
//		vertexDFS.setColor('b');
//		time++;
//		vertexDFS.setF(time);
//	}
	
	
	
	public static void main(String[] args) {
//		Vertex[] arr = new Vertex[2];
//		Vertex c1 = new Vertex(null, null, 1, 'w', "s");
//		Vertex c2 = new Vertex(null, null, 1, 'w', "k");
//		arr[0]=c1;
//		arr[1]=c2;
//		System.out.println(arr[0].name);
//		System.out.println(arr[1].name);
//		for (Vertex vertex : arr) {
//			vertex.name="Promenjena";
//		}
//		System.out.println(arr[0].name);
//		System.out.println(arr[1].name);
//		System.out.println(arr[0].name);
//		System.out.println(arr[1].name);
		Vertex[] vertexArray = new Vertex[8];
		Vertex s = new Vertex(null, 0, 'w', "s");
		Vertex w = new Vertex(null, 0, 'w', "w");
		Vertex r = new Vertex(null, 0, 'w', "r");
		Vertex t = new Vertex(null, 0, 'w', "t");
		Vertex x = new Vertex(null, 0, 'w', "x");
		Vertex v = new Vertex(null, 0, 'w', "v");
		Vertex u = new Vertex(null, 0, 'w', "u");
		Vertex y = new Vertex(null, 0, 'w', "y");
		
		
		
//		

		s.addEdge(w);
		s.addEdge(r);
		w.addEdge(s);
		w.addEdge(t);
		w.addEdge(x);
		r.addEdge(s);
		r.addEdge(v);
		t.addEdge(w);
		t.addEdge(x);
		t.addEdge(u);
		x.addEdge(w);
		x.addEdge(t);
		x.addEdge(u);
		x.addEdge(y);
		v.addEdge(r);
		u.addEdge(t);
		u.addEdge(x);
		u.addEdge(y);
		y.addEdge(x);
		y.addEdge(u);
		
		// namerno ih mesam jer bi trebalo da ide po ivicama a ne po mestima u nizu
		vertexArray[0]=y;
		vertexArray[1]=u;
		vertexArray[2]=v;
		vertexArray[3]=x;
		vertexArray[4]=s;
		vertexArray[5]=t;
		vertexArray[6]=w;
		vertexArray[7]=r;
		
		Graph graf = new Graph(vertexArray);
		try {
			graf.breadthFirstSearch(s);
			System.out.println();
			System.out.println();
			graf.printPath(s, u);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
