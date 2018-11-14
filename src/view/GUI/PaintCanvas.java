package view.GUI;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends JComponent {

    private static final PaintCanvas instance = new PaintCanvas();

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    public static PaintCanvas getInstance() {
        return instance;
    }
}
