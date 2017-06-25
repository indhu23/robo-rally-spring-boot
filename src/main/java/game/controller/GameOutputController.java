package game.controller;

import game.Application;
import game.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public GameJoinOutputWrapper joinGame(@PathVariable String id,@RequestBody GameJoinInputWrapper joinGame) {
        ResponseEntity<Void> input= restTemplate.postForEntity(server.buildURI("/games/"+id+"/join1"),joinGame,Void.class);
        container.setSecreValue(input.getHeaders().getFirst("Secret"));
        return (new GameJoinOutputWrapper(input.getStatusCodeValue()));
    }
    @PostMapping("/games/{id}/round/sendRegisters")
    public GameJoinOutputWrapper sendRegisters(@PathVariable String id,@RequestBody RoundActionInputWrapper register) {
        //System.out.println("herer..:"+container.getSecreValue());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Secret", container.getSecreValue());
        HttpEntity<RoundActionInputWrapper> request = new HttpEntity<RoundActionInputWrapper>(register, headers);
        System.out.println("request"+request);
        ResponseEntity<Void> input= restTemplate.postForEntity(server.buildURI("/games/"+id+"/join1"),request,Void.class);
        return (new GameJoinOutputWrapper(input.getStatusCodeValue()));
    }
    @PostMapping("/games/create")
    public GameViewOutputWrapper createGame(@RequestBody Game game ){
        return restTemplate.postForObject(server.buildURI("/games/create1"), game, GameViewOutputWrapper.class);
    }

    @PostMapping("/games/{id}/leave")
    public ResponseEntity<String> leaveTheGame(@PathVariable(value="id") String id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("abc", "secret");
        HttpEntity<String> httpEntity = new HttpEntity(id, httpHeaders);

        String s1 = restTemplate.postForObject(server.buildURI("/games/"+id+"/leave1"),httpEntity, String.class);
        return new ResponseEntity<>(s1, HttpStatus.OK);
    }

}


