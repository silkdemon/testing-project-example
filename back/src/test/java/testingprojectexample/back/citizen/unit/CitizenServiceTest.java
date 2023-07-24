package testingprojectexample.back.citizen.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testingprojectexample.back.citizen.CitizenModel;
import testingprojectexample.back.citizen.CitizenRepository;
import testingprojectexample.back.citizen.CitizenService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {


    private CitizenService citizenService;

    @Mock
    private CitizenRepository citizenRepository;

    @BeforeEach
    void setUp() {
        citizenService = new CitizenService(citizenRepository);
    }

    @AfterEach
    void tearDown() {
        citizenRepository.deleteAll();
    }

    @Test
    void shouldGetAllCitizens() {
//        citizenService.getCitizens();

        ArrayList<CitizenModel> citizens = new ArrayList<>();
        citizens.add(citizen);
        when(citizenRepository.findAll()).thenReturn(citizens);

        assertThat(citizenService.getCitizens()).hasSize(1);
    }


    @Test
    void shouldAddNewCitizen() {
        when(citizenRepository.save(any(CitizenModel.class))).thenReturn(citizen);

        CitizenModel savedCitizen = citizenService.addNewCitizen(citizen);

        assertThat(savedCitizen).isNotNull();
    }

    @Test
    void updateCitizen() {
    }

    @Test
    void deleteCitizen() {
    }

    private CitizenModel citizen = new CitizenModel("Bart",
            "Simpson",
            123,
            LocalDate.of(1999, 11, 12),
            "Moscow",
            "Russia");
}