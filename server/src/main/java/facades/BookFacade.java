package facades;

import dtos.BookDTO;
import dtos.LoanDTO;
import entities.Loan;
import entities.User;
import entities.book.Book;
import entities.book.BookRepository;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class BookFacade implements BookRepository {

    private static BookFacade instance;
    private static EntityManagerFactory emf;

    private BookFacade() {
    }

    public static BookFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<BookDTO> getAllBooks() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
            return BookDTO.getBookDTOSFromBooks(books);
        } catch (Exception e) {
            throw new WebApplicationException("Unable to find any books", 404);
        } finally {
            em.close();
        }
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) throws WebApplicationException {
        Book book = new Book(bookDTO);
        executeInsideTransaction(em -> em.persist(book));
        return new BookDTO(book);
    }

    @Override
    public LoanDTO loanBook(String username, int isbn) throws WebApplicationException {
        return withUser(username, (user, em) -> {
            Book book = em.find(Book.class, isbn);
            if (book == null) {
                throw new WebApplicationException("Unable to find book with isbn: " + isbn);
            }
            Loan loan = new Loan(null);
            em.getTransaction().begin();
            book.addLoad(loan);
            user.addLoan(loan);
            em.getTransaction().commit();
            return new LoanDTO(loan);
        });
    }

    public LoanDTO withUser(String username, UserAction<LoanDTO> action) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, username);
            if (user == null) {
                throw new WebApplicationException("Unable to find user with that username", 403);
            }
            return action.commit(user, em);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage());
        } finally {
            em.close();
        }
    }

}

@FunctionalInterface
interface UserAction<T> {
    T commit(User user, EntityManager em) throws WebApplicationException;
}
