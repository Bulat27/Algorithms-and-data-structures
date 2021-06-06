package knjiga;

public class QueuePrekoPriorityQueue extends HeapPriorityQueue implements IQueue {
	
	int priority;
	
	public QueuePrekoPriorityQueue(int size) {
		super(size);
		priority=arr.length;
		
	}
	
	@Override
	public boolean isEmpty() {
		return heapSize == -1;
	}

	@Override
	public boolean enqueue() throws Exception {
		if(isFull()) {
			return false;
		}
		heapSize++;
		arr[heapSize]= Integer.MIN_VALUE;
		increaseKey(heapSize, priority);
		priority--;
		return true;
	}

	@Override
	public int dequeue() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		 return super.extractMax();
	}

	@Override
	public int peekFirst() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return arr[0];
	}

	@Override
	public int peekLast() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return arr[heapSize];
	}
	
	
	
	public static void main(String[] args) {
		QueuePrekoPriorityQueue queue = new QueuePrekoPriorityQueue(5);
		try {
			queue.enqueue();
			queue.enqueue();
			queue.enqueue();
			queue.enqueue();
			queue.enqueue();
			queue.printHeap();
			while(!queue.isEmpty()) {
				System.out.println(queue.dequeue());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
