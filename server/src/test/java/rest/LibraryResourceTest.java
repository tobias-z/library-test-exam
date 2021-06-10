package rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import dtos.BookDTO;
import entities.Role;
import entities.User;
import entities.book.Book;
import entities.library.Library;
import facades.LibraryFacade;
import io.restassured.http.ContentType;
import java.util.Arrays;
import javax.persistence.EntityManager;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

class LibraryResourceTest extends SetupRestTests {

    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            book = new Book("Harry Potter", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard",
                "Something");
            em.getTransaction().begin();
            TestUtils.dropTables(em);
            em.persist(book);

            Role userRole = new Role("admin");
            user = new User("bob", "1234");
            user.addRole(userRole);
            em.persist(userRole);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }

    private String login() {
        String json = String.format("{username: \"%s\", password: \"%s\"}", user.getUserName(), "1234");
        return given()
            .contentType("application/json")
            .body(json)
            .when().post("/login")
            .then()
            .extract().path("token");
    }


    @Test
    @DisplayName("get library should return 200 response")
    void getLibraryShouldReturn200Response() throws Exception {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/library")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("get library should hava name")
    void getLibraryShouldHavaName() throws Exception {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/library")
            .then()
            .body("name", Matchers.equalTo(Library.getLibraryName()));
    }

    @Test
    @DisplayName("delete book should delete a book and remove it from the library")
    void deleteBookShouldDeleteABookAndRemoveItFromTheLibrary() throws Exception {
        String token = login();
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .pathParam("isbn", book.getIsbn())
            .when()
            .delete("/library/{isbn}")
            .then()
            .statusCode(200)
            .body("name", Matchers.equalTo(Library.getLibraryName()));
    }

    @Test
    @DisplayName("add book should return with a 200 response code")
    void addBookShouldReturnWithA200ResponseCode() throws Exception {
        String token = login();
        BookDTO bookDTO = new BookDTO("New book", Arrays.asList("SOmeone"), "Someone", 1998, "The Wizzard",
            "Something");
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .body(bookDTO)
            .when()
            .post("/library/")
            .then()
            .statusCode(200)
            .body("name", Matchers.equalTo(Library.getLibraryName()));
    }

}