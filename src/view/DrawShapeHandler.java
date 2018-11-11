package view;

import model.IDraw;
import model.IShape;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.ArrayList;

public class DrawShapeHandler implements IDraw {

    private Graphics2D G2D;
    private PaintCanvas PC;
    public DrawShapeHandler(PaintCanvas PC){

        this.G2D = PC.getGraphics2D();
        this.PC = PC;
    }


    @Override
    public void DrawShapes(DrawShapeHandler draw, ArrayList<IShape> Jshapes) {
        clearcanvas();
            for (IShape shape : Jshapes) {
                System.out.println(shape.getsx());
                shape.draw(PC);
            }
    }

    public void clearcanvas(){
        G2D.setColor(Color.WHITE);
        G2D.fillRect(0,0, 50000, 50000);
    }
}
