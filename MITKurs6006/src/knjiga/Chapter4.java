package knjiga;

import java.util.concurrent.TimeUnit;

import knjiga.Chapter4.Pom;

public class Chapter4 {
	// 4.1
	// The maximum subarray problem
	// l is low,m is mid,h is high
	// metoda vraca strukturu od tri broja,tako da moram da je napravim
	public class Pom {
		int maxLeft;
		int maxRight;
		int maxSum;
	}

	public Pom findMaxCrossingSubarray(int[] arr, int l, int m, int h) {
		int leftSum = Integer.MIN_VALUE;
		int maxL = 0;
		int maxR = 0;
		int sum = 0;
		for (int i = m; i >= l; i--) {
			sum += arr[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxL = i;
			}
		}
		sum = 0;
		int rightSum = Integer.MIN_VALUE;
		for (int i = m + 1; i <= h; i++) {
			sum += arr[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxR = i;
			}
		}
		Pom pom = new Pom();
		pom.maxLeft = maxL;
		pom.maxRight = maxR;
		pom.maxSum = leftSum + rightSum;
		return pom;
	}

	public Pom findMaximumSubarray(int[] arr, int l, int h) {
		if (h == l) {
			return makePom(l, h, arr[l]);
		}
		int mid = (l + h) / 2;
		Pom left = findMaximumSubarray(arr, l, mid);
		Pom right = findMaximumSubarray(arr, mid + 1, h);
		Pom cross = findMaxCrossingSubarray(arr, l, mid, h);
		return maxPom(left, right, cross);

	}

	private Pom maxPom(Pom left, Pom right, Pom cross) {
		int max = Math.max(Math.max(left.maxSum, right.maxSum), cross.maxSum);
		if (max == left.maxSum) {
			return left;
		} else if (max == right.maxSum) {
			return right;
		} else {
			return cross;
		}
	}

	private Pom makePom(int maxLeft, int maxRight, int maxSum) {// Mogu da napravim i pravu klasu pa da odradim ovo
																// preko konstruktora,ali zelim da koristim private
																// class-u.Informisi
		// se o prednostima i manama ovakog nacina.Sad ipak mora public da bude jer mi
		// izbacuje gresku,vidi u cemu je fora
		Pom pom = new Pom();
		pom.maxLeft = maxLeft;
		pom.maxRight = maxRight;
		pom.maxSum = maxSum;
		return pom;
	}

	// Exercise 4.1-3
	public Pom bruteForceFindMaxSubarray(int[] arr) {
		if (arr == null || arr.length == 0)
			return null;
		int sum = 0;
		int l = 0, r = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				if (sum > max) {
					max = sum;
					l = i;
					r = j;
				}

			}
		}
		return makePom(l, r, max);
	}

	// Section 4.2
	// Straightforward algorithm for matrix multiplication
	public static int[][] squareMatrixMultiply(int a[][], int b[][]) throws Exception {
		if (a == null || a.length == 0 || b == null || b.length == 0) {
			throw new Exception("There is no matrix");
		}
		int n = b.length;
		int[][] c = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					c[i][j] += a[i][k] * b[k][j];// vec je inicijalizovano na nula po defaultu
				}
			}
		}
		return c;
	}

	public static void printMatrix(int[][] m) {
		if (m == null || m.length == 0) {
			return;
		}
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}

	}
	//Straight forward divide and conquer algorithm(it is not faster than the previous one)
	//Ima dosta smaranja oko implementacije koji zapravo magle algoritam,a nije toliko ni bitno,uradi kad stignes,ako stignes.
	public static int[][] squareMatrixMultiplyRecursive(int[][] a,int[][] b) throws Exception {
		if (a == null || a.length == 0 || b == null || b.length == 0) {
			throw new Exception("There is no matrix");
		}
		 return squareMatrixMultiplyRecursiveHelp(a,b,0,0,0,0,a.length);
		
	}
	
	
	private static int[][] squareMatrixMultiplyRecursiveHelp(int[][] a, int[][] b,int rowA,int colA,int rowB,int colB,int size) {
		int[][] c = new int [size][size];
		if(size==1) {
			c[0][0] = a[rowA][colA]*b[rowB][colB];	
		}else {
			size = size /2;//vidi da li mora new size
			matrixAddition(c, squareMatrixMultiplyRecursiveHelp(a, b, rowA, colA, rowB, colB, size), squareMatrixMultiplyRecursiveHelp(a, b, rowA, colA+size, rowB+size, colB, size), 0, 0);
			matrixAddition(c, squareMatrixMultiplyRecursiveHelp(a, b, rowA, colA, rowB, colB+size, size), squareMatrixMultiplyRecursiveHelp(a, b, rowA, colA+size, rowB+size, colB+size, size), 0, size);
			matrixAddition(c, squareMatrixMultiplyRecursiveHelp(a, b, rowA+size, colA, rowB, colB, size), squareMatrixMultiplyRecursiveHelp(a, b, rowA+size, colA+size, rowB+size, colB, size), size, 0);
			matrixAddition(c, squareMatrixMultiplyRecursiveHelp(a, b, rowA+size, colA, rowB, colB+size, size), squareMatrixMultiplyRecursiveHelp(a, b, rowA+size, colA+size, rowB+size, colB+size, size), size, size);
			
		}
		return c;
	}
		
	
	private static void matrixAddition(int[][] c,int[][]a ,int[][]b,int rowC,int colC){
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < b.length; j++) {
				c[i+rowC][j+colC]=a[i][j] + b[i][j];
			}
		}
	}
	
	//4.1-5
	//bitna stvar,morao sam da pogledam na netu,zajebano je
	public  Pom findMaxSubbarayRecursiveLinear(int[] arr) {
		if(arr==null || arr.length==0) {
			return null;
		}
		int maxSum = Integer.MIN_VALUE;
		int sum = Integer.MIN_VALUE;
		int low = 0;
		int high = 0;
		int currentHigh =0;
		int currentLow=0;
		for (int i = 0; i < arr.length; i++) {
			currentHigh = i;
			if(sum > 0) {
				sum += arr[i];
			}else {
				currentLow = i;
				sum = arr[i];
			}
			if(sum>maxSum) {
				maxSum = sum;
				low =currentLow;
				high = currentHigh;
			}
		}
		Pom pom =  new Pom();
		pom.maxLeft = low;
		pom.maxRight = high;
		pom.maxSum = maxSum;
		return pom;
		
	}
	
	
	
	public static void main(String[] args) {
		int[] arr = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		Chapter4 a = new Chapter4();
		Pom p = a.findMaximumSubarray(arr, 0, arr.length-1);
		System.out.println(p.maxLeft);
		System.out.println(p.maxRight);
		System.out.println(p.maxSum);
		
	}

}
