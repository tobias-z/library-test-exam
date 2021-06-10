package entities.book;

import dtos.BookDTO;
import entities.library.Library;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@NamedQueries({
    @NamedQuery(name = "Book.deleteAllRows", query = "DELETE from Book")
})
public class Book implements Serializable {

    private static final long serialVersionUID = 8763875038534464071L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer isbn;

    @Column(name = "title")
    private String title;

    @ElementCollection
    @Column(name = "authors")
    private List<String> authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publishYear")
    private Integer publishYear;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private Library library;

    public Book() {
    }

    public Book(String title, List<String> authors, String publisher, Integer publishYear,
        String description, String image) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.image = image;
    }

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.authors = bookDTO.getAuthors();
        this.publisher = bookDTO.getPublisher();
        this.publishYear = bookDTO.getPublishYear();
        this.description = bookDTO.getDescription();
        this.image = bookDTO.getImage();
    }

    public void setIsbn(Integer id) {
        this.isbn = id;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
