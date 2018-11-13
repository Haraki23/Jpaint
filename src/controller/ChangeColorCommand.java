package controller;

import model.IShape;
import model.persistence.ApplicationState;

public class ChangeColorCommand implements ICommand {
    private SelectedJShapeList SJList;
    private JShapeList JList;
    private ApplicationState AS;

    public ChangeColorCommand(ApplicationState AS, SelectedJShapeList SJList, JShapeList JList) {
        this.JList = JList;
        this.SJList = SJList;
        this.AS = AS;
    }

    @Override
    public void run() {
        for (IShape shape : SJList.getShapeList()) {
            if ((!shape.getPrimary().equals(AS.getActivePrimaryColor()) ||
                            !shape.getSecondary().equals(AS.getActiveSecondaryColor()))){
                shape.setPrimary();
                shape.setSecondary();
        }
    }

    }
}