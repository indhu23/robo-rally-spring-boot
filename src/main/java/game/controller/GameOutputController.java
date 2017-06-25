package game.controller;

import game.Application;
import game.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class GameOutputController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Application.URIBuilder server;

    @Autowired
    private SecretContainer container;

    @GetMapping("/games/list")
    public List<GameViewOutputWrapper> getGameList() {
        return restTemplate.getForEntity(server.buildURI("/games/list1"),wrapper.class).getBody().getGames();
    }
    @PostMapping("/games/{id}/join")
    public String joinGame(@PathVariable String id,@RequestBody GameJoinInputWrapper joinGame) {
        ResponseEntity<Void> input= restTemplate.postForEntity(server.buildURI("/games/"+id+"/join1"),joinGame,Void.class);
        String SecretHeader=input.getHeaders().containsKey("Secret")?input.getHeaders().getFirst("Secret"):"";
        if(!SecretHeader.equals(null) || !SecretHeader.equals("")) {
            container.setSecreValue(input.getHeaders().getFirst("Secret"));
        }
        int status= input.getStatusCodeValue();
        switch (status){
            case 200 : return "Successfully joined game";
            case 404 : return "Game not found";
            case 403 : return "Sorry , joining of the game failed";
            default : return "Something went wrong";
        }
    }
    @PostMapping("/games/{id}/round/sendRegisters")
    public GameJoinOutputWrapper sendRegisters(@PathVariable String id,@RequestBody RoundActionInputWrapper register) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Secret", container.getSecreValue());
        HttpEntity<RoundActionInputWrapper> request = new HttpEntity<RoundActionInputWrapper>(register, headers);
        ResponseEntity<Void> input= restTemplate.postForEntity(server.buildURI("/games/"+id+"/join1"),request,Void.class);
        return (new GameJoinOutputWrapper(input.getStatusCodeValue()));
    }
}


