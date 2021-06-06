package knjiga;

public interface PriorityQueue {
	
	public void insert(int x) throws Exception;
	public int maximum() throws Exception;
	public int extractMax() throws Exception;
	public void increaseKey(int i,int k) throws Exception;
}
