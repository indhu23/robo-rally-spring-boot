package game.controller;

import game.Entity.Game;
import game.Entity.GameViewOutputWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;
/**
 * <h1>Mock Server</h1>
 * Responds for all the requests from the client
 */

@Controller
public class GameMockServer {

    /**
     * This method returns mock response for get gamelist service
     * @return list of games
     */
    @RequestMapping(value="/games/list1", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List<GameViewOutputWrapper>> greeting(){
        return ResponseEntity.ok(Arrays.asList(new GameViewOutputWrapper("1", "Tom's game", 6, 4), new GameViewOutputWrapper("2","john",6,2)));
    }

    /**
     * This method returns mock response for get join game service
     * @return status
     */
    @PostMapping("/games/{id}/join1")
    public ResponseEntity<Void> joinGameServer(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Secret", "secretValue");
        return new ResponseEntity<>(headers,HttpStatus.valueOf(200));
    }

    /**
     * This method returns mock response for send register service
     * @return status
     */
    @PostMapping("/games/{id}/round/sendRegisters1")
    public ResponseEntity<Void> sendRegisterServer(){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers,HttpStatus.valueOf(204));
    }

    /**
     * This method returns mock response for creat game service
     * @return details of the created game
     */
    @PostMapping("/games/create1")
    public GameViewOutputWrapper newGame(@RequestBody Game game) {
        GameViewOutputWrapper gameViewOutputWrapper=new GameViewOutputWrapper();
        gameViewOutputWrapper.setId("dDSG");
        gameViewOutputWrapper.setMaxRobotCount(5);
        gameViewOutputWrapper.setCurrentRobotCount(0);
        gameViewOutputWrapper.setName("sdgvdf");
        return gameViewOutputWrapper;
    }

    /**
     * This method returns mock response for leave game service
     * @return status
     */
    @PostMapping("/games/{id}/leave1")
    public ResponseEntity<String> clientLeaveGame(@RequestBody String id) {
        String s= "the client left the game";
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
