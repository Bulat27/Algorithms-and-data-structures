package mojMiniHashingProjekat;



public class AutomobileDoubleyLinkedList implements AutomobileListI {
	
	AutomobileNode head;

	public AutomobileDoubleyLinkedList(AutomobileNode head) {
		super();
		this.head = head;
	}

	@Override
	public void insertFirst(Automobile x) throws Exception {
		if(x==null)throw new Exception();
		head = new AutomobileNode(head, null, x);
		if(head.next!=null) {
			head.next.previous=head;
		}
		
	}

	@Override
	public void delete(AutomobileNode x) throws Exception {
		if(x==null)throw new Exception();
		if(x.previous==null) {
			head=x.next;
			if(head!=null)head.previous=null;
			return;
		}
		x.previous.next=x.next;
		if(x.next!=null) {
			x.next.previous = x.previous;
		}
	}

	@Override
	public AutomobileNode search(String idNumber) throws Exception {
		if(idNumber == null || idNumber.length()==0)throw new Exception();
		AutomobileNode temp = head;
		while(temp!=null) {
			if(temp.automobile.getIdNumber().equals(idNumber)) {
				return temp;
			}
			temp=temp.next;
		}
		return null;
	}
	public void printList() {
		AutomobileNode temp = head;
		int count  =1 ;
		while(temp!=null) {
			System.out.print(count +"." + temp.automobile.toString() + "  ");
			temp = temp.next;
			count++;
		}
		System.out.println();
		
	}
	
	
	
}
