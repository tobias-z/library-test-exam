package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.BookDTO;
import dtos.LoanDTO;
import entities.Role;
import entities.User;
import entities.book.Book;
import entities.book.BookRepository;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.TestUtils;

class BookFacadeTest {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();

    private BookRepository repo;
    private Book book1, book2, book3;
    private User user;

    @BeforeEach
    void setUp() {
        repo = BookFacade.getInstance(EMF);
        EntityManager em = EMF.createEntityManager();
        try {
            book1 = new Book("Harry Potter", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard", "Something");
            book2 = new Book("Harry Potter2", Arrays.asList("JK Rowling2"), "Someone2", 1999, "The Wizzard2", "Something2");
            book3 = new Book("Harry Potter3", Arrays.asList("JK Rowling3"), "Someone3", 2000, "The Wizzard3", "Something3");
            em.getTransaction().begin();
            TestUtils.dropTables(em);
            // Books
            em.persist(book1);
            em.persist(book2);
            em.persist(book3);

            // User stuff
            Role userRole = new Role("user");
            user = new User("bob", "1234");
            user.addRole(userRole);
            em.persist(userRole);
            em.persist(user);
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

    @Test
    @DisplayName("create book should create a book and return it")
    void createBookShouldCreateABookAndReturnIt() throws Exception {
        BookDTO bookDTO = new BookDTO("Harry Potter4", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard", "Something");
        BookDTO createdBook = repo.createBook(bookDTO);
        assertNotNull(createdBook);
    }

    @Test
    @DisplayName("loanBook should loan a book to a user")
    void loanBookShouldLoanABookToAUser() throws Exception {
        LoanDTO loanDTO = repo.loanBook(user.getUserName(), book1.getIsbn());
        assertNotNull(loanDTO);
        assertEquals(book1.getIsbn(), loanDTO.getBook().getIsbn());
        assertEquals(user.getUserName(), loanDTO.getUser().getUsername());
    }
}