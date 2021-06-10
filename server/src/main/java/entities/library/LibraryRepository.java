package entities.library;

import dtos.LibraryDTO;
import javax.ws.rs.WebApplicationException;

public interface LibraryRepository {

    LibraryDTO getLibrary() throws WebApplicationException;

    //TODO: add book to library

}
