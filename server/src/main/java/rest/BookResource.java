package rest;

import dtos.BookDTO;
import dtos.LoanDTO;
import entities.book.BookRepository;
import facades.BookFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import rest.provider.Provider;

@Path("books")
public class BookResource extends Provider {

    private final BookRepository REPO = BookFacade.getInstance(EMF);

    @Context
    SecurityContext securityContext;

    @POST
    @RolesAllowed({"user", "admin"})
    @Path("/loan/{isbn}")
    public Response loanBook(@PathParam("isbn") Integer isbn) {
        String username = securityContext.getUserPrincipal().getName();
        LoanDTO loanDTO = REPO.loanBook(username, isbn);
        return Response.ok(GSON.toJson(loanDTO)).build();
    }

    @GET
    @RolesAllowed({"user", "admin"})
    public Response getAllUserLoans() {
        String username = securityContext.getUserPrincipal().getName();
        List<LoanDTO> loanDTOS = REPO.getAllUserLoans(username);
        return Response.ok(GSON.toJson(loanDTOS)).build();
    }

    @PUT
    @RolesAllowed("admin")
    public Response editBook(String requestBody) {
        BookDTO bookDTO = GSON.fromJson(requestBody, BookDTO.class);
        BookDTO editedBook = REPO.editBook(bookDTO);
        return Response.ok(GSON.toJson(editedBook)).build();
    }

}