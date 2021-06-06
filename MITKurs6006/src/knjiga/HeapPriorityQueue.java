package knjiga;

import java.util.Arrays;

import kurs.AlgoritmiSaCasova;

public class HeapPriorityQueue implements PriorityQueue{
	
	int[] arr;
	int heapSize;
	
	public HeapPriorityQueue(int length) {
		arr = new int[length];
		heapSize = -1;//hocu da mi stoji na trenutnom poslednjem elementu
	}

	@Override
	public void insert(int x) throws Exception {
		if(arr==null || arr.length==0) {//ovde ne proveravam da li je heapSize nula jer i da jeste,u prazan svj zelim da ubacim
			//Uradicu i situaciju kada u array-u nema dovoljno mesta
			throw new Exception();
		}
		if(!isFull()) {
			heapSize++;
			arr[heapSize]=Integer.MIN_VALUE;	
		}else {
			 arr=resizeAndAdd(arr,Integer.MIN_VALUE);
			 heapSize++;
		}
		increaseKey(heapSize, x);
		
	}

	private int[] resizeAndAdd(int[] arr, int x) {//vrv ima bolji nacin ali za sad cu ovako
		int[]copy= Arrays.copyOf(arr, arr.length+1);
		copy[arr.length]=x;
		arr=copy;
		return arr;
	}

	@Override
	public int maximum() throws Exception {
		if(arr==null || arr.length==0 || heapSize<0) {
			throw new Exception();
		}
		return arr[0];
	}

	@Override
	public int extractMax() throws Exception {
		if(arr==null || arr.length==0 || heapSize<0) {
			throw new Exception();
		}
		int max = arr[0];
		arr[0]=arr[heapSize];
		heapSize--;
		AlgoritmiSaCasova.maxHeapify(arr,0, heapSize+1);//tamo sam implementirao u sustini da je heap size broj elemenata a ne poslednji indeks,pa cu radi provere tamo odavde slati heap size + 1
		//metodi max heapifz
		return max;
	}

	@Override
	public void increaseKey(int i, int k) throws Exception {
		if(arr==null || arr.length==0 || i>heapSize ||heapSize<0 || k<arr[i]) {//increase key mora da poveca,ne moze da smanji vrednost,samim tim ovo se mora proveriti!
			throw new Exception();
		}
		arr[i]=k;
		int parentI =(i - 1)/2;
		while(i>0 && arr[parentI]<arr[i]) {
			AlgoritmiSaCasova.swap(arr, i, parentI);
			i=parentI;
			parentI = (i-1)/2;
		}
		
	}
	
	
	

	public boolean isFull() throws Exception {
		if(arr==null) {
			throw new Exception();
		}
		if(arr.length - 1 == heapSize) {
			return true;
		}
		return false;
	}
	
	//6.5-6 Heap increase key but modified so it reduces the number of assignments using the idea from insertion sort
	public void increaseKeyModified(int i, int k) throws Exception {
		if(arr==null || arr.length==0 || heapSize<0 || k<arr[i]) {//increase key mora da poveca,ne moze da smanji vrednost,samim tim ovo se mora proveriti!
			throw new Exception();
		}
		arr[i]=k;
		int parentI =(i - 1)/2;
		while(i>0 && arr[parentI]<k) {
			arr[i] = arr[parentI];
			i=parentI;
			parentI=(i-1)/2;		
		}
		arr[i]=k;
	}
	public void printHeap() {
		for (int i = 0; i <=heapSize; i++) {
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	
	//6.5-8
	//Nije mi skroz isto kao na netu,ali mislim da je dobro
	public void heapDelete(int i) throws Exception {
		if(arr==null || arr.length==0 || heapSize<0 || i > heapSize) {//increase key mora da poveca,ne moze da smanji vrednost,samim tim ovo se mora proveriti!
			throw new Exception();
		}
		int p = (i-1)/2;
		arr[i]=arr[heapSize];
		heapSize--;
		if(arr[i]>arr[p]) {
			increaseKey(i,arr[i]);
		}else {
			AlgoritmiSaCasova.maxHeapify(arr, i, heapSize+1);
		}
	}
	
	public static void main(String[] args) {
		HeapPriorityQueue queue =  new HeapPriorityQueue(10);
		try {
			queue.insert(87);
			queue.insert(27);
			queue.insert(15);
			queue.insert(20);
			queue.insert(7);
			queue.insert(4);
			queue.insert(12);
			queue.insert(1);
			queue.insert(19);
			
			queue.printHeap();
			queue.heapDelete(6);
			queue.printHeap();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
