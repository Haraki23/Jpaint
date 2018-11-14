package view.Handlers;

import model.Interfaces.IDraw;
import model.Interfaces.IShape;

import java.util.ArrayList;

public class NullDrawShapeHandler implements IDraw {
    @Override
    public void DrawShapes(IDraw draw, ArrayList<IShape> Jshapes) {

    }

    @Override
    public void clearcanvas() {

    }
}
