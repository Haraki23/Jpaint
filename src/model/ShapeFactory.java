package model;

import model.persistence.ApplicationState;
import view.EventName;
import view.interfaces.IDialogChoice;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

import java.awt.*;

public class ShapeFactory{
    private StartAndEndPointMode SEP;
    //public Color jcolor;

    public ShapeFactory(ApplicationState AS) { // Also take in the ShapeList Class here
        }
//Pass appstate in here id move this to an interface the definition of the method should be in the interface
    public  Rectangle createRect(int[] WH) {
        //this.SEP = AS.getActiveStartAndEndPointMode();
        //this.PColor = AS.getActivePrimaryColor();
        //this.SColor = AS.getActiveSecondaryColor();
        //this.Shade = AS.getActiveShapeShadingType();
        //this.Type = AS.getActiveShapeType();
        //System.out.println(SEP);
        //System.out.println(PColor);
        //System.out.println(SColor);
        //System.out.println(Shade);
        //System.out.println(Type);
        int area = WH[0] * WH[1];
        System.out.println("Rectangle Created With Size " + WH[0] + "    " + WH[1]);
        //rectangle class needs to take all the applications tate arguments and the width height to be created
        //add object to shapelist ios a class instantiated once in main ... shapelist.add which will call a redraw the canvas drawshape
        //will take in the paintcanvas aa an argument
        return new Rectangle(area);
    }


}
