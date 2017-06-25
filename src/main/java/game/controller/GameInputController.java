package game.controller;

import game.Application;
import game.Entity.*;
import game.exception.IllegalAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<Void> startGame(@PathVariable String id, @RequestBody RoundActionInputWrapper input, @RequestHeader(value = "Secret") String secret)throws IllegalAccessException{
        System.out.println("input: " + secret);
        if (secret.equals(container.getSecreValue())) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            throw new IllegalAccessException("Secrets doesnt match");
     }

    @PostMapping(value= "/games/{id}/start")
    public ResponseEntity<RobotPositionClientRespond> startGame(@RequestBody BoardLayout boardLayout, @PathVariable(value="id") String id,@RequestHeader(value="Secret") String secret) throws IllegalAccessException {
        RobotPositionClientRespond robotPositionClientRespond = new RobotPositionClientRespond();
        if(secret.equals(container.getSecreValue()))
        {
        AvailableStartingPositions availableStartingPositions = boardLayout.getAvailableStartingPositions()[0];
        Position position = new Position();
        position.setX(availableStartingPositions.getX());
        position.setY(availableStartingPositions.getY());
        robotPositionClientRespond.setRobot(boardLayout.getAvailableRobots()[0]);
        robotPositionClientRespond.setPosition(position);
        return new ResponseEntity<RobotPositionClientRespond>(robotPositionClientRespond,HttpStatus.OK);
    }
    else
        {
            throw new IllegalAccessException("Secrets doesnt match");
        }
    }


    @PostMapping(value="/games/{id}/round/start",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> startRound(@PathVariable (value="id") String id, @RequestBody Player player,@RequestHeader(value="Secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            {
                throw new IllegalAccessException("Secrets doesnt match");
        }

    }
    @PostMapping(value="/games/{id}/round/ends",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>endRound(@PathVariable (value="id") String id,@RequestHeader(value="Secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            String s="the round has ended";
            return new ResponseEntity<>(s,HttpStatus.OK);
        }
        else
        {
            throw new IllegalAccessException("Secrets doesnt match");
        }

    }
    @PostMapping(value = "/games/{id}/round/timeLimitWarning",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> timeLimit(@PathVariable (value="id") String id, @RequestBody TimeLimitWarning timeLimitWarning,@RequestHeader(value="Secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            String s="got your time limit warning of" + timeLimitWarning.getSecondsLeft();
            return new ResponseEntity<>(s,HttpStatus.OK);
        }
        else
        {
            throw new IllegalAccessException("Secrets doesnt match");
        }

    }

    @PostMapping(value = "/games/{id}/end")
    public ResponseEntity<HttpStatus> endGame(@RequestBody String reason,@PathVariable(value = "id") String id,@RequestHeader(value="secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            throw new IllegalAccessException("Secrets doesnt match");
        }
        }
    }


