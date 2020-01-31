package contact05;

import java.io.Serializable;

public class ContactVO implements Serializable {


	private static final long serialVersionUID = 1L;
	private String name;
    private String phoneNumber;
    private String email;

    public ContactVO() {
    }

    public ContactVO(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String str = " 이름 : " + getName() + "\n" +  "이메일 : " + getEmail() + "\n" + "전화번호 : " + getPhoneNumber();
        return str;
    }
}
