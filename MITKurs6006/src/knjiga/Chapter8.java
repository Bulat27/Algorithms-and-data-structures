package knjiga;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chapter8 {
	// counting sort kada bi bio samo za integere,radim da bih video zasto mora da
	// se "komplikuje",poenta je u tome da je ta komplikovanija verzija stora
	// "stable"
	// sto nam omogucava da radimo za bilo koje elemente ciji su kljucevi integeri,a
	// ne samo za integere
	// ovakav radi samo kada su svi razliciti i kada su svi integeri,poenta je da
	// radi za vise istih elemenata i plus da mogu da budu bilo koja struktura ciji
	// je key integer.Ako key nije integer
	// naravno moze da se mapira da bude integer.
	// Mogao bih seljacki da resim taj problem sto ne mogu vise njih da se
	// ponavljaju,ali oni imaju bolji nacin,sammo bih napravio jos jednu petlju koja
	// ce da ubacuje sve dok ne dodje br
	// ponavljanja do nule,a istovreme povecavam i iterator (i) tako cu u sustini
	// opet proci k puta i ako imam dve petlje jer unutrasnja petlja takodje iterira
	// glavnu kada se poziva vise od
	// jedanput
	public static int[] integerCountingSort(int[] arr, int k) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		int[] c = new int[k + 1];
		int[] b = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			c[arr[i]]++;
		}
		int count = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) {
				b[count++] = i;
			}
		}
		return b;
	}

	private static void printArray(int[] arr) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// evo ga ovde sad kompletan sa integere,a posle cu za objekte.Fora je u tome
	// sto bi za objekte bilo potrebno napraviti generic type,a tu ima dosta posla
	// oko sintakse.
	// Bilo bi jako pametno da to uradim kada zavrsim sa algoritmima,treba da se
	// pozabavim sa upotrebom i cakama Jave u naprednijem smislu.
	public static int[] countingSort(int[] arr, int k) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		int n = arr.length;
		int[] c = new int[k + 1];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			c[arr[i]]++;
		}
		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			b[c[arr[i]] - 1] = arr[i];
			c[arr[i]]--;
		}
		return b;
	}

	// Pisem ovde radix sort,ponasacu se kao da je b = 10 jer cu da radim sa
	// integerima,tako da ce biti Theta(n) kada je n = 10
	// Inace bi trebalo te digits predstaviti preko b koji ti odgovara,ali to je
	// trenutno komlikovano a nepotrebno tako da cu dublje analizirati radix sort
	// ako mi ikad bude bilo potrebno
	//Imas na Geeksforgeeks isto samo sa deljenjem da bi se doslo do cifara,a ne ovako preko stringova,sto je verovatno efikasnije,tj ima bolje konstante,ali asimptotski je isto
	//Fora je u tome da imas neki exp kao brojac koji pocinje od 1 i svaki put se mnozi sa 10 i onda u svakom prolazu kazes (arr[i]/exp)%10,sto ti u prvom prolazu da samo arr[i]|%10,
	//u drugom (arr[i]/10)%10 i tako dalje,a to su bas redom cifre,a pri tom ni u jednom trenutku ne odsecas stvarno vec samo pristupas toj cifri koja ti treba tako da nije potrebno pravljenje
	//dodatnih nizova
	//Njihov nacin je bolji jer ti omogucava da radis sa brojevima koji imaju razlicit broj cifara,kod mene u tom slucaju puca kod,a kod njih kad nema tu cifru,jednostavno ce biti nula i 
	//svakako ce biti manja od ovog sto ima tu cifru
	
	public static void radixSort(int[] arr,int d,int b) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		for (int i=d-1; i >=0; i--) {
			countingSort2(arr,i,b);
		}
	}
	

	private static void countingSort2(int[] arr, int d, int b) {
		int n =arr.length;
		int[] c =new int[b];//ako je osnova npr 10,hocu brojeve 0-9
		int[] temp = new int[n];//pre svakog prolaza za sledecu cifru,temp ponovo kopira arr i bude bas ono sto treba
		for (int i = 0; i < temp.length; i++) {
			temp[i]=arr[i];
		}
		for (int i = 0; i < n; i++) {
			c[Character.getNumericValue(String.valueOf(arr[i]).charAt(d))]++;
		}
		for (int i = 1; i < b; i++) {
			c[i]+=c[i-1];
		}
		for (int i =n-1; i >=0; i--) {
			//int[] temp2 = new int[n];
			int index = Character.getNumericValue(String.valueOf(temp[i]).charAt(d));//ovde temp[i],u tome je glavna fora jer ako stavis arr[i[,dok ga menjas ti preleplujes i onda ne budu
			//oni indeksi koje zelis
			arr[c[index]-1]=temp[i];
			c[index]--;
		}
	}
	
	//Problem 8-2 b
	public static void sortTheRecord(int[] arr) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		int i = 0;
		int j = arr.length - 1;
		while(true) {
			while(arr[i]==0) {
				i++;
				if(i==arr.length)break;
			}
			while(arr[j]==1) {
				j--;
				if(j==-1)break;
			}
			if(i>=j)break;
			exchange(arr,i,j);
			
		}
	}

	private static void exchange(int[] arr, int i1, int i2){
		int temp = arr[i1];
		arr[i1]=arr[i2];
		arr[i2]=temp;
		
	}
	//Problem 8.2 
	public static void countingSortInPlace(int[]arr ,int k) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		int[] c = new int[k+1];//ide od 1...k
		for (int i = 0; i <arr.length; i++) {
			c[arr[i]]++;
		}
		int j =0;
		int count = 1;
		
		for (int i = 0; i < arr.length; i++) {
			for (j =count; j <=c[count]+count-1; j++) {
				arr[i++]=count;
			}
			count++;
			i--;
		}
	}
	
	
	//Ovde impementiram RadixSort i Counting sort kao sa Geeksforgeeks,fora je u tome sto radi i za kada brojevi imaju razlicit broj cifara
	
	public static void radixSortGeeks(int[] arr,int b) throws Exception {
		if (arr == null || arr.length == 0) {
			throw new Exception("Meine");
		}
		int max = max(arr);
		for (int i = 1;(max/i)>0; i*=10) {
			countingSortGeeks(arr,b,i);
		}
	}
	private static void countingSortGeeks(int[] arr, int b, int exp) {
		int n = arr.length;
		int[] c = new int[b];
		int[] temp = new int[n];
		for (int i = 0; i < n; i++) {
			temp[i]=arr[i];
		}
		for (int i = 0; i < n; i++) {
			c[(arr[i]/exp)%10]++;
		}
		for (int i = 1; i < b; i++) {
			c[i]+=c[i-1];
		}
		for (int i = n - 1; i >=0; i--) {
			int index = (temp[i]/exp)%10;
			arr[c[index] - 1] =temp[i];
			c[index]--;
		}
	}

	public static int max(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]>max) {
				max = arr[i];			}
		}
		return max;
	}
	//8-4 c Algoritam je zasnovan na Randomized Quick Sort-u koji nisam radio ali razlika je u tome sto se u toku partition-a pivot bira random,a ne uvek poslednji.
	public static Pair[] groupBlueAndRedJugs(int[] blue,int[] red) throws Exception {
		if(blue==null || blue.length==0 || red==null || red.length==0||blue.length!=red.length) {
			throw new Exception("sdgds");
		}
		Pair[] p =  new Pair[blue.length];
		groupBlueAndRedJugsRec(blue,red,0,blue.length-1);
		for (int i = 0; i < p.length; i++) {
			p[i]= new Pair(blue[i], red[i]);
		}
		return p;
	}
	
	
	private static void groupBlueAndRedJugsRec(int[] blue, int[] red, int l, int r) {
		if(l>=r)return;
		int random = ThreadLocalRandom.current().nextInt(l, r+1);
		int q =partition(blue,l,r,red[random]);
		int p = partition(red,l,r,blue[q]);
		groupBlueAndRedJugsRec(blue, red, l, p-1);
		groupBlueAndRedJugsRec(blue, red, p+1, r);
		
	}
	
	

	private static int partition(int[] arr, int l, int r, int x) {
		int i =l-1;
		for (int j = l; j <= r-1; j++) {
			if(arr[j]==x) {
				exchange(arr, j, r);
			}
			if(arr[j]<x) {
				i++;
				exchange(arr, i, j);
			}
		}
		exchange(arr, i+1, r);
		return i+1;
	}

	//Exercise 11.3-4
	//Napisacu metodu da ne bih rucno racunao sve
	public static int hashFunction(int key,int m) {
		double a = (Math.sqrt(5) - 1) / 2 ;
		double h = Math.floor(m*(key*a % 1));
		return (int)h;
	}
	
	public static void main(String[] args) {
		int[] blue = {7,1,10,2,8,5,3,6,4,9};
		int[] red = {2,7,4,10,6,1,8,5,3,9};
		try {
			Pair[] p = Chapter8.groupBlueAndRedJugs(blue, red);
			for (int i = 0; i < p.length; i++) {
				System.out.println(p[i].blue+"->" + p[i].red);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
