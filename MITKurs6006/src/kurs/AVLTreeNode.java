package kurs;

public class AVLTreeNode {

	int info;
	int height;
	AVLTreeNode left;
	AVLTreeNode right;
	AVLTreeNode parent;
	
	public AVLTreeNode(int info, int height, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent) {
		super();
		this.info = info;
		this.height = height;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	
}
