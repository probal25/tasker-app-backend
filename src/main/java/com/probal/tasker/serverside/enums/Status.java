package com.probal.tasker.serverside.enums;

public enum Status {
    TODO("todo"),
    PENDING("pending"),
    IN_PROGRESS("in progress"),
    DONE("done");

    public final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status forValue(String value) {
        for (Status status : values()) {
            if (status.value.equals(value)) return status;
        }
        return null;
    }
}
