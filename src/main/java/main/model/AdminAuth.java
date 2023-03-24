package main.model;

import java.util.UUID;

public class AdminAuth {
    public UUID id;
    public String password;

    public AdminAuth(String password) {
        this.id = UUID.randomUUID();
        setPassword(password);
    }

    public AdminAuth(UUID id, String password) {
        this.id = id;
        this.password = password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cant be null");
        }
    }
}
