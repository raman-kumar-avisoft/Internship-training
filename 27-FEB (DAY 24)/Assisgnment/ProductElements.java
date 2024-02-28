import java.util.Scanner;

public class ProductElements {
    public static void main(String[] args) {
        Scanner scInt = new Scanner(System.in);

        System.out.print("Enter the Number of element in the array: ");
        int siz = scInt.nextInt();

        int[] arr = new int[siz];
        System.out.println("Enter the element");
        for (int i=0; i<siz; i++){
            arr[i] = scInt.nextInt();
        }

//        Calculating the product of all the elements in the array
        StringBuilder product = new StringBuilder("");
        for(int i=0; i<siz; i++){
            product = productElements(product, arr[i]);
        }

//        arr2 is output array
        StringBuilder[] arr2 = new StringBuilder[siz];

//        for each output array index we are dividing the product with that current element which will give the product of the all element except the same element
        for(int i=0; i<siz; i++){
            arr2[i] = divide(product, arr[i]);
        }

//        printing the output array
        for (int i=0; i<siz; i++){
            System.out.println(arr2[i]);
        }
    }

//    divide function to divide a string with the number(current element)
    public static StringBuilder divide(StringBuilder num1, int num2) {
        String str = num1.toString();

        int dividend = Integer.parseInt(str);
        int value = dividend/num2;
        return (new StringBuilder(Integer.toString(value)));
    }

//    product of elements with current element
    static StringBuilder productElements(StringBuilder prod, int elem){
//        initially when we are at 0th element prod will be empty and the current element will become our product
        if(prod.toString().equals("")){
            return new StringBuilder(Integer.toString(elem));
        }

//        reverse the product as we will multiply from the last in real world to do so we have to reverse the array for better understanding.
        prod.reverse();

//        count is initialized for adding tailing zeroes to the product as when we add we don't need to skip and iteration.
        StringBuilder count = new StringBuilder();

//        product is what we get after multiplying the element with string particular index and keep adding the product with new value2.
        StringBuilder product = new StringBuilder("");
        for(int i=0; i<prod.length(); i++){
           int digit = prod.charAt(i) - 48;
           int value = digit*elem;
           StringBuilder value2 = new StringBuilder("");
           value2.append(Integer.toString(value) + count);
           product = addString(product, value2);
           count.append("0");
        }
        return product;
    }

//    method to add two strings
    static StringBuilder addString(StringBuilder num1, StringBuilder num2){
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);
        }
        return result;
    }
}