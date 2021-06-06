package mojMiniHashingProjekat;

//SVE U SVEMU,ovo radi problem je samo sto nisam skroz odradio hash funkcije tako da verovatno postoji nacin koji ovo radi sa boljom verovatnocom,sa lepsom raspodelom
//Ovaj moj nacin lose radi za male brojeve,male tabele,ali kad se povecaju,skroz je ok,relativno uniformno razbaca elemente
//Ona situacija kada izmedju printa prazne redove je zbog toga sto ne brisem liste kad deletuj-em vec samo postavim na null,ali ona je bila inicijalizovana tako da ostane da postoji lista,i ako je
//head null

public class DynamicTable implements DynamicTableI {
	// Java LinkedList class uses a doubly linked list to store the elements. It
	// provides a linked-list data structure. It inherits the AbstractList class and
	// implements List and Deque interfaces.
	AutomobileDoubleyLinkedList[] table;
	int num;
	int q;// ne vidim za sad bolji nacin,nisam siguran koliko je pametno staviti ga kao
			// atribut
	// table.length is the size of the table,and num is the number of elements that
	// are actually in the table

	public DynamicTable(AutomobileDoubleyLinkedList[] table, int num, int q) {// mozda je bolje zakucati im default
																				// vrednosti na null,0 i 0 ,ali ja cu
																				// ovako jer i onako sad nije ceo
																				// projekat vec
		// ja samo pravim svaki put
		super();
		this.table = table;
		this.num = num;
		this.q = q;
	}

	@Override
	public void tableInsert(Automobile x) throws Exception {
		if (x == null)
			throw new Exception("The automobile does not exist");
		if (num == 0) {
			table = new AutomobileDoubleyLinkedList[1];
		}
		int size = table.length;
		if (num == size) {
			AutomobileDoubleyLinkedList[] t = new AutomobileDoubleyLinkedList[size * 2];
			q = nextPrime(t.length / 2);
			copyTable(table, t, q);
			/*
			 * for (int i = 0; i < table.length; i++) { while (table[i] != null &&
			 * table[i].head != null) { insert(t, table[i].head.automobile, q);// this
			 * insert will use hash method to find appropriate place // for an element and
			 * then call insert on the list that is // pointed to by that element
			 * table[i].head = table[i].head.next;// check this,I think that I can use the
			 * head to traverse and // change it since it will make new lists anyway } }
			 */
			table = t;
			size *= 2;
		}
		insert(table, x, q);
		num++;
	}

	private void copyTable(AutomobileDoubleyLinkedList[] table, AutomobileDoubleyLinkedList[] t, int q)
			throws Exception {
		for (int i = 0; i < table.length; i++) {
			while (table[i] != null && table[i].head != null) {
				insert(t, table[i].head.automobile, q);// this insert will use hash method to find appropriate place
														// for an element and then call insert on the list that is
														// pointed to by that element
				table[i].head = table[i].head.next;// check this,I think that I can use the head to traverse and
													// change it since it will make new lists anyway
			}
		}
	}

	// Imam problem sa stvaranjem sledeceg veceg prime-a,kopiracu kod sa geeks for
	// geeks-a da ne bih sada analizirao,nisam siguran kako utice na efikasnost i
	// ima li efikasnijeg nacina
	// da se nadje sledeci veci prime
	private boolean isPrime(int n) {
		// Corner cases
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;

		// This is checked so that we can skip
		// middle five numbers in below loop
		if (n % 2 == 0 || n % 3 == 0)
			return false;

		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;

		return true;
	}

	private int nextPrime(int N) {

		// Base case
		if (N <= 1)
			return 2;

		int prime = N;
		boolean found = false;

		// Loop continuously until isPrime returns
		// true for a number greater than n
		while (!found) {
			prime++;

			if (isPrime(prime))
				found = true;
		}

		return prime;
	}

	private void insert(AutomobileDoubleyLinkedList[] table, Automobile x, int q) throws Exception {
		if (table == null || x == null) {
			throw new Exception("My exception maan");
		}

		int pos = hash(x.getIdNumber(), 256, q);
		if (table[pos] == null) {
			table[pos] = new AutomobileDoubleyLinkedList(null);
		}
		table[pos].insertFirst(x);
	}

	// I will use division method since it is the simplest one
	// Razbaca mi elemente relativno uniformno,sigurno da moze da se napravi da bude
	// i bolje,ali trenutno nema svrhe da se previse udubim u to jer ce mi oduzeti
	// bas mnogo
	// vremena,ako ikada budem radio realnu implementaciju hashinga,potrebno je da
	// se bolje informisem i stavim bolje q,bolju hashing funkciju i tako dalje
	private int hash(String key, int d, int q) {
		int h = 0;
		for (int i = 0; i < key.length(); i++) {
			h = (d * h + key.charAt(i)) % q;
		}

		return h;
	}

	public void printTable() throws Exception {
		if (table == null) {
			throw new Exception();
		}
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				table[i].printList();

			}

		}
	}

	// Oni kazu da deletion treba da bude constant jer ti samo izbacis iz liste,ali
	// da bih to uradio potrebno mi je da prosledim bas cvor,a to mi nema mnogo
	// smisla,logcnije je da brisem preko
	// ID-a ili preko objekta Automobile,ali AutomobileNode,to mi nema
	// smisla.Verovartno da to njihovo ima logike,ali morao bih da vidim konkretnu
	// implementaciju da bih to uradio
	// Tako da cu ja traziti preko automobila,a ne cvora,i zato je losija
	// efikasnost.Nisam siguran sta se desava sa efikasnoscu,tesko je analizirati
	// jer bi trebalo potpuno znati ponasaje hash-a.
	@Override
	public void tableDelete(Automobile x) throws Exception {
		if (x == null)
			throw new Exception("Given automobile is null");
		int size = table.length;
		if (num == size / 4) {
			AutomobileDoubleyLinkedList[] t = new AutomobileDoubleyLinkedList[size / 2];
			q = nextPrime(t.length / 2);
			copyTable(table, t, q);
			table = t;
			size /= 2;
		}
		AutomobileNode a = nodeSeach(x.getIdNumber());
		int pos = hash(a.automobile.getIdNumber(), 256, q);// opet ovo moram da trazim,verovatno da moze bolje da se ne
															// bi gubila efikasnost
		table[pos].delete(a);
		num--;
	}

	private AutomobileNode nodeSeach(String idNumber) throws Exception {
		int pos = hash(idNumber, 256, q);
		if(table[pos]==null)throw new Exception("There is no such automobile");
		AutomobileNode a = table[pos].search(idNumber);
		if (a == null)
			throw new Exception("There is no automobile in the table with a given ID");
		return a;
	}

	@Override
	public Automobile search(String idNumber) throws Exception {
		if (idNumber == null || idNumber.length() == 0) {
			throw new Exception("Invalid ID");
		}
		return nodeSeach(idNumber).automobile;
	}

	public static void main(String[] args) {
		DynamicTable t = new DynamicTable(null, 0, 1);
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
			
			System.out.println();
			System.out.println(t.search("BG1390KD"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
