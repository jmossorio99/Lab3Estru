package model;

public class RBTree<T extends Comparable<T>> {

	public final static int RED = 0;
	public final static int BLACK = 1;

	private RBNode<T> nil = new RBNode<T>();
	private RBNode<T> root = nil;

	public RBTree() {

		root.setLeft(nil);
		root.setRight(nil);
		root.setParent(nil);

	}

	public RBNode<T> getRoot(){
		return root;
	}
	
	public void insert(RBNode<T> z) {

		RBNode<T> x = root;
		RBNode<T> y = nil;

		while (!isNil(x)) {

			y = x;
			if (z.getData().compareTo(x.getData()) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}

		}
		z.setParent(y);
		if (isNil(y)) {
			root = z;
		} else {
			if (z.getData().compareTo(y.getData()) < 0) {
				y.setLeft(z);
			} else {
				y.setRight(z);
			}
		}
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(RED);
		fixUp(z);
	}

	private void fixUp(RBNode<T> z) {

		RBNode<T> y = nil;

		while (z.getParent().getColor() == RED) {

			if (z.getParent() == z.getParent().getParent().getLeft()) {
				y = z.getParent().getParent().getRight();
				if (y.getColor() == RED) {
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				} else if (z == z.getParent().getRight()) {

					z = z.getParent();
					leftRotate(z);
				} else {
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					rightRotate(z.getParent().getParent());
				}

			} else {

				y = z.getParent().getParent().getLeft();
				if (y.getColor() == RED) {
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				} else if (z == z.getParent().getLeft()) {

					z = z.getParent();
					rightRotate(z);

				} else {
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					leftRotate(z.getParent().getParent());
				}
			}

		}
		root.setColor(BLACK);

	}

	public RBNode<T> deleteNode(RBNode<T> node) {

		RBNode<T> y = nil;
		RBNode<T> x = nil;
		if (node.getLeft() == nil || node.getRight() == nil) {
			y = node;
		} else {
			y = getSuccessor(node);
		}
		if (!isNil(y.getLeft())) {
			x = y.getLeft();
		} else {
			x = y.getRight();
		}
		x.setParent(y.getParent());
		if (isNil(y.getParent())) {
			root = x;
		} else {
			if (!isNil(y.getParent().getLeft()) && y == y.getParent().getLeft()) {
				y.getParent().setLeft(x);
			} else if (!isNil(y.getParent().getRight()) && y == y.getParent().getRight()) {
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

		RBNode<T> w;
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

	private void leftRotate(RBNode<T> x) {

		RBNode<T> y = x.getRight();
		x.setRight(y.getLeft());
		if (!isNil(y.getLeft())) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if (isNil(x.getParent())) {
			root = y;
		} else if (x.getParent().getLeft() == x) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);

	}

	private void rightRotate(RBNode<T> y) {

		RBNode<T> x = y.getLeft();
		y.setLeft(x.getRight());
		if (!isNil(x.getRight())) {
			x.getRight().setParent(y);
		}
		x.setParent(y.getParent());
		if (isNil(y.getParent())) {
			root = x;
		} else if (y.getParent().getRight() == y) {
			y.getParent().setRight(x);
		} else {
			y.getParent().setLeft(x);
		}
		x.setRight(y);
		y.setParent(x);

	}

	private boolean isNil(RBNode<T> x) {

		return x == nil;

	}

	public T getMinValue() {

		RBNode<T> current = root;
		if (isNil(current)) {
			return null;
		}
		while (!isNil(current.getLeft())) {
			current = current.getLeft();
		}
		return current.getData();

	}

	public T getMaxValue() {

		RBNode<T> current = root;
		if (isNil(current)) {
			return null;
		}
		while (!isNil(current.getRight())) {
			current = current.getRight();
		}
		return current.getData();

	}

	public RBNode<T> getMinNode(RBNode<T> node) {

		RBNode<T> current = node;
		if (isNil(current)) {
			return null;
		}
		while (!isNil(current.getLeft())) {
			current = current.getLeft();
		}
		return current;

	}

	public RBNode<T> getMaxNode(RBNode<T> node) {

		RBNode<T> current = node;
		if (isNil(current)) {
			return null;
		}
		while (!isNil(current.getRight())) {
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

	public RBNode<T> search(T key) {

		RBNode<T> current = root;
		while (!isNil(current)) {

			if (current.getData().equals(key)) {

				return current;

			} else if (current.getData().compareTo(key) < 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}

		}
		return null;

	}

}
