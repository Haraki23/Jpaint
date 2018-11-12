package controller;

import model.IShape;
import model.persistence.ApplicationState;

public class CopyShapeCommand implements ICommand {
    private CopyPasteJShapeList CPJList;
    private SelectedJShapeList SJList;
    private ApplicationState AS;

    public CopyShapeCommand(ApplicationState AS, SelectedJShapeList SJList, CopyPasteJShapeList CPJList) {
        this.CPJList = CPJList;
        this.SJList = SJList;
        this.AS = AS;
    }

    @Override
    public void run() {
        CPJList.clear_list();
        for (IShape shape2 : SJList.getShapeList()) {
            if (CPJList.getShapeList().contains(shape2) == false) {
                CPJList.registerObserver(shape2);
            }
        }
        System.out.println("<<<Copy List Size>>> " + SJList.getShapeList().size());
        SJList.clear_list();
    }
}