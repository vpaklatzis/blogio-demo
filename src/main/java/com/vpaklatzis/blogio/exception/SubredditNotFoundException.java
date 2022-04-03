package com.vpaklatzis.blogio.exception;

public class SubredditNotFoundException extends RuntimeException{
    public SubredditNotFoundException(String exception) {
        super(exception);
    }
}
