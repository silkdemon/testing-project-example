package testingprojectexample.back.citizen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import testingprojectexample.back.country.CitizenController;
import testingprojectexample.back.country.CitizenModel;
import testingprojectexample.back.country.CitizenRepository;
import testingprojectexample.back.country.CitizenService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CitizenControllerTest {

    @Mock
    private CitizenRepository citizenRepository;

    @Mock
    private CitizenService citizenService = new CitizenService(citizenRepository);

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        CitizenController citizenController = new CitizenController(citizenService);
        mockMvc = MockMvcBuilders.standaloneSetup(citizenController).build();
    }

    @AfterEach
    void tearDown() {
        citizenRepository.deleteAll();
    }

    @Test
    void shouldGetAllCitizens() throws Exception {
        ArrayList<CitizenModel> citizens = new ArrayList<>();
        citizens.add(citizen);
        when(citizenService.getCitizens()).thenReturn(citizens);

        mockMvc.perform(get("/api/v1/citizen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void shouldPostNewCitizen() throws Exception {
        objectMapper.findAndRegisterModules();
        MvcResult response = mockMvc.perform(post("/api/v1/citizen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citizen)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteCitizen() {
    }

    @Test
    void updateCitizen() {
    }



    private CitizenModel citizen = new CitizenModel("Bart",
            "Simpson",
            123,
            LocalDate.of(1999, 11, 12),
            "Moscow",
            "Russia");
}