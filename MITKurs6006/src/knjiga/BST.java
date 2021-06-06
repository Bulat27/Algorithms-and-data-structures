package knjiga;

import java.util.Stack;

public class BST {

	private BSTNode root;

	public void inorderTreeWalk(BSTNode r) {
		if (r == null)
			return;
		inorderTreeWalk(r.left);
		System.out.println(r.info);
		inorderTreeWalk(r.right);
	}

	public void preorderTreeWalk(BSTNode r) {
		if (r == null)
			return;
		System.out.println(r.info);
		preorderTreeWalk(r.left);
		preorderTreeWalk(r.right);
	}

	public void postorderTreeWalk(BSTNode r) {
		if (r == null)
			return;
		postorderTreeWalk(r.left);
		postorderTreeWalk(r.right);
		System.out.println(r.info);
	}

	public void insert(int info) {
		BSTNode x = root;
		BSTNode y = null;
		while (x != null) {
			y = x;
			if (info > x.info) {
				x = x.right;
			} else {
				x = x.left;
			}
		}
		BSTNode z = new BSTNode(info, null, null, y);
		if (y == null) {
			root = z;
			return;
		}
		if (info > y.info) {
			y.right = z;
		} else {
			y.left = z;
		}
	}

	// 12.1-3
	// non recursive inorder tree walk
	// prvo sa stackom,a onda bez stack-a
	
		
	public void iterativeInorderTreeWalk(BSTNode r) {
		if(r==null)return;
		Stack<BSTNode> stack = new Stack<>();
		boolean done = false;
		BSTNode curr = r;
		while(!done) {
			if(curr!=null) {
				stack.add(curr);
				curr=curr.left;
			}else {
				if(!stack.isEmpty()) {
					curr = stack.pop();
					System.out.println(curr.info);
					curr=curr.right;
				}else {
					done= true;
				}
			}
			
		}
				
		
	}
	
	
	

	public BSTNode treeSearch(BSTNode r, int key) {
		if (r == null || r.info == key) {
			return r;
		}
		if (key > r.info) {
			return treeSearch(r.right, key);
		} else {
			return treeSearch(r.left, key);
		}

	}

	public BSTNode treeSearchIterative(BSTNode r, int key) {
		while (r != null && r.info != key) {
			if (key > r.info) {
				r = r.right;
			} else {
				r = r.left;
			}
		}
		return r;
	}

	// Iterative versions
	public BSTNode min(BSTNode r) {
		if (r == null) {
			return null;// There is no min since it is empty
		}
		while (r.left != null) {
			r = r.left;
		}
		return r;
	}

	public BSTNode max(BSTNode r) {
		if (r == null) {
			return null;// There is no min since it is empty
		}
		while (r.right != null) {
			r = r.right;
		}
		return r;
	}

	public BSTNode successor(BSTNode x) {
		if (root == null || x == null) {
			return null;
		}
		if (x.right != null) {
			return min(x.right);
		}
		BSTNode y = x.parent;
		while (y != null && x != y.left) {
			x = y;
			y = x.parent;
		}
		return y;
	}

	// Exercise 12.2-2
	// recursive versions of min and max
	public BSTNode minRecursive(BSTNode r) {
		if (r == null || r.left == null) {
			return r;
		}
		return minRecursive(r.left);
	}

	public BSTNode maxRecursive(BSTNode r) {
		if (r == null || r.right == null) {
			return r;
		}
		return maxRecursive(r.right);
	}

	// Exercise 12.2-3
	// Tree-Predecessor
	public BSTNode treePredecessor(BSTNode x) {
		if (root == null || x == null) {
			return null;
		}
		if (x.left != null) {
			return max(x.left);
		}
		BSTNode y = x.parent;
		while (y != null && x != y.right) {
			x = y;
			y = x.parent;
		}
		return y;
	}

	// 12.2-7
	// Ne trazi mi u vezbanju da pisem ali zelim da bih bolje razumeo algoritam
	public void inorderTreeWalkModified() {
		if (root == null)
			return;
		BSTNode min = min(root);
		System.out.println(min.info);
		while ((min = successor(min)) != null) {
			System.out.println(min.info);
		}
	}
	
	//Deletion
	//Fora je u tome sto sad stvarno menjamo cvorove,a ne samo vrednosti kao sto smo radili u spa
	//transplant ne azurira pokazivace left i right,to se radi dodatno
	public void transplant(BSTNode u,BSTNode v) {
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
	public void treeDelete(BSTNode z) {
		if(root==null)return;
		if(z.left==null) {
			transplant(z, z.right);
		}else if(z.right==null) {
			transplant(z, z.left);
		}else {
			BSTNode y = min(z.right);
			if(y!=z.right) {
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = z;
			}
			transplant(z, y);
			y.left=z.left;
			y.left.parent = y;
		}
	}
	

	// Exercise 12.3-1
	// Recursive version of tree insert
	public void treeInsertRec(int info) {
		if (root == null) {
			root = new BSTNode(info, null, null, null);
			return;
		}
		treeInsertRecH(root, info);
	}

	private void treeInsertRecH(BSTNode r, int info) {
		if (info > r.info && r.right != null) {
			treeInsertRecH(r.right, info);
		} else if (info < r.info && r.left != null) {
			treeInsertRecH(r.left, info);
		} else {
			BSTNode z = new BSTNode(info, null, null, r);
			if (info > r.info) {
				r.right = z;
			} else {
				r.left = z;
			}
		}

	}

	public static void main(String[] args) {
		BST bst = new BST();
		bst.treeInsertRec(15);
		bst.treeInsertRec(6);
		bst.treeInsertRec(18);
		bst.treeInsertRec(3);
		bst.treeInsertRec(7);
		bst.treeInsertRec(17);
		bst.treeInsertRec(20);
		bst.treeInsertRec(2);
		bst.treeInsertRec(4);
		
		bst.iterativeInorderTreeWalk(bst.root);
		System.out.println();
		
		
		System.out.println(89 % 11);
		
		
		
	

	}

}
