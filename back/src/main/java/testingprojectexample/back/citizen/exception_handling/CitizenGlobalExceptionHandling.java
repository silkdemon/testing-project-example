package testingprojectexample.back.citizen.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CitizenGlobalExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<CitizenIncorrectData> handleException(
            CitizenNotFoundException ex
    ) {
        CitizenIncorrectData data = new CitizenIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
