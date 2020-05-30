package com.android.covid.network;

public class State {

    public enum Status {
        LOADING,
        SUCCESS,
        FAILED
    }

    private final Status status;
    private final String msg;

    public static final State SUCCESS;
    public static final State LOADING;

    public State(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        SUCCESS = new State(Status.SUCCESS, "Success");
        LOADING = new State(Status.LOADING, "Running");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
