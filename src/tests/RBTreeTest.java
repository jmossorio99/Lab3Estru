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
		
	}
	
	@Test
	void testCreation() {
		setUpScenario1();
		assertNotNull(rb.getRoot());
	}
}
