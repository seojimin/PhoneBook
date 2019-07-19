import java.io.Serializable;

//Serialization - sub class is able to serialize as well.
public class PhoneInfo implements Serializable {
    String name;
    String phoneNum;

    public PhoneInfo(String name, String num){
        this.name = name;
        phoneNum = num;
    }

    public void showInfo(){
        System.out.println("name: " + name);
        System.out.println("phone: " + phoneNum);
    }

    //hashCode() overriding
    public int hashCode(){
        //String hashCode
        return name.hashCode();
    }

    //equals() overriding
    public boolean equals(Object obj){
        PhoneInfo cmp = (PhoneInfo)obj;
        if(name.compareTo(cmp.name) == 0){ //compareTo 0 means equal
            return true; //same name
        }else{
            return false; //different name
        }
    }
}
