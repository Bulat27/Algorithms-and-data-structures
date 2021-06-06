package grafoviDFS;

import java.util.LinkedList;
import java.util.Stack;

public class GraphDFS {
	
	private VertexDFS[] vertexArray;
	int time;

	public GraphDFS(VertexDFS[] vertexArray) {
		super();
		this.vertexArray = vertexArray;
	}
	
	
	public void depthFirstSearch() {
		for (VertexDFS vertexDFS : vertexArray) {
			vertexDFS.setColor('w');
			vertexDFS.setParent(null);
		}
		time=0;
		
		for (VertexDFS vertexDFS : vertexArray) {
			if(vertexDFS.getColor()=='w')
				depthFirstSearchVisit(vertexDFS);
		}
	}


	private void depthFirstSearchVisit(VertexDFS vertexDFS) {
		if(vertexDFS!=null)System.out.println(vertexDFS.getName());
		time++;
		vertexDFS.setD(time);
		vertexDFS.setColor('g');
		for (VertexDFS v : vertexDFS.getNeighbours()) {
			if(v.getColor()=='w') {
				v.setParent(vertexDFS);
				depthFirstSearchVisit(v);
			}
		}
		vertexDFS.setColor('b');
		time++;
		vertexDFS.setF(time);
	}
	
	//22.3-7
	//Iste metode, samo sto cemo umesto rekurzije koristiti stack
	// Moze da se uradi jednostavno, ali nece ici istim redom kao sto ide ovo klasicno, a walkccc je uradio malo tezi nacin i radi isto, ali msm da kvari efikasnost
	public void depthFirstSearch2() {
		for (VertexDFS vertexDFS : vertexArray) {
			vertexDFS.setColor('w');
			vertexDFS.setParent(null);
		}
		time=0;
		
		for (VertexDFS vertexDFS : vertexArray) {
			if(vertexDFS.getColor()=='w')
				depthFirstSearchVisit2(vertexDFS);
		}
	}

	
	//ovo radi,ali ima problem, nemam nacin da u pravom trenutku dodelim v.f, ne mogu lepo da dodelim trenutak kad se zavrsio. Ovaj algoritam sad pogresno dodeljuje i pocetna vremena
	
	private void depthFirstSearchVisit2(VertexDFS vertexDFS) {
		Stack<VertexDFS> stack = new Stack<>();
//		time++;
		vertexDFS.setD(1);
		stack.add(vertexDFS);
		while(!stack.isEmpty()) {
			VertexDFS current = stack.pop();
			System.out.println(current.getName() + "->" + current.getD());
//			time++;
//			vertexDFS.setD(time);
			vertexDFS.setColor('g');
			for (VertexDFS v : current.getNeighbours()) {
				if(v.getColor()=='w') {
				v.setParent(current);
				v.setColor('g');
				time++;
				v.setD(time);
				stack.add(v);
				}
			}
		}
		//Mogao bih da implementiram na nacin sa walkccc, a da efikasnost popravim preko atributa brojaca za svaki cvor i onda da pretrazivanje svacije liste susedstva pocnem
		// od toga gde mu je trenutno brojac, odnosno od onog gde je stao. I tako ce se odrzati linearna efikasnost.
		
		
		
	}
	
	
	
	
	
	//22.3-10
	public void depthFirstSearch3() {
		for (VertexDFS vertexDFS : vertexArray) {
			vertexDFS.setColor('w');
			vertexDFS.setParent(null);
		}
		time=0;
		
		for (VertexDFS vertexDFS : vertexArray) {
			if(vertexDFS.getColor()=='w')
				depthFirstSearchVisit3(vertexDFS);
		}
	}
	private void depthFirstSearchVisit3(VertexDFS vertexDFS) {
//		if(vertexDFS!=null)System.out.println(vertexDFS.getName());
		time++;
		vertexDFS.setD(time);
		vertexDFS.setColor('g');
		for (VertexDFS v : vertexDFS.getNeighbours()) {
			if(v.getColor()=='w') {
				v.setParent(vertexDFS);
				System.out.println("(" + vertexDFS.getName() +","+ v.getName()+")" + " is a tree edge");
				depthFirstSearchVisit3(v);
			}else if(v.getColor()=='g') {
					System.out.println("(" +vertexDFS.getName() +","+ v.getName()+")" + " is a back edge");
			}else if(v.getD()>vertexDFS.getD()) {
				System.out.println("(" +vertexDFS.getName() +","+ v.getName()+")" + " is a forward edge");

			}else {
				System.out.println("(" +vertexDFS.getName() +","+ v.getName()+")" + " is a cross edge");

			}
			
		}
		vertexDFS.setColor('b');
		time++;
		vertexDFS.setF(time);
	}

	
	
