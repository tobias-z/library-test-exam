package utils;

import javax.persistence.EntityManager;

public class TestUtils {

    public static void dropTables(EntityManager em) {
        em.createNamedQuery("Book.deleteAllRows").executeUpdate();
        em.createNamedQuery("Library.deleteAllRows").executeUpdate();
    }

}
