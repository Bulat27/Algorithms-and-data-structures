package kurs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import knjiga.Exercises;

public class AlgoritmiSaCasova {
	//Lecture 2
	
	//document distance
	//Ne mogu da nadjem na netu implementaciju u Javi,vidi za bolje nacine,sad radim sam '
	//Problem je malo komplikovaniji,zahteva vise vremena,uradicu ga svakako!
	//https://www.andrew.cmu.edu/course/15-121/labs/HW-4%20Document%20Distance/lab.html
	//Koristan link !
	
	/*private static String[] splitDocIntoWords(String docName) {
		String s ="";
		try(FileReader fr = new FileReader(docName);
				BufferedReader in =  new BufferedReader(fr)){
			boolean end = false;
			while(!end) {
				String temp = in.readLine();
				if(temp==null) {
					end = true;
				}else {
					s=s+temp;
				}
			}
			
		}catch(Exception e) {
			System.out.println("Greska"+e.getMessage());
		}
		s.replaceAll("."," ");
		s.replaceAll("!", " ");
		s.replaceAll("?", " ");
		
		return s;
	}*/
	
	
	//Lecture 3
	
	//Merge Sort (using sentitenls - to je ona njihova fora sa beskonacno)
	
	public static void mergeSort(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		mergeSortRec(arr,0,arr.length-1);
	}

