package facades;

import dtos.BookDTO;
import dtos.LibraryDTO;
import entities.book.Book;
import entities.book.BookRepository;
import entities.library.Library;
import entities.library.LibraryRepository;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class LibraryFacade implements LibraryRepository {

    private static LibraryFacade instance;
    private static EntityManagerFactory emf;
    private static BookRepository bookRepo;

    private LibraryFacade() {
    }

    public static LibraryFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LibraryFacade();
            bookRepo = BookFacade.getInstance(_emf);
        }
        return instance;
    }

    @Override
    public LibraryDTO getLibrary() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            Library library = getTransientLibrary(em);
            return new LibraryDTO(library);
        } finally {
            em.close();
        }
    }

    @Override
    public LibraryDTO addBook(BookDTO bookDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            BookDTO createdBook = bookRepo.createBook(bookDTO);
            Library library = getTransientLibrary(em);
            Book book = em.find(Book.class, createdBook.getIsbn());
            em.getTransaction().begin();
            library.addBook(book);
            em.getTransaction().commit();
            return new LibraryDTO(library);
        } catch (Exception e) {
            throw new WebApplicationException("Unable to add book: " + bookDTO.getTitle(), 500);
        } finally {
            em.close();
        }
    }

    @Override
    public LibraryDTO deleteBook(int isbn) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            Book book = em.find(Book.class, isbn);
            if (book == null) {
                throw new WebApplicationException("Unable to find book", 403);
            }
            Library library = getTransientLibrary(em);
            em.getTransaction().begin();
            library.getBooks().remove(book);
            em.getTransaction().commit();
            return new LibraryDTO(library);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage());
        } finally {
            em.close();
        }
    }

    private Library getTransientLibrary(EntityManager em) {
        Library library;
        String name = Library.getLibraryName();
        try {
            library = em.createQuery("SELECT l FROM Library l WHERE l.name = :name", Library.class)
                .setParameter("name", name)
                .getSingleResult();
        } catch (Exception e) {
            library = new Library(name);
            em.getTransaction().begin();
            em.persist(library);
            em.getTransaction().commit();
        }
        return library;
    }
}