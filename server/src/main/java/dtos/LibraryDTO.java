package dtos;

import entities.book.Book;
import entities.library.Library;
import java.util.List;

public class LibraryDTO {

    private String name;
    private List<BookDTO> books;

    public LibraryDTO(String name) {
        this.name = name;
    }

    public LibraryDTO(Library library) {
        this.name = library.getName();
        this.books = BookDTO.getBookDTOSFromBooks(library.getBooks());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
