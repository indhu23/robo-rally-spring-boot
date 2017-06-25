package game.controller;

import game.Entity.GameViewOutputWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<Void>(headers,HttpStatus.valueOf(200));
    }
    @PostMapping("/games/{id}/round/sendRegisters1")
    public ResponseEntity<Void> sendRegisterServer(){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers,HttpStatus.valueOf(204));
    }
}
