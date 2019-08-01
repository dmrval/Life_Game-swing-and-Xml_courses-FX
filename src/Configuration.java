import java.awt.*;

public class Configuration {
    public static final int SIZE = 5;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int SLEEPMILS = 500;

    public static Color getColor(String status) {
        switch (status) {
            default:
            case "no":
                return Color.BLACK;
            case "born":
                return Color.BLACK;
            case "live":
                return Color.WHITE;
            case "die":
                return Color.WHITE;
        }
    }
}
