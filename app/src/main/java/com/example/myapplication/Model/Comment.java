package com.example.myapplication.Model;

public class Comment {
    private Integer postId;
    private Integer id;
    private String email;
    private String body;

    public Comment(Integer postId, Integer id, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.email = email;
        this.body = body;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
