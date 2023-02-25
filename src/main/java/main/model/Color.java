package main.model;

import java.util.Arrays;
import java.util.List;

public class Color {
    public static final List<String> COLORS = Arrays.asList(
            "RED",
            "GREEN",
            "BLUE",
            "YELLOW",
            "ORANGE",
            "PINK",
            "BLACK",
            "WHITE",
            "GRAY",
            "BROWN"
    );

    public static void validateColor(String color) throws Exception {
        if (!COLORS.contains(color)) {
            throw new Exception("Invalid color");
        }
    }
}
