package com.mlabs.bbm.firstandroidapp_morningclass;

/**
 * Created by ChristianJohn on 9/22/2016.
 */
public class User {

    private long id;
    private String email;

    private String password;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
