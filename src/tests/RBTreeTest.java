package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.RBNode;
import model.RBTree;

public class RBTreeTest {

	private RBTree<Integer> rb;
	
	private void setUpScenario1() {
		rb = new RBTree<Integer>();
		rb.insert(new RBNode<Integer>(11, null, null));
	}
	
	private void setUpScenario2() {
		setUpScenario1();
		rb.insert(new RBNode<Integer>(14, null, null));
		rb.insert(new RBNode<Integer>(2, null, null));
	}
	
	private void setUpScenario3() {
		setUpScenario2();
		rb.insert(new RBNode<Integer>(15, null, null));
		rb.insert(new RBNode<Integer>(1, null, null));
		rb.insert(new RBNode<Integer>(7, null, null));
		rb.insert(new RBNode<Integer>(5, null, null));
		rb.insert(new RBNode<Integer>(8, null, null));
	}
	
	@Test
	void testCreation() {
		setUpScenario1();
		assertNotNull(rb.getRoot());
		assertEquals(RBTree.BLACK, rb.getRoot().getColor());
	}
	
	@Test
	void testInsertion() {
		setUpScenario2();
		assertTrue(rb.getRoot().getRight().compareTo(new RBNode<Integer>(14, null, null)) == 0);
		assertTrue(rb.getRoot().getLeft().compareTo(new RBNode<Integer>(2, null, null)) == 0);
	}
	
	@Test
	void testColoring() {
		setUpScenario3();
		assertEquals(RBTree.BLACK, rb.getRoot().getColor());
		assertEquals(RBTree.BLACK, rb.getRoot().getRight().getColor());
		assertEquals(RBTree.BLACK, rb.getRoot().getLeft().getLeft().getColor());
		assertEquals(RBTree.BLACK, rb.getRoot().getLeft().getRight().getColor());
		assertEquals(RBTree.RED, rb.getRoot().getLeft().getColor());
		assertEquals(RBTree.RED, rb.getRoot().getLeft().getRight().getLeft().getColor());
		assertEquals(RBTree.RED, rb.getRoot().getLeft().getRight().getLeft().getColor());
	}
	
	@Test
	void testDeletion() {
		setUpScenario3();
		RBNode<Integer> temp = rb.search(7);
		rb.deleteNode(temp);
		assertEquals(RBTree.BLACK, rb.getRoot().getLeft().getRight().getColor());
		assertEquals(RBTree.RED, rb.getRoot().getLeft().getRight().getLeft().getColor());
		assertNull(rb.getRoot().getLeft().getRight().getRight().getData());
		assertEquals(RBTree.BLACK, rb.getRoot().getLeft().getRight().getColor());
	}
}
