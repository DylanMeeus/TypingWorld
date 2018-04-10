package rest.controllers;

import org.springframework.web.bind.annotation.*;
import typingworld.services.TypingService;

import java.util.List;

@RestController
public class TypingController {

    @RequestMapping("/words")
    public List<String> getWords(
            @RequestParam(value = "lang", defaultValue = "en") final String language,
            @RequestParam(value = "amount", defaultValue = "10") final String amount) {
        int count;
        try {
            count = Integer.parseInt(amount);
        } catch (NumberFormatException ex) {
            count = 10;
        }
        return TypingService.getWords(language, count);
    }

    /**
     * Log a typing  test to the database
     * Data comes in as string and is converted to appropriate type
     * The sendtime is determined by the server rather than the client.
     * @param username
     * @param cpm
     * @param totalchars
     * @param correctchars
     * @return
     */
    @PostMapping("/logtypingtest")
    public boolean logTypingTest(final String username,
                                 final String cpm,
                                 final String totalchars,
                                 final String correctchars) {

        if (username == null || username.isEmpty()) {
            return false;
        }

        try {
            var icpm = Integer.parseInt(cpm);
            var itotalChars = Integer.parseInt(totalchars);
            var iCorrectChars = Integer.parseInt(correctchars);
            return TypingService.logTypingTest(username, icpm, itotalChars, iCorrectChars);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;
        }
    }
}
