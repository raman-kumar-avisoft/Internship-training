import java.util.*;

public class MergeKSortedList {
    static class Node {
        private int data;
        private Node next;

        Node(int data) {
            setData(data);
            setNext(null);
        }
        void setData(int data){
            this.data = data;
        }
        void setNext(Node node){
            this.next = node;
        }
        int getData(){
            return this.data;
        }
        Node getNext(){
            return next;
        }
    }
    public static void main(String[] args) {
        ArrayList<Node> arrayListOfSortedLL = createListOfKHeadNodes();
        System.out.println("Size of arrayList: " + arrayListOfSortedLL.size());

        Node head = mergeKLists(arrayListOfSortedLL);
        while(head != null){
            if(head.getNext() == null){
                System.out.print(head.getData());
            }else{
                System.out.print(head.getData()+"->");
            }
            head = head.getNext();
        }
    }
    static public Node mergeKLists(ArrayList<Node> lists) {
        if (lists==null||lists.size()==0) return null;

        PriorityQueue<Node> queue= new PriorityQueue<Node>(lists.size(),new Comparator<Node>(){
            @Override
            public int compare(Node o1,Node o2){
                if (o1.getData() < o2.getData())
                    return -1;
                else if (o1.getData() == o2.getData())
                    return 0;
                else
                    return 1;
            }
        });

        Node dummy = new Node(0);
        Node tail=dummy;

        for (Node node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.setNext(queue.poll());
            tail = tail.getNext();

            if (tail.getNext() != null)
                queue.add(tail.getNext());
        }
        return dummy.getNext();
    }
    static ArrayList<Node> createListOfKHeadNodes(){
        Scanner scannerInt = new Scanner(System.in);
        System.out.print("Enter the Number of sorted LinkedList: ");

        int NumberOfSortedLL = scannerInt.nextInt();
        ArrayList<Node> arrayListOfSortedLL = new ArrayList<>();
        for(int NumberOfSortedLLIndex = 0; NumberOfSortedLLIndex<NumberOfSortedLL; NumberOfSortedLLIndex++){
            System.out.println("Enter the data of LinkedList Number: " + (NumberOfSortedLLIndex+1));
            arrayListOfSortedLL.add(createLL());
        }
        return arrayListOfSortedLL;
    }
    static Node createLL(){
        Scanner scannerInt = new Scanner(System.in);
        Node node = null;
        try{
            System.out.print("Enter the Data for the Node (-1 if you want to skip): ");
            int data = scannerInt.nextInt();
            if(data != -1){
                node = new Node(data);
                node.next = createLL();
            }
        }catch (InputMismatchException e){
            System.out.println("Enter the correct value");
        }
        return node;
    }
}