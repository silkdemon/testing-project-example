package testingprojectexample.back.citizen;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<CitizenModel> getCitizenById(Long citizenId) {
        return citizenRepository.findById(citizenId);
    }

    public CitizenModel addNewCitizen(CitizenModel citizen) {
        citizenRepository.save(citizen);
        return citizen;
    }

    @Transactional
    public Optional<CitizenDTO> updateCitizen(Long id, CitizenModel updatedCitizen) {
        return citizenRepository.findById(id).map(existingCitizen -> {
            existingCitizen.setFirstName(updatedCitizen.getFirstName());
            existingCitizen.setSecondName(updatedCitizen.getSecondName());
            existingCitizen.setPassport(updatedCitizen.getPassport());
            existingCitizen.setBirthdate(updatedCitizen.getBirthdate());
            existingCitizen.setCity(updatedCitizen.getCity());
            existingCitizen.setCountry(updatedCitizen.getCountry());
            // Convert to DTO
            CitizenDTO citizenDTO = new CitizenDTO();
            citizenDTO.setFirstName(existingCitizen.getFirstName());
            citizenDTO.setSecondName(existingCitizen.getSecondName());
            citizenDTO.setPassport(existingCitizen.getPassport());
            citizenDTO.setBirthdate(existingCitizen.getBirthdate());
            citizenDTO.setCity(existingCitizen.getCity());
            citizenDTO.setCountry(existingCitizen.getCountry());

            return citizenDTO;
        });
    }

    public void deleteCitizen(Long citizenId) {
        boolean exists = citizenRepository.existsById(citizenId);
        if (!exists) {
            throw new IllegalStateException("citizen with id " + citizenId + " does not exists");
        }
        citizenRepository.deleteById(citizenId);
    }
}
