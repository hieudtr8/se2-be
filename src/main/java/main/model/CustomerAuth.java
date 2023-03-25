package main.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class CustomerAuth {
    private UUID id;
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        validateUsername(username);
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        validateEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public CustomerAuth(String email,String username, String password) throws Exception {
        this.id = UUID.randomUUID();
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public CustomerAuth(UUID id,String username, String email, String password) throws Exception {
        this.id = id;
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public static void validateUsername(String username) throws Exception {
        if (username == null)
            throw new Exception("Email can't be null");
        if (username.length() == 0)
            throw new Exception("Email can't be empty");
    }

    public static void validateEmail(String email) throws Exception {
        if (email == null)
            throw new Exception("Email can't be null");
        if (email.length() == 0)
            throw new Exception("Email can't be empty");
        if (!email.matches("^(.+)@(\\S+)$"))
            throw new Exception("Not valid email");
    }

    public static void validatePassword(String password) throws Exception {
        if (password == null)
            throw new Exception("Email can't be null");
        if (password.length() == 0)
            throw new Exception("Email can't be empty");
    }
}
