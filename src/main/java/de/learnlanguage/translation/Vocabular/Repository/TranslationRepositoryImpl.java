package de.learnlanguage.translation.Vocabular.Repository;

public class TranslationRepositoryImpl implements TranslationRepositoryCustom {

    private final RepoBean rB;

    public TranslationRepositoryImpl(RepoBean repoBean) {
        rB = repoBean;
    }

    @Override
    public void doSpezialOperation() {
        rB.getThis();
    }
}

