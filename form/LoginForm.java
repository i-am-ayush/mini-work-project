package form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginForm extends Form implements Validatable {
    private String email;
    private String pass;
    private List<String> errorMessage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private void validateEmail() {
        if (email.isEmpty()) {
            errorMessage.add("Email can't be empty");
        } else if (!email.matches("^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$")) {
            errorMessage.add( "Email format incorrect!");
        }
    }

    private void validatePassword() {
        if (pass.isEmpty()) {
            errorMessage.add("password can't be empty");
        }
    }

    @Override
    public List<String> validate() {
        errorMessage = new ArrayList<>();
        validateEmail();
        validatePassword();
        return errorMessage;
    }
}

