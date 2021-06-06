package kurs;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree implements AVLTreeI {

	private AVLTreeNode root;

	// Oni su posmatrali visine kao -1,0,1... Ja cu da pocnem od 0(ako ne postoji)
	// pa 1,2,3... Samo zato sto mi je tako prirodnije
	
	/*public void insertWrapper(int info) throws Exception {
		if(root==null) {
			root = new  AVLTreeNode(info, 1, null, null);
			return;
		}
		 root=insert(root, info);//fora je u tome sto insert uvek ide skroz do gore,uvek ide do korena,a ako ne stavis taj poslednji da bude koren,onda ce ti koren ostati ono sto je bio
		 //a potencijalno je doslo do rotacije koja je promenila koren
	}*/
	//OSTALO MI JE DA PROVERIM SLUCAJ KAD IMA VISE ROTACIJA,ALI NE DUPLA,NEGO VISE KA GORE
	/*@Override
	public AVLTreeNode insert(AVLTreeNode node, int info) throws Exception {// mozda je bolje da se info zove key,ali
																			// svejedno u sustini
		if (node == null) {
			return new AVLTreeNode(info, 1, null, null);
		}
		if (info > node.info) {
			node.right = insert(node.right, info);
		} else if (info < node.info) {
			node.left = insert(node.left, info);
		} else {
			// return node;//Ne zelimo da postoje duplikati,jer su oni ovde keys,mozes u tom
			// slucaju da bacis i Exception
			throw new Exception("Duplicates are now allowed");
		}
		node.height = updatedHeight(node);

		int balance = balance(node);

		if (balance > 1) {// proveri ovaj deo,necu isto kao geeksforgeeks
			if (balance(node.left) == -1) {
				node.left = rotateLeft(node.left);
			}
			return rotateRight(node);
		}
		if (balance < -1) {
			if (balance(node.right) == 1) {
				node.right = rotateRight(node.right);
			}
			return rotateLeft(node);
		}
		return node;// ako je balansiran ovom gore ga vracas nepromenjenog
	}*/

	private AVLTreeNode rotateRight(AVLTreeNode x) {
		if (x == null)
			return null;
		AVLTreeNode y = x.left;
		AVLTreeNode z = y.right;// pokriva i kada je null i kada nije null

		y.right = x;
		x.left = z;
		// Bitno je kod azuriranja primetiti da se height gleda odozdo tako da
		// potencijalno se menja height samo kod onih gde je nesto ispod njih promenjeno
		x.height = updatedHeight(x);
		y.height = updatedHeight(y);

		return y;
	}

	private AVLTreeNode rotateLeft(AVLTreeNode x) {
		if (x == null)
			return null;
		AVLTreeNode y = x.right;
		AVLTreeNode z = y.left;

		y.left = x;
		x.right = z;

		x.height = updatedHeight(x);
		y.height = updatedHeight(y);

		return y;

	}

	private int updatedHeight(AVLTreeNode x) {
		return Math.max(height(x.left), height(x.right)) + 1;
	}

	private int height(AVLTreeNode x) {// mnogo je bolje napraviti ovde metodu nego svaki put proveravati te
										// null-ove.Cim se nesto vise puta ponavlja,napravi metodu koja to radi
		if (x == null)
			return 0;
		return x.height;
	}

	private int balance(AVLTreeNode x) {
		if (x == null)
			return 0;

		return height(x.left) - height(x.right);
	}

	private void inOrderTreeTraversal(AVLTreeNode node) {
		if (node == null)
			return;
		inOrderTreeTraversal(node.left);
		System.out.println(node.info);
		inOrderTreeTraversal(node.right);
	}

	private void levelOrderTraversal(AVLTreeNode node) {
		if (node == null)
			return;
		Queue<AVLTreeNode> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			AVLTreeNode temp = queue.remove();
			System.out.println(temp.info);
			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp.right);
			}
		}
	}
	//kada bih radio sam parent pokazivacem mogao bih da napravim metodu koja ce da nadje bas taj cvor,pa onda delete koji dobije bas cvor i onda vrsim rotacije sve do gore,ali
	//hocu da uradim bez parent pokazivaca cisto radi vezbe.Koristicu to sto dk ga trazim ja prodjem kroz sve pretke,sto znaci da se rekurzivno u povratku krecem kroz njih.
	@Override
	public void delete(int info) throws Exception {
		AVLTreeNode z = search(info);
		if(z==null)throw new Exception("There is no such node in the tree");
		AVLTreeNode x = z.parent;
		if(z.left==null) {
			transplant(z, z.right);
		}else if(z.right==null) {
			transplant(z, z.left);
		}else {
			x=null;
			AVLTreeNode y =min(z.right);
			if(y.parent!=z) {
				x=y.parent;
				transplant(y, y.right);
				y.right=z.right;
				y.right.parent=y;
					
			}
			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;//sigurno ima nesto levo jer ima oba deteta
			if(x==null) {
				x=y;
			}
		}
		while(x!=null) {
			x.height = updatedHeight(x);
			int balance = balance(x);
			if(balance<-1) {
				if(balance(x.right)==1) {
					rightRotate(x.right);
				}
				leftRotate(x);
				x=x.parent;
			}
			if(balance>1) {
				if(balance(x.left)==-1) {
					leftRotate(x.left);
				}
				rightRotate(x);
				x=x.parent;
			}
			x=x.parent;
		}
		
		
	}
	private AVLTreeNode search(int info) {
		AVLTreeNode x = root;
		while(x!=null) {
			if(x.info==info) {
				return x;
			}else if(info>x.info) {
				x=x.right;
			}else {
				x=x.left;
			}
		}
		return null;
	}
	
	
	private void transplant(AVLTreeNode u,AVLTreeNode v) {
		if(u.parent==null) {
			root=v;
		}else if(u==u.parent.left) {
			u.parent.left=v;
		}else {
			u.parent.right=v;
		}
		if(v!=null) {
			v.parent=u.parent;
		}
	}
	
	@Override
	public AVLTreeNode min(AVLTreeNode r) throws Exception {
		if(r==null)return null;
		while(r.left!=null) {
			r=r.left;
		}
		return r;
	}

	@Override
	public AVLTreeNode max(AVLTreeNode root) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AVLTreeNode successor(AVLTreeNode root) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AVLTreeNode predecessor(AVLTreeNode root) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		AVLTree avl = new AVLTree();
		try {
			avl.insert(12);
			avl.insert(7);
			avl.insert(27);
			avl.insert(4);
			avl.insert(8);
			avl.insert(20);
			avl.insert(30);
			avl.insert(3);
			avl.insert(28);
			avl.insert(15);
			avl.insert(22);
			avl.insert(14);
			avl.delete(27);
			


			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		avl.levelOrderTraversal(avl.root);
		
		
	}
	//Ipak cu raditi sa parentom,imam gore verziju insert-a rekurizvno bez parenta,sto je jako dobro kao ideja i bitno je to sto posle trazenja u povratku prolazis kroz sve prethodnike i mozes
	//da radis tacno ono sto zelis.Ali ipak zbog delete-a zelim da uradim ovo na svoj nacin.
	@Override
	public void insert( int info) throws Exception {
	
		AVLTreeNode x =root;
		AVLTreeNode y = null;
		while(x!=null){
			y=x;
			if(info>x.info) {
				x=x.right;
			}else if(info<x.info) {
				x=x.left;
			}else {
				throw new Exception("Duplicates are not allowed");
			}
		}
		if(y==null) {//situacija kada je prazno stablo,tu sigurno nema sta da se rotira
			root = new AVLTreeNode(info, 1, null, null, null);
			return;
		}
		if(info>y.info) {
			y.right = new AVLTreeNode(info, 1, null, null, y);
		}else {
			y.left = new AVLTreeNode(info, 1, null, null, y);
		}
		
		while(y!=null) {
			y.height = updatedHeight(y);
			int balance = balance(y);
			if(balance<-1) {
				if(balance(y.right)==1) {
					rightRotate(y.right);
				}
				leftRotate(y);
				y=y.parent;
			}
			if(balance>1) {
				if(balance(y.left)==-1) {
					leftRotate(y.left);
				}
				rightRotate(y);
				y=y.parent;
			}
			y=y.parent;
		}
		
		
	}

	private void rightRotate(AVLTreeNode x) {
		AVLTreeNode y =x.left;
		AVLTreeNode z = y.right;
		
		
		y.right =x;
		x.left =z;
		y.parent=x.parent;
		if(x==root) {
			root=y;
		}
		else if(y.parent.right == x) {
			y.parent.right =y;
		}else {
			y.parent.left = y;
		}
		x.parent=y;
		if(z!=null)z.parent=x;
		
		x.height = updatedHeight(x);
		y.height = updatedHeight(y);
		
	}

	private void leftRotate(AVLTreeNode x) {
		AVLTreeNode y = x.right;
		AVLTreeNode z = y.left;
		
		y.left = x;
		x.right = z;
		y.parent=x.parent;
		if(x==root) {
			root=y;
		}
		else if(y.parent.right == x) {
			y.parent.right =y;
		}else {
			y.parent.left = y;
		}
		x.parent =y;
		if(z!=null)z.parent = x;
				
		
		x.height = updatedHeight(x);
		y.height = updatedHeight(y);
	}
	
	
}
