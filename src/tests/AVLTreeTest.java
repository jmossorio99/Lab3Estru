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
	private void testCreation() {
		setUpScenario1();
		assertNotNull(avl);
	}
	
	private void testInsertion() {
		
	}
}
