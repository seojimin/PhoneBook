public class PhoneCompanyInfo extends PhoneInfo{
    String company;

    public PhoneCompanyInfo(String name, String num, String company){
        super(name, num);
        this.company = company;
    }

    public void showInfo(){
        super.showInfo();
        System.out.println("company: " + company);
    }


}
