package utils;

import javax.persistence.EntityManager;

public class TestUtils {

    public static void dropTables(EntityManager em) {
        em.createNamedQuery("Loan.deleteAllRows").executeUpdate();
        em.createNamedQuery("Book.deleteAllRows").executeUpdate();
        em.createNamedQuery("Library.deleteAllRows").executeUpdate();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
    }

}
