import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Scanner;

class LibraryMember extends Library{
    Scanner sc = new Scanner(System.in);
    String name;
    int memberId;
    ArrayList<Book> bookIssued = new ArrayList<>();
    ArrayList<Book> bookReturned = new ArrayList();

    public LibraryMember(){
        System.out.println("WELCOME TO LIBRARY MEMBER MODULE OF THE CODE");
        System.out.print("Enter the name of the new member: ");
        setName(sc.nextLine());
        System.out.print("Enter the member id: ");
        setMemberId(sc.nextInt());
    }

    void setBookIssued(int value){
        boolean idCheck = false;
        int i=0;
        for( ; i<itemBook.size(); i++){
            if(value == itemBook.get(i).uniqueId){
                idCheck = true;
                break;
            }
        }
        if(idCheck){
            bookIssued.add(itemBook.get(i));
        }else{
            System.out.println("No Book Found with this ID");
        }
    }
    void setBookReturned(int value){
        boolean checkBook = false;
        for(int i = 0; i<bookIssued.size(); i++){
            if(value == bookIssued.get(i).uniqueId){
                checkBook = true;
                bookReturned.add(bookIssued.get(i));
                bookIssued.remove(i);
                break;
            }
        }
        if(!checkBook){
            System.out.println("No Book with given id is issued from this library member");
        }
    }
    void getBookIssued(){
        System.out.println("Books Issued to this member with titles are: ");
        for(int i=0; i<bookIssued.size(); i++){
            System.out.println("Name: " + bookIssued.get(i).name + " and id is: " +bookIssued.get(i).uniqueId);
        }
    }

    void setName(String data){
        this.name = data;
    }
    String getName(){
        return this.name;
    }
    void setMemberId(int data){
        this.memberId = data;
    }
    int getMemberId(){
        return this.memberId;
    }
}
class Library{
    Scanner sc = new Scanner(System.in);
    int option;

    ArrayList<Book> itemBook = new ArrayList<>();
    ArrayList<Magazine> itemMagazine = new ArrayList<>();
    ArrayList<LibraryMember>  libraryMembers = new ArrayList<LibraryMember>();

