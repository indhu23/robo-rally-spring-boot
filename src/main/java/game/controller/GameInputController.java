package game.controller;

import game.Application;
import game.Entity.*;
import game.exception.IllegalAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>Game input controller</h1>
 * Client controller which contains all the
 * server-client requests
 */
@RestController
public class GameInputController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Application.URIBuilder server;

    @Autowired
    private SecretContainer container;

    /**
     * This method receives the actions of the activation phase from the server
     * @param id This is the id of the game
     * @param input This contains the order of the robot actions in a particular round
     * @return status of the equest
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
    @PostMapping("/games/{id}/round/actions")
    public ResponseEntity<Void> startGame(@PathVariable String id, @RequestBody RoundActionInputWrapper input, @RequestHeader(value = "Secret") String secret)throws IllegalAccessException{
        if (secret.equals(container.getSecreValue())) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            throw new IllegalAccessException("Secrets doesnt match");
     }

    /**
     * Inform all clients, that the game starts.It sends the board layout of the game.
     * @param id This is the id of the game
     * @param boardLayout This is the board layout of the game
     * @return Selected robot and position
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
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

    /**
     * Inform all clients, that the next round is about to start.
     * @param id This is the id of the game
     * @param player This is the current board state and drawpile
     * @return status of the request
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
    @PostMapping(value="/games/{id}/round/start",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> startRound(@PathVariable (value="id") String id, @RequestBody Player player,@RequestHeader(value="Secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }
        else{
            throw new IllegalAccessException("Secrets doesn't match");
        }

    }

    /**
     * Informs all clients, that the all the rounds are over now.
     * @param id This is the id of the game
     * @return status of the request
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
    @PostMapping(value="/games/{id}/round/ends",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>endRound(@PathVariable (value="id") String id,@RequestHeader(value="Secret") String secret)throws IllegalAccessException
    {
        if(secret.equals(container.getSecreValue())) {
            String s="the round has ended";
            return new ResponseEntity<>(s,HttpStatus.OK);
        }
        else
        {
            throw new IllegalAccessException("Secrets doesn't match");
        }
    }

    /**
     * Server sends this timelimit warning if either a client has finished
     * programming the registers for this round or the server decides, that
     * it is time to finish the current round for some other reason.
     * @param id This is the id of the game
     * @return status of the request
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
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

    /**
     * Informs all clients that the game has ended and why it ended.
     * @param id This is the id of the game
     * @param reason This contains the reason for nding the game
     * @return status of the request
     * @throws IllegalAccessException if there is a mismatch in the secret header value
     */
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


