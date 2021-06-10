package utils;

import dtos.BookDTO;
import entities.book.Book;
import entities.library.LibraryRepository;
import facades.LibraryFacade;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class SetupDummyData {

    public static void main(String[] args) {
        addDummyBooks();
    }

    public static void addDummyBooks() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        LibraryRepository repo = LibraryFacade.getInstance(emf);
        EntityManager em = emf.createEntityManager();

        try {
            BookDTO book1 = new BookDTO("Harry Potter and the Philosopher's Stone", Arrays.asList("JK Rowling"),
                "Bloomsbury", 1997, "About a boy who is a wizzard",
                "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fbestfantasybooks.com%2Fblog%2Fwp-content%2Fuploads%2F2013%2F02%2Flotr.png&f=1&nofb=1");
            BookDTO book2 = new BookDTO("The Lord of the Rings", Arrays.asList("Tolkien"), "Allen & Unwin", 1940,
                "An evil master and his rings",
                "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fbestfantasybooks.com%2Fblog%2Fwp-content%2Fuploads%2F2013%2F02%2Flotr.png&f=1&nofb=1");
            BookDTO book3 = new BookDTO("The Lion, the Witch and the Wardrobe", Arrays.asList("C. S. Lewis"),
                "HarperCollins", 1956,
                "Brothers and sisters journey into an unknown land, for dangers and glory",
                "https://pictures.abebooks.com/WESTPORTALBOOKS/1001928577.jpg");

            repo.addBook(book1);
            repo.addBook(book2);
            repo.addBook(book3);
        } catch (Exception e) {
            throw new WebApplicationException("Unable to create books. Maybe you already had them?");
        } finally {
            em.close();
        }
    }

}
