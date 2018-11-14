package controller.Commands;

import controller.Interfaces.IUndoable;
import model.Interfaces.IShape;
import model.Persistence.ApplicationState;
import view.Handlers.DrawShapeHandler;
import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    private DrawShapeHandler draw;
    private ApplicationState AS;
    private ArrayList<IShape> DeleteList;

    public DeleteShapeCommand(ApplicationState AS, DrawShapeHandler draw) {
        this.AS = AS;
        this.draw = draw;
        this.DeleteList = new ArrayList<>();
    }

    @Override
    public void run() {
        //Iterate through our list of selected shapes
        for (IShape sshape : AS.getSJList().getShapeList()) {
            //Add the shape to our deleted list
            DeleteList.add(sshape);
            //Remove it from our main list
            AS.getJList().removeObserver(sshape);
        }
        //Redraw Everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());
    }

    @Override
    public void undo() {
        //Instead well add back the shape from our deleted list
        for (IShape dshape: DeleteList) {
            AS.getJList().registerObserver(dshape);
        }
        //Redraw Everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());    }

    @Override
    public void redo() {
        //Instead well delete back the shape from our deleted list
        for (IShape dshape: DeleteList) {
            AS.getJList().removeObserver(dshape);
        }
        //Redraw Everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());    }
}
