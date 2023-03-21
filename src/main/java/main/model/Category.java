package main.model;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;

    public Category(UUID id, String name) {
        this.id = id;
        setName(name);
    }

    public Category(String name) {
        id = UUID.randomUUID();
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public static void validateName(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException("Name can't be null");
        if (name.length() == 0)
            throw new IllegalArgumentException("Name can't be empty");
        if (name.length() > 30)
            throw new IllegalArgumentException("Name too long");
    }

}
