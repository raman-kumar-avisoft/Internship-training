import java.util.Scanner;

public class DistributedFileSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Distributed File System !!");
        int count = 0;
        do{
            try{
                displayMainMenu();
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        System.out.println("Addition of Servers");
                        addServer();
                        break;
                    case 2:
                        System.out.println("Uploading a File");
                        break;
                    case 3:
                        System.out.println("Downloading a File");
                        break;
                    case 4:
                        System.out.println("Replicate a File");
                        break;
                    default:
                        if(count == 3){
                            System.out.println("Too many tries --");
                            System.exit(1);
                        }else{
                            System.out.println("Select a valid option --");
                            break;
                        }
                }
            }catch (Exception e){
                System.out.println(e);
            }finally{
                count++;
            }
        }while(count<=3);
    }
    static void displayMainMenu(){
        System.out.println("\n1. Add a Server");
        System.out.println("2. Upload File");
        System.out.println("3. Download File");
        System.out.println("4. Replicate File");
        System.out.println("5. Exit");
        System.out.println("Select one option out of the following: ");
    }
    static void addServer(){

    }
}