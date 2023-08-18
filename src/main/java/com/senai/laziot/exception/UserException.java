package com.senai.laziot.exception;

public class UserException extends Exception {

    static final long serialVersionUID = 1L;
    private boolean suppressStacktrace = false;

        public UserException(String message, boolean suppressStacktrace) {
            super(message, null, suppressStacktrace, !suppressStacktrace);
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
