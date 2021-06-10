package entities.library;

import entities.book.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "library")
@NamedQueries({
    @NamedQuery(name = "Library.deleteAllRows", query = "DELETE from Library")
})
public class Library implements Serializable {

    private static final long serialVersionUID = 7548386265052987151L;

    @Id
    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Book> books;

    public Library() {
    }

    public static String getLibraryName() {
        return "The Library";
    }

    public void addBook(Book book) {
        if (book != null) {
            this.books.add(book);
            book.setLibrary(this);
        }
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

}
