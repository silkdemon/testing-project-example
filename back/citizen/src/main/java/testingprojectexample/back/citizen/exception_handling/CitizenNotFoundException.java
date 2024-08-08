package testingprojectexample.back.citizen.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CitizenNotFoundException extends RuntimeException {

    public CitizenNotFoundException(String message) {
        super(message);
    }
}
