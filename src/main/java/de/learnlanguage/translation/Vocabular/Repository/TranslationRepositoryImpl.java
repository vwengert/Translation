package de.learnlanguage.translation.Vocabular.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TranslationRepositoryImpl implements TranslationRepositoryCustom {

    private final RepoBean rB;

    public TranslationRepositoryImpl(RepoBean repoBean) {
        rB = repoBean;
    }

    @Override
    public void doSpezialOperation() {
        log.trace("doSpezialOperation");
        rB.getThis();
    }
}

