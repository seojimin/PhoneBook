public class PhoneUnivInfo extends PhoneInfo{
    String major;
    int year;

    public PhoneUnivInfo(String name, String num, String major, int year){
        super(name,num);
        this.major = major;
        this.year = year;
    }

    public void showInfo(){
        super.showInfo();
        System.out.println("major: " + major);
        System.out.println("year: " + year);
    }
}
