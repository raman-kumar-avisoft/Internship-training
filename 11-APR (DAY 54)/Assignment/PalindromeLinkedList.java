import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode() {
        val = -1;
        next = null;
    }
}

public class PalindromeLinkedList {
    public static int isPalindrome(ListNode A) {
        if (A == null || A.next == null) {
            return 1; // A linked list with 0 or 1 node is always a palindrome
        }

        // Find the middle of the linked list
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the linked list
        ListNode prev = null;
        ListNode current = slow;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Compare the first half with the reversed second half
        ListNode firstHalf = A;
        ListNode secondHalf = prev;
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return 0; // Not a palindrome
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return 1; // Palindrome
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList();

        System.out.println("ANSWER IS: " + isPalindrome(head)); // Output: 1

    }
    public static ListNode createLinkedList() {
        Scanner scannerInteger = new Scanner(System.in);

        ListNode head = null;
        ListNode last = null;
        int maxWrongTries = -1;
        boolean cond = true;
        while (cond) {
            maxWrongTries = 3;
            while (--maxWrongTries >= 0) {
                try {
                    System.out.print("Enter the data of the node: ");
                    int value = scannerInteger.nextInt();
                    if(value == -1){
                        cond =false;
                        break;
                    }
                    ListNode temp = new ListNode();
                    temp.val = value;
                    if(head == null){
                        head = temp;
                        last = temp;
                    }else{
                        last.next = temp;
                        last = last.next;
                    }
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("NOT A VALID DATA **");
                    scannerInteger.nextLine();
                }
            }
            if (maxWrongTries < 0) {
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }
        return head;
    }
}
