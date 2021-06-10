package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.BookDTO;
import dtos.LibraryDTO;
import entities.book.Book;
import entities.library.Library;
import entities.library.LibraryRepository;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
            em.createNamedQuery("Library.deleteAllRows").executeUpdate();
            em.persist(library);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getLibrary() {
        LibraryDTO libraryDTO = repo.getLibrary();
        assertNotNull(libraryDTO);
        assertEquals(library.getName(), libraryDTO.getName());
    }

    @Test
    @DisplayName("add book to library")
    void addBookToLibrary() throws Exception {
        BookDTO bookDTO = new BookDTO("Harry Potter", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard", "Something");
        LibraryDTO libraryDTO = repo.addBook(bookDTO);
        assertNotNull(libraryDTO);
    }
}