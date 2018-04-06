package typingworld;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class WordStore {

    @NotNull
    private static final WordStore store = new WordStore();
    private final List<String> words;

    /**
     * Singleton to access the word-list
     */
    private WordStore(){
        var lines = new ArrayList<String>(100_000); // make the assumption we can load them
        try (final var stream = getClass().getResourceAsStream("/english.txt")) {
            var bufferReader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = bufferReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException iox) {
            lines = new ArrayList<>(); // remove reserved slots
            iox.printStackTrace();
        } finally {
            words = lines;
        }
    }

    @NotNull
    public static WordStore getStore(){
        return store;
    }

    @NotNull
    public List<String> getWords(){
        return words;
    }



}
