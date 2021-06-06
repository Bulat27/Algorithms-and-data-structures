package knjiga;

public interface IQueue {
	
	public boolean isFull() throws Exception;

	public boolean isEmpty();

	public boolean enqueue() throws Exception;

	public int dequeue() throws Exception;

	public int peekFirst() throws Exception;

	public int peekLast() throws Exception;
	
}
