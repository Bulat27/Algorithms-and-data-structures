package grafoviDFS;

import java.util.LinkedList;

//This Class is used for Exercise 22.4-2
public class GraphPaths {
	
	private VertexSimplePaths[] vertexArray;

	public GraphPaths(VertexSimplePaths[] vertexArray) {
		super();
		this.vertexArray = vertexArray;
	}
	
	public int numberOfSimplePaths(VertexSimplePaths s,VertexSimplePaths t) {
		if(s==t)return 1;
		else if (s.getPaths()!=0)return s.getPaths();
		else {
			for (VertexSimplePaths vertexSimplePaths : s.getNeighbours()) {
				s.setPaths(s.getPaths()+numberOfSimplePaths(vertexSimplePaths, t));
			}
		}
		return s.getPaths();
		
	}
	
	public static void main(String[] args) {
		VertexSimplePaths[] arr = new VertexSimplePaths[14];
		
		VertexSimplePaths m = new VertexSimplePaths(null, 'w', "m", new LinkedList<>(), 0);
		VertexSimplePaths n = new VertexSimplePaths(null, 'w', "n", new LinkedList<>(), 0);
		VertexSimplePaths o = new VertexSimplePaths(null, 'w', "o", new LinkedList<>(), 0);
		VertexSimplePaths p = new VertexSimplePaths(null, 'w', "p", new LinkedList<>(), 0);
		VertexSimplePaths q = new VertexSimplePaths(null, 'w', "q", new LinkedList<>(), 0);
		VertexSimplePaths r = new VertexSimplePaths(null, 'w', "r", new LinkedList<>(), 0);
		VertexSimplePaths s = new VertexSimplePaths(null, 'w', "s", new LinkedList<>(), 0);
		VertexSimplePaths t = new VertexSimplePaths(null, 'w', "t", new LinkedList<>(), 0);
		VertexSimplePaths u = new VertexSimplePaths(null, 'w', "u", new LinkedList<>(), 0);
		VertexSimplePaths v = new VertexSimplePaths(null, 'w', "v", new LinkedList<>(), 0);
		VertexSimplePaths w = new VertexSimplePaths(null, 'w', "w", new LinkedList<>(), 0);
		VertexSimplePaths x = new VertexSimplePaths(null, 'w', "x", new LinkedList<>(), 0);
		VertexSimplePaths y = new VertexSimplePaths(null, 'w', "y", new LinkedList<>(), 0);
		VertexSimplePaths z = new VertexSimplePaths(null, 'w', "z", new LinkedList<>(), 0);
		
		
		m.addEdge(x);
		m.addEdge(q);
		m.addEdge(r);
		n.addEdge(q);
		n.addEdge(u);
		n.addEdge(o);
		o.addEdge(r);
		o.addEdge(v);
		o.addEdge(s);
		p.addEdge(o);
		p.addEdge(s);
		p.addEdge(z);
		q.addEdge(t);
		r.addEdge(u);
		r.addEdge(y);
		s.addEdge(r);
		u.addEdge(t);
		v.addEdge(x);
		v.addEdge(w);
		w.addEdge(z);
		y.addEdge(v);
		
		
		arr[0]=m;
		arr[1]=n;
		arr[2]=o;
		arr[3]=p;
		arr[4]=q;
		arr[5]=r;
		arr[6]=s;
		arr[7]=t;
		arr[8]=u;
		arr[9]=v;
		arr[10]=w;
		arr[11]=x;
		arr[12]=y;
		arr[13]=z;
		
		
		
		
		GraphPaths g = new GraphPaths(arr);
		System.out.println(g.numberOfSimplePaths(p, v));
		
		
		
		
	}
	
}
