package com.android.covid.network;

import static com.android.covid.network.NetworkState.Status.RUNNING;
import static com.android.covid.network.NetworkState.Status.SUCCESS;

public class NetworkState {

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(SUCCESS, "Success");
        LOADING = new NetworkState(RUNNING, "Running");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
