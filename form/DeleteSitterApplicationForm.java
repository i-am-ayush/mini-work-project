package form;

import form.Form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteSitterApplicationForm extends Form implements Validatable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<String> errorMessage;


    private void validateId() {
        if (id > 0 && id < 1000)
            errorMessage.add( "id is not valid");
    }

    @Override
    public List<String> validate() {
        errorMessage = new ArrayList<>();
        validateId();
        return errorMessage;

    }
}
