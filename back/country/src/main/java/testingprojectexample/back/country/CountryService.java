package testingprojectexample.back.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testingprojectexample.back.dto.CitizenDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Map<String, Long> countPopulationCountry(LinkedList<CitizenDTO> response) {
        Map<String, Long> citizenCountByCountry = new HashMap<>();

        for (CitizenDTO citizen : response) {
            citizenCountByCountry.merge(citizen.getCountry(), 1L, Long::sum);
        }

        for (Map.Entry<String, Long> entry : citizenCountByCountry.entrySet()) {
            CountryModel countryCount = new CountryModel(entry.getKey(), entry.getValue());
            countryRepository.save(countryCount);
        }
        return citizenCountByCountry;
    }
}