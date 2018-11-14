package controller.Commands;

import model.ShapeLists.JShapeList;
import model.Factories.ShapeFactory;


public class UpdateShapeCommand implements ICommand {
    private JShapeList Jshape;
    private ShapeFactory shape;
    public UpdateShapeCommand(ShapeFactory shape, JShapeList Jshape) {
        this.Jshape = Jshape;
        this.shape = shape;

    }

    @Override
    public void run(){shape.updateShape(Jshape);}


}
