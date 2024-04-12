import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class TwoNodesWithSum {
    private static final int MAX_TRIES = 3;
    private int wrongTries = 0;

    // Method to insert a value into the BST
    public TreeNode insert(TreeNode root, int val) throws Exception {
        // If the root is null, create a new node with the given value
        if (root == null) {
            return new TreeNode(val);
        }

        // If the value is less than the root value, insert it into the left subtree
        if (val < root.val) {
            root.left = insert(root.left, val);
        }
        // If the value is greater than or equal to the root value, insert it into the right subtree
        else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // Method to insert a value into the BST with exception handling for wrong tries
    public TreeNode insertWithExceptionHandling(TreeNode root, int val) throws Exception {
        try {
            wrongTries = 0; // Reset wrongTries count for each insertion attempt
            return insert(root, val);
        } catch (Exception e) {
            wrongTries++;
            if (wrongTries >= MAX_TRIES) {
                throw new Exception("Maximum number of wrong tries exceeded for this node.");
            }
            System.out.println("Invalid input. Please try again.");
            return insertWithExceptionHandling(root, val); // Recursive call to try again
        }
    }

    // Method to check if there exist two different nodes X and Y such that X.value + Y.value = B
    public boolean findPair(TreeNode root, int B) {
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode leftNode = root;
        TreeNode rightNode = root;

        // In-order traversal to initialize the left stack with nodes in increasing order
        while (leftNode != null) {
            leftStack.push(leftNode);
            leftNode = leftNode.left;
        }

        // Reverse in-order traversal to initialize the right stack with nodes in decreasing order
        while (rightNode != null) {
            rightStack.push(rightNode);
            rightNode = rightNode.right;
        }

        // Traverse until pointers meet
        while (!leftStack.isEmpty() && !rightStack.isEmpty() && leftStack.peek().val < rightStack.peek().val) {
            int sum = leftStack.peek().val + rightStack.peek().val;

            if (sum == B) {
                return true; // Pair found
            } else if (sum < B) {
                // Move left pointer to the right
                TreeNode node = leftStack.pop().right;
                while (node != null) {
                    leftStack.push(node);
                    node = node.left;
                }
            } else {
                // Move right pointer to the left
                TreeNode node = rightStack.pop().left;
                while (node != null) {
                    rightStack.push(node);
                    node = node.right;
                }
            }
        }

        return false; // Pair not found
    }

    public static void main(String[] args) {
        // Create the binary search tree
        TwoNodesWithSum bst = new TwoNodesWithSum();
        TreeNode root = null;

        // Sample values to insert into the BST
        int[] values = {10, 5, 15, 3, 7, 12, 18};

        // Insert values into the BST
        for (int val : values) {
            try {
                root = bst.insertWithExceptionHandling(root, val);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break; // Exit loop if maximum number of wrong tries exceeded
            }
        }

        // Example inputs
        int B1 = 19;
        int B2 = 40;

        // Find pair for B1
        boolean pairExists1 = bst.findPair(root, B1);
        System.out.println("Pair exists for B1: " + (pairExists1 ? "Yes" : "No"));

        // Find pair for B2
        boolean pairExists2 = bst.findPair(root, B2);
        System.out.println("Pair exists for B2: " + (pairExists2 ? "Yes" : "No"));
    }
}
