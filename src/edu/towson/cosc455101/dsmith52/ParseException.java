package edu.towson.cosc455101.dsmith52;

/*
COURSE: COSC455101
SUBMITTER: dsmith52
NAMES: David Smith, Ryan Fongheiser 
/**
 * Parsing exception. For simplicity, this is used for both lexical errors as
 * well as parsing errors. A more complete design would include a hierarchy of
 * specific error types.
 *
 * @author Adam J. Conover, D.Sc. <aconover@towson.edu>
 */
public class ParseException extends Exception {

    public ParseException(String errMsg) {
        super(errMsg);
    }

    public String getErrMsg() {
        return super.getMessage();
    }
}
