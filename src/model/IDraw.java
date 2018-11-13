package model;

import view.DrawShapeHandler;

import java.util.ArrayList;
public interface IDraw {
    void DrawShapes(IDraw draw, ArrayList<IShape> Jshapes);
    void clearcanvas();
    }
