package facades;

import dtos.LibraryDTO;
import entities.library.Library;
import entities.library.LibraryRepository;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class LibraryFacade implements LibraryRepository {

    private static LibraryFacade instance;
    private static EntityManagerFactory emf;

    private LibraryFacade() {
    }

    public static LibraryFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LibraryFacade();
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
    public LibraryDTO getLibrary() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
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
        } finally {
            em.close();
        }
        return new LibraryDTO(library);
    }
}