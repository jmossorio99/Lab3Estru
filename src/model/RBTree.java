package model;

public class RBTree<T extends Comparable<T>> {

	public final static int RED = 0;
	public final static int BLACK = 1;

	private RBNode<T> root = null;

	public void insert(RBNode<T> node) {

		RBNode<T> x = root;
		RBNode<T> y = null;
		while (x != null) {
			y = x;
			if (node.compareTo(x) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		node.setParent(y);
		if (y == null) {
			root = node;
		} else {
			if (node.compareTo(y) < 0) {
				y.setLeft(node);
			} else {
				y.setRight(node);
			}
		}
		node.setLeft(null);
		node.setRight(null);
		node.setColor(RED);
		fixUp(node);

	}

	private void fixUp(RBNode<T> node) {

		RBNode<T> current = node;
		while (current.getParent().getColor() == RED) {
			if (current.getParent() == current.getParent().getParent().getLeft()) {
				RBNode<T> y = current.getParent().getParent().getRight();
				if (y.getColor() == RED) {
					current.getParent().setColor(BLACK);
					y.setColor(BLACK);
					current.getParent().getParent().setColor(RED);
					current = current.getParent().getParent();
				} else {
					if (current == current.getParent().getRight()) {
						current = current.getParent();
						leftRotate(current);
					}
					current.getParent().setColor(BLACK);
					current.getParent().getParent().setColor(RED);
					rightRotate(current.getParent().getParent());
				}
			} else {
				RBNode<T> y = current.getParent().getParent().getLeft();
				if (y.getColor() == RED) {
					current.getParent().setColor(BLACK);
					y.setColor(BLACK);
					current.getParent().getParent().setColor(RED);
					current = current.getParent().getParent();
				} else {
					if (current == current.getParent().getLeft()) {
						current = current.getParent();
						rightRotate(current);
					}
					current.getParent().setColor(BLACK);
					current.getParent().getParent().setColor(RED);
					leftRotate(current.getParent().getParent());
				}
			}
		}
		root.setColor(BLACK);

	}

	public RBNode<T> deleteNode(RBNode<T> node) {

		RBNode<T> y = null;
		RBNode<T> x = null;
		if (node.getLeft() == null || node.getRight() == null) {
			y = node;
		} else {
			y = getSuccessor(node);
		}
		if (y.getLeft() != null) {
			x = y.getLeft();
		} else {
			x = y.getRight();
		}
		x.setParent(y.getParent());
		if (y.getParent() == null) {
			root = x;
		} else {
			if (y == y.getParent().getLeft()) {
				y.getParent().setLeft(x);
			} else {
				y.getParent().setRight(x);
			}
		}
		if (y != node) {
			node.setData(y.getData());
			node.setDate(y.getDate());
			node.setName(y.getName());
		}
		if (y.getColor() == BLACK) {
			deleteFixUp(x);
		}
		return y;

	}

	private void deleteFixUp(RBNode<T> x) {

		RBNode<T> w = null;
		while (x != root && x.getColor() == BLACK) {
			if (x == x.getParent().getLeft()) {
				w = x.getParent().getRight();
				if (w.getColor() == RED) {
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor() == BLACK && w.getRight().getColor() == BLACK) {
					w.setColor(RED);
					x = x.getParent();
				} else {
					if (w.getRight().getColor() == BLACK) {
						w.getLeft().setColor(BLACK);
						w.setColor(RED);
						rightRotate(w);
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(BLACK);
					w.getRight().setColor(BLACK);
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				w = x.getParent().getLeft();
				if (w.getColor() == RED) {
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if (w.getLeft().getColor() == BLACK && w.getLeft().getColor() == BLACK) {
					w.setColor(RED);
					x = x.getParent();
				} else {
					if (w.getLeft().getColor() == BLACK) {
						w.getLeft().setColor(BLACK);
						w.setColor(RED);
						leftRotate(w);
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(BLACK);
					w.getRight().setColor(BLACK);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor(BLACK);

	}

	public T getMinValue() {

		RBNode<T> current = root;
		if (current == null) {
			return null;
		}
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current.getData();

	}

	public T getMaxValue() {

		RBNode<T> current = root;
		if (current == null) {
			return null;
		}
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getData();

	}

	public RBNode<T> getMinNode(RBNode<T> node) {

		RBNode<T> current = node;
		if (current == null) {
			return null;
		}
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;

	}

	public RBNode<T> getMaxNode(RBNode<T> node) {

		RBNode<T> current = node;
		if (current == null) {
			return null;
		}
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current;

	}

	public RBNode<T> getSuccessor(RBNode<T> node) {

		if (node.getRight() != null) {
			return getMinNode(node.getRight());
		}
		RBNode<T> y = node.getParent();
		while (y != null && node == y.getRight()) {
			node = y;
			y = y.getParent();
		}
		return y;

	}

	private void leftRotate(RBNode<T> node) {

		RBNode<T> y = node.getRight();
		node.setRight(y.getLeft());
		y.getLeft().setParent(node);
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else {
			if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(y);
			} else {
				node.getParent().setRight(y);
			}
		}
		y.setLeft(node);
		node.setParent(y);

	}

	private void rightRotate(RBNode<T> node) {

		RBNode<T> y = node.getLeft();
		node.setLeft(y.getRight());
		y.getRight().setParent(node);
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else {
			if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(y);
			} else {
				node.getParent().setRight(y);
			}
		}
		y.setRight(node);
		node.setParent(y);

	}

	public RBNode<T> searchNode(T data) {
		if (root == null || root.getData().compareTo(data) == 0) {
			return root;
		}
		if (root.getData().compareTo(data) < 0) {
			return searchNodeRercursive(data, root.getLeft());
		} else {
			return searchNodeRercursive(data, root.getRight());
		}
	}

	private RBNode<T> searchNodeRercursive(T data, RBNode<T> node) {
		if (node == null || node.getData().compareTo(data) == 0) {
			return node;
		}
		if (node.getData().compareTo(data) < 0) {
			return searchNodeRercursive(data, node.getLeft());
		} else {
			return searchNodeRercursive(data, node.getRight());
		}
	}

}
