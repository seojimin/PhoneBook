import java.util.Scanner;

public class MenuViewer {
    public static Scanner keyboard = new Scanner(System.in);

    public static void menuViewer(){
        System.out.println("Select the option below");
        System.out.println("1. Input");
        System.out.println("2. Search");
        System.out.println("3. Delete");
        System.out.println("4. Exit");
        System.out.print("Enter: ");
    }
}
