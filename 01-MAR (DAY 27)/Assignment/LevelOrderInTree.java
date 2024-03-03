import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LevelOrderInTree {
    static class Node {
        private int nodeData;
        private Node leftSubTree;
        private Node rightSubTree;
        private Node next;

        Node(int nodeData) {
            setNodeData(nodeData);
            next = null;
            leftSubTree = null;
            rightSubTree = null;
        }

        void setNodeData(int nodeData){
            this.nodeData = nodeData;
        }
        void setLeftSubTree(Node leftNode){
            this.leftSubTree = leftNode;
        }
        void setRightSubTree(Node rightNode){
            this.rightSubTree = rightNode;
        }
        void setNext(Node nextNode){
            this.next = nextNode;
        }
        int getNodeData(){
            return this.nodeData;
        }
        Node getLeftSubTree(){
            return this.leftSubTree;
        }
        Node getRightSubTree(){
            return this.rightSubTree;
        }
        Node getNext(){
            return this.next;
        }
    }

    public static void main(String[] args) {
        ArrayList<Node> levelOrder = new ArrayList<>();
        Node root = createBinaryTree();
        levelOrder = settingNextNode(root, levelOrder);

        displayLevelOrder(levelOrder);
    }

    static void displayLevelOrder(ArrayList<Node> levelOrder) {
        for (Node root : levelOrder) {
            while (root != null) {
                if(root.getNext() == null){
                    System.out.print(root.nodeData);
                }else{
                    System.out.print(root.nodeData + "->");
                }
                root = root.getNext();
            }
            System.out.println();
        }
    }

    static ArrayList<Node> settingNextNode(Node root, ArrayList<Node> levelOrder) {
        int height = maxDepthOfTree(root, 0);
        for (int i = 1; i <= height; i++) {
            levelOrderTraversal(root, i, 1, null, levelOrder);
        }
        System.out.println("Height of the Binary Tree is: " + height);
        return levelOrder;
    }

    static Node levelOrderTraversal(Node root, int count, int currentNode, Node prev, ArrayList<Node> levelWiseTraversal) {
        if (root == null) {
            return null;
        }
        if (currentNode == count) {
            if (prev == null) {
                prev = root;
                levelWiseTraversal.add(prev);
            } else {
                prev.setNext(root);
                prev = root;
            }
            return prev;
        }

        prev = levelOrderTraversal(root.getLeftSubTree(), count, currentNode + 1, prev, levelWiseTraversal);
        prev = levelOrderTraversal(root.getRightSubTree(), count, currentNode + 1, prev, levelWiseTraversal);
        return prev;
    }

    static int maxDepthOfTree(Node root, int count) {
        if (root == null) {
            return count;
        }

        int leftSubTreeHeight = maxDepthOfTree(root.getLeftSubTree(), count + 1);
        int rightSubTreeHeight = maxDepthOfTree(root.getRightSubTree(), count + 1);
        return Math.max(leftSubTreeHeight, rightSubTreeHeight);
    }

    static Node createBinaryTree() {
        Scanner scannerInt = new Scanner(System.in);

        int nodeData, wrongTries = 2;
        Node node = null;

        while(wrongTries>0){
            try{
                System.out.println("Enter the Node data (enter -1 to skip): ");
                nodeData = scannerInt.nextInt();

                if (nodeData <= -1) {
                    return null;
                }

                node = new Node(nodeData);
                System.out.println("Enter the left node of data " + node.getNodeData() + ": ");
                node.setLeftSubTree(createBinaryTree());
                System.out.println("Enter the right node of data " + node.getNodeData() + ": ");
                node.setRightSubTree(createBinaryTree());
                wrongTries = -1;
            }catch (InputMismatchException e){
                System.out.println("Enter a valid number -- (Tires Remaining are: " + wrongTries + ")");
                wrongTries--;
            }
        }
        if (wrongTries == 0){
            System.out.println("Too many wrong tries --");
            System.exit(0);
        }
        return node;
    }
}