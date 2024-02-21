import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Csv {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter threshold percentage");
        double p=sc.nextDouble();
        String line = "";
        String splitBy = ",";
        int sum=0,c=0;
        try
        {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\21-FEB (DAY 20)\\Assignment\\input.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] employee = line.split(splitBy);    // use comma as separator
                int n=Integer.parseInt(employee[7]);
                c++;
                sum=sum+n;
                n=0;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int avg=sum/c;
        System.out.println("avg is : "+(sum/c));
        try
        {

//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\21-FEB (DAY 20)\\Assignment\\input.csv"));
            FileWriter file=new FileWriter("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\21-FEB (DAY 20)\\Assignment\\output.csv");
            BufferedWriter bw=new BufferedWriter(file);
            bw.write("The employees with salary "+p+"% above average are : ");
            bw.write("\n");
            bw.write("*****************************\n");
            line = br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] em = line.split(splitBy);    // use comma as separator
                int n=Integer.parseInt(em[7]);
                if(n>(avg+((avg*p)/100)))
                {
                    bw.write("Name : "+em[1]+" "+em[2]);
                    bw.write("\nSalary : "+em[7]+"$"+"\n*****************************\n");
                }
            }
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}