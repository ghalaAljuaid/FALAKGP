package com.example.falaksign;

public class User {

    private String fname;
    private String lname;
    private String mail;
    private String password;
    private String image;
    private String user;
    private String typeUser;
    private String id;

    public User(String fname, String lname, String mail, String password, String image, String user, String typeUser, String id) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.password = password;
        this.image = image;
        this.user = user;
        this.typeUser = typeUser;
        this.id = id;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
