package kurs;

public interface AVLTreeI {
	
	public void insert(int info) throws Exception;
	public void delete(int info) throws Exception;
	public AVLTreeNode min(AVLTreeNode root)throws Exception;
	public AVLTreeNode max(AVLTreeNode root)throws Exception;
	public AVLTreeNode successor(AVLTreeNode root) throws Exception;
	public AVLTreeNode predecessor(AVLTreeNode root) throws Exception;
}
