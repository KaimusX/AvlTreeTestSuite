import org.example.AvlTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AvlTreeTest {
    @BeforeEach
    /* Creates a tree with root 6, avl.left 3, avl.right 9, and total height 2
         6
        / \
       3   9    height: 2
     */
    public void createTree() {
        AvlTree avlLeft = new AvlTree(3);
        AvlTree avlRight = new AvlTree(9);
        AvlTree avl = new AvlTree(6,2,avlLeft,avlRight);
    }

    /* TODO *
      1. Insert 1x left into Balanced Tree and (load factor == -1 || 1 || 0 )
        - Insert 1x right into Balanced Tree and (load factor == -1 || 1 || 0)
        - Insert 2x left into Balanced Tree and (load factor == -1 || 1 || 0)
        - Insert 2x right into Balanced Tree and (load factor == -1 || 1 || 0)
      2. Verify value with left rotation.
      3. Verify value with double left rotation.
      4. Verify value with right rotation.
      5. Verify value with double right rotation.
      6. Verify node persistence after insert.
      7. Verify node persistence after left rotation.
      8. Verify node persistence after double left rotation.
      9. Verify node persistence after right rotation.
      10. Verify node persistence after double right rotation.
      11. Verify insert less than root and avl.left goes to avl.left.left
      12. Verify insert more than root and avl.right goes to avl.right.right
     */

}
