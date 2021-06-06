package knjiga;

import java.lang.reflect.Array;
import java.util.Arrays;

import kurs.AlgoritmiSaCasova;

public class Exercises {

	// 2.3-4
	// Recursive insertion sort
	//Jako zanimljivo,ovi rekurzivni pozivi prokopaju i u sustini rade posao velike for petlje u insertion sortu
	public static void recursiveInsertionSort(int arr[]) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception();
		}
		recursiveInsertionSortRec(arr, arr.length - 1);
	}

	private static void recursiveInsertionSortRec(int[] arr, int n) {
		if(n<=0)return;
		recursiveInsertionSortRec(arr, n - 1);
		insert(arr, n);
	}

	private static void insert(int[] arr, int k) {
		int temp = arr[k];
		int index = k - 1;
		while (index >= 0 && arr[index] > temp) {
			arr[index + 1] = arr[index];
			index--;
		}
		arr[index + 1] = temp;

	}

	public static void printArray(int[] arr) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception();
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 2.3-5
	// Binary Search
	public static int binarySearch(int arr[], int v) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception();
		}
		return binarySearchRec(arr, 0, arr.length - 1, v);
	}

	private static int binarySearchRec(int[] arr, int l, int r, int v) {
		if (l > r)
			return -1;
		int mid = (l + r) / 2; // l + (r - l) / 2;
		if (v > arr[mid]) {
			return binarySearchRec(arr, mid + 1, r, v);
		}
		if (v < arr[mid]) {
			return binarySearchRec(arr, l, mid - 1, v);// ovde sad mid ne uzimas u obzir jer si ga vec razmotrio(pod ne
														// uzimas u obzir zelim da naglasim razliku u odnosu na deljenje
														// u npr.
			// merge sort-u gde i taj srednji pada u jednu polovinu jer i on treba da se
			// sortira)
		}
		return mid;
	}

	//6.2-2
	public static void minHeapify(int[] arr,int index,int heapSize) {
		if(arr==null || arr.length==0)return;
		int l = 2*index + 1;
		int r = 2*index + 2;
		int min;
		if(l<heapSize && arr[l]<arr[index]) {
			min = l;
		}else {
			min=index;
		}
		if(r<heapSize && arr[r]<arr[min]) {
			min = r;
		}
		if(min!=index) {
			AlgoritmiSaCasova.swap(arr, index, min);
			minHeapify(arr, min, heapSize);
		}
	}
	
	public static void buildMinHeap(int[] arr) {
		for (int i = arr.length/2 - 1; i >=0; i--) {
			Exercises.minHeapify(arr, i, arr.length);
		}
		
	}
	
	
	
	//6.2-5
	public static void iterativeMaxHeapify(int[] arr,int index,int heapSize) {
		int max = index;
		while(true) {
			int l = 2*max+1;
			int r = 2*max + 2;
			if(l<heapSize && arr[l]>arr[max]) {
				max = l;
			}
			if(r<heapSize && arr[r]>arr[max]) {
				max = r;
			}
			if(max==index) {
				break;
			}
			AlgoritmiSaCasova.swap(arr, index, max);
			index =  max;
		}
	}
	//Priority Queues
	//6.5-3
	//On mi trazi za minimum,ali ja cu prvo za max da kucam pa cu onda da promenim i da prilagodim za min
	//URADIO SAM PREKO POSEBNOG INTERFACE-A I KLASE JER MI JE POTREBNO DA ODRZAVAM TO NON STOP!
	
	/*public static int heapMaximum(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception();
		}
		return arr[0];
	}
	public static int heapExtractMax(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception();
		}
		int heapSize = arr.length;
		int max = arr[0];
		arr[0]=arr[heapSize-1];
		heapSize=heapSize -
	}*/
	
	
	
	
	
	
	public static void main(String[] args) {
		int arr[] = {1,3,5,7,9,2,4,6,8,10,12};
		Exercises.buildMinHeap(arr);
		
		try {
			Exercises.printArray(arr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
