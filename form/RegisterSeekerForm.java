package form;

import bean.Member;
import com.mysql.cj.util.StringUtils;
import service.MemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterSeekerForm extends Form implements Validatable {
    private String firstname;
    private String lastname;
    //public enum Status {ACTIVE, INACTIVE}
    //private Status status;
    private int phone;
    private String email;
    private String address;
    private String password;

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
    private String spousename;
    private int children;
    //    private Member membertype;
//    public enum Member{SEEKER,SITTER;
//        public static Member stringToEnum(String s) {
//            if (s.equals("SITTER"))
//                return SITTER;
//            return SEEKER;
//        }}
    private List<String> errorMessage;

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

//    public Member getMembertype() {
//        return membertype;
//    }
//
//    public void setMembertype(Member membertype) {
//        this.membertype = membertype;
//    }

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

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    private void validateNoOfChildren() {
        if (String.valueOf(children).isEmpty()) {
            errorMessage.add("No of Children can't be empty!");
        } else if (!String.valueOf(children).matches("^0$|^[1-9][0-9]*$")) {
            errorMessage.add("No zeros before any number allowed!");
        }
    }

    private void validateSpouseName() {
        if (spousename.isEmpty()) {
            errorMessage.add("spousename can't be empty!");
        } else if (!spousename.matches("[A-za-z]+")) {
            errorMessage.add(" In spousename Only characters allowed!");
        }
    }

    private void validateFirstName() {
        if (firstname.isEmpty()) {
            errorMessage.add("firstname can't be empty!");
        } else if (!firstname.matches("[A-za-z]+"))
            errorMessage.add("firstname should consist of only alphabets");
    }

    private void validateLastName() {
        if (email.isEmpty()) {
            errorMessage.add("Email id can't be empty!");
        } else if (!lastname.matches("[A-za-z]+"))
            errorMessage.add("lastname should consist of only alphabets");
    }


    private void validatePhoneNo() {
        if (String.valueOf(phone).isEmpty()) {
            errorMessage.add("Phone number can't be empty!");
        } else if (!String.valueOf(phone).matches("^[0-9]{10}$")) {
            errorMessage.add("Phone number should be of 10 digits only.");
        }
    }

    private void validateEmail() {
        if (email.isEmpty()) {
            errorMessage.add("Email id can't be empty!");
        } else if (!email.matches("^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+.)+[a-z]{2,5}$")) {
            errorMessage.add("Incorrect email format");
        } else if (MemberService.getMemberIdByEmail(email) > 0) {
            errorMessage.add("Account already exist with given emailId");
        }
    }

    private void validateAddress() {
        if (StringUtils.isNullOrEmpty(address)) {
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
        return errorMessage;
    }
}
