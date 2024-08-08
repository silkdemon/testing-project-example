package testingprojectexample.back.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import testingprojectexample.back.dto.CitizenDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public Map<String, Long> getCountriesPopulationList() {
        ResponseEntity<LinkedList> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/api/v1/citizen", LinkedList.class);
        LinkedList citizenList = responseEntity.getBody();
        LinkedList<CitizenDTO> response = new LinkedList<CitizenDTO>();

        for (Object o : citizenList){
            response.add((CitizenDTO) o);
        }


        return countryService.countPopulationCountry(citizenList);
    }
}
