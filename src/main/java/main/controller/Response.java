package main.controller;

public class Response<T> {
    public String message;
    public boolean success;
    public T data;

    public Response (String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <R> Response<R> success(String message, R data) {
        return new Response<>(message, true, data);
    }

    public static Response<?> fail(String message) {
        return new Response<>(message, false, null);
    }
}
