package game.controller;

import game.Entity.GameJoinInputWrapper;
import game.Entity.GameJoinOutputWrapper;
import game.Entity.GameViewOutputWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@WebMvcTest(controllers = {GameOutputController.class})
@RunWith(SpringRunner.class)
public class GameOutputControllerTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GameOutputController controller;

    MockRestServiceServer mockRestServiceServer;
    @Before
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getGameList() throws Exception {

        String responseBody="{\"games\":[{\"id\": \"haam\",\"name\":\"hi\",\"maxRobotCount\":6,\"currentRobotCount\":1}]}";
        mockRestServiceServer.expect(ExpectedCount.once(),
                requestTo("http://localhost:8080/games/list1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(responseBody,APPLICATION_JSON));
        List<GameViewOutputWrapper> input= controller.getGameList();
        assertEquals("haam",input.get(0).getId());
        mockRestServiceServer.verify();

    }

    @Test
    public void joinGame() throws Exception {

        mockRestServiceServer.expect(
                requestTo("http://localhost:8080/games/1/join1"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());
        GameJoinOutputWrapper input=controller.joinGame("1",new GameJoinInputWrapper("mike","http://10.1.11.101:8080/rest/"));
        assertEquals(200,input.getStatus());
        mockRestServiceServer.verify();
    }

    @Test
    public void sendRegister() throws Exception {
    }

}