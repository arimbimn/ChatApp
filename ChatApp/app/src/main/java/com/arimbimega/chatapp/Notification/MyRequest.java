package com.arimbimega.chatapp.Notification;

import java.util.HashMap;

public class MyRequest {

    private String to;
    private Data data;

    public MyRequest(String to, Data data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
