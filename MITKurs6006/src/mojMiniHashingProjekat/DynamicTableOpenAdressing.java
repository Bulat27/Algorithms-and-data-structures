package mojMiniHashingProjekat;



public class DynamicTableOpenAdressing implements DynamicTableI{
	
	private AutomobileWithFlag[] table;
	private int num;//// table.length is the size of the table,and num is the number of elements that
	// are actually in the table
	private int q;
	private int tableLength;
	
	//I will be using double hashing for my probing sequence,I will be usign h1 and h2 as recommended at Geeksforgeeks,it probably can be done better,but it will suffice for now.
	
	private int h1(String key) throws Exception {
		int h = key.hashCode();//I can implement this on my own for different radix,but I will use this one for now
		if(tableLength==0)throw new Exception("There is no els in the table");//I might not need this,I should prevenet it eariler
		h=h%tableLength;
		if(h<0)h+=tableLength;//OVAJ DEO POGLEDAJ ZASTO RADI,I NA GEEKSU SU TAKO RADILI,RAZMISLI O TOME
		return h;
	}
	
	private int h2(String key) throws Exception {
		int h = key.hashCode();
		if(tableLength==0)throw new Exception("There is no els in the table");//I might not need this,I should prevenet it eariler
		int ku = q;
		int length = tableLength;
		h=h%tableLength;
		if(h<0)h+=tableLength;
		return q - (h%q);
	}
	
	private int h(String key,int i) throws Exception {
		return (h1(key)+ i*h2(key)) % tableLength;
	}

	
	
	public DynamicTableOpenAdressing(AutomobileWithFlag[] table, int num, int q,int tableLength) {
		super();
		this.table = table;
		this.num = num;
		this.q = q;
		this.tableLength=tableLength;
	}

	@Override
	public void tableInsert(Automobile x) throws Exception {
		if (x == null)
			throw new Exception("The automobile does not exist");
		if (tableLength == 0) {
			table = new AutomobileWithFlag[1];
			tableLength=1;
		}
		if (num == tableLength) {
			AutomobileWithFlag[] t = new AutomobileWithFlag[tableLength * 2];
			q = getPrime(t.length);
			tableLength*=2;
			copyTable(t);
		
			table = t;
			//size *= 2;
		}
		insert(table,x);
		num++;
		
	}

	

	//https://www.geeksforgeeks.org/nearest-prime-less-given-number-n/ -OVDE JE JEDAN EFIKASAN NACIN DA SE NALAZE PRIME-OVI DO MILION
	//Ja cu za sad da implementriam relativno neefikasan nacin,nasao sam na stackoverflow objasnjenje zasto je dovoljno ici do korena
	//function that returns prime less than n,we will use n as table size
	private int getPrime(int n) {//GLEDA keca kao prime,i ako on nije,ali to mi odg za situaciju kad imam m=2,da q bude 1
		for (int i =n-1; i>=1; i--) {
			int count = 0;
			int sqrt =(int)Math.sqrt(i);
			for (int j = 2; j<=sqrt; j++) {
				if(i%j==0) {
					break;
				}
				count++;
			}
			if(count==sqrt-1) {
				return i;
			}
		}
		return 3;
	}

	private void copyTable(AutomobileWithFlag[] t) throws Exception {
		for (int i = 0; i < t.length/2; i++) {
			if(table[i]!=null) {
				insert(t,table[i].automobile);
			}
		}
		
	}

	private void insert(AutomobileWithFlag[] table,Automobile x) throws Exception {
		int i = 0;
		while(i<table.length) {
			int pos = h(x.getIdNumber(), i);
			if(table[pos]==null || table[pos].deleted) {
				table[pos]=new AutomobileWithFlag(x, false);
				return;
			}else {
				i++;
			}
		}
		throw new Exception("Table is full");
		//Baca mi ovde exception jer ovaj moj probe ide samo po 1 3 5 7,ne znam zasto,pokusacu jos malo da provalim,ako ne uradicu sa linear probingom.Problem je sto mi
		//probe sequence ne pokriva sva moguca polja
	}

	@Override
	public void tableDelete(Automobile x) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Automobile search(String idNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public void printTable() throws Exception {
		if(table==null)throw new Exception("Table does not exist");
		for (int i = 0; i < table.length; i++) {
			System.out.println(table[i].automobile.toString());
		}
	}
	
	public static void main(String[] args) {
		DynamicTableOpenAdressing t = new DynamicTableOpenAdressing(null, 0, 1, 0);
		
		Automobile a1 = new Automobile("Alfa Romeo", "147 Q2", "BG1390KD");
		Automobile a2 = new Automobile("Lancia", "Lybra", "BG144KP");
		Automobile a3 = new Automobile("Fiat", "Stilo", "BG227DP");
		Automobile a4 = new Automobile("Toyota", "Yaris", "BG156KN");
		Automobile a5 = new Automobile("Yugo", "Koral", "NS913PP");
		Automobile a6 = new Automobile("Fiat", "Bravo", "BG653DF");
		Automobile a7 = new Automobile("Fiat", "Brava", "BG4132JF");
		Automobile a8 = new Automobile("Ford", "Focus", "BG112LP");
		Automobile a9 = new Automobile("Fiat", "Punto", "NS6513DF");
		Automobile a10 = new Automobile("Alfa", "Brera", "KG1513KG");
		Automobile a11 = new Automobile("Mitsubishi", "Vitara", "BG421AF");
		Automobile a12 = new Automobile("Fiat", "Panda", "BG6118MM");
		Automobile a13 = new Automobile("Dacia", "Logan", "BG59RN");
		Automobile a14 = new Automobile("Dacia", "Sandero", "BG789PK");
		Automobile a15 = new Automobile("Hyundai", "i30", "BG263NB");
		
		try {
			t.tableInsert(a1);
			t.tableInsert(a2);
			t.tableInsert(a3);
			t.tableInsert(a4);
			t.tableInsert(a5);
			t.tableInsert(a6);
			t.tableInsert(a7);
			t.tableInsert(a8);
			t.tableInsert(a9);
			t.tableInsert(a10);
			t.tableInsert(a11);
			t.tableInsert(a12);
			t.tableInsert(a13);
			t.tableInsert(a14);
			t.tableInsert(a15);
			
			t.printTable();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
