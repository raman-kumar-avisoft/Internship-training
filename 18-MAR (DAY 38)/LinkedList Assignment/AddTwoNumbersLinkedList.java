import java.util.LinkedList;

public class AddTwoNumbersLinkedList {
    public LinkedList<Integer> addTwoNumbers(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;

        while (!l1.isEmpty() || !l2.isEmpty()) {
            int sum = carry;
            if (!l1.isEmpty()) {
                sum += l1.removeLast();
            }
            if (!l2.isEmpty()) {
                sum += l2.removeLast();
            }
            carry = sum / 10;
            result.addFirst(sum % 10);
        }

        if (carry > 0) {
            result.addFirst(carry);
        }

        return result;
    }

    public void printLinkedList(LinkedList<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println("-1");
    }

    public static void main(String[] args) {
        AddTwoNumbersLinkedList solution = new AddTwoNumbersLinkedList();

        // Sample Input 1
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(1);

        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(9);
        l2.add(9);
        l2.add(9);

        LinkedList<Integer> result = solution.addTwoNumbers(l1, l2);
        solution.printLinkedList(result);  // Output: 1 0 1 0 -1

        // Sample Input 2
        LinkedList<Integer> l3 = new LinkedList<>();
        l3.add(3);
        l3.add(8);
        l3.add(1);
        l3.add(2);
        l3.add(9);

        LinkedList<Integer> l4 = new LinkedList<>();
        l4.add(9);
        l4.add(8);
        l4.add(2);
        l4.add(9);

        result = solution.addTwoNumbers(l3, l4);
        solution.printLinkedList(result);  // Output: 4 7 9 5 8 -1
    }
}
