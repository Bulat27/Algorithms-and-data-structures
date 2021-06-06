package grafoviDFS;

import java.util.LinkedList;

public class VertexSaCcAtributom {

	private VertexSaCcAtributom parent;
	private char color;
	private int d;
	private int f;
	private String name;
	private LinkedList<VertexSaCcAtributom> neighbours;
	int cc;
	
	
	
	
	public VertexSaCcAtributom(VertexSaCcAtributom parent, char color, int d, int f, String name,
			LinkedList<VertexSaCcAtributom> neighbours, int cc) {
		super();
		this.parent = parent;
		this.color = color;
		this.d = d;
		this.f = f;
		this.name = name;
		this.neighbours = neighbours;
		this.cc = cc;
	}


	


	public VertexSaCcAtributom getParent() {
		return parent;
	}





	public void setParent(VertexSaCcAtributom parent) {
		this.parent = parent;
	}





	public char getColor() {
		return color;
	}





	public void setColor(char color) {
		this.color = color;
	}





	public int getD() {
		return d;
	}





	public void setD(int d) {
		this.d = d;
	}





	public int getF() {
		return f;
	}





	public void setF(int f) {
		this.f = f;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public LinkedList<VertexSaCcAtributom> getNeighbours() {
		return neighbours;
	}





	public void setNeighbours(LinkedList<VertexSaCcAtributom> neighbours) {
		this.neighbours = neighbours;
	}





	public int getCc() {
		return cc;
	}





	public void setCc(int cc) {
		this.cc = cc;
	}





	public void addEdge(VertexSaCcAtributom destVertex) {
		if(neighbours==null) {
			neighbours=new LinkedList<>();
		}
		neighbours.add(destVertex);
	}
	
	
}



