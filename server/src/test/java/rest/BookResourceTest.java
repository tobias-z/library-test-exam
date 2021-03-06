package rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import dtos.BookDTO;
import entities.Role;
import entities.User;
import entities.book.Book;
import facades.BookFacade;
import io.restassured.http.ContentType;
import java.util.Arrays;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

class BookResourceTest extends SetupRestTests {

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }

    private Book book1;
    private User user;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            book1 = new Book("Harry Potter", Arrays.asList("JK Rowling"), "Someone", 1998, "The Wizzard", "Something");
            em.getTransaction().begin();
            TestUtils.dropTables(em);
            em.persist(book1);

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user = new User("bob", "1234");
            user.addRole(userRole);
            user.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
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
    void loanBook() {
        String token = login();
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .pathParam("isbn", book1.getIsbn())
            .when()
            .post("/books/loan/{isbn}")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("get all user loans should return a 200 response")
    void getAllUserLoansShouldReturnA200Response() throws Exception {
        String token = login();
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .when()
            .get("/books/")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("edit book returns a 200 response code")
    void editBookReturnsA200ResponseCode() throws Exception {
        String token = login();
        BookDTO bookDTO = new BookDTO(book1);
        bookDTO.setTitle("Changed title");
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token",token)
            .body(bookDTO)
            .put("/books")
            .then()
            .statusCode(200);

    }


}