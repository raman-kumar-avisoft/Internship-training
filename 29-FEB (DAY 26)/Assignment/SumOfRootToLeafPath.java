import java.util.InputMismatchException;
import java.util.Scanner;

public class SumOfRootToLeafPath {
    static class Tree{
        int nodeData;
        Tree leftSubTree;
        Tree rightSubTree;

        Tree(int data){
            this.nodeData = data;
        }
    }

    public static void main(String[] args) {
        Tree root = createTree();
        int sumOfRootToLeaf  = preOrderTraversal(root, "", 0);
        System.out.println("The sum of root to leaf % 1003 is: " + sumOfRootToLeaf);
    }
    static int preOrderTraversal(Tree node, String sumString, int actualSum){
        if(node.leftSubTree == null && node.rightSubTree == null){
            sumString += node.nodeData;
            actualSum += Integer.parseInt(sumString);
            actualSum %= 1003;
            System.out.println("Each left not number is: " + actualSum);
            return actualSum;
        }

        sumString += node.nodeData;

        actualSum = preOrderTraversal(node.leftSubTree, sumString, actualSum);
        actualSum = preOrderTraversal(node.rightSubTree, sumString, actualSum);
        return actualSum;
    }
    static Tree createTree(){
        Scanner scannerInt = new Scanner(System.in);
        int wrongInputTries = 2;
        int nodeData = -1;
        while (wrongInputTries > 0){
            try{
                System.out.print("Enter the Node Data: ");
                nodeData = scannerInt.nextInt();
                wrongInputTries = -1;
            }catch (InputMismatchException e){
                System.out.print("Wrong Input value Wrong Input Tries left: " + wrongInputTries);
                wrongInputTries--;
            }
        }

        if(nodeData <= -1){
            return null;
        }

        Tree root = new Tree(nodeData);
        System.out.println("Enter the left part of: " + nodeData);
        root.leftSubTree = createTree();
        System.out.println("Enter the right part of: " + nodeData);
        root.rightSubTree = createTree();

        return root;
    }
}