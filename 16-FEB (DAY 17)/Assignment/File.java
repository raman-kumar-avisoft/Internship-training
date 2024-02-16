import java.util.Scanner;

public class File {
    private String fileName;
    private int size;
    private boolean isReplicated;

    File(){

    }
    void setDetails(String fileName, boolean cond){
        Scanner sc = new Scanner(System.in);
        setFileName(fileName);
        int count = 0;
        do{
            try{
                System.out.println("Enter the size of the File: ");
                setSize(sc.nextInt());
                sc.nextLine();
                if(getSize()<1 || getSize()>1000){
                    throw new Exception("Either size is less than 1 or greater than 1000");
                }
                if(count == 3){
                    System.out.println("Too many tries --");
                    System.exit(1);
                }else{
                    System.out.println("Select a valid option --");
                    break;
                }
            }catch (Exception e){
                System.out.println(e);
            }finally{
                count++;
            }
        }while(count<=3);
        if(cond){
            setIsReplicated(true);
        }else{
            setIsReplicated(false);
        }
    }
    void setFileName(String fileName){
        this.fileName = fileName;
    }
    void setSize(int size){
        this.size = size;
    }
    void setIsReplicated(boolean cond){
        this.isReplicated = cond;
    }
    String getFileName(){
        return this.fileName;
    }
    int getSize(){
        return this.size;
    }
    boolean getIsReplicated(){
        return this.isReplicated;
    }
}