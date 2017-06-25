package game.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {GameInputController.class})
@RunWith(SpringRunner.class)
public class GameInputControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SecretContainer container;

    @Test
    public void roundAction() throws Exception {

        String request = "{\"position\":[{\"robot\":\"Hammer Bot\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\": 4,\"y\":3}}],\"action\":[{\"playerId\": \"ABGDGEGA1256\",\"action\": \"MOVE2\",\"damageCards\": [\"SPAM\"]},{\"playerId\": \"ZZZDAD\",\"action\": \"MOVE3\",\"damageCards\": []}]}";
        mockMvc.perform(post("/games/{id}/round/actions", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void timeLimit() throws Exception {
        container.setSecreValue("secretValue");
        String request ="{\"secondsLeft\": \"30\",\"reason\":\"yes,got it\"}";
        mockMvc.perform(post("/games/{id}/round/timeLimitWarning","FSDF")
               .contentType(MediaType.APPLICATION_JSON)
               .content(request)
               .header("Secret","secretValue"))
               .andDo(print())
               .andExpect(status().is(204))
               .andReturn();
    }
    @Test
    public void endGame() throws Exception {
        container.setSecreValue("secretValue");
        String request="mike has won the game";
        mockMvc.perform(post("/games/{id}/end","FSDF")
                .contentType(MediaType.TEXT_PLAIN)
                .content(request)
                .header("Secret","secretValue"))
                .andDo(print())
                .andExpect(status().is(204))
                .andReturn();
    }
    @Test
    public void endRound() throws Exception {
        container.setSecreValue("secretValue");
        mockMvc.perform(post("/games/{id}/round/ends","ANCDEF")
                .header("Secret","secretValue"))
                .andDo(print())
                .andExpect(status().is(204))
                .andReturn();
    }
    @Test
    public void startRound() throws Exception {
     container.setSecreValue("secretValue");
     String request="{\"playerPositions\":[{\"robot\":\"HAMMERBOT\",\"direction\":\"NORTH\",\"playerName\":\"Mike\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\":4,\"y\":3}},{\"robot\":\"HULKX90\",\"direction\":\"NORTH\",\"playerName\":\"Toms\",\"playerId\":\"ABGDGEGA1256\",\"position\":{\"x\":4,\"y\":3}}],\"drawPile\":[\"MOVE1\",\"MOVE2\",\"UTURN\",\"SPAM\",\"LEFT_TURN\",\"RIGHT_TURN\",\"MOVE3\",\"MOVE_BACK\",\"AGAIN\"]}";
     mockMvc.perform(post("/games/{id}/round/start","DSFD")
             .header("Secret","secretValue")
             .contentType(MediaType.APPLICATION_JSON)
             .content(request))
             .andDo(print())
             .andExpect(status().is(204))
             .andReturn();
    }
    @Test
    public void startGame() throws Exception {
        container.setSecreValue("secretValue");
        String request="{\"board\":{\"size\":{\"width\":10,\"height\":30},\"fields\":[{\"x\":0,\"y\":0,\"field\":\"EMPTY\"},{\"x\":1,\"y\":0,\"field\":\"START\"},{\"x\":9,\"y\":29,\"field\":\"PIT\"}]},\"availableRobots\":[\"HAMMERBOT\",\"HULKX90\"],\"availableStartingPositions\":[{\"x\":1,\"y\":0},{\"x\":4,\"y\":3}]}";
        mockMvc.perform(post("/games/{id}/start","EFE")
                .header("Secret","secretValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(jsonPath("$.robot", is("HAMMERBOT")))
                .andExpect(jsonPath("$.position.x",is(1)))
                .andExpect(jsonPath("$.position.y",is(0)));
    }
}
