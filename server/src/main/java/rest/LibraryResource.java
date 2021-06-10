package rest;

import dtos.BookDTO;
import dtos.LibraryDTO;
import entities.library.LibraryRepository;
import facades.LibraryFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import utils.SetupDummyData;

@Path("library")
public class LibraryResource extends Provider {

    private final LibraryRepository REPO = LibraryFacade.getInstance(EMF);

    @GET
    public Response getLibrary() {
        LibraryDTO libraryDTO = REPO.getLibrary();
        return Response.ok(libraryDTO).build();
    }

    @GET
    @Path("/generate")
    public Response addDummyBooks() {
        SetupDummyData.addDummyBooks();
        return Response.ok().build();
    }

    @DELETE
    @Path("/{isbn}")
    @RolesAllowed("admin")
    public Response deleteBook(@PathParam("isbn") int isbn) {
        LibraryDTO libraryDTOS = REPO.deleteBook(isbn);
        return Response.ok(GSON.toJson(libraryDTOS)).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response addBook(String requestBody) {
        BookDTO bookDTO = GSON.fromJson(requestBody, BookDTO.class);
        LibraryDTO libraryDTO = REPO.addBook(bookDTO);
        return Response.ok(libraryDTO).build();
    }

}