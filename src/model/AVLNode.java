package model;

public class AVLNode<T extends Comparable<T>> {

	private T data;
	private AVLNode<T> left;
	private AVLNode<T> right;
	public int level;
	private int height;

	public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {

		this.data = data;
		this.left = left;
		this.right = right;
		height = 1;

	}

	public AVLNode(T data) {
		this(data, null, null);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
		this.left = right;
	}

	public void setHeight(int depth) {
		this.height = depth;
	}

	public int getHeight() {
		return height;
	}

	public int compareTo(AVLNode<T> o) {
		return this.data.compareTo(o.getData());
	}

}
