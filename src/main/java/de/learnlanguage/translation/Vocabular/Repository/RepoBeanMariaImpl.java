package de.learnlanguage.translation.Vocabular.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@ConditionalOnProperty(
        value = "translation.service.implementation",
        havingValue = "MARIADB")
@Service
public class RepoBeanMariaImpl implements RepoBean {

    @Override
    public void getThis() {
        log.trace("getThis()");
        System.out.println("Version Mariadb");
    }
}
