package com.senai.laziot.exception;

public class PageableException  extends Exception {

    static final long serialVersionUID = 1L;
    private boolean suppressStacktrace = false;

    public PageableException(boolean suppressStacktrace) {
        super("Maximum size for page are 20 items!", null, suppressStacktrace, !suppressStacktrace);
        this.suppressStacktrace = suppressStacktrace;
    }

    @Override
    public String toString() {
        if (suppressStacktrace) {
            return getLocalizedMessage();
        } else {
            return super.toString();
        }
    }

}
