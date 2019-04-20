package model;

public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	public AVLTree() {
		root = null;
	}

	public AVLTree(AVLNode<T> root) {
		this.root = root;
	}

	public AVLNode<T> getRoot() {
		return root;
	}

	public T getMax() {

		AVLNode<T> current = root;
		if (current == null) {
			return null;
		}
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getData();

	}

	public T getMin() {

		AVLNode<T> current = root;
		if (current == null) {
			return null;
		}
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current.getData();

	}

	public void insert(T data, T date, T name) {

		root = insert(root, data, date, name);

	}

	private AVLNode<T> insert(AVLNode<T> node, T data, T date, T name) {

		if (node == null) {
			return new AVLNode<T>(data, date, name);
		} else {
			if (node.getData().compareTo(data) > 0) {
				node.setLeft(insert(node.getLeft(), data, date, name));
			} else {
				node.setRight(insert(node.getRight(), data, date, name));
			}
			node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
			int balanceFactor = balanceFactor(node);
			if (balanceFactor < -1) {
				if (balanceFactor(node.getRight()) > 0) {
					node.setRight(rotateRight(node.getRight()));
					return rotateLeft(node);
				} else {
					return rotateLeft(node);
				}
			} else if (balanceFactor > 1) {
				if (balanceFactor(node.getLeft()) < 0) {
					node.setLeft(rotateLeft(node.getLeft()));
					return rotateRight(node);
				} else {
					return rotateRight(node);
				}
			}
		}
		return node;

	}

	public AVLNode<T> delete(AVLNode<T> root, T data) {

		if (root == null) {
			return root;
		}
		if (data.compareTo(root.getData()) < 0) {
			root.setLeft(delete(root.getLeft(), data));
		} else if (data.compareTo(root.getData()) > 0) {
			root.setRight(delete(root.getRight(), data));
		} else {

			if (root.getLeft() == null || root.getRight() == null) {

				AVLNode<T> temp = null;
				if (temp == root.getLeft()) {
					temp = root.getRight();
				} else {
					temp = root.getLeft();
				}

				if (temp == null) {
					temp = root;
					root = null;
				} else {
					root = temp;
				}

			} else {
				AVLNode<T> temp = minValueNode(root.getRight());
				root.setData(temp.getData());
				root.setDate(temp.getDate());
				root.setName(temp.getName());
				root.setRight(delete(root.getRight(), temp.getData()));
			}

		}

		if (root == null) {
			return root;
		}
		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
		int balanceFactor = balanceFactor(root);
		if (balanceFactor < -1) {
			if (balanceFactor(root.getRight()) > 0) {
				root.setRight(rotateRight(root.getRight()));
				return rotateLeft(root);
			} else {
				return rotateLeft(root);
			}
		} else if (balanceFactor > 1) {
			if (balanceFactor(root.getLeft()) < 0) {
				root.setLeft(rotateLeft(root.getLeft()));
				return rotateRight(root);
			} else {
				return rotateRight(root);
			}
		}
		return root;

	}

	public int balanceFactor(AVLNode<T> node) {
		if (node == null) {
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	private AVLNode<T> rotateLeft(AVLNode<T> node) {

		AVLNode<T> r = node.getRight();
		node.setRight(r.getLeft());
		r.setLeft(node);
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		r.setHeight(Math.max(height(r.getLeft()), height(r.getRight())) + 1);
		return r;

	}

	private AVLNode<T> rotateRight(AVLNode<T> node) {

		AVLNode<T> l = node.getLeft();
		node.setLeft(l.getRight());
		l.setRight(node);
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		l.setHeight(Math.max(height(l.getLeft()), height(l.getRight())) + 1);
		return l;

	}

	private int height(AVLNode<T> node) {
		if (node == null) {
			return 0;
		}
		return node.getHeight();
	}

	private AVLNode<T> minValueNode(AVLNode<T> node) {

		AVLNode<T> current = node;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;

	}

}
