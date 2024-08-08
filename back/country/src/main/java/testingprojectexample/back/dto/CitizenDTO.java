package testingprojectexample.back.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Integer passport;
    private LocalDate birthdate;
    private String city;
    private String country;
}
