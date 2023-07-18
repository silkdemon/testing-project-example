package testingprojectexample.back.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping
    public void registerNewCitizen(@RequestBody CitizenModel citizen) {
        citizenService.addNewCitizen(citizen);
    }

    @DeleteMapping(path = "{citizenId}")
    public void deleteCitizen(@PathVariable("citizenId") Long citizenId) {
        citizenService.deleteCitizen(citizenId);
    }

    @PutMapping(path = "{citizenId}")
    public void updateCitizen(@PathVariable("citizenId") Long citizenId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String secondName,
                              @RequestParam(required = false) String passport,
                              @RequestParam(required = false) LocalDate birthdate,
                              @RequestParam(required = false) String city,
                              @RequestParam(required = false) String country) {
        citizenService.updateCitizen(citizenId, firstName, secondName, passport, birthdate, city, country);
    }
}
