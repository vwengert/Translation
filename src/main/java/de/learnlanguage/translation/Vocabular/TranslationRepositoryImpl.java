package de.learnlanguage.translation.Vocabular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TranslationRepositoryImpl implements TranslationRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private RepoBean rB;

    @Override
    public void doSpezialOperation() {
        rB.getThis();
    }
}

interface RepoBean {
    void getThis();
}

@ConditionalOnProperty(
        value="translation.service.implementation",
        havingValue = "MARIADB")
@Service
class RepoBeanMariaImpl implements RepoBean {

    @Override
    public void getThis() {
        System.out.println("Version Mariadb");
    }
}

@ConditionalOnProperty(
        value="translation.service.implementation",
        havingValue = "LIST",
        matchIfMissing = true)
@Service
class RepoBeanGenericImpl implements RepoBean {

    @Override
    public void getThis() {
        System.out.println("Version Generic");
    }
}
