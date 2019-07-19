import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class PhoneManager {

    private final File dataFile = new File("PhoneBook.txt");
    HashSet<PhoneInfo> phoneInfo = new HashSet<>();

    //Singleton pattern.
    private PhoneManager() {
        try {
            readFile();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static PhoneManager inst = null;

    public static PhoneManager initPhoneManager() {
        if (inst == null) {
            inst = new PhoneManager();
        }
        return inst;
    }

    public PhoneInfo friendInput() {
        System.out.print("name: ");
        String name = MenuViewer.keyboard.nextLine();
        System.out.print("phone: ");
        String phone = MenuViewer.keyboard.nextLine();

        return new PhoneInfo(name, phone);
    }

    public PhoneInfo univInput() {
        System.out.print("name: ");
        String name = MenuViewer.keyboard.nextLine();
        System.out.print("phone: ");
        String phone = MenuViewer.keyboard.nextLine();
        System.out.print("major: ");
        String major = MenuViewer.keyboard.nextLine();
        System.out.print("year: ");
        int year = MenuViewer.keyboard.nextInt();
        MenuViewer.keyboard.nextLine();

        return new PhoneUnivInfo(name, phone, major, year);
    }

    public PhoneInfo companyInput() {
        System.out.print("name: ");
        String name = MenuViewer.keyboard.nextLine();
        System.out.print("phone: ");
        String phone = MenuViewer.keyboard.nextLine();
        System.out.print("company: ");
        String company = MenuViewer.keyboard.nextLine();

        return new PhoneCompanyInfo(name, phone, company);
    }

    public void inputData() throws MenuChoiceException {
        System.out.println("\nStart to input data");
        System.out.println("1.Friend, 2.University, 3.Company");
        System.out.print("Enter: ");

        //Check only int input
        while (!MenuViewer.keyboard.hasNextInt()) {
            MenuViewer.keyboard.next(); //discard
            System.out.println("\nYou entered character. Please, enter a number to choose \n");
            System.out.println("Select the number");
            System.out.println("1.Friend, 2.University, 3.Company");
            System.out.print("Enter: ");
        }
        int select = MenuViewer.keyboard.nextInt();
        MenuViewer.keyboard.nextLine();
        PhoneInfo info = null;

        if (select < INPUT_SELECT.FRIEND || select > INPUT_SELECT.COMPANY) {
            throw new MenuChoiceException(select);
        }

        switch (select) {
            case INPUT_SELECT.FRIEND:
                info = friendInput();
                break;
            case INPUT_SELECT.UNI:
                info = univInput();
                break;
            case INPUT_SELECT.COMPANY:
                info = companyInput();
                break;
        }

        boolean isAdded = phoneInfo.add(info);
        if (isAdded == true)
            System.out.println("completed\n");
        else
            System.out.println("Duplicated.\n");
    }

    public void searchData() {
        System.out.println("\nStart to search data");
        System.out.print("Enter the name: ");
        String name = MenuViewer.keyboard.nextLine();

        //It's not Array anymore, so we need an object to search the data
        Iterator<PhoneInfo> itr = phoneInfo.iterator(); //iterate phoneInfo
        while (itr.hasNext()) { //return true until no more elements left to iterate
            PhoneInfo curInfo = itr.next();
            if (name.compareTo(curInfo.name) == 0) {
                curInfo.showInfo();
            }
        }
        System.out.println("");
    }

    public void deleteData() {
        System.out.println("\nStart to delete data");
        System.out.print("Enter the name: ");
        String name = MenuViewer.keyboard.nextLine();

        Iterator<PhoneInfo> itr = phoneInfo.iterator(); //iterate phoneInfo
        while (itr.hasNext()) { //return true until no more elements left to iterate
            PhoneInfo curInfo = itr.next();
            if (name.compareTo(curInfo.name) == 0) {
                itr.remove();
                System.out.println("deleted\n");
                return;
            }
        }
        System.out.println("No data matched\n");
    }

    public void storeFile() {
        BufferedOutputStream file = null;
        try {
            file = new BufferedOutputStream (new FileOutputStream(dataFile));
            try {
                ObjectOutputStream out = new ObjectOutputStream(file);
                Iterator<PhoneInfo> itr = phoneInfo.iterator();
                while (itr.hasNext())
                    out.writeObject(itr.next());

                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFile() throws ClassNotFoundException {
        if (!dataFile.exists())
            return;

        ObjectInputStream in = null;
        try {
            BufferedInputStream fileIn = new BufferedInputStream (new FileInputStream(dataFile));
            if (fileIn == null) {
                throw new IOException("Can't find file.");
            }
            in = new ObjectInputStream(fileIn);

            while (true) {
                PhoneInfo info = (PhoneInfo) in.readObject();
                if (info == null) //read til end of file (null)
                    break;
                phoneInfo.add(info);
            }
        } catch (EOFException e) {
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close(); //You should always close in a finally block.
                }
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
        }
    }
}
