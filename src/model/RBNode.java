package model;

public class RBNode<T extends Comparable<T>> {

	private T data;
	private int color = RBTree.BLACK;
	private RBNode<T> left = null;
	private RBNode<T> right = null;
	private RBNode<T> parent = null;

	public RBNode(T data, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public RBNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public RBNode<T> getLeft() {
		return left;
	}

	public void setLeft(RBNode<T> left) {
		this.left = left;
	}

	public RBNode<T> getRight() {
		return right;
	}

	public void setRight(RBNode<T> right) {
		this.right = right;
	}

	public RBNode<T> getParent() {
		return parent;
	}

	public void setParent(RBNode<T> parent) {
		this.parent = parent;
	}

	public int compareTo(RBNode<T> o) {
		return this.data.compareTo(o.getData());
	}

}
