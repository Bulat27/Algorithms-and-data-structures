package knjiga;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.print.DocFlavor.CHAR_ARRAY;

public class Chapter5 {

	// Implementiracu dva algortima za radnom permutovanje niza
	// Ovaj prvi nije in place,drugi je in place,drugi je bolji,imas dokaze u knjizi
	// da oba prave "uniform randomized permutation",
	// sto u prevodu znaci da je podjednako verovatno da metoda vrati bilo koju od
	// permutacija

	// ima ugradjeno u Math power,ali namerno da napisem rekurzivno
	private static int power(int n, int p) {
		if (p == 0)
			return 1;
		return n * power(n, p - 1);
	}

	public static void permuteBySorting(int[] arr) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("BLA");
		}
		int n =arr.length;
		//pozivam arr.length dosta puta,tako da ima smisla sacuvati to u n i onda n koristiti kako bi samo jedno pozivao,ne znam koliko vremena trosi,ali svakako je bolje ovako
		int[] p = new int[n];
		
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			p[i] = random.nextInt(power(n, 3));
		}
		// ovde sad treba sortirati arr ali koristeci p kao sort keys,to bi moglo da se
		// uradi Radix sortom,ali ja cu sada napisati bilo koji sort,samo zato sto je
		// tesko smaranje modifikovati radix
		// a lakse neki drugi sort
		bubbleSort(arr, p);
	}

	public static void bubbleSort(int arr[], int[] p) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (p[j] > p[j + 1]) {
					// swap arr[j+1] and arr[i]
					exchange(arr, j, j+1);
					exchange(p, j, j+1);
				}
	}
	public static void exchange(int[] arr,int i1,int i2) {
		int temp = arr[i1];
		arr[i1]=arr[i2];
		arr[i2]=temp;
	}
	public static void printArray(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("sarfsafasfa");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	//Sada drugi algoritam koji to radi in place,bolji je
	public static void randomizeInPlace(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Array is either null or empty");
		}
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			exchange(arr, i,ThreadLocalRandom.current().nextInt(i, n) );
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		
		try {
			Chapter5.randomizeInPlace(arr);
			Chapter5.printArray(arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