	private static void mergeSortRec(int[] arr, int l, int r) {
		if(l>=r)return;
		int mid = (l+r)/2;
		mergeSortRec(arr, l, mid);
		mergeSortRec(arr, mid+1, r);
		merge(arr,l,mid,r);
		
	}
	//using sentinels
	//radio sam vec bez tih sentinels,onda samo kad se zavrsi jedan niz,prodjes kroz ovaj drugi(u sustini kroz oba jer ne zna skoji se zavrsio) i dopunis tim elementima
	//nacin bez sentinela imas na laptopu(while petlje)
	private static void merge(int[] arr, int l, int mid, int r) {
		int n1= mid - l + 1;
		int n2 = r - mid;
		int L[] = new int[n1+1];
		int R[] = new int[n2+1];
		for (int i = 0; i < n1; i++) {//ide dok je manje od n1,obrati paznju,jer ti ne zelis da popunis onaj poslednji a takodje ides do manje od n1 jer ti indeksi idu od nula do n-1
			L[i]=arr[l+i];
		}
		for (int i = 0; i < n2; i++) {
			R[i]=arr[mid + 1 + i];
		}
		L[n1]=Integer.MAX_VALUE;
		R[n2]=Integer.MAX_VALUE;//sentinels
		
		int i = 0,j = 0;
		for (int k = l; k <=r; k++) {//jako je bitno da ides od donje do gornje granice,ti punis bas taj pravi deo u nizu i zato ne mozes od nula da krenes!Jako je bitno da k pocinje od l (leve granice)
			if(L[i]<=R[j]) {
				arr[k]=L[i];
				i++;
			}else {
				arr[k]=R[j];
				j++;
				
			}
		}
		
	}
	private static void printArray(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	
	//Binary Insertion Sort
	
	public static void binaryInsertionSort(int arr[]) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int index = binaryS(arr,0,i-1,temp);
			int j = i ;
			while(j>index) {
				arr[j]=arr[j-1];
				j--;
			}
			arr[j]=temp;
		}
	}
	//nije klasican binary search,vec za potrebe ovakvog sorta
	private static int binaryS(int[] arr, int l, int r,int v) {
		if(r<=l) {//obuhvata sve slucajeve i ako je ovako jednostavno na prvi pogled,bitno je ovo > u return-u,tako pokriva i slucaj kad je taj vec najveci,da je >= moglo bi da pukne
			//u tom slucaju!
			return v>arr[l] ? l + 1 : l;
		}
		int mid = (r+l)/2;
		if(v>arr[mid]) {
			return binaryS(arr, mid+1, r, v);
		}
		if(v<arr[mid]) {
			return binaryS(arr, l, mid-1, v);
		}
		return mid;
	}
	//2.3-7 Ovaj exercise ima zvezdicu!
	//Pitanje je da li smem da sortiram u nlogn vremenu i onda posle primenim nlog n algoritam?To se racuna kao nlogn vreme?
	public static boolean doTheyExist(int[] arr,int x) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		AlgoritmiSaCasova.mergeSort(arr);
		for (int i = 0; i < arr.length; i++) {
			int cont = binaryS2(arr,i+1,arr.length-1,x,arr[i]);//isto nije klasican binary search vec je prilagodjen za ovu situaciju
			if(cont!=-1) {
				return true;
			}
		}
		
		return false;
		
	}
	
	//Mogao si bukvalno da radis binary search za x - taj broj,ali to u sustini i radim ovde
	private static int binaryS2(int[] arr, int l, int r, int v,int s) {
		if(l>r) {
			return -1;
		}
		int mid = (l+r)/2;
		if(v>s + arr[mid]) {
			return binaryS2(arr, mid+1, r, v, s);
		}
		if(v<s+arr[mid]) {
			return binaryS2(arr, l, mid-1, v, s);
		}
		return mid;
	}
	//Evo ga bolji algoritam,kazu da je teta(nlogn) i da ima bolje konstante,samo sto ne razumem kako
	//Deluje kao da je ovo dole n,a da je zbog sorta nlogn,vidi ovo jos!
	/*
	 MERGE-SORT(S, 1, n)
left = 0
right = n - 1
while (left < right) {
    if (S[left] + S[right] == x) {
        return true
    } else if (S[left + S[right] < x) {
        left++;
    } else {
        right--;
    }
}
return false

	 */

	//Lecture 4: Heap sort
	public static void heapSort(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		buildmaxHeap(arr);
		//int heapSize = arr.length;//oni rade preko atributa HeapSize,ja cu samo da ga pravim kao promenljivu i prosledjujem maxHeapify-u
		//jaka je fora sto je i bas za jedan manje od heap size-a,a svkai put se smanjuje za jos po jedan,pa mozes njega da iskoristis umesto da pravis posebnu promenljivu
		for (int i = arr.length-1; i > 0; i--) {
			swap(arr, i, 0);
			//maxHeapify(arr, 0, i);
			//Exercises.minHeapify(arr, 0, i);
			Exercises.iterativeMaxHeapify(arr, 0, i);
		}
	}
	
	
	public static void maxHeapify(int[] arr, int index,int heapSize) {
		int l = 2 * index + 1;
		int r = 2 * index + 2;
		int max;
		if(l<heapSize && arr[l]>arr[index]) {
			max = l;
		}else {
			max = index ;
		}
		if(r<heapSize && arr[r]>arr[max]) {
			max = r;
		}
		if(max!=index) {
			swap(arr, index, max);
			maxHeapify(arr, max, heapSize);
		}
	}

	public static void swap(int[] arr,int i1, int i2) {
		int temp = arr[i1];
		arr[i1]=arr[i2];
		arr[i2]=temp;
		
	}

	private static void buildmaxHeap(int[] arr) {
		for (int i = arr.length/2 - 1; i >=0; i--) {
			//maxHeapify(arr, i, arr.length);
			//Exercises.minHeapify(arr, i, arr.length);
			Exercises.iterativeMaxHeapify(arr, i, arr.length);
		}
		
	}
	
	
	public static int faktorijal(int x) {
		int y = 1;
		while(x>=2) {
			y*=x;
			x--;
		}
		return y;
	}
	public static int power (int x,int s) {
		if(s==0)return 1;
		int y = x;
		while(s>1) {
			y*=x;
			s--;
		}
		return y;
	}
	

	public static void main(String[] args) {
		System.out.println(AlgoritmiSaCasova.faktorijal(20));
		System.out.println(AlgoritmiSaCasova.power(20, 19));
	}
	
}
