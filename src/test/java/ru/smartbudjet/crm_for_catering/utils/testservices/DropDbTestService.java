package ru.smartbudjet.crm_for_catering.utils.testservices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DropDbTestService implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private String tableNamesString;


    @Transactional
    public void dropAll() {
        entityManager.flush();
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableNamesString + " RESTART IDENTITY")
                .executeUpdate();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> tableNames = entityManager.createNativeQuery(
                        "SELECT table_name FROM information_schema.tables WHERE table_schema='public'")
                .getResultList();
        tableNamesString = String.join(",", tableNames);
    }

}
