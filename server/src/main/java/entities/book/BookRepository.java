package entities.book;

import dtos.BookDTO;
import dtos.LoanDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface BookRepository {

    List<BookDTO> getAllBooks() throws WebApplicationException;

    BookDTO createBook(BookDTO bookDTO) throws WebApplicationException;

    LoanDTO loanBook(String username, int isbn) throws WebApplicationException;

    List<LoanDTO> getAllUserLoans(String username) throws WebApplicationException;

}
