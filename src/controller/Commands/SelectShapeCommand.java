package controller.Commands;

import controller.BoundingBox;
import controller.JPoint;
import model.ShapeLists.JShapeList;
import model.ShapeLists.SelectedJShapeList;
import model.Interfaces.IShape;

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
        int counter = 0;
        BoundingBox test = new BoundingBox(Pt.getstartx(),Pt.getstarty(),Pt.getendx(),Pt.getendy());
        for(IShape shape: JList.getShapeList()){
            if(shape.getBB().point_intersect(test)){
                SJList.registerObserver(shape);
                counter++;
            }
            SJList.getShapeList();
        }
        if (counter == 0) SJList.clear_list();
        System.out.println("<<<Selected List Size>>> " + SJList.getShapeList().size());
    }

}