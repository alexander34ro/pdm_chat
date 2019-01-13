package com.example.mars.pdmchat2.Login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("next")
    @Expose
    private String next;

    /**
     * No args constructor for use in serialization
     *
     */
    public Users() {
    }

    /**
     *
     * @param users
     * @param next
     */
    public Users(List<User> users, String next) {
        super();
        this.users = users;
        this.next = next;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}