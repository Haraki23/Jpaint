package model.Factories;

import controller.Interfaces.IShapeAdder;
import controller.Strategies.AddEllipseStrategy;
import controller.Strategies.AddRectangleStrategy;
import controller.Strategies.AddTriangleStrategy;
import model.ShapeLists.JShapeList;
import model.ShapeType;
import model.Interfaces.IDraw;
import model.Persistence.ApplicationState;
import view.Handlers.NullDrawShapeHandler;

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