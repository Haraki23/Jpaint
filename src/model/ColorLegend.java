package model;
import java.awt.*;
public class ColorLegend {

    public static Color getColor(String color) {
        color = color.toUpperCase();
        if (color.equals("BLACK")) {
            return Color.BLACK;
        }
        if (color.equals("BLUE")) {
            return Color.BLUE;
        }
        if (color.equals("CYAN")) {
            return Color.CYAN;
        }
        if (color.equals("DARK_GRAY")) {
            return Color.DARK_GRAY;
        }
        if (color.equals("GRAY")) {
            return Color.GRAY;
        }
        if (color.equals("GREEN")) {
            return Color.GREEN;
        }
        if (color.equals("LIGHT_GRAY")) {
            return Color.LIGHT_GRAY;
        }
        if (color.equals("MAGENTA")) {
            return Color.MAGENTA;
        }
        if (color.equals("ORANGE")) {
            return Color.ORANGE;
        }
        if (color.equals("PINK")) {
            return Color.PINK;
        }
        if (color.equals("RED")) {
            return Color.RED;
        }
        if (color.equals("WHITE")) {
            return Color.WHITE;
        }
        if (color.equals("YELLOW")) {
            return Color.YELLOW;
        }
        return Color.BLACK;
    }
}
