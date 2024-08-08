package testingprojectexample.back.country;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "country_model")
public class CountryModel {


    @Id
    @SequenceGenerator(name = "country_model_id_seq",
            sequenceName = "country_model_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "country_model_id_seq"
    )
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "population")
    private Long population;

    public CountryModel() {
    }

    public CountryModel(String country, Long population) {
        this.country = country;
        this.population = population;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}