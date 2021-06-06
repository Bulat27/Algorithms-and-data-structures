package grafoviBFS;

import java.util.LinkedList;

public class Vertex {
	
	LinkedList<Vertex> neighbours;
	Vertex parent;
	int distance;
	char color;// moze preko enum-a da se ogranice vrednosti, vidi da to uradis taman da obnovis enum-e
	String name;//sluzice mi kao ime cvora, tipa s,v,k,...
	
	public Vertex( Vertex parent, int distance, char color, String name) {
		super();
		this.parent = parent;
		this.distance = distance;
		this.color = color;
		this.name = name;
	}
	
	public void addEdge(Vertex destVert) {
		if(neighbours==null) {
			neighbours = new LinkedList<>();  	
		}
		neighbours.add(destVert);
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertex))return false;
		Vertex v= (Vertex)obj;
		return name.equals(v.name);// trebalo bi da stavim da je private pa preko gettera da ga uzmem
		
	}
	
}
