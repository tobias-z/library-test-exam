package entities.book;

import dtos.BookDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface BookRepository {

    List<BookDTO> getAllBooks() throws WebApplicationException;

    BookDTO createBook(BookDTO bookDTO) throws WebApplicationException;

}
