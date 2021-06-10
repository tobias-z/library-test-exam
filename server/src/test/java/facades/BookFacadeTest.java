package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.BookDTO;
import entities.book.Book;
import entities.book.BookRepository;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

class BookFacadeTest {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();

    private BookRepository repo;
    private Book book1, book2, book3;

    @BeforeEach
    void setUp() {
        repo = BookFacade.getInstance(EMF);
        EntityManager em = EMF.createEntityManager();
        try {
            book1 = new Book("Harry Potter", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard", "Something");
            book2 = new Book("Harry Potter2", Arrays.asList("JK Rowling2"), "Someone2", 1999, "The Wizzard2", "Something2");
            book3 = new Book("Harry Potter3", Arrays.asList("JK Rowling3"), "Someone3", 2000, "The Wizzard3", "Something3");
            em.getTransaction().begin();
            em.persist(book1);
            em.persist(book2);
            em.persist(book3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Book.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAllBooks() {
        List<BookDTO> bookDTOS = repo.getAllBooks();
        assertNotNull(bookDTOS);
        assertEquals(3, bookDTOS.size());
    }
}