	//Topological sort
	//We could just print out that list or return it and than print it, it's not that important, depends of the usage
	
	public LinkedList<VertexDFS> topologicalSort(){
		for (VertexDFS vertexDFS : vertexArray) {
			vertexDFS.setColor('w');
			vertexDFS.setParent(null);
		}
		time=0;
		LinkedList<VertexDFS> list= new LinkedList<>();
		
		for (VertexDFS vertexDFS : vertexArray) {
			if(vertexDFS.getColor()=='w')
				depthFirstSearchVisitTopologicalSort(vertexDFS,list);
		}
		return list;
	}
		
	


	private void depthFirstSearchVisitTopologicalSort(VertexDFS vertexDFS,LinkedList<VertexDFS> list) {
		
		time++;
		vertexDFS.setD(time);
		vertexDFS.setColor('g');
		for (VertexDFS v : vertexDFS.getNeighbours()) {
			if(v.getColor()=='w') {
				v.setParent(vertexDFS);
				depthFirstSearchVisitTopologicalSort(v,list);
			}
		}
		vertexDFS.setColor('b');
		time++;
		vertexDFS.setF(time);
		list.addFirst(vertexDFS);
		
	}
	
	private static void printList(LinkedList<VertexDFS> list) {
		for (VertexDFS vertexDFS : list) {
			System.out.print(vertexDFS.getName()+", ");
		}
	}
	
	
	
	
	
	

	public static void main(String[] args) {
//		VertexDFS[] arr = new VertexDFS[6];
//		
//		VertexDFS u = new VertexDFS(null, 'w', 0, 0, "u", null);
//		VertexDFS v = new VertexDFS(null, 'w', 0, 0, "v", null);
//		VertexDFS w = new VertexDFS(null, 'w', 0, 0, "w", null);
//		VertexDFS x = new VertexDFS(null, 'w', 0, 0, "x", null);
//		VertexDFS y = new VertexDFS(null, 'w', 0, 0, "y", null);
//		VertexDFS z = new VertexDFS(null, 'w', 0, 0, "z", null);
//		
//		u.addEdge(v);
//		u.addEdge(x);
//		v.addEdge(y);
//		x.addEdge(v);
//		y.addEdge(x);
//		w.addEdge(y);
//		w.addEdge(z);
//		z.addEdge(z);
//		
//		arr[0]=u;
//		arr[1]=v;
//		arr[2]=w;
//		arr[3]=x;
//		arr[4]=y;
//		arr[5]=z;
//		
//		GraphDFS g = new GraphDFS(arr);
//		
//		g.depthFirstSearch3();
		
		VertexDFS[] arr = new VertexDFS[9];
		
		VertexDFS socks = new VertexDFS(null, 'w', 0, 0, "socks", null);
		VertexDFS undershorts = new VertexDFS(null, 'w', 0, 0, "undershorts", null);
		VertexDFS pants = new VertexDFS(null, 'w', 0, 0, "pants", null);
		VertexDFS shoes = new VertexDFS(null, 'w', 0, 0, "shoes", null);
		VertexDFS watch = new VertexDFS(null, 'w', 0, 0, "watch", null);
		VertexDFS shirt = new VertexDFS(null, 'w', 0, 0, "shirt", null);
		VertexDFS belt = new VertexDFS(null, 'w', 0, 0, "belt", null);
		VertexDFS tie = new VertexDFS(null, 'w', 0, 0, "tie", null);
		VertexDFS jacket = new VertexDFS(null, 'w', 0, 0, "jacket", null);
		
		undershorts.addEdge(pants);
		undershorts.addEdge(shoes);
		pants.addEdge(belt);
		pants.addEdge(shoes);
		belt.addEdge(jacket);
		shirt.addEdge(tie);
		shirt.addEdge(belt);
		tie.addEdge(jacket);
		socks.addEdge(shoes);
		
		
		GraphDFS g = new GraphDFS(arr);
		arr[0]=shirt;
		arr[1]=tie;
		arr[2]=jacket;
		arr[3]=belt;
		arr[4]=watch;
		arr[5]=undershorts;
		arr[6]=pants;
		arr[7]=shoes;
		arr[8]=socks;
		
	LinkedList<VertexDFS> list = g.topologicalSort();
	printList(list);
		
		

			
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
