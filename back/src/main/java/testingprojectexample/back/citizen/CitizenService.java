package testingprojectexample.back.citizen;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CitizenService {
    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public List<CitizenModel> getCitizens() {
        return citizenRepository.findAll();
    }

    public void addNewCitizen(CitizenModel citizen) {
    }

    @Transactional
    public void updateCitizen(Long citizenId,
                              String firstName,
                              String secondName,
                              String passport,
                              LocalDate birthdate,
                              String city,
                              String country) {
        CitizenModel citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalStateException("citizen with id " + citizenId + "does not exists"));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(citizen.getFirstName(), firstName)) {
            citizen.setFirstName(firstName);

        }

        if (secondName != null && secondName.length() > 0 && !Objects.equals(citizen.getSecondName(), secondName)) {
            citizen.setSecondName(secondName);

        }

        if (passport != null && passport.length() > 0 && !Objects.equals(citizen.getPassport(), passport)) {
            citizen.setPassport(passport);

        }

        if (birthdate != null  && !Objects.equals(citizen.getBirthdate(), birthdate)) {
            citizen.setBirthdate(birthdate);

        }

        if (city != null && city.length() > 0 && !Objects.equals(citizen.getCity(), city)) {
            citizen.setCity(city);

        }

        if (country != null && country.length() > 0 && !Objects.equals(citizen.getCountry(), country)) {
            citizen.setCountry(country);

        }
    }

    public void deleteCitizen(Long citizenId) {
        boolean exists = citizenRepository.existsById(citizenId);
        if (!exists) {
            throw new IllegalStateException("citizen with id " + citizenId + "does not exists");
        }
        citizenRepository.deleteById(citizenId);
    }
}
