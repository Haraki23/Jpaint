package model;

import controller.BoundingBox;
import controller.JPoint;
import view.gui.PaintCanvas;

import java.util.UUID;

public interface IShape {
    UUID getShape_ID();
    BoundingBox getBB();
    void set_new_pts(JPoint set);
    void draw(PaintCanvas PC);
    void movepos(int dx, int dy);
    void moveneg(int dx, int dy);
    int getsx();
    IShape copyShape();
}
