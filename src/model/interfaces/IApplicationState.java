package model.interfaces;

import controller.CopyPasteJShapeList;
import controller.JPoint;
import controller.JShapeList;
import controller.SelectedJShapeList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

import java.awt.*;

public interface IApplicationState {
    void setActiveShape();

    void setActiveShapeTester(ShapeType test);

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    void copy();

    void paste();

    void delete();

    void undo();

    void redo();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    JShapeList getJList();

    CopyPasteJShapeList getCPJList();

    SelectedJShapeList getSJList();

    void setstartxy(int x, int y);

    void setendxy(int x, int y);

    int getstartx();

    int getstarty();

    int getendx();

    int getendy();

    JPoint getASPoint();


}

