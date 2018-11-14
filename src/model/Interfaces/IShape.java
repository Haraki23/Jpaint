package model.Interfaces;

import controller.BoundingBox;
import controller.JPoint;
import model.ShapeColor;
import view.GUI.PaintCanvas;

import java.util.UUID;

public interface IShape {
    UUID getShape_ID();

    BoundingBox getBB();

    void set_new_pts(JPoint set);

    void draw(PaintCanvas PC);

    void movepos(int dx, int dy);

    void moveneg(int dx, int dy);

    void printall();

    IShape copyShape();

    void setPrimary();

    void setSecondary();

    ShapeColor getPrimary();

    ShapeColor getSecondary();

    String getShapeType();
}
