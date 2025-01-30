import org.example.AvlTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class AvlTreeTest {
    private AvlTree avlTree;

    @BeforeEach
    public void createTree() {
        AvlTree avlLeft = new AvlTree(3);
        AvlTree avlRight = new AvlTree(9);
        avlTree = new AvlTree(6,2,avlLeft,avlRight);
    }

 /* Creates a tree with root 6, avl.left 3, avl.right 9, and total height 2
     6
    / \
   3   9    height: 2
 */


//  AVL Tree Construction**
//  1. Ensure the AVL tree can be successfully instantiated. // Create an empty AVL tree.
    @Test
    public void testAVLTreeInstantiation() {
        assertNotNull(avlTree);
        assertEquals(6, avlTree.element);
        assertEquals(2, avlTree.height);
        assertNotNull(avlTree.left);
        assertNotNull(avlTree.right);
        assertEquals(3, avlTree.left.element);
        assertEquals(9, avlTree.right.element);
    }

//  Integer to be Added Definition**
//  2. Insert value on the left. // Add a smaller value to force left insertion.
    @Test
    public void testAVLTreeInsertValueLeft() {
        avlTree = avlTree.insert(avlTree, 1);
        assertNotNull(avlTree.left);
        assertEquals(2, avlTree.left.element);
    }

//  3. Insert value on the right. // Add a larger value to force right insertion.
    @Test
    public void testAVLTreeInsertValueRight() {
        avlTree = avlTree.insert(avlTree, 12);
        assertNotNull(avlTree.right);
        assertEquals(10, avlTree.right.element);
    }

//  4. Verify the balance factor remains valid. // Check that no node is unbalanced.
    @Test
    public void testAVLTreeBalance() {
        int leftHeight = AvlTree.height(avlTree.left);
        int rightHeight = AvlTree.height(avlTree.right);
        int diffrence = Math.abs(leftHeight - rightHeight);
        assertTrue(diffrence == 0 || diffrence == 1);
    }

//  Assertion: Expected Property Holds**
//  5. Trigger and verify single left rotation. // Right-heavy tree should rotate left.
    @Test
    public void testAVLTreeSingleLeftRotation() {
        // Make right heavy
        avlTree = avlTree.insert(avlTree, 12);
        avlTree = avlTree.insert(avlTree, 15);

        assertEquals(9, avlTree.element);

        assertNotNull(avlTree.left);
        assertNotNull(avlTree.right);
        assertNotNull(avlTree.left.left);
        assertNotNull(avlTree.right.right);

        assertEquals(6, avlTree.left.element);
        assertEquals(12, avlTree.right.element);
        assertEquals(3, avlTree.left.left.element);
        assertEquals(15, avlTree.right.right.element);
    }

//  6. Trigger and verify single right rotation. // Left-heavy tree should rotate right.
    @Test
    public void testAVLTreeSingleRightRotation() {
        // Make left heavy
        avlTree = avlTree.insert(avlTree, 2);
        avlTree = avlTree.insert(avlTree, 1);

        assertEquals(3, avlTree.element);

        assertNotNull(avlTree.left);
        assertNotNull(avlTree.right);
        assertNotNull(avlTree.left.left);
        assertNotNull(avlTree.right.right);

        assertEquals(2, avlTree.left.element);
        assertEquals(6, avlTree.right.element);
        assertEquals(1, avlTree.left.left.element);
        assertEquals(9, avlTree.right.right.element);
    }

//  7. Trigger and verify left-right double rotation. // Left-Right case should rotate twice.
//  8. Trigger and verify right-left double rotation. // Right-Left case should rotate twice.
//  9. Verify node persistence after all insertions. // Ensure no node is lost.
    @Test
    public void testAVLTreeBNodePersistence() {
        Set<Integer> expectedElements = new HashSet<>(Arrays.asList(1, 2, 3, 6, 9));
        Set<Integer> capturedElements = new HashSet<>();

        avlTree = avlTree.insert(avlTree, 2);
        avlTree = avlTree.insert(avlTree, 1);

        // add all found elements in captured elements
        // create a method to traverse through

        assertTrue(expectedElements == capturedElements);

    }
//  10. Verify correct BST ordering after all insertions. // Tree must maintain BST properties.
//  11. Perform a stress test with sequential insertions. // Insert many values in order.
//  12. Ensure rebalancing occurs during stress test. // Check that tree remains balanced.




}
