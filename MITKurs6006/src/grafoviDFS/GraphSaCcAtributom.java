package grafoviDFS;


//ovo ovde pravim zbog 22.3-12!
public class GraphSaCcAtributom {
	
	private VertexSaCcAtributom[] vertexArray;
	int time;
	int cc;

	public GraphSaCcAtributom(VertexSaCcAtributom[] vertexArray) {
		super();
		this.vertexArray = vertexArray;
	}
	
	public void depthFirstSearchCc() {
		for (VertexSaCcAtributom vertexDFS : vertexArray) {
			vertexDFS.setColor('w');
			vertexDFS.setParent(null);
			vertexDFS.setCc(0);
		}
		time=0;
		cc=0;
		for (VertexSaCcAtributom vertexDFS : vertexArray) {
			if(vertexDFS.getColor()=='w') {
				cc++;
				vertexDFS.setCc(cc);
				depthFirstSearchVisitCc(vertexDFS);
			}
		}
	}


	private void depthFirstSearchVisitCc(VertexSaCcAtributom vertexDFS) {
		if(vertexDFS!=null)System.out.println(vertexDFS.getName());
		time++;
		vertexDFS.setD(time);
		vertexDFS.setColor('g');
		for (VertexSaCcAtributom v : vertexDFS.getNeighbours()) {
			if(v.getColor()=='w') {
				v.setParent(vertexDFS);
				v.setCc(vertexDFS.cc);
				depthFirstSearchVisitCc(v);
			}
		}
		vertexDFS.setColor('b');
		time++;
		vertexDFS.setF(time);
	}
	
	
	
	
	public static void main(String[] args) {
		VertexSaCcAtributom[] arr = new VertexSaCcAtributom[6];
		
		VertexSaCcAtributom u= new VertexSaCcAtributom(null, 'w', 0, 0 , "u", null, 0);
		VertexSaCcAtributom v= new VertexSaCcAtributom(null, 'w', 0, 0 , "v", null, 0);
		VertexSaCcAtributom w= new VertexSaCcAtributom(null, 'w', 0, 0 , "w", null, 0);
		VertexSaCcAtributom x= new VertexSaCcAtributom(null, 'w', 0, 0 , "x", null, 0);
		VertexSaCcAtributom y= new VertexSaCcAtributom(null, 'w', 0, 0 , "y", null, 0);
		VertexSaCcAtributom z= new VertexSaCcAtributom(null, 'w', 0, 0 , "z", null, 0);
		
		u.addEdge(v);
		u.addEdge(x);
		v.addEdge(y);
		x.addEdge(v);
		y.addEdge(x);
		w.addEdge(y);
		w.addEdge(z);
		z.addEdge(z);
		
		arr[0]=u;
		arr[1]=v;
		arr[2]=w;
		arr[3]=x;
		arr[4]=y;
		arr[5]=z;
		
		GraphSaCcAtributom g = new GraphSaCcAtributom(arr);
		
		g.depthFirstSearchCc();
		
		System.out.println(u.cc);
		System.out.println(v.cc);
		System.out.println(y.cc);
		System.out.println(x.cc);
		System.out.println(w.cc);
		System.out.println(z.cc);
		
		
	}
	
}
