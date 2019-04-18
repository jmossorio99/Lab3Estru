package model;

public class AVLNode<T extends Comparable<T>> {

	private T date;
	private T name;
	private T data;
	private AVLNode<T> left;
	private AVLNode<T> right;
	public int level;
	private int height;

	public AVLNode(T data, T date, T name, AVLNode<T> left, AVLNode<T> right) {

		this.data = data;
		this.date = date;
		this.name = name;
		this.left = left;
		this.right = right;
		height = 1;

	}

	public AVLNode(T data, T date, T name) {
		this(data, date, name, null, null);
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
		this.right = right;
	}

	public void setHeight(int depth) {
		this.height = depth;
	}

	public int getHeight() {
		return height;
	}

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}

	public T getName() {
		return name;
	}

	public void setName(T name) {
		this.name = name;
	}

	public int compareTo(AVLNode<T> o) {
		return this.data.compareTo(o.getData());
	}

}
