package form;
import bean.Member;
import service.MemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterSitterForm extends Form implements Validatable{
    private String firstname;

    public double getExpectedpay() {
        return expectedpay;
    }

    public void setExpectedpay(double expectedpay) {
        this.expectedpay = expectedpay;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    private String lastname;
    private double expectedpay;
    private int experience;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//
//    public Member getMembertype() {
//        return membertype;
//    }
//
//    public void setMembertype(Member membertype) {
//        this.membertype = membertype;
//    }


    //public enum Status {ACTIVE, INACTIVE}
    //private Status status;
    private int phone;
    private String email;
    private String address;
    private String password;
    private List<String> errorMessage;


    private void validateexpectedPay(){
        if(expectedpay<=0.0){
            errorMessage.add("expected pay is not valid");
        }
    }
    private void validateexperience(){
        if(experience<0){
            errorMessage.add("experience is not valid");
        }
    }
    private void validateFirstName() {
        if (firstname.isEmpty()) {
            errorMessage.add( "firstname can't be empty!");
        } else if (!firstname.matches("[A-za-z]+"))
            errorMessage.add("firstname should consist of only alphabets");
    }

    private void validateLastName() {
        if (email.isEmpty()) {
            errorMessage.add("Email id can't be empty!");
        } else if (!lastname.matches("[A-za-z]+"))
            errorMessage.add( "lastname should consist of only alphabets");
    }


    private void validatePhoneNo() {
        if (String.valueOf(phone).isEmpty()) {
            errorMessage.add( "Phone number can't be empty!");
        } else if (!String.valueOf(phone).matches("^[0-9]{10}$")) {
            errorMessage.add("Phone number should be of 10 digits only.");
        }
    }

    private void validateEmail() {
        if (email.isEmpty()) {
            errorMessage.add( "Email id can't be empty!");
        } else if (!email.matches("^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+.)+[a-z]{2,5}$")) {
            errorMessage.add( "Incorrect email format");
        } else if (MemberService.getMemberIdByEmail(email) > 0) {
            errorMessage.add( "Account already exist with given emailId");
        }
    }

    private void validateAddress() {
        if (address.isEmpty()) {
            errorMessage.add("Address can't be empty!");
        }
    }

    @Override
    public List<String> validate() {
        errorMessage = new ArrayList<>();
        validateFirstName();
        validateLastName();
        validateAddress();
        validateEmail();
        validatePhoneNo();
        validateexperience();
        validateexpectedPay();
        return errorMessage;
    }
//    private Member membertype;
//    public enum Member{SEEKER,SITTER;
//        public static Member stringToEnum(String s) {
//            if (s.equals("SITTER"))
//                return SITTER;
//            return SEEKER;
//        }}
}
