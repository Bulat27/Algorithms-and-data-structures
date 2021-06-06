package grafoviDFS;

import java.util.LinkedList;

public class VertexSimplePaths {
	
	private VertexSimplePaths parent;
	private char color;
	private String name;
	private LinkedList<VertexSimplePaths> neighbours;
	private int paths;
	
	
	
	
	
	
	
	
	public VertexSimplePaths(VertexSimplePaths parent, char color, String name,
			LinkedList<VertexSimplePaths> neighbours, int paths) {
		super();
		this.parent = parent;
		this.color = color;
		this.name = name;
		this.neighbours = neighbours;
		this.paths = paths;
	}








	public VertexSimplePaths getParent() {
		return parent;
	}








	public void setParent(VertexSimplePaths parent) {
		this.parent = parent;
	}








	public char getColor() {
		return color;
	}








	public void setColor(char color) {
		this.color = color;
	}








	public String getName() {
		return name;
	}








	public void setName(String name) {
		this.name = name;
	}








	public LinkedList<VertexSimplePaths> getNeighbours() {
		return neighbours;
	}








	public void setNeighbours(LinkedList<VertexSimplePaths> neighbours) {
		this.neighbours = neighbours;
	}








	public int getPaths() {
		return paths;
	}








	public void setPaths(int paths) {
		this.paths = paths;
	}



	public void addEdge(VertexSimplePaths destVertex) {
		neighbours.add(destVertex);
	}
	
	
	
	
	
}
