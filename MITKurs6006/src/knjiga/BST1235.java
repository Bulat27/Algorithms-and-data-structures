package knjiga;

public class BST1235 {
	// Exercise 12.3-5 trazi promenjen node
	BSTNode1235 root;

	public void inorderTreeWalk(BSTNode1235 r) {
		if (r == null)
			return;
		inorderTreeWalk(r.left);
		System.out.println(r.info);
		inorderTreeWalk(r.right);
	}

	// mislim da su pogresili na
	public void insert(int info) {
		BSTNode1235 x = root;
		BSTNode1235 y = null;
		BSTNode1235 temp = null;
		while (x != null) {
			y = x;
			if (info > x.info) {
				temp = x;// mnogo bitna fora jer mora da se azurira succesor na poslednjem mestu gde si
							// krenuo desno jer ce taj dodati biti najmanji u njegovom desnom podstablu
				// medjutim ako si isao samo levo(ako je ovo ostalo null) onda nista ne menjas
				// jer kad ubacis najmanji element nikome ne menjas succesor
				x = x.right;
			} else {
				x = x.left;
			}
		}
		BSTNode1235 z = new BSTNode1235(info, null, null, null);// moram da azuriram
		if (y == null) {
			root = z;// successor mu ostaje null
		} else {// u ova dva slucaja mi umecemo neki novi el medju vec postojuce sto znaci da se
				// dva successor pokazivac menjaju
			if (info > y.info) {
				y.right = z;
				z.successor = y.successor;
				y.successor = z;
			} else {
				y.left = z;
				z.successor = y;
				if (temp != null) {// ako nisi isao samo levo (ako si isao samo levo,onda ni ne treba da se menja)
					temp.successor = z;
				}
				// predecessor(z).successor = z;//sigurno ce da krene na gore jer ovaj nema
				// desno podstablo jer je list
			}
		}

	}

	private BSTNode1235 predecessor(BSTNode1235 x) {
		if (root == null || x == null) {
			return null;
		}
		if (x.left != null) {
			return max(x.left);
		}
		BSTNode1235 p = parent(root, x.info);
		BSTNode1235 y = p;
		while(y!=null && y.left==x) {//nisam bas siguran da je ovo ovde logaritamski
			x=y;
			y=parent(root, y.info);
		}
		return y;
	}

	private BSTNode1235 min(BSTNode1235 r) {
		if(r==null)return null;
		while(r.left!=null) {
			r=r.left;
		}
		return r;
	}
	
	public BSTNode1235 max(BSTNode1235 r) {
		if (r == null) {
			return null;// There is no min since it is empty
		}
		while (r.right != null) {
			r = r.right;
		}
		return r;
	}
	// Search algoritam je potpunu isti pa ga necu ponovo pisati

	public void treeDelete(BSTNode1235 z) {
		if (root == null || z == null)return;
		BSTNode1235 predecessor =predecessor(z);
		if(predecessor!=null) {
			predecessor.successor =z.successor; 
		}
		if(z.left==null) {
			transplant(z, z.right);
		}else if(z.right == null) {
			transplant(z, z.left);
		}else {
			BSTNode1235 y = min(z.right);
			if(parent(root, y.info)!= z) {
				transplant(y, y.right);
				y.right=z.right;
			}
			transplant(z, y);
			y.left = z.left;
		}
			
	}

	// We assume that all the keys are distinct
	private BSTNode1235 parent(BSTNode1235 r, int info) {
		if (r == null || r.info == info)
			return null;

		if ((r.left != null && r.left.info == info) || (r.right != null && r.right.info == info)) {
			return r;
		}
		if (info > r.info) {
			return parent(r.right, info);
		} else {
			return parent(r.left, info);
		}

	}
	//zanimljiva ideja za parent metodu sa neta
	
	private BSTNode1235 parent2(BSTNode1235 r,BSTNode1235 x) {
		if(r==x)return null;
		BSTNode1235 y = max(x).successor;
		if(y==null) {
			y=root;
		}else {
			if(y.left == x)return y;
			y=y.left;
		}
		while(y.right!=x) {
			y=y.right;
		}
		return y;
		
	}
	
	

	private void transplant(BSTNode1235 u, BSTNode1235 v) {
		BSTNode1235 p = parent(root, u.info);
		if (p == null) {
			root = v;
		} else if (p.right == u) {
			p.right = v;
		} else {
			p.left = v;
		}
	}

	public static void main(String[] args) {
		BST1235 bst = new BST1235();
		bst.insert(12);
		bst.insert(5);
		bst.insert(18);
		bst.insert(2);
		bst.insert(9);
		bst.insert(15);
		bst.insert(19);
		bst.insert(13);
		bst.insert(17);
		bst.treeDelete(bst.root.left);
		System.out.println(bst.root.left.left.successor.info);

		

	}

}
