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
        avlTree = avlTree.insert(avlTree, 1); // Insert value 1

        assertNotNull(avlTree.left);
        assertNotNull(avlTree.left.left);

        assertEquals(3, avlTree.left.element);
        assertEquals(1, avlTree.left.left.element);
    }

//  3. Insert value on the right. // Add a larger value to force right insertion.
    @Test
    public void testAVLTreeInsertValueRight() {
        avlTree = avlTree.insert(avlTree, 12); // Insert value 12

        assertNotNull(avlTree.right); // Right child should exist (9)
        assertNotNull(avlTree.right.right); // Right of right should now exist (12)

        assertEquals(9, avlTree.right.element); // Right child should still be 9
        assertEquals(12, avlTree.right.right.element); // 12 should be at right.right
    }

//  4. Verify the balance factor remains valid. // Check that no node is unbalanced.
    @Test
    public void testAVLTreeBalance() {
        int leftHeight = AvlTree.height(avlTree.left);
        int rightHeight = AvlTree.height(avlTree.right);
        int diffrence = Math.abs(leftHeight - rightHeight);
        assertTrue(diffrence <= 1);
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
    @Test
    public void testAvlTreeLeftRightRotation() {
        // Initial tree setup: root 6, left child 3, right child 9

        // Insert 4 to trigger Left-Right Rotation
        avlTree = avlTree.insert(avlTree, 4);

        // Expected tree structure after Left-Right rotation:
        //        4
        //       / \
        //      3   6
        //           \
        //            9
        assertEquals(4, avlTree.element);
        assertEquals(3, avlTree.left.element);
        assertEquals(6, avlTree.right.element);
        assertEquals(9, avlTree.right.right.element);
    }

//  8. Trigger and verify right-left double rotation. // Right-Left case should rotate twice.

    @Test
    public void testAvlTreeRightLeftRotation() {
        // Initial tree setup: root 6, left child 3, right child 9

        // Insert 8 to trigger Right-Left Rotation
        avlTree = avlTree.insert(avlTree, 8);

        // Expected tree structure after Right-Left rotation:
        //        8
        //       / \
        //      6   9
        //     /
        //    3
        assertEquals(8, avlTree.element);
        assertEquals(6, avlTree.left.element);
        assertEquals(9, avlTree.right.element);
        assertEquals(3, avlTree.left.left.element);
    }

//  9. Verify node persistence after all insertions. // Ensure no node is lost.
    @Test
    public void testAVLTreeNodePersistence() {
        List<Integer> expectedElements = Arrays.asList(1, 2, 3, 6, 9);
        List<Integer> capturedElements = new ArrayList<>();

        avlTree = avlTree.insert(avlTree, 2);
        avlTree = avlTree.insert(avlTree, 1);

        // Perform in-order traversal and capture elements
        inorderTraversal(avlTree, capturedElements);

        // Verify that all expected nodes are present and in the correct order
        assertEquals(expectedElements, capturedElements);
    }


    //  10. Verify correct BST ordering after all insertions. // Tree must maintain BST properties.
        @Test
        public void testBSTOrdering() {
            avlTree = avlTree.insert(avlTree, 2);
            avlTree = avlTree.insert(avlTree, 8);
            avlTree = avlTree.insert(avlTree, 4);

            List<Integer> inorderTraversal = new ArrayList<>();
            inorderTraversal(avlTree, inorderTraversal);

            List<Integer> sortedList = new ArrayList<>(inorderTraversal);
            Collections.sort(sortedList);

            assertEquals(sortedList, inorderTraversal);
        }

    //  11. Perform a stress test with sequential insertions. // Insert many values in order.
        @Test
        public void testSequentialInsertions() {
            avlTree = new AvlTree();

            for (int i = 1; i <= 1000; i++) {
                avlTree = avlTree.insert(avlTree, i);
            }

            // Ensure all elements are in the tree
            for (int i = 1; i <= 1000; i++) {
                assertTrue(avlTree.contains(avlTree, i));
            }
        }

    //  12. Ensure rebalancing occurs during stress test. // Check that tree remains balanced.
        @Test
        public void testRebalancingDuringStressTest() {
            avlTree = new AvlTree();

            for (int i = 1; i <= 1000; i++) {
                avlTree = avlTree.insert(avlTree, i);
                assertTrue(avlTree.repOK_Structure(avlTree));
            }
        }

        private void inorderTraversal(AvlTree node, List<Integer> result) {
            if (node != null) {
                inorderTraversal(node.left, result);
                result.add(node.element);
                inorderTraversal(node.right, result);
            }
        }
    }


