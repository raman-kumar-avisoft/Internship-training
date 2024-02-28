import java.util.Scanner;

public class HistogramRectangularArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total building in the histogram: ");
        int totalBuilding = sc.nextInt();

        int[] histogram = new int[totalBuilding];
        System.out.println("Enter the height of the Building in histograms: ");

        for(int buildingIndex = 0; buildingIndex < totalBuilding; buildingIndex++){
            histogram[buildingIndex] = sc.nextInt();
        }

        int area = 0;
        for(int buildingIndex = 0; buildingIndex < totalBuilding; buildingIndex++){
            int minHeight = histogram[buildingIndex];

            for(int buildingIndex2 = buildingIndex+1; buildingIndex2 < totalBuilding; buildingIndex2++){
                if(histogram[buildingIndex2]<minHeight){
                    minHeight = histogram[buildingIndex2];
                }
                area = Integer.max(area, minHeight*(buildingIndex2-buildingIndex+1));
            }
        }

        System.out.println("Maximum Area is: " + area);
    }
}