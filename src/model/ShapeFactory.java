package model;

import controller.*;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;
import view.NullDrawShapeHandler;

public class ShapeFactory {
    private ApplicationState AS;
    private IDraw draw;
    private IShapeAdder shapeAdd = null;
    public ShapeFactory(ApplicationState AS, IDraw draw) {
        if (draw == null){
            draw = new NullDrawShapeHandler();
        }
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