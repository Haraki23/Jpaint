package model;

import controller.*;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;

public class ShapeFactory {
    ApplicationState AS;
    private DrawShapeHandler draw;
    private IShapeAdder shapeAdd = null;
    public ShapeFactory(ApplicationState AS, DrawShapeHandler draw) {
        this.AS = AS;
        this.draw = draw;
    }

    public void createObject(JShapeList JList) {
        ShapeType shapeType = AS.getActiveShapeType();
        switch(shapeType){
            case RECTANGLE:
                shapeAdd = new AddRectangleStrategy(JList,AS);
                shapeAdd.ShapeAdder(shapeType);
                break;
            case ELLIPSE:
                shapeAdd = new AddEllipseStrategy(JList,AS);
                shapeAdd.ShapeAdder(shapeType);
                break;
            case TRIANGLE:
                shapeAdd = new AddTriangleStrategy(JList,AS);
                shapeAdd.ShapeAdder(shapeType);
                break;
        }
        draw.DrawShapes(draw, JList.getShapeList());
    }

    public void updateShape(JShapeList JList){
        draw.clearcanvas();
        draw.DrawShapes(draw, JList.getShapeList());
    }
}