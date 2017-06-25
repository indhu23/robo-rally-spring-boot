package game.controller;

import game.Entity.Game;
import game.Entity.GameViewOutputWrapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class GameMockServer {

    @RequestMapping(value="/games/list1", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List<GameViewOutputWrapper>> greeting(){
        return ResponseEntity.ok(Arrays.asList(new GameViewOutputWrapper("1", "Tom's game", 6, 4), new GameViewOutputWrapper("2","john",6,2)));
    }
    @PostMapping("/games/{id}/join1")
    public ResponseEntity<Void> joinGameServer(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Secret", "secretValue");
        return new ResponseEntity<>(headers,HttpStatus.valueOf(200));
    }
    @PostMapping("/games/{id}/round/sendRegisters1")
    public ResponseEntity<Void> sendRegisterServer(){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers,HttpStatus.valueOf(204));
    }
    @PostMapping("/games/create1")
    public ResponseEntity<GameViewOutputWrapper> newGame(@RequestBody Game game) {
        GameViewOutputWrapper gameViewOutputWrapper=new GameViewOutputWrapper();
        gameViewOutputWrapper.setId("ANCDEFR");
        gameViewOutputWrapper.setMaxRobotCount(game.getMaxRobotCount()+3);
        gameViewOutputWrapper.setCurrentRobotCount(0);
        gameViewOutputWrapper.setName(game.getName());
        return new ResponseEntity<GameViewOutputWrapper>(gameViewOutputWrapper,HttpStatus.OK);
    }
    @PostMapping("/games/{id}/leave1")
    public ResponseEntity<Void> clientLeaveGame() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
