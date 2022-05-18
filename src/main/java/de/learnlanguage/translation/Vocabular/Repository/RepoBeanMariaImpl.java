package de.learnlanguage.translation.Vocabular.Repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value = "translation.service.implementation",
        havingValue = "MARIADB")
@Service
public class RepoBeanMariaImpl implements RepoBean {

    @Override
    public void getThis() {
        System.out.println("Version Mariadb");
    }
}
