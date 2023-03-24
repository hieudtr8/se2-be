package main.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class CustomerProfile {
    private UUID id;
    private String name;
    private Integer age;
    private String phone;
    private String address;
    private String avatar;

    public CustomerProfile(String name, Integer age, String phone, String address, String avatar) throws Exception {
        this.id = UUID.randomUUID();
        setName(name);
        setAge(age);
        setPhone(phone);
        setAddress(address);
        setAvatar(avatar);
    }

    public CustomerProfile(UUID id ,String name, Integer age, String phone, String address, String avatar) throws Exception {
        this.id = id;
        setName(name);
        setAge(age);
        setPhone(phone);
        setAddress(address);
        setAvatar(avatar);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress()  {
        return address;
    }

    public String getAvatar()  {
        return avatar;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) throws Exception {
        validateName(name);
        this.name = name;
    }

    public void setAge(Integer age)  throws Exception {
        validateAge(age);
        this.age = age;
    }

    public void setPhone(String phone)  throws Exception {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) throws Exception {
        validateAddress(address);
        this.address = address;
    }

    public void setAvatar(String avatar) throws Exception {
        validateAvatar(address);
        this.avatar = avatar;
    }

    public static void validateName(String name) throws Exception {
        if (name == null)
            throw new Exception("Name can't be null");
        if (name.length() == 0)
            throw new Exception("Name can't be empty");
        if (name.length() > 50)
            throw new Exception("Name too long");
    }

    public static void validateAge(Integer age) throws Exception {
        if (age == null)
            throw new Exception("Age can't be null");
        if (age > 90 || age < 6)
            throw new Exception("Not valid age");
    }
    public static void validatePhone(String phone) throws Exception {
        if (phone == null)
            throw new Exception("Age can't be null");
        if (!phone.matches("(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b"))
            throw new Exception("Not valid phone number, make sure your phone number is from Viet Nam");
    }
    public static void validateAddress(String address) throws Exception {
        if (address == null)
            throw new Exception("Address can't be null");
        if (address.length() == 0)
            throw new Exception("Address can't be empty");
    }
    public static void validateAvatar(String avatar) throws Exception {
        if (avatar == null)
            throw new Exception("Avatar can't be null");
        if (avatar.length() == 0)
            throw new Exception("Avatar can't be empty");
    }
}