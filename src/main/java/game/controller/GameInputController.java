package game.controller;

import game.Application;
import game.Entity.RoundActionInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class GameInputController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Application.URIBuilder server;

    @Autowired
    private SecretContainer container;

    @PostMapping("/games/{id}/round/actions")
    public ResponseEntity<Void> startGame(@PathVariable String id,@RequestBody RoundActionInputWrapper input,@RequestHeader(value="Secret") String secret) {
        System.out.println("input: "+secret);
        if(secret.equals(container.getSecreValue())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


