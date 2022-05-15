package de.learnlanguage.translation.Vocabular;


import de.learnlanguage.translation.TranslationApplication;
import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest(classes = TranslationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class ContractBase{
    TranslationService translationService = mock(TranslationServiceImpl.class);
    TranslationController translationController = new TranslationController(translationService);

    @BeforeEach
    public void setup() {
        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.standaloneSetup(this.translationController);

        List<Translation> translationList = new ArrayList<>();
        translationList.add(new Translation(1L, "Hallo", "Hello"));
        when(translationService.getTranslations()).thenReturn(translationList);

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word taken"))
                .when(translationService)
                .addNewTranslation( new Translation(null, "Existiert", "Exists")  );
    }

}
