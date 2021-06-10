package rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import entities.library.Library;
import facades.LibraryFacade;
import io.restassured.http.ContentType;
import javax.persistence.EntityManager;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LibraryResourceTest extends SetupRestTests {

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
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

}