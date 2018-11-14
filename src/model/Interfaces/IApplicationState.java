package model.Interfaces;

import model.ShapeLists.CopyPasteJShapeList;
import controller.JPoint;
import model.ShapeLists.JShapeList;
import model.ShapeLists.SelectedJShapeList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {
    void setActiveShape();

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

