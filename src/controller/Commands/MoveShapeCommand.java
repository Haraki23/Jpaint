package controller.Commands;

import controller.Interfaces.IUndoable;
import controller.JPoint;
import model.ShapeLists.JShapeList;
import model.ShapeLists.SelectedJShapeList;
import model.Interfaces.IShape;

import java.util.ArrayList;


public class MoveShapeCommand implements ICommand, IUndoable {
    private JPoint Pt;
    private JShapeList JList;
    private SelectedJShapeList SJList;
    private ArrayList<IShape> shapesMoved;
    public MoveShapeCommand(JPoint Pt, JShapeList JList, SelectedJShapeList SJList) {
        this.JList = JList;
        this.SJList = SJList;
        this.Pt = Pt;
        this.shapesMoved = new ArrayList<>();
    }

    @Override
    public void run() {
        for (IShape shape : JList.getShapeList()) {
            for (IShape shape2 : SJList.getShapeList()) {
                if (shape.equals(shape2)) {
                    shape.set_new_pts(Pt);
                    shapesMoved.add(shape2);
                    //System.out.println("Were updating this shape " + shape.getShape_ID() );
                }
            }
        }
        //SJList.clear_list();
    }

    @Override
    public void undo() {

        int ex = Pt.getstartx();
        int ey = Pt.getstarty();
        int fex = Pt.getendx();
        int fey = Pt.getendy();
        int deltax = (fex - ex);
        int deltay = (fey - ey);
        System.out.println("Attempting to move back our shapes");
        for (IShape shape : shapesMoved) {
            shape.movepos(deltax, deltay);
        }

    }

    @Override
    public void redo() {

        int ex = Pt.getstartx();
        int ey = Pt.getstarty();
        int fex = Pt.getendx();
        int fey = Pt.getendy();
        int deltax = (fex - ex);
        int deltay = (fey - ey);
        System.out.println("Attempting to move back our shapes");
        for (IShape shape : shapesMoved) {
            shape.moveneg(deltax, deltay);
        }

    }
}
