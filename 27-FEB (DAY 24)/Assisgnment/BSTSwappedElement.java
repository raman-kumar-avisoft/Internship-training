import java.util.ArrayList;
import java.util.Collections;

//class for creating a tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BSTSwappedElement {
    public static void main(String[] args) {
        // Construction of a sample binary tree
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(10);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        ArrayList<Integer> arr = new ArrayList<>();
//        now inOrderTraversal changes the arraylist arr
        inOrderTraversal(root, arr);

        ArrayList<Integer> arr2 = new ArrayList<>();
//        we are creating other arr2 with exact same value in arr as the inorder traversal of BST Should be sorted
        System.out.println(arr.size());
        for(int i=0; i<arr.size(); i++){
            arr2.add(arr.get(i));
        }

//        sort the arr2 so we can match the differences between two.
        Collections.sort(arr2);

//        answer is stored in ans arraylist
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<arr.size(); i++){
            if(arr.get(i) != arr2.get(i)){
                ans.add(arr.get(i));
            }
        }

        System.out.print("Swapped Pair is: ");
        for (int i=0; i<ans.size(); i++){
            System.out.print(ans.get(i) + " ");
        }
    }
    private static void inOrderTraversal(TreeNode root, ArrayList<Integer> arrayList) {
        if (root != null) {
            // Traverse the left subtree
            inOrderTraversal(root.left, arrayList);

            // Enter the data to the arraylist
            arrayList.add(root.val);

            // Traverse the right subtree
            inOrderTraversal(root.right, arrayList);
        }
    }
}
