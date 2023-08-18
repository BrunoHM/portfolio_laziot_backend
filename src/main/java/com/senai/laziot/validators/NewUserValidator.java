package com.senai.laziot.validators;

import org.springframework.stereotype.Component;


public class NewUserValidator extends Exception {

    static final long serialVersionUID = 1L;
    private boolean suppressStacktrace = false;

    public NewUserValidator(boolean suppressStacktrace) {
        super("Failed to create new user!", null, suppressStacktrace, !suppressStacktrace);
        this.suppressStacktrace = suppressStacktrace;
    }

    public NewUserValidator(String msg, boolean suppressStacktrace) {
        super(msg, null, suppressStacktrace, !suppressStacktrace);
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
