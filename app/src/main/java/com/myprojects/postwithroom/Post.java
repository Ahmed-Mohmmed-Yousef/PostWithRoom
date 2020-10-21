package com.myprojects.postwithroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts_table")
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private User user;
    private String title;
    private String body;

    public Post(int id, User user, String title, String body) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
