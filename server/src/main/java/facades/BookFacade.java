package facades;

import dtos.BookDTO;
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
}