import java.util.InputMismatchException;
import java.util.Scanner;

class Node{
    private char data;
    private Node next;
    Node(){
        this.data = '\0';
        this.next = null;
    }
    Node(char c){
        this.data = c;
        this.next = null;
    }
    void setData(char c){
        this.data = c;
    }
    void setNext(Node newNode){
        this.next = newNode;
    }
    Node getNext(){
        return this.next;
    }
    char getData(){
        return this.data;
    }
}
public class SliceStringToLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = enterInput();
        Node head = convertToLL(str);
        displayLinkedList(head);

        int count = 3;
        int begin = -1, end = -1;
        while(count>0){
            try{
                System.out.print("Enter the beginning position: ");
                begin = sc.nextInt();
                System.out.print("Enter the ending position: ");
                end = sc.nextInt();

                if(end >= str.length() || end<begin  || end < 0 || begin<0){
                    throw new InputMismatchException("check kar begin/end apna");
                }else{
                    count = -1;
                }
            }catch (InputMismatchException e){
                System.out.println("Enter the valid values");
            }finally {
                count--;
                if(count == 0){
                    System.out.println("Too many wrong inputted value !!");
                    System.exit(0);
                }
            }
        }
        String afterSlicing = sliceTheLL(head, begin, end);
        System.out.println(afterSlicing);
    }
    static Node convertToLL(String str){
        Node head = null;
        Node temp = null;
        for(int i=0; i<str.length(); i++){
            Node newNode = new Node(str.charAt(i));
            if(i==0){
                head = newNode;
                temp = newNode;
            }else{
                temp.setNext(newNode);
                temp = newNode;
            }
        }
        return head;
    }
    static String sliceTheLL(Node head, int begin, int end){
        String ans = "";
        int i = 0;
        while(i<end && head != null){
            if(i>=begin){
                ans += head.getData();
            }
            head = head.getNext();
            i++;
        }
        return ans;
    }
    static String enterInput(){
        Scanner sc = new Scanner(System.in);
        int count = 3;
        String str = "";
        while(count >0){
            try{
                System.out.print("Enter the String to convert it in linked List: ");
                str = sc.nextLine();
                count = -1;
            }catch(InputMismatchException e){
                System.out.println("Enter a valid input value");
            }finally {
                count--;
                if(count == 0){
                    System.out.println("Too many wrong inputted value !!");
                    System.exit(0);
                }
            }
        }
        return str;
    }
    static void displayLinkedList(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.getData());
            temp = temp.getNext();
        }
    }
}