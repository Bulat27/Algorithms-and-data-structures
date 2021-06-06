package knjiga;



public class IntegerArithmetic {
	// Radim Karastuba Multiplication algoritam, pokusacu da uradim koristeci
	// BigInteger klasu, ne znam da li time gubim na efikasnosti, ako ikad budem
	// radio nesto vise,
	// moracu da se dalje informisem o ovome svakako
	// Ipak necu raditi sa BigInteger klasom, radicu sa long, ne zelim da maglim sam
	// algoritam, ukoliko mi ikad bude potreban rad sa zaista velikim brojevima
	// svakako cu morati
	// da se udubim u temu. Ipak cu pokusati da ih privatim kao stringove

	// XY = 22ceil(n/2) XlYl + 2ceil(n/2) * [(Xl + Xr)(Yl + Yr) - XlYl - XrYr] +
	// XrYr
	public static long karatsubaMultiplication(String x, String y, int r) throws Exception {
		if (x == null || x.length() == 0 || x == null || x.length() == 0 || r < 1) {
			throw new Exception("Bad parameters");
		}
		/*int dif = x.length() - y.length();
		if (dif > 0) {
			y = fill(y, dif);
		} else if (dif < 0) {
			x = fill(x, Math.abs(dif));
		}*/

		return karatsubaRec2(x, y, r);
	}

	private static long karatsubaRec(String x, String y, int r) {
		if (x.length() == 1 || y.length() == 1) {
			return Long.parseLong(x) * Long.parseLong(y);// nije se bunio ni sa parseInt al za svaki slucaj
		}
		int dif = x.length() - y.length();//Radi ako ih svaki put izravnam, to moze biti neefikasno mada i nije strasno s obzirom da te razlike u duzini uglavnom budu male, vidi kako da se to
		//uradi bez ovog nadodavanja nula u svakoj iteraciji
		if (dif > 0) {
			y = fill(y, dif);
		} else if (dif < 0) {
			x = fill(x, Math.abs(dif));
		}

		
		double temp = (double) x.length() / 2;
		int m = (int) Math.floor(temp);
		String xL = x.substring(0, m);
		String xR = x.substring(m);
		String yL = y.substring(0, m);
		String yR = y.substring(m);

		long z0 = karatsubaRec(xL, yL, r);
		long z2 = karatsubaRec(xR, yR, r);
		long z1 = karatsubaRec(addUpStrings(xL, xR),addUpStrings(yL, yR), r) - z0 - z2;
		int ceil =(int) Math.ceil(temp);
		
		return powerN(r, 2 * ceil) * z0 + powerN(r, ceil) * z1 + z2;// Moguce je koristiti neki efikasniji algoritam za
																// stepenovanje ali s obzirom da je r osnova, nema ni
																// smisla da bude neki ogroman broj
		// bice 2 ili 10 u vecini slucajeva. m bi mogao biti problematican ako su bas
		// ogromni brojevi
	}

	// ovo je neka matematicka metoda koja se koristi za efikasnje stepenovanje, za
	// sad sam je kopirao, ako mi bude trebalo mogu i da je analiziram
	private static long powerN(long number, int power) {
		long res = 1;
		long sq = number;
		while (power > 0) {
			if (power % 2 == 1) {
				res *= sq;
			}
			sq = sq * sq;
			power /= 2;
		}
		return res;
	}
	//Operator + concatenates two string by default, I need a method that will add up their number values and return it as String
	private static String addUpStrings(String x,String y) {
		return String.valueOf(Long.parseLong(x)+Long.parseLong(y));
	}
	
	private static void makeLenghtsEqual(String x, String y) {
		int dif = x.length() - y.length();
		if (dif > 0) {
			fill(y, dif);
		} else if (dif < 0) {
			fill(x, Math.abs(dif));
		}

	}

	private static String fill(String x, int count) {
		for (int i = 0; i < count; i++) {
			x = 0 + x;
		}
		return x;
	}

	//REKAPITULACIJA: Radi mi ovaj algoritam gore, medjutim ima jedan problem. Algoritam je efikasniji od obicnog samo u slucaju kad su duzine borjeva koji se mnoze iste ili priblize duzibne. Fora je
	//u tome sto npr. 123456*12 u klasicnom mnozenju ima 12 mnozenja jednocifrenih brojeva. Karastuba ce u tom slucaju da napuni nulama i mnozi 123456*0000012 i onda ce to biti manje efikasno zato
	//sto su mnogo duzi brojeve pa ce i pored efikasnijeg algoritma imati vise mnozenja brojeva duzine 1 jer ce mnogo puta mnoziti sa dodatim nulama. Pokusacu da napisem algoritam koji ce
	//da posebno deli ova dva broja i da odma mnozi sa nulom kada je potrebno,a ne da padduje sa 100 nula i sjebava efikasnost.
	
	private static long karatsubaRec2(String x, String y, int r) {
		if (x.length() == 1 || y.length() == 1) {
			return Long.parseLong(x) * Long.parseLong(y);// nije se bunio ni sa parseInt al za svaki slucaj
		}
	
		if(y.length()>x.length()) {//ovo radim da bi radilo i kad je duzi y i kad je duzi x, a da ne moram da menjam kod, mozda moze malo efikasnije, al ko ga jebe sad
			String t = x;
			x=y;
			y=t;
		}
		
		int max = x.length();
		double temp = (double) max / 2;
		int m = (int) Math.floor(temp);
		String xL = x.substring(0, m);
		String xR = x.substring(m);
		
		int dif = xR.length()-y.length();
		String yR="";
		String yL="";
		if(dif>=0) {
			yR = y;
			 yL = "0";
		}else {
			int absDif = Math.abs(dif);
			 yR =y.substring(absDif);
			 yL = y.substring(0, absDif);
		}
		

		long z0 = karatsubaRec2(xL, yL, r);
		long z2 = karatsubaRec2(xR, yR, r);
		long z1 = karatsubaRec2(addUpStrings(xL, xR),addUpStrings(yL, yR), r) - z0 - z2;
		int ceil =(int) Math.ceil(temp);
		
		return powerN(r, 2 * ceil) * z0 + powerN(r, ceil) * z1 + z2;// Moguce je koristiti neki efikasniji algoritam za
																// stepenovanje ali s obzirom da je r osnova, nema ni
																// smisla da bude neki ogroman broj
		// bice 2 ili 10 u vecini slucajeva. m bi mogao biti problematican ako su bas
		// ogromni brojevi
	}
	

	
	
	
	
	public static void main(String[] args) {
		String x= "143";
		String y ="152";
		try {
			System.out.println(karatsubaMultiplication(x, y, 10));
			long a= 143;
			long b = 152;
			System.out.println(a*b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
