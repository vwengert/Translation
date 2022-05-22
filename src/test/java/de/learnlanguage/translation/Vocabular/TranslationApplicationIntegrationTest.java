package de.learnlanguage.translation.Vocabular;

import de.learnlanguage.translation.Vocabular.util.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class TranslationApplicationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @IntegrationTest
    void getTranslations_Returns200() throws Exception {
        mockMvc.perform(get("/api/v1/translation")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].translation", is("World")))
                .andExpect(jsonPath("$.[0].word", is("Hallo")));
    }

}