package game.controller;

import game.exception.IllegalAccessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {GameInputController.class})
@RunWith(SpringRunner.class)
public class GameInputControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecretContainer container;


    @Test
    public void startGame() throws Exception {

        container.setSecreValue("secretValue");
        String request1 = "{\"positions0\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"actions0\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}],";
        String request2 = "\"positions1\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"actions1\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}],";
        String request3 = "\"positions2\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"actions2\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}],";
        String request4 = "\"positions3\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"actions3\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}],";
        String request5 = "\"positions4\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"actions4\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}]}";
        mockMvc.perform(post("/games/{id}/round/actions", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request1+request2+request3+request4+request5)
                .header("Secret","secretValue"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

}