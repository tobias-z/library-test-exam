package entities.library;

import dtos.BookDTO;
import dtos.LibraryDTO;
import javax.ws.rs.WebApplicationException;

public interface LibraryRepository {

    LibraryDTO getLibrary() throws WebApplicationException;

    LibraryDTO addBook(BookDTO bookDTO) throws WebApplicationException;

}
