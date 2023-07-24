package testingprojectexample.back.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testingprojectexample.back.citizen.exception_handling.CitizenIncorrectData;
import testingprojectexample.back.citizen.exception_handling.CitizenNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/citizen")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping
    public List<CitizenModel> getCitizens() {
        return citizenService.getCitizens();


    }

    @GetMapping(path = "{citizenId}")
    public Optional<CitizenModel> getCitizenById(@PathVariable("citizenId") Long citizenId) {
        Optional<CitizenModel> citizen = citizenService.getCitizenById(citizenId);
        if (!citizen.isPresent()) {
            throw new CitizenNotFoundException("Citizen with id " + citizenId + " does not exists");
        } else {
            return citizen;
        }
    }

    @PostMapping
    public void registerNewCitizen(@RequestBody CitizenModel citizen) {
        citizenService.addNewCitizen(citizen);
    }

    @DeleteMapping(path = "{citizenId}")
    public void deleteCitizen(@PathVariable("citizenId") Long citizenId) {
        citizenService.deleteCitizen(citizenId);
    }

    @PutMapping(path = "{citizenId}")
    public CitizenModel updateCitizen(@RequestBody CitizenModel citizen, @PathVariable("citizenId") Long citizenId) {
        citizenService.updateCitizen(citizenId, citizen.getFirstName(), citizen.getSecondName(), citizen.getPassport(), citizen.getBirthdate(), citizen.getCity(), citizen.getCountry());
        return citizen;
    }
}
