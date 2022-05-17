package de.learnlanguage.translation.Vocabular;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

public class TranslationRepositoryImpl implements TranslationRepositoryCustom{

    private RepoBean rB;

    public TranslationRepositoryImpl(RepoBean repoBean) {
        rB = repoBean;
    };

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
