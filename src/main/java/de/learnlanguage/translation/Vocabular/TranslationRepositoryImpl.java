package de.learnlanguage.translation.Vocabular;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TranslationRepositoryImpl implements TranslationRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void doSpezialOperation() {
        System.out.println("Fancy DB Stuff, like stored procedure");
    }
}
