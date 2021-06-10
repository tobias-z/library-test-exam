package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.LibraryDTO;
import entities.library.Library;
import entities.library.LibraryRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

class LibraryFacadeTest {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();

    private LibraryRepository repo;
    private Library library;

    @BeforeEach
    void setUp() {
        repo = LibraryFacade.getInstance(EMF);
        EntityManager em = EMF.createEntityManager();
        try {
            library = new Library("The Library");
            em.getTransaction().begin();
            em.persist(library);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getLibrary() {
        LibraryDTO libraryDTO = repo.getLibrary();
        assertNotNull(libraryDTO);
        assertEquals(library.getName(), libraryDTO.getName());
    }
}