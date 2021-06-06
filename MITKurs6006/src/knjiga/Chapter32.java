package knjiga;

import java.io.BufferedReader;
import java.io.FileReader;

public class Chapter32 {

	public static void naiveStringMatcher(String docName, String p) throws Exception {// t is the name of the file that
																						// we are examining,p is the
																						// pattern we are looking for
		if (p == null || p.length() == 0 || docName == null || docName.length() == 0) {
			throw new Exception("One of the strings does not exist");
		}
		String t = loadFromDoc(docName);
		int n = t.length();
		int m = p.length();
		for (int i = 0; i <= n - m; i++) {
			String temp = t.substring(i, i + m);
			int endIndex = i + m - 1;
			if (p.equals(temp)) {
				System.out.println("Pattern occurs at indices:" + i + "->" + endIndex);
			}
		}
	}

	private static String loadFromDoc(String docName) {
		String s = "";// nisam siguran da je ovo dobra praksa,ali mislim da moze da prodje
		try (FileReader fIn = new FileReader(docName); BufferedReader brIn = new BufferedReader(fIn);) {
			boolean kraj = false;
			while (!kraj) {
				String pom = brIn.readLine();
				if (pom == null) {
					kraj = true;
				} else {
					s += pom;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		return s;
	}

	// Exercise 32.1-2
	// Oni ne traze implementaciju,ali ja hocu
	public static void naiveStringMatcher2(String t, String p) throws Exception {
		if (p == null || p.length() == 0 || t == null || t.length() == 0) {
			throw new Exception("One of the strings does not exist");
		}

		int n = t.length();
		int m = p.length();
		for (int i = 0; i <= n - m; i++) {
			int count = 0;
			for (int j = 0; j < m; j++) {
				if (p.charAt(j) == t.charAt(i)) {
					i++;
					count++;
					if (count == m) {
						int beginIndex = i - m;
						int endIndex = i - 1;
						System.out.println("Pattern occurs at indices:" + beginIndex + "->" + endIndex);
						i--;
					}
				} else {
					if (count != 0)
						i--;
					break;
				}
			}
		}
	}

	// Rabin-Karp-Matcher
	public static void RabinKarpMatcher(String docName, String P, int d, int q) throws Exception {
		if (P == null || P.length() == 0 || docName == null || docName.length() == 0) {
			throw new Exception("One of the strings does not exist");
		}
		String T = loadFromDoc(docName);
		int n = T.length();
		int m = P.length();
		//int temp = (int) (Math.pow(d, m - 1));
		int h =1;
		 for ( int i = 0; i < m-1; i++) {//mora ovako,fora je u tome sto daje isti broj na neku foru(isto bude kao da si ga digao na power pa uradio mod q),ALI kad radis onako h
			 //veoma brzo predje preko max vrednosti za integer i zato je ovaj nacin jedini dobar
	            h = (h*d)%q;
		 }
		//int h = temp % q;
		int p = 0;
		int t = 0;
		// int tempP = 0;
		// int tempT = 0;
		for (int i = 0; i < m; i++) {
			p = (d * p + P.charAt(i)) % q;
			// tempP = (d*tempP + P.charAt(i)) % q;
			// tempT =(d*tempT) + T.charAt(i) % q;

			t = (d * t + T.charAt(i)) % q;
		}
		for (int s = 0; s <= n - m; s++) {
			if (p == t) {
				String k = T.substring(s, s + m);
				if (P.equals(k)) {
					int endIndex = s + m - 1;// Sabiram ovde jer ako to uradim na licu mesta,on u printu ih nadovezuje
												// kao stringove i ne sabere lepo
					System.out.println("Pattern occurs at indices: " + s + "->" + endIndex);
				}
			}
			if (s < n - m) {// ovo mora da se proveri jer ce inace u poslednjem koraku(kada je s=n-m)
							// pokusati da uzme karakter koji je out of range i puci ce
				t = (d * (t - T.charAt(s) * h) + T.charAt(s + m)) % q;

				if (t < 0) {//ovo nema u knjizi,ne razumem zasto su propustili kada cesto ode u minus
					t = (t + q);
				}
			}
		}

	}

	public static int check(int m,int q,int d) {
		int h = 1;
		 for ( int i = 0; i < m-1; i++) {
	            h = (h*d)%q;
		 }
		 return h;
	}

	public static void main(String[] args) {
		String a ="Nikola";
		String b ="Bulat";
		System.out.println(a.equals(b));
	}

}
