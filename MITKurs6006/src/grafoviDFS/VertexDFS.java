package grafoviDFS;

import java.util.LinkedList;

public class VertexDFS {

	private VertexDFS parent;
	private char color;
	private int d;
	private int f;
	private String name;
	private LinkedList<VertexDFS> neighbours;
	
	public VertexDFS(VertexDFS parent, char color, int d, int f, String name, LinkedList<VertexDFS> neighbours) {
		super();
		this.parent = parent;
		this.color = color;
		this.d = d;
		this.f = f;
		this.name = name;
		this.neighbours = neighbours;
	}

	public VertexDFS getParent() {
		return parent;
	}

	public void setParent(VertexDFS parent) {
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

	public LinkedList<VertexDFS> getNeighbours() {
		if(neighbours==null)neighbours=new LinkedList<>();
		return neighbours;
	}

	public void setNeighbours(LinkedList<VertexDFS> neighbours) {
		this.neighbours = neighbours;
	}
	
	
	public void addEdge(VertexDFS destVertex) {
		if(neighbours==null) {
			neighbours=new LinkedList<>();
		}
		neighbours.add(destVertex);
	}
	
	
	
}
