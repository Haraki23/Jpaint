package model;

import model.persistence.ApplicationState;
import view.EventName;
import view.interfaces.IDialogChoice;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

public class ShapeFactory{
    private StartAndEndPointMode SEP;
    private ShapeColor PColor;
    private ShapeColor SColor;
    private ShapeShadingType Shade;
    private ShapeType Type;
    int Width;
    int Height;

    public ShapeFactory(ApplicationState AS) {
    this.SEP = AS.getActiveStartAndEndPointMode();
    this.PColor = AS.getActivePrimaryColor();
    this.SColor = AS.getActiveSecondaryColor();
    this.Shade = AS.getActiveShapeShadingType();
    this.Type = AS.getActiveShapeType();
    this.Width = Width;
    this.Height = Height;
    }

    public  Rectangle createRect(int[] WH) {
        //System.out.println(SEP);
        //System.out.println(PColor);
        //System.out.println(SColor);
        //System.out.println(Shade);
        //System.out.println(Type);
        int area = WH[0] * WH[1];
        System.out.println("Rectangle Created With Size " + WH[0] + "    " + WH[1]);
        return new Rectangle(area);
    }


}
