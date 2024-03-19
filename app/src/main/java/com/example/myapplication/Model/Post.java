package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("userId")
    private Integer uerId;

    public Post(String title, String body, Integer uerId) {
        this.title = title;
        this.body = body;
        this.uerId = uerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUerId() {
        return uerId;
    }

    public void setUerId(Integer uerId) {
        this.uerId = uerId;
    }
}
