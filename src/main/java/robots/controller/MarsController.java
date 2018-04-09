package robots.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import robots.exception.InvalidPositionException;
import robots.model.Mars;
import robots.model.Robot;

@RestController
@RequestMapping("/rest/mars")
public class MarsController {

    private Robot robot;

    private static final String BAD_REQUEST_MESSAGE = HttpStatus.BAD_REQUEST.toString() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase();

    @RequestMapping(value = "/{commands}", method = RequestMethod.POST)
    public ResponseEntity<String> command(@PathVariable("commands") String commands) {

        ResponseEntity<String> response;
        final String pattern = "(M|L|R)*";
        if (commands.matches(pattern)) {
            try {
                robot = new Robot(new Mars());
                this.sendCommands(commands.toCharArray());
                response = new ResponseEntity<>(robot.currentPosition(), HttpStatus.OK);
            } catch (final InvalidPositionException e) {
                response = new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        } else {
            response = new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    private void sendCommands(char[] commands) throws InvalidPositionException {

        for (final char command : commands) {
            robot.executeCommand(command);
        }
    }



}
