package game.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {GameInputController.class})
@RunWith(SpringRunner.class)
public class GameInputControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void startGame() throws Exception {

        String request = "{\"position\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"action\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}]}";
        mockMvc.perform(post("/games/{id}/round/actions", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}