    public Library(){
        System.out.println("LIBRARY MANAGEMENT BY RAMAN");
    }
    public void displayMainMenu(){
        System.out.println("1. Add a Book");
        System.out.println("2. Add a Member");
        System.out.println("3. Library Operations(Issue/Return)");
        System.out.println("4. Display Book Details");
        System.out.println("5. Number of Books/Magazine");
        System.out.println("6. EXIT");
    }
    public void setOption(int data){
//        System.out.print("Enter your choice: ");
        this.option = data;
    }
    public int getOption(){
        return this.option;
    }
    public void sendingToOption(){

        switch (this.option){
            case 1:
                addBookOrMagazine();
                break;

            case 2:
                addLibraryMember();
//                System.out.println("nice add member function working nicely");
                break;

            case 3:
                performOtherOperations();
//                System.out.println("WE are into the return and issue book section");
                break;

            case 4:
                displayRecord();
                break;

            case 5:
                totalBooks();
                break;

            case 6:
                System.out.println("THANK YOU!!");
                break;

            default:
                System.out.println("Wrong Option Selected !!");
                break;
        }
    }
    void performOtherOperations(){
        System.out.println("1. Issue a Book");
        System.out.println("2. Return a Book");
        System.out.println("Enter the option you want to perform operation: ");
        int choice = sc.nextInt();

        System.out.println("Enter the Book ID");
        int performOperationBookId = sc.nextInt();

        System.out.println("Enter the Member ID");
        int performOperationMemberId = sc.nextInt();
        if(choice == 1){
            boolean found = false;
//            adding the book details in issued arraylist of particular library members and updating count
            for(int i=0; i<libraryMembers.size(); i++){
                if(performOperationMemberId == libraryMembers.get(i).memberId){
                    for(int j=0; j<itemBook.size(); j++){
                        if(performOperationBookId == itemBook.get(j).uniqueId){
                            libraryMembers.get(i).bookIssued.add(itemBook.get(i));
                            itemBook.get(i).setCount(itemBook.get(i).getCount()-1);
                            found = true;
                        }
                    }
                }
            }
            if(!found){
                System.out.println("Wrong inputted values");
            }
        }else if(choice == 2){
            boolean found = false;
//            finding the member then book in library then matching it with member issued book and updating count
            for(int i=0; i<libraryMembers.size(); i++) {
                if (performOperationMemberId == libraryMembers.get(i).memberId) {
                    for (int j = 0; j < itemBook.size(); j++) {
                        if (performOperationBookId == itemBook.get(j).uniqueId) {
                            for (int k = 0; k < libraryMembers.get(i).bookIssued.size(); k++) {
                                if (libraryMembers.get(i).bookIssued.get(k).uniqueId == itemBook.get(j).uniqueId) {
                                    libraryMembers.get(i).bookIssued.add(itemBook.get(i));
                                    itemBook.get(i).setCount(itemBook.get(i).getCount() + 1);
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if(!found){
                System.out.println("Wrong inputted values");
            }
        }else{
            System.out.println("Wrong input selected");
        }
    }
    void totalBooks(){
        int choice;
        System.out.println("1. For Overall Items in Library");
        System.out.println("2. For Distinct Items in Library");
        System.out.println("Enter your option: ");
        choice = sc.nextInt();

        if(choice == 1){
            int bookscount=0, magazinescount = 0;
            for(int i=0; i<itemBook.size(); i++){
                bookscount += itemBook.get(i).count;
            }
            for(int i=0; i<itemMagazine.size(); i++){
                magazinescount += itemMagazine.get(i).count;
            }
            System.out.println("Total Books are: " +(bookscount+magazinescount));
            System.out.println("Books Count is: "+ bookscount);
            System.out.println("Books Count is: "+ magazinescount);
        }else{
            System.out.println("Total ITEMS of distinct types are: "+(itemMagazine.size()+itemBook.size()));
            System.out.println("Books of distinct types: " + itemBook.size());
            System.out.println("Books of distinct types: " + itemMagazine.size());
        }

    }
    void displayRecord(){
        System.out.println("1. Book Record");
        System.out.println("2. Magazine Record");
        System.out.println("Press the key for the record you want to be displayed");

        int choice = sc.nextInt();
        if(choice == 1){
            bookRecord();
        }else if(choice == 2){
            magazineRecord();
        }else{
            System.out.println("WRONG OPTION SELECTED !!");
        }
    }
    void bookRecord(){
        System.out.println("INSIDE BOOK RECORDS");
        for(int i=0; i<itemBook.size(); i++){
            System.out.println("NAME: " + itemBook.get(i).name);
            System.out.println("AUTHOR: " + itemBook.get(i).author);
            System.out.println("ISBN NUMBER: " + itemBook.get(i).isbn);
            System.out.println("COUNT: " + itemBook.get(i).count);
            System.out.println();
        }
    }
    void magazineRecord(){
        System.out.println("INSIDE Magazine RECORDS");
        for(int i=0; i<itemMagazine.size(); i++){
            System.out.println("NAME: " + itemMagazine.get(i).name);
            System.out.println("AUTHOR: " + itemMagazine.get(i).author);
            System.out.println("COUNT: " + itemMagazine.get(i).count);
            System.out.println("ISSUE NUMBER: " + itemMagazine.get(i).issueNumber);
            System.out.println("GENRE: " + itemMagazine.get(i).genre);
            System.out.println();
        }
    }
    void addLibraryMember(){
        libraryMembers.add(new LibraryMember());
    }
    void addBookOrMagazine(){
        System.out.println("in addBookOrMagazine");
        boolean type = bookType();
        if(type){
            itemBook.add(new Book(type));
        }else{
            itemMagazine.add(new Magazine(type));
        }
    }
    boolean bookType(){
        System.out.println("1. If Item is a book");
        System.out.println("2. If Item is a Magazine");

        if(sc.nextInt()==1){
            return true;
        }else{
            return false;
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Library obj =new Library();
        int choice = 0;

        while(choice != 6){
            obj.displayMainMenu();
            System.out.println("Enter the option you want to select: ");
            choice = sc.nextInt();
            obj.setOption(choice);
            obj.sendingToOption();
        }
    }
}

class Item{
    Scanner sc = new Scanner(System.in);
    int uniqueId;
    String name;
    String author;
    int count;
    boolean type;

    public Item(boolean type){
        this.type = type;
        insertDetails();
    }
    public void insertDetails(){
        System.out.println("Enter the details of the Item you want to insert");
        System.out.print("Enter the name of the Item: ");
        setName(sc.nextLine());
        System.out.print("Enter the name of Author: ");
        setAuthor(sc.nextLine());
        System.out.print("Enter the count of Item: ");
        setCount(sc.nextInt());
        System.out.print("Enter the UniqueId of Item: ");
        setUniqueId(sc.nextInt());
    }
    void setUniqueId(int id){
        this.uniqueId = id;
    }
    void setName(String data){
        this.name = data;
    }
    void setAuthor(String data){
        this.author = data;
    }
    void setCount(int data){
        this.count = data;
    }
    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public int getCount(){
        return count;
    }
}
class Book extends Item{
    int isbn;
    public Book(boolean type){
        super(type);
        System.out.println("Inside book");
        System.out.println("Enter the ISBN number: ");
        setIsbn(sc.nextInt());

        System.out.println("Entry Entered Successfully");
    }
    void setIsbn(int data){
        this.isbn = data;
    }
    int getIsbn(){
        return this.isbn;
    }
}
class Magazine extends Item{
    int issueNumber;
    String genre;
    public Magazine(boolean type){
        super(type);
        System.out.println("Inside magazine");
        System.out.println("Enter the issue number of the magazine");
        setIssueNumber(sc.nextInt());
        System.out.println("Enter the genre for the magazine");
        setGenre(sc.next());
        sc.nextLine();

        System.out.println("Entry Entered Successfully");
    }
    void setIssueNumber(int data){
        this.issueNumber = data;
    }
    int getIssueNumber(){
        return this.issueNumber;
    }
    void setGenre(String data){
        this.genre = data;
    }
    String getGenre(){
        return this.genre;
    }
}