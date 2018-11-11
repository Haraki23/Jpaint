package model;

import controller.BoundingBox;
import controller.JPoint;
import view.gui.PaintCanvas;

import java.util.UUID;

public interface IShape {
    String getShapeType();
    JPoint getPoint();
    UUID getShape_ID();
    BoundingBox getBB();
    void set_new_pts(JPoint set);
    JPoint return_new_pts();
    void draw(PaintCanvas PC);
    int getsx();
}
