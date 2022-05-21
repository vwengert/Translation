package de.learnlanguage.translation.Vocabular.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@ConditionalOnProperty(
        value = "translation.service.implementation",
        havingValue = "LIST",
        matchIfMissing = true)
@Service
public class RepoBeanGenericImpl implements RepoBean {

    @Override
    public void getThis() {
        log.trace("getThis()");
        System.out.println("Version Generic");
    }
}
