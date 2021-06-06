package knjiga;

import java.util.EmptyStackException;

public interface IStack {
	
	public boolean isFull() throws Exception;

	public boolean isEmpty();

	public boolean push() throws Exception;

	public int pop() throws Exception;

	public int peek() throws EmptyStackException;
}
