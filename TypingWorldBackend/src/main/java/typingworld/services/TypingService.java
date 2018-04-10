package typingworld.services;

import org.jetbrains.annotations.NotNull;
import typingworld.WordStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface TypingService {

    final WordStore store = WordStore.getStore();

    @NotNull
    public static List<String> getWords(@NotNull final String language,
                                        final int amount) {
        var words = store.getWords();
        return IntStream.generate(() -> new Random().nextInt(words.size()))
                .limit(amount)
                .boxed()
                .map(words::get)
                .collect(Collectors.toUnmodifiableList());
    }

    public static boolean logTypingTest(final String username,
                                 final int cpm,
                                 final int totalchars,
                                 final int correctchars) {
        return false;
    }
}
