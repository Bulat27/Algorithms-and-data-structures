package kurs;

import knjiga.BST;

public class RunwayReservationSystem {
	//ova struktura je implementirana sa Lecture 5,pokazuje upotrebu BST stabla
	
	private RRSNode root;
	//check i increment mogu da se urade u jednom prolazu,ali to je malo zajebanije,prvo cu ovako,ne zaboravi da pokusas taj bolji nacin
	public void insertFlight(int time,int k) {
		if(isTaken(time, k)) {
			return;
		}
		RRSNode x = root;
		RRSNode y = null;
		while(x!=null) {
			x.size++;
			y=x;
			if(time>x.time) {
				x=x.right;
			}else {
				x=x.left;
			}
		}
		RRSNode z = new RRSNode(1, time, null, null, y);
		if(y==null) {
			root=z;
			return;
		}
		if(time>y.time) {
			y.right = z;
		}else {
			y.left=z;
		}
		
	}
	private boolean isTaken(int time,int k) {
		RRSNode x= root;
		while(x!=null) {
			if(Math.abs(x.time-time)<k) {
				return true;
			}
			if(time>x.time) {
				x=x.right;
			}else {
				x=x.left;
			}
		}
		return false;
	}
	public void inorderTreeWalk(RRSNode r) {
		if(r==null)return;
		inorderTreeWalk(r.left);
		System.out.println(r.time +"->" + r.size);
		inorderTreeWalk(r.right);
	}
	//max,min,predecessor i successor su isti kao sa obican BST,a vec sam ih radio par puta tako da necu ponovo
	//Nije dobar delete,problem je u tome sto se ne updatu-je size od 42 (succesora) vec ostaje ono sto je on bio,a to ne valja.Treba da
	//razmotrim u kojim sve situcijama moze doci do ovoga,ovo je prva situacija(kada ima oba deteta pa se menja i zato i nastaje problem).Fora je
	//sto transplant menja cvorove i onda cvor zadrzi svoj size,tako da update nije dovoljan.U mainu ti sad stoji ova situacija koja ne valja,vidi koje 
	//jos sve postoje
	public void delete(RRSNode z) {
		if(root==null)return;
		update(z);
		if(z.left==null) {
			transplant(z, z.right);
		}else if(z.right==null) {
			transplant(z, z.left);
		}else {
			RRSNode y = min(z.right);
			if(y!=z.right) {
				RRSNode temp = y;
				while(temp!=z) {
					temp.size--;
					temp=temp.parent;
				}
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = z;
			}
			transplant(z, y);
			y.left=z.left;
			y.left.parent = y;
			y.size = z.size - 1;
		}
		
	}
	private void update(RRSNode z) {//ne treba mi pomocni pokazivac jer metoda ne menja stvarno z vec radi sa njegovom kopijom,a opet menja stvarno atritbute
		z=z.parent;                       
		while(z!=null) {
			z.size--;//mogu da preskocim za z jer njega svakako brisem,ali nije ni bitno posto ce svejedno biti obrisan,ipak moram da preskocim jer sam menjao metodu!
			z=z.parent;
		}
	}
	private RRSNode min(RRSNode r) {
		if(r==null)return null;
		while(r.left!=null) {
			r=r.left;
		}
		return r;
	}
	public void transplant(RRSNode u,RRSNode v) {
		if(u.parent==null) {
			root = v;
		}else if(u == u.parent.left) {
			u.parent.left = v;
		}else {
			u.parent.right = v;
		}
		if(v!=null) {
			v.parent = u.parent;
		}
	}
	//Poenta je sto nemam cvor ciji rank zelim da vratim vec vreme ciji rank zelim da vratim.Samim tim,potrebno je da istvoremeno trazim taj cvor i da sabiram sve koji su pre njega.
	public int rank(int t) throws Exception {
		if(root==null) {
			throw new Exception();
		}
		int rank = 0;
		RRSNode r = root;
		while(t!=r.time) {
			if(t > r.time) {
				rank++;
				rank+= r.left!=null ? r.left.size : 0;
				r=r.right; 
			}else {
				r=r.left;
			}
		}
		rank++;
		rank+= r.left!=null ? r.left.size : 0;
		return rank;
	}
	
	
	public static void main(String[] args) {
		RunwayReservationSystem rss = new RunwayReservationSystem();
		rss.insertFlight(49, 3);
		rss.insertFlight(23, 3);
		rss.insertFlight(85, 3);
		rss.insertFlight(15, 3);
		rss.insertFlight(28, 3);
		rss.insertFlight(75, 3);
		rss.insertFlight(98, 3);
		rss.insertFlight(71, 3);
		rss.insertFlight(67, 3);
		rss.insertFlight(60, 3);
		rss.inorderTreeWalk(rss.root);
		
		try {
			System.out.println(rss.rank(60));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
