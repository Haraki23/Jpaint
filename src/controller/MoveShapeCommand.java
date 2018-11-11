package controller;

import model.IShape;


public class MoveShapeCommand implements ICommand {
    private JPoint Pt;
    private JShapeList JList;
    private SelectedJShapeList SJList;
    public MoveShapeCommand(JPoint Pt, JShapeList JList, SelectedJShapeList SJList) {
        this.JList = JList;
        this.SJList = SJList;
        this.Pt = Pt;
    }

    @Override
    public void run() {
        for (IShape shape : JList.getShapeList()) {
            for (IShape shape2 : SJList.getShapeList()) {
                if (shape.equals(shape2)) {
                    shape.set_new_pts(Pt);
                    //System.out.println("Were updating this shape " + shape.getShape_ID() );
                }
            }
        }
        SJList.clear_list();
    }
}
