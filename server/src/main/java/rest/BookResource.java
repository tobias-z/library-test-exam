package rest;

import dtos.BookDTO;
import dtos.LoanDTO;
import entities.book.BookRepository;
import facades.BookFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import rest.provider.Provider;

@Path("book")
public class BookResource extends Provider {

    private final BookRepository REPO = BookFacade.getInstance(EMF);

    @Context
    SecurityContext securityContext;

    @POST
    @RolesAllowed("user")
    @Path("/loan/{isbn}")
    public Response loanBook(@PathParam("isbn") Integer isbn) {
        String username = securityContext.getUserPrincipal().getName();
        LoanDTO loanDTO = REPO.loanBook(username, isbn);
        return Response.ok(GSON.toJson(loanDTO)).build();
    }
}