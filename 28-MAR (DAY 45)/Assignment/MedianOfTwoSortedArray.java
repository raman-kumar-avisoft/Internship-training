import java.util.*;

public class MedianOfTwoSortedArray {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0;
        int high = n;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (n + m + 1) / 2 - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == n) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == m) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                if ((n + m) % 2 == 0) {
                    return (double) (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    return (double) Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int m = 0;

        // Exception handling for input size 'n'
        boolean validInputN = false;
        int attemptsN = 0;
        while (!validInputN && attemptsN < 3) {
            try {
                System.out.println("Enter the size of array 'a':");
                n = Integer.parseInt(scanner.nextLine());
                if (n > 0) {
                    validInputN = true;
                } else {
                    System.out.println("Invalid input! Size must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer value.");
            }
            attemptsN++;
        }

        // Exception handling for input size 'm'
        boolean validInputM = false;
        int attemptsM = 0;
        while (!validInputM && attemptsM < 3) {
            try {
                System.out.println("Enter the size of array 'b':");
                m = Integer.parseInt(scanner.nextLine());
                if (m > 0) {
                    validInputM = true;
                } else {
                    System.out.println("Invalid input! Size must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer value.");
            }
            attemptsM++;
        }

        if (validInputN && validInputM) {
            int[] a = new int[n];
            int[] b = new int[m];

            System.out.println("Enter the elements of array 'a':");
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            System.out.println("Enter the elements of array 'b':");
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
            }

            double median = findMedianSortedArrays(a, b);
            System.out.println("Median of the two sorted arrays: " + median);
        } else {
            System.out.println("Failed to read input after 3 attempts.");
        }

        scanner.close();
    }
}
