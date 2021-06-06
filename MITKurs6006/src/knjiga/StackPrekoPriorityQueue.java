package knjiga;

import java.util.EmptyStackException;

public class StackPrekoPriorityQueue extends HeapPriorityQueue implements IStack{
	
	
	
	public StackPrekoPriorityQueue(int length) {
		super(length);
	}
	
	@Override
	public boolean isEmpty() {
		return heapSize==-1;
	}

	@Override
	public boolean push() throws Exception {
		if(isFull()) {
			return false;
		}
		heapSize++;
		arr[heapSize]=Integer.MIN_VALUE;
		super.increaseKey(heapSize, heapSize+1);
		return true;
	}

	@Override
	public int pop() throws Exception {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		 return super.extractMax();
		
	}

	@Override
	public int peek() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return arr[0];
	}
	public static void main(String[] args) {
		StackPrekoPriorityQueue stack =  new StackPrekoPriorityQueue(5);
		try {
			stack.push();
			stack.push();
			stack.push();
			stack.push();
			stack.push();
			
			System.out.println(stack.peek());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
