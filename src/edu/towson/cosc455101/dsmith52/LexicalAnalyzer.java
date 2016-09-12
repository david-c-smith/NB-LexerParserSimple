package edu.towson.cosc455101.dsmith52;

/*
COURSE: COSC455101
SUBMITTER: dsmith52
NAMES: David Smith, Ryan Fongheiser 
/*
 * A Simple Lexical Analyzer Adapted from Sebesta (2010) by Josh Dehlinger
 * further modified by Adam Conover (2012)
 *
 * This lexical analyzer simply finds lexemes separated by a single space and
 * places it in the Compiler classes currentToken global String. The lexical
 * analyzer here takes a source line and does a character- by-character analysis
 * to determine lexemes/tokens. Note that this lexical analyzer does not lookup
 * a lexeme to find its "class" to determine its token type, as shown in the
 * book. This lexical analyzer also limits each lexeme/token to 100 characters
 * or less.
 */
public class LexicalAnalyzer {

    private String sourceLine;
    private char nextChar;
    private int curPosition;
    protected TOKEN curToken;
    protected StringBuilder lexemeBuffer;

    /**
     * The main driver of this class. This method takes a "program", in this
     * case a single line of text in the form of a sentence, and gets the first
     * lexeme/token.
     */
    public void start(String line) throws ParseException {
        sourceLine = line;
        curPosition = 0;

        getChar();
        parseNextToken();
    }

    /**
     * This method does a character-by-character analysis to get the next token
     * and set it in the Compiler class's currentToken global String variable.
     * This simple lexical analyzer does not differentiate between letters,
     * digits and other special characters - it simply looks for characters,
     * spaces and end of line characters to determine relevant tokens.
     */
    public void parseNextToken() throws ParseException {
        resetLexemeBuffer();

        // Ignore spaces and add the first character to the token
        getNextNonBlank();
        addChar();
        getChar();

        // Continue gathering characters for the token
        while ((nextChar != '\n') && (nextChar != ' ') && (nextChar != '!') && (nextChar != '.') && (nextChar != ',')) {
            addChar();
            getChar();
        }

        // Convert the gathered character array token into a String
        String lexeme = lexemeBuffer.toString();

        // Set the new token
        this.curToken = TOKEN.fromLexeme(lexeme);
    }

    /**
     * This method gets the next character from the "program" string.
     */
    private void getChar() {
        if (curPosition < sourceLine.length()) {
            nextChar = sourceLine.charAt(curPosition);
            curPosition++;
        } else {
            nextChar = '\n';
        }
    }

    /**
     * A (trivial) helper method to determine if the current character is a space.
     */
    private boolean isSpace(char c) {
        return (c == ' ');
    }

    /**
     * A helper method to get the next non-blank character.
     */
    private void getNextNonBlank() {
        while (isSpace(nextChar)) {
            getChar();
        }
    }

    /**
     * This method adds the current character the the token after checking to
     * make sure that the length of the token isn't too long, a lexical error in
     * this case.
     */
    private void addChar() throws ParseException {
        if (lexemeBuffer.length() <= 98) {
            lexemeBuffer.append(nextChar);
        } else {
            throw new ParseException("LEXICAL ERROR: The found lexeme is too long! ");

/* Code to Skip Lexical errors instead of throwing an error... Good for debugging,
    but problematic for (hopefully) obvious reasons. */
//            System.out.println("LEXICAL ERROR: The found lexeme is too long! -- Skipping");
//            resetLexemeBuffer();
//
//            if (!isSpace(nextChar)) {
//                while (!isSpace(nextChar)) {
//                    getChar();
//                }
//            }
//
//            getNonBlank();
//            addChar();
        }
    }

    /**
     * Simple method to reset the lexeme buffer.
     */
    private void resetLexemeBuffer() {
        lexemeBuffer = new StringBuilder();
    }
}
