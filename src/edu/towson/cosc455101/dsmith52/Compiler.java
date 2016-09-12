package edu.towson.cosc455101.dsmith52;

/*
COURSE: COSC455101
SUBMITTER: dsmith52
NAMES: David Smith, Ryan Fongheiser 
 * A Simple Compiler Adapted from Sebesta (2010) by Josh Dehlinger further modified by Adam Conover
 * (2012-2015)
 *
 * A simple compiler used for the simple English grammar in Section 2.2 of Adam Brooks Weber's
 * "Modern Programming Languages" book. Parts of this code was adapted from Robert Sebesta's
 * "Concepts of Programming Languages".
 *
 * This compiler assumes that the source file containing the sentences to parse is provided as the
 * first runtime argument. Within the source file, the compiler assumes that each sentence to parse
 * is provided on its own line.
 *
 * NOTE: A "real" compiler would more likely treat an entire file as a single stream of input,
 * rather than each line being an independent input stream.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {

    /**
     * It is assumed that the first argument provided is the name of the source file that is to be
     * "compiled".
     */
    public static void main(String[] args) throws IOException {
        //args = new String[]{"<some hard coded path for testing>"};
        //args = new String[]{"/Users/Dave/Desktop/file.txt"};

        if (args.length < 1) {
            System.out.println("Need a filename!");
        } else {
            // Java 7 "try-with-resource" to create the file input buffer.
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                // Create the new lexer.
                LexicalAnalyzer lexer = new LexicalAnalyzer();

                // Start lexing and parsing.
                processFile(lexer, br);
            }
        }
    }

    /**
     * Reads each line of the input file and invokes the lexer and parser for each.
     */
    static void processFile(LexicalAnalyzer lexer, BufferedReader br) throws IOException {
        String sourceLine;

        // Read each line in the source file to be compiled as a unique sentence
        // to check against the grammar.
        while ((sourceLine = br.readLine()) != null) {
            // Ignore empty lines and comments.
            if (sourceLine.trim().length() <= 0) {
                continue;
            }
            if (sourceLine.trim().startsWith("#")) {
                System.out.println("Comment: " + sourceLine.substring(1).trim());
                continue;
            }

            // Create a new syntax analyzer over the provided lexer.
            SyntaxAnalyzer parser = new SyntaxAnalyzer(lexer);

            // Parse the given sentence against the given grammar. We assume that the
            // sentence, <S>, production is the start state.
            try {
                // Start the lexer...
                lexer.start(sourceLine);

                // Start the parser...
                parser.analyze();
                
                // No exceptions, so we must be good!
                System.out.printf("The sentence '%s' follows the BNF grammar.%n", sourceLine);
            } catch (ParseException error) {
                // If a syntax error was found, print that the sentence does not follow the grammar.
                System.out.printf("SYNTAX ERROR while processing: '%s'%n", sourceLine);
                System.out.printf("ERROR MSG: %s%n", error.getErrMsg());
            }

            System.out.println("-----------------------------------------------------------");
        }
    }
}
