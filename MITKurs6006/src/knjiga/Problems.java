package knjiga;

public class Problems {
	
	//2-3 Horners Rule
	//b.
	
	public static int naivePolynomialEvaluation(int n,int x,int[] a) {
		int p=0;
		for (int k = 0; k <=n; k++) {
			p+=a[k] * power(x,k);
		}
		return p;
	}
	
	private static int power(int x, int k) {
		if(k==0)return 1;
		int temp =x;
		
		while(k>1) {
			x*=temp;
			k--;
		}
		return x;
	}
	//raspisano da bi se videla efikasnost
	public static int naivePolynomialEvaluation2(int n ,int x, int[] a) {
		int p = 0;
		int count = -1;
		for (int i = 0; i <=n; i++) {
			int y =x;
			for (int j = 0; j <count; j++) {
				y*=x;
			}
			if(i==0)y=1;
			p+=a[i]*y;
			count++;
		}
		return p;
	}
	
	

	//2-4 Inversions
	//d.It needs to be theta(nlgn) complexity
	//It is a modification of merge sort
	
	public static int numOfInversions(int[] arr) throws Exception {
		if(arr==null || arr.length==0) {
			throw new Exception("Moj");
		}
		return numOfInversionsRec(arr,0,arr.length-1);
	}

	private static int numOfInversionsRec(int[] arr, int l, int r) {
		if(l>=r) {
			return 0;
		}
		int mid = (l+r)/2;
		int lS =numOfInversionsRec(arr, l, mid);
		int dS =numOfInversionsRec(arr, mid+1, r);
		return merge(arr,l,mid,r) + lS + dS;
	}

	private static int merge(int[] arr, int l, int mid, int r) {
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
		
		int count = 0;
		int i = 0,j = 0;
		for (int k = l; k <=r; k++) {//jako je bitno da ides od donje do gornje granice,ti punis bas taj pravi deo u nizu i zato ne mozes od nula da krenes!Jako je bitno da k pocinje od l (leve granice)
			if(R[j]<L[i]) {
				arr[k]=R[j];
				j++;
				count+=(L.length-1-i);
			}else {
				arr[k]=L[i];
				i++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int arr[]= {1,2,3,4,5,6,7,8,9,10,11,12,13};
		System.out.println(Problems.naivePolynomialEvaluation2(12, 5, arr));
		
		
	}
}
