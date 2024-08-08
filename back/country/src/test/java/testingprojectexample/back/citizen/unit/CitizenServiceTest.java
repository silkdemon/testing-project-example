package testingprojectexample.back.citizen.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testingprojectexample.back.country.CitizenDTO;
import testingprojectexample.back.country.CitizenModel;
import testingprojectexample.back.country.CitizenRepository;
import testingprojectexample.back.country.CitizenService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        assertThat(savedCitizen.getFirstName()).isEqualTo(citizen.getFirstName());
    }

    @Test
    void shouldGetCitizenById() {
        when(citizenRepository.findById(anyLong())).thenReturn(Optional.ofNullable(citizen));
        Optional<CitizenModel> foundCitizen = citizenService.getCitizenById(1L);
        assertThat(foundCitizen).isNotNull();
        assertThat(foundCitizen.get().getFirstName()).isEqualTo(citizen.getFirstName());
    }

    @Test
    void shouldUpdateCitizen() {
        when(citizenRepository.findById(anyLong())).thenReturn(Optional.ofNullable(citizen));
        citizen.setCountry("USA");

        CitizenDTO updatedCitizen = citizenService.updateCitizen(1L, citizen).get();
        assertThat(updatedCitizen.getCountry()).isEqualTo("USA");
    }

    @Test
    void shouldDeleteCitizen() {
        when(citizenRepository.existsById(1L)).thenReturn(true);
        citizenService.deleteCitizen(1L);
        verify(citizenRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeleteCitizenIfItsNotExist() {
        assertThatThrownBy(() -> citizenService.deleteCitizen(1L)).isInstanceOf(IllegalStateException.class);
    }

    private CitizenModel citizen = new CitizenModel("Bart",
            "Simpson",
            123,
            LocalDate.of(1999, 11, 12),
            "Moscow",
            "Russia");
}