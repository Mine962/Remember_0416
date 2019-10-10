package com.dgsw.remember.Write_0416;

public class Data_write {
    private String message;
    private String time;

    public Data_write() { }

    public Data_write(String message, String time) {
        this.message = message;
        this.time = time;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }
}