package edu.towson.cosc455101.dsmith52;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
COURSE: COSC455101
SUBMITTER: dsmith52
NAMES: David Smith, Ryan Fongheiser 
/**
 * The Token set as an Enum type. This is just "binds" each token to its list of
 * valid lexemes.
 *
 * @author Adam J. Conover
 */
public enum TOKEN {

    ARTICLE("a", "the"), // a list of articles
    NOUN("dog", "cat", "rat"), // a list of nouns
    VERB("loves", "hates", "eats"), // a list of verbs
    CONJUNCTION("and", "or"), // a list of conjunctions
    ADJECTIVES("furry", "fast", "slow", "delicious"), // a list of adjectives
    ADVERBS("quickly", "secretly"), // a list of adverbs
    TERMINATOR(".", "!"), // a list of terminators
    COMMA(","), // a comma
    UNKNOWN(); // keep our lexemes "type-safe"!
    //
    // The lexemes under this token
    private List<String> lexemeList;

    // Construct the token with the list of lexems
    private TOKEN(String... tokenStrings) {
        lexemeList = new ArrayList<>(tokenStrings.length);
        lexemeList.addAll(Arrays.asList(tokenStrings));
    }

    // Gets a token from a lexeme
    public static TOKEN fromLexeme(String str) {
        // Search through the lexemes looking for a match.
        for (TOKEN t : TOKEN.values()) {
            if (t.lexemeList.contains(str)) {
                return t;
            }
        }

        // If nothing matches then return UNKNOWN.
        return UNKNOWN;
    }
}