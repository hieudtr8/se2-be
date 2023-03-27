package main.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private Double price;
    private String description;

    private List<String> images;
    private Integer amount;
    private String brand;

    public Product(String name, Double price, String description, List<String> images, Integer amount, String brand) throws Exception {
        this.id = UUID.randomUUID();
        setName(name);
        setPrice(price);
        setDescription(description);
        setImages(images);
        setAmount(amount);
        setBrand(brand);
    }

    public Product(UUID id, String name, Double price, String description, List<String> images, Integer amount, String brand) throws Exception {
        this.id = id;
        setName(name);
        setPrice(price);
        setDescription(description);
        setImages(images);
        setAmount(amount);
        setBrand(brand);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return Collections.unmodifiableList(images);
    }

    public Integer getAmount() {
        return amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String name) throws Exception {
        validateName(name);
        this.name = name;
    }

    public void setPrice(Double price) throws Exception {
        validatePrice(price);
        this.price = price;
    }

    public void setDescription(String description) throws Exception {
        validateDescription(description);
        this.description = description;
    }

    public void setImages(List<String> images) throws Exception {
        validateImages(images);
        this.images = images;
    }

    public void setAmount(Integer amount) throws Exception {
        validateAmount(amount);
        this.amount = amount;
    }

    public void setBrand(String brand) throws Exception {
        validateBrand(brand);
        this.brand = brand;
    }

    public static void validateName(String name) throws Exception {
        if (name == null)
            throw new Exception("Name can't be null");
        if (name.length() == 0)
            throw new Exception("Name can't be empty");
        if (name.length() > 50)
            throw new Exception("Name too long");
    }

    public static void validatePrice(Double price) throws Exception {
        if (price == null)
            throw new Exception("Price can't be null");
        if (price < 0)
            throw new Exception("Price can't be negative");
        if (price > 1000000)
            throw new Exception("Price too high");
    }

    public static void validateDescription(String description) throws Exception {
        if (description == null)
            return;
        if (description.length() == 0)
            throw new Exception("Description can't be empty");
        if (description.length() > 3000)
            throw new Exception("Description too long");
    }

    public static void validateImages(List<String> images) throws Exception {
        if (images == null)
            throw new Exception("Images can't be null");
        if (images.size() == 0)
            throw new Exception("Images can't be empty");
        if (images.size() > 10)
            throw new Exception("Maximum 10 images");
        for (String image : images) {
            if (image.length() == 0)
                throw new Exception("Image can't be empty");
            if (image.length() > 1000)
                throw new Exception("Image url too long");
        }
        for (int i = 0; i < images.size(); i++) {
            for (int j = i + 1; j < images.size(); j++) {
                if (images.get(i).equals(images.get(j)))
                    throw new Exception("Images can't be duplicated");
            }
        }
    }

    public static void validateAmount(Integer amount) throws Exception {
        if (amount == null)
            throw new Exception("Amount can't be null");
        if (amount < 0)
            throw new Exception("Amount can't be negative");
        if (amount > 1000000)
            throw new Exception("Too many products");
    }

    public static void validateBrand(String brand) throws Exception {
        if (brand == null)
            throw new Exception("Brand can't be null");
    }

    @Override
    public String toString() {
        return "Product {" +
                "\n\tid = " + id +
                "\n\tname = '" + name + '\'' +
                "\n\tprice = " + price +
                "\n\tdescription = '" + description + '\'' +
                "\n\timages = " + images +
                "\n\tamount = " + amount +
                "\n\tbrand = " + brand +
                "\n}";
    }
}

