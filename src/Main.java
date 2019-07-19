
public class Main {
    public static void main(String[] args) {
        PhoneManager phoneManager = PhoneManager.initPhoneManager(); //singleton pattern

        while (true) {
            try {
                MenuViewer.menuViewer(); //Static method
                //Check only int input
                while (!MenuViewer.keyboard.hasNextInt()) {
                    MenuViewer.keyboard.next(); //discard
                    System.out.println("You entered character. Please, enter a number to choose \n");
                    MenuViewer.menuViewer();
                }
                int select = MenuViewer.keyboard.nextInt();
                MenuViewer.keyboard.nextLine(); //nextLine after nextInt to remove delimiter.

                if (select < INIT_MENU.INPUT || select > INIT_MENU.EXIT) {
                    throw new MenuChoiceException(select);
                }

                switch (select) {
                    case INIT_MENU.INPUT:
                        phoneManager.inputData();
                        break;
                    case INIT_MENU.SEARCH:
                        phoneManager.searchData();
                        break;
                    case INIT_MENU.DELETE:
                        phoneManager.deleteData();
                        break;
                    case INIT_MENU.EXIT:
                        phoneManager.storeFile();
                        System.out.println("Exit the program");
                        return;
                }
            } catch (MenuChoiceException e) {
                e.showError();
                System.out.println("You need to enter a number again.");
            }

        }

    }
}
