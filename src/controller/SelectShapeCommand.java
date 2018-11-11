package controller;

import model.IShape;

public class SelectShapeCommand implements ICommand {
    private JPoint Pt = new JPoint();
    private JShapeList JList;
    private SelectedJShapeList SJList;
    public SelectShapeCommand(JPoint Pt, JShapeList JList, SelectedJShapeList SJList) {
        this.JList = JList;
        this.SJList = SJList;
        this.Pt = Pt;
    }

    @Override
    public void run(){
        BoundingBox test = new BoundingBox(Pt.getstartx(),Pt.getstarty(),Pt.getendx(),Pt.getendy());
        for(IShape shape: JList.getShapeList()){
            if(shape.getBB().point_intersect(test)){SJList.registerObserver(shape);}
            SJList.getShapeList();
        }
    }

}
