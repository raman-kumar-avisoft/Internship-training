import java.util.ArrayList;
import java.util.Scanner;

public class UnixToAbsolutePath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the unix path: ");
        String unixPath = sc.nextLine();

        String absolutePath = getAbsolutePath(unixPath);
        System.out.print("Absolute path is: " + absolutePath);
    }

    static String getAbsolutePath(String unixPath){

        ArrayList<String> arrayListDirectories = new ArrayList<>();
        String word = "";

        for(int unixPathIndex=0; unixPathIndex < unixPath.length(); unixPathIndex++){
            if(unixPath.charAt(unixPathIndex) == '/'){
                if(!word.equals("")){
                    arrayListDirectories.add(word);
                    word = "";
                }

                if((unixPathIndex+1) != unixPath.length() && unixPath.charAt(unixPathIndex+1) == '.'){
                    if((unixPathIndex+2) != unixPath.length() && unixPath.charAt(unixPathIndex+2) == '.'){
                        if(!arrayListDirectories.isEmpty()){
                            arrayListDirectories.remove(arrayListDirectories.size()-1);
                        }
                        unixPathIndex++;
                    }
                    unixPathIndex++;
                }

            }else if(unixPath.charAt(unixPathIndex) != '.'){
                word += unixPath.charAt(unixPathIndex);
            }
        }

//        when last letter is not \ or .
        if(!word.equals("")){
            arrayListDirectories.add(word);
        }

        String absolutePath = "/";
        for(int arrayListDirectoriesIndex=0; arrayListDirectoriesIndex < arrayListDirectories.size(); arrayListDirectoriesIndex++){
            absolutePath += arrayListDirectories.get(arrayListDirectoriesIndex);
        }
        return absolutePath;
    }

}