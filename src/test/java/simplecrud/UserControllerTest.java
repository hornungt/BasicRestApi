package simplecrud;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class UserControllerTest {

    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void createUserSavesAndReturnsNewUser(){
        // arrange
        User newUser = new User("user1", 45);
        HttpRequest<User> req = HttpRequest.POST("/users", newUser);

        String expected = "{\"name\":\"user1\",\"age\":45,\"idNum\":0}";

        // act
        String result = client.toBlocking().retrieve(req);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void getAllUsersReturnsListOfAllUsers(){
        // arrange
        HttpRequest<String> req = HttpRequest.GET("/users");

        String expected =
                "[{\"name\":\"alpha\",\"age\":1,\"idNum\":0}," +
                "{\"name\":\"beta\",\"age\":2,\"idNum\":0}," +
                "{\"name\":\"charlie\",\"age\":3,\"idNum\":0}]";

        // act
        String result = client.toBlocking().retrieve(req);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void getUserByIdReturnsFirstUserWithId(){
        // arrange
        HttpRequest<String> req = HttpRequest.GET("/users/0");

        String expected = "{\"name\":\"alpha\",\"age\":1,\"idNum\":0}";
        // act
        String result = client.toBlocking().retrieve(req);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void updateUserReturnsUpdateResult(){
        // arrange
        User newUser = new User("user1", 45);
        HttpRequest<User> req = HttpRequest.PUT("/users/0", newUser);

        String expected = "{\"matchedCount\":1,\"modifiedCount\":1,\"modifiedCountAvailable\":true}";
        // act
        String result = client.toBlocking().retrieve(req);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void deleteUserReturnsDeleteResult(){
        // arrange
        HttpRequest<String> req = HttpRequest.DELETE("/users/0");

        String expected = "{\"deletedCount\":1}";

        // act
        String result = client.toBlocking().retrieve(req);

        // assert
        assertEquals(expected, result);
    }

}
