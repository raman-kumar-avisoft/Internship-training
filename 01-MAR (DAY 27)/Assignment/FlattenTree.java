import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlattenTree {
    static class Tree{
        private int nodeData;
        private Tree leftSubTree;
        private Tree rightSubTree;

        Tree(int data){
            this.nodeData = data;
        }
        void setNodeData(int nodeData){
            this.nodeData = nodeData;
        }
        void setLeftSubTree(Tree leftSubTree){
            this.leftSubTree = leftSubTree;
        }
        void setRightSubTree(Tree rightSubTree) {
            this.rightSubTree = rightSubTree;
        }
        int getNodeData(){
            return this.nodeData;
        }
        Tree getLeftSubTree(){
            return this.leftSubTree;
        }
        Tree getRightSubTree(){
            return  this.rightSubTree;
        }
    }

    public static void main(String[] args) {
        Tree rootNode = createTree();
        ArrayList<Integer> flattenListData = new ArrayList<>();
        setFlattenListData(rootNode, flattenListData);

        Tree flattenListRoot = createFlattenTree(0, flattenListData);
        displayTree(flattenListRoot);
    }
    static Tree createFlattenTree(int index, ArrayList<Integer> array){
        if(index == array.size()){
            return null;
        }
        Tree root = new Tree(array.get(index));
        root.setLeftSubTree(null);
        root.setRightSubTree(createFlattenTree(index+1, array));

        return root;
    }
    static Tree createTree(){
        Scanner scannerInt = new Scanner(System.in);
        int nodeData, maxWrongTries = 2;
        Tree root = null;
        while(maxWrongTries>0){
            try{
                System.out.println("Enter the data of the Node (-1 for null)");

                nodeData = scannerInt.nextInt();
                if(nodeData <= -1){
                    return null;
                }
                root = new Tree(nodeData);

                System.out.println("Enter the node data for the left Subtree whose data is: " + nodeData);
                root.setLeftSubTree(createTree());
                System.out.println("Enter the node data for the right Subtree whose data is: " + nodeData);
                root.setRightSubTree(createTree());
                maxWrongTries = -1;
            }catch (InputMismatchException e){
                System.out.println("Enter the valid node Data --");
                maxWrongTries--;
            }
        }
        if(maxWrongTries == 0){
            System.out.println("Too many wrong tries");
            System.exit(0);
        }

        return root;
    }
    static void displayTree(Tree node){
        if(node == null){
            return;
        }

        System.out.println(node.getNodeData());
        displayTree(node.getLeftSubTree());
        displayTree(node.getRightSubTree());
    }
    static void setFlattenListData(Tree node, ArrayList<Integer> arr){
        if(node == null){
            return;
        }

        arr.add(node.nodeData);
        setFlattenListData(node.leftSubTree, arr);
        setFlattenListData(node.rightSubTree, arr);
    }
}