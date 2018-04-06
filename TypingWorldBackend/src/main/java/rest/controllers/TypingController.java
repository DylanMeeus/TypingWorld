package rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
