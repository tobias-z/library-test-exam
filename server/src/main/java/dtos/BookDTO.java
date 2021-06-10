package dtos;

import entities.book.Book;
import java.util.List;
import java.util.stream.Collectors;

public class BookDTO {

    private Integer isbn;
    private String title;
    private List<String> authors;
    private String publisher;
    private Integer publishYear;
    private String description;
    private String image;

    public BookDTO(String title, List<String> authors, String publisher, Integer publishYear,
        String description, String image) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.image = image;
    }

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.authors = book.getAuthors();
        this.publisher = book.getPublisher();
        this.publishYear = book.getPublishYear();
        this.description = book.getDescription();
        this.image = book.getImage();
    }

    public static List<BookDTO> getBookDTOSFromBooks(List<Book> books) {
        return books.stream()
            .map(book -> new BookDTO(book))
            .collect(Collectors.toList());
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
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
}
