package tests;

import model.AVLNode;
import model.AVLTree;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {

	private AVLTree<Integer> avl;
	
	private void setUpScenario1() {
		avl = new AVLTree<Integer>(new AVLNode<Integer>(new Integer(5), null, null));
	}
	
	private void setUpScenario2() {
		setUpScenario1();
		avl.insert(new Integer(8), null, null);
		avl.insert(new Integer(6), null, null);
	}
	
	@Test
	void testCreation() {
		setUpScenario1();
		assertNotNull(avl.getRoot());
	}
	
	@Test
	void testInsertion() {
		setUpScenario2();
		assertTrue(new AVLNode<Integer>(6, null, null).compareTo(avl.getRoot()) == 0);
		assertTrue(new AVLNode<Integer>(5, null, null).compareTo(avl.getRoot().getLeft()) == 0);
		assertTrue(new AVLNode<Integer>(8, null, null).compareTo(avl.getRoot().getRight()) == 0);
	}
	
	@Test
	void testFixUps() {
		setUpScenario2();
		avl.insert(10, null, null);
		avl.insert(4, null, null);
		avl.insert(3, null, null);
		assertTrue(new AVLNode<Integer>(6, null, null).compareTo(avl.getRoot()) == 0);
		assertTrue(new AVLNode<Integer>(4, null, null).compareTo(avl.getRoot().getLeft()) == 0);
		assertTrue(new AVLNode<Integer>(8, null, null).compareTo(avl.getRoot().getRight()) == 0);
		assertTrue(new AVLNode<Integer>(10, null, null).compareTo(avl.getRoot().getRight().getRight()) == 0);
		assertNull(avl.getRoot().getRight().getLeft());
		assertTrue(new AVLNode<Integer>(3, null, null).compareTo(avl.getRoot().getLeft().getLeft()) == 0);
		assertTrue(new AVLNode<Integer>(5, null, null).compareTo(avl.getRoot().getLeft().getRight()) == 0);
	}
}
