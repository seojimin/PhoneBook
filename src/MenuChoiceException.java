
//user defined error handling class
public class MenuChoiceException extends Exception{
    int num;

    public MenuChoiceException(int select){
        System.out.println("your choice is not available.");
        num = select;
    }

    public void showError(){
        System.out.println("There is no option for " + num);
    }
}
