package com.org.custom_springboot_template.core.utilities.results;

public class ErrorResult extends Result {

    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }

}
