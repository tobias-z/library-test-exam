package rest;

import dtos.LibraryDTO;
import entities.library.LibraryRepository;
import facades.LibraryFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import rest.provider.Provider;

@Path("library")
public class LibraryResource extends Provider {

    private final LibraryRepository REPO = LibraryFacade.getInstance(EMF);

    @GET
    public Response getLibrary() {
        LibraryDTO libraryDTO = REPO.getLibrary();
        return Response.ok(libraryDTO).build();
    }

}