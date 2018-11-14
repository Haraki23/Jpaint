package controller.Strategies;

import controller.Interfaces.IShapeAdder;
import model.ShapeLists.JShapeList;
import model.ShapeType;
import model.Factories.TriangleFactory;
import model.Persistence.ApplicationState;

public class AddTriangleStrategy implements IShapeAdder {

    private TriangleFactory triangle;
    private JShapeList JList;
    private ApplicationState AS;

    public AddTriangleStrategy(JShapeList JList, ApplicationState AS) {
        this.triangle = new TriangleFactory(AS);
        this.JList = JList;
        this.AS = AS;
    }

    public void ShapeAdder(ShapeType shapeType) {

        JList.registerObserver(triangle.createShape());
    }

}
