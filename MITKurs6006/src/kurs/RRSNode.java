package kurs;

public class RRSNode {
	
	public int size;//ovo mi je potrebno za rank metodu(u njemu se nalazi velicina podstabla sa korenom u tom cvoru)
	public int time;
	public RRSNode left;
	public RRSNode right;
	public RRSNode parent;//mozda je bolje da se stavi private pa da radis preko gettera i settera ali nije sad toliko bitno
	
	public RRSNode(int size, int time, RRSNode left, RRSNode right, RRSNode parent) {
		super();
		this.size = size;
		this.time = time;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	
	
	
	
}
