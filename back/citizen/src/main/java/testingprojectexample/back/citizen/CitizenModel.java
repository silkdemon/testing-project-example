package testingprojectexample.back.citizen;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "citizen_model3")
public class CitizenModel {
    @Id
    @SequenceGenerator(name = "citizen_model3_id_seq",
            sequenceName = "citizen_model3_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "citizen_model3_id_seq"
    )
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private Integer passport;

    @Column
    private LocalDate birthdate;

    @Column
    private String city;

    @Column
    private String country;

    public CitizenModel() {
    }

    public CitizenModel(String firstName, String secondName, Integer passport, LocalDate birthdate, String city, String country) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passport = passport;
        this.birthdate = birthdate;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "CitizenModel{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", passport='" + passport + '\'' +
                ", birthdate=" + birthdate +